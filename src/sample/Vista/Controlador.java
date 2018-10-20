package src.sample.Vista;

import com.jfoenix.controls.JFXPasswordField;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import src.sample.Modelo.*;
import src.sample.Modelo.Conexion.Conexion;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;


import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;


public class Controlador implements Initializable {
    
    
    //Columnas tabla Inmuebles
    
    @FXML
    private TableColumn < Propietario_Tiene_Inmueble, Integer > ColumnaIDCasa;
    @FXML
    private TableColumn < Propietario_Tiene_Inmueble, Integer > ColumnaCasa;
    @FXML
    private TableColumn < Propietario_Tiene_Inmueble, Integer > ColumnaTerraza;
    //Columnas tabla servicio
    
    @FXML
    private TableColumn < Servicios, Integer > ColumnaID;
    @FXML
    private TableColumn < Servicios, String > ColumnaTipoS;
    @FXML
    private TableColumn < Servicios, String > ColumnaDescripcion;
    @FXML
    private TableColumn < Servicios, Integer > ColumnaCosto;
    
    //Columnas tabla cuota ordinaria
    
    @FXML
    private TableColumn < Cuota_Ordinaria, Integer > ColumnaIDCuotaOrdinaria;
    @FXML
    private TableColumn < Cuota_Ordinaria, Date > ColumnaFechaEstablecidaCuotaOrdinaria;
    @FXML
    private TableColumn < Cuota_Ordinaria, Integer > ColumnaMontoEstablecido;
    
    
    //Columnas tabla cuota extraordinaria
    
    @FXML
    private TableColumn < Cuota_Extraordinaria, Integer > ColumnaIDCuotaExtraordinaria;
    @FXML
    private TableColumn < Cuota_Extraordinaria, String > ColumnaNombreCuotaExtraordinaria;
    @FXML
    private TableColumn < Cuota_Extraordinaria, String > ColumnaDescripcionCuotaExtraordinaria;
    @FXML
    private TableColumn < Cuota_Extraordinaria, Date > ColumnaFechaCuotaExtraordinaria;
    @FXML
    private TableColumn < Cuota_Extraordinaria, Integer > ColumnaCostoCuotaExtraordinaria;
    
    //Columnas tabla articulos disponibles
    
    @FXML
    private TableColumn < Articulo, Integer > ColumnaIDArticulo;
    @FXML
    private TableColumn < Articulo, String > ColumnaNombreArticulo;
    @FXML
    private TableColumn < Articulo, Integer > ColumnaPrecioArticulo;
    @FXML
    private TableColumn < Articulo, Integer > ColumnaCantidadArticulo;
    
    //Columnas tabla gestion de recursos
    
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaCasaGestion;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaNombreGestion;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaOrdinariaGestion;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaCasaGestionCuotaExtra;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaNombreGestionCuotaExtra;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaExtraGestion;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaCasaGestionArticulo;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaNombreGestionArticulo;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaArticuloGestion;
    
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaCasaMoroso;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaTerrazaMoroso;
    @FXML
    private TableColumn < Gestion_de_Recursos, String > ColumnaNombreMoroso;
    @FXML
    private TableColumn < Gestion_de_Recursos, Integer > ColumnaMesesMoroso;
    
    @FXML
    private TableColumn < Propietario_Cancela_Cuota_Ordinaria, Integer > ColumnaAlicuotaOrdinaria;
    @FXML
    private TableColumn < Propietario_Cancela_Cuota_Ordinaria, Integer > ColumnaMontoAlicuotaOrdinaria;
    @FXML
    private TableColumn < Propietario_Cancela_Cuota_Ordinaria, Integer > ColumnaAlicuotaExtraordinaria;
    @FXML
    private TableColumn < Propietario_Cancela_Cuota_Ordinaria, Integer > ColumnaAlicuotaMontoExtraordinaria;
    
    //Colecciones
    
    ArrayList < String > prefijo_telefonico = new ArrayList <> ( );
    ObservableList < String > prefijo_telefonico0 = FXCollections.observableArrayList ( prefijo_telefonico );
    ArrayList numero = new ArrayList ( );
    ObservableList numero_telefonico0 = FXCollections.observableArrayList ( numero );
    ArrayList < String > dominio1 = new ArrayList <> ( );
    ObservableList < String > dominios = FXCollections.observableArrayList ( dominio1 );
    ArrayList < String > dominio2 = new ArrayList <> ( );
    ObservableList < String > usuarios = FXCollections.observableArrayList ( dominio2 );
    ArrayList < Integer > id_telefonos = new ArrayList < Integer > ( );
    ObservableList < Integer > ids_Telefonos = FXCollections.observableArrayList ( id_telefonos );
    ArrayList < Integer > id_correos = new ArrayList < Integer > ( );
    ObservableList < Integer > ids_Correos = FXCollections.observableArrayList ( id_correos );
    ArrayList < Integer > id_casa = new ArrayList < Integer > ( );
    ObservableList < Integer > ids_casas = FXCollections.observableArrayList ( id_casa );
    
    Terraza TerrazaCorrespondiente = new Terraza ( );
    ArrayList < Integer > lista = new ArrayList <> ( );
    ArrayList < Integer > lista2 = new ArrayList <> ( );
    ArrayList < Date > cuotasAPagar = new ArrayList <> ( );
    ArrayList < Long > montosAPagar = new ArrayList <> ( );
    ArrayList < Integer > id_cuota = new ArrayList <> ( );
    ArrayList < Long > total = new ArrayList <> ( );
    ArrayList < Integer > id_extra = new ArrayList <> ( );
    ArrayList < Date > fecha_extra = new ArrayList <> ( );
    ArrayList < Long > montos_extras = new ArrayList <> ( );
    ArrayList < Long > total1 = new ArrayList <> ( );
    
    ObservableList < Integer > observable = FXCollections.observableArrayList ( lista );
    ObservableList < Integer > observable2 = FXCollections.observableArrayList ( lista2 );
    ObservableList < Date > observable3 = FXCollections.observableArrayList ( cuotasAPagar );
    ObservableList < Long > observable4 = FXCollections.observableArrayList ( montosAPagar );
    ObservableList < Integer > observable5 = FXCollections.observableArrayList ( id_cuota );
    ObservableList < Long > observable6 = FXCollections.observableArrayList ( total );
    ObservableList < Integer > observable7 = FXCollections.observableArrayList ( id_extra );
    ObservableList < Date > observable8 = FXCollections.observableArrayList ( fecha_extra );
    ObservableList < Long > observable9 = FXCollections.observableArrayList ( montos_extras );
    ObservableList < Long > observable10 = FXCollections.observableArrayList ( total );
    Inmueble_Esta_Ubicado_En_Terraza Id = new Inmueble_Esta_Ubicado_En_Terraza ( );
    
    ArrayList < Integer > casas = new ArrayList <> ( );
    ObservableList < Integer > numero_de_casa = FXCollections.observableArrayList ( casas );
    
    //Componentes GUI
    
    @FXML
    private TableView < Servicios > TablaServicios;
    @FXML
    private TableView < Cuota_Ordinaria > TablaCuotaOrdinaria;
    @FXML
    private TableView < Cuota_Extraordinaria > TablaCuotaExtraordinaria;
    @FXML
    private TableView < Propietario_Tiene_Inmueble > TablaPropiedades;
    @FXML
    private TableView < Cuota_Ordinaria > TablaHistorialCuotaOrdinaria;
    @FXML
    private TableView < Cuota_Extraordinaria > TablaHistorialCuotaExtraordinaria;
    @FXML
    private TableView < Articulo > TablaInventarioArticulos;
    @FXML
    private TableView < Gestion_de_Recursos > TablaGestionRecursos;
    @FXML
    private TableView < Gestion_de_Recursos > TablaGestionRecursosExtra;
    @FXML
    private TableView < Gestion_de_Recursos > TablaGestionRecursosArticulos;
    @FXML
    private TableView < Gestion_de_Recursos > TablaListaMorososCuotaOrdinaria;
    @FXML
    private TableView < Propietario_Cancela_Cuota_Ordinaria > TablaHistorialAlicuotaOrdinaria;
    @FXML
    private TableView < Propietario_Paga_Cuota_Extraordinaria > TablaHistorialAlicuotaExtraordinaria;
    
    @FXML
    private ListView ListaDeDeuda;
    @FXML
    private ImageView Visualizar;
    @FXML
    private ImageView Visualizar1;
    @FXML
    private ImageView Visualizar2;
    @FXML
    private ImageView Visualizar11;
    @FXML
    private Pane Advertencia_Cedula_Login;
    @FXML
    private Pane  Advertencia_Primer_Nombre_Login;
    @FXML
    private Pane  Advertencia_Primer_Apellido_Login;
    @FXML
    private Pane  Advertencia_Fecha_de_Nacimiento_Login;
    @FXML
    private Pane  Advertencia_Sexo_Login;
    @FXML
    private Pane  Advertencia_Estado_Civil_Login;
  
    @FXML
    private Label LabelTotalAPagar;
    @FXML
    private Label LabelDeudaCuotaOrdinaria;
    @FXML
    private Label LabelExitoArticulo;
    @FXML
    private ListView ListaDeDeudaCuotaExtraordinaria;
    @FXML
    private Pane PaneListaHistorialCuotas;
    @FXML
    private Pane PaneGestionRecursos;
    @FXML
    private Label LabelDeudaCuotaExtraordinaria;
    @FXML
    private Label LabelTotalCuotaOrdinaria;
    @FXML
    private Label LabelTotalArticulos;
    @FXML
    private Label LabelTotalCuotaExtraordinaria;
    @FXML
    private Label LabelCargo;
    
    
    @FXML
    private Button BotonDeshabilitarPropietario;
    @FXML
    private Button BotonModificarPropietario;
    @FXML
    private Button BotonAgregarServicio;
    @FXML
    private Button BotonModificarServicios;
    @FXML
    private Button BotonDeshabilitarServicios;
    @FXML
    private Button BotonModificarUsuario;
   
    @FXML
    private Button BotonModificarArticulo;
    @FXML
    private Button  BotonAgregarUsuario;
    @FXML
    private Button BotonEliminarUsuario;
    
    
    @FXML
    private Pane PaneDatosPropietario;
    @FXML
    private Pane PaneCuotaOrdinaria;
    @FXML
    private Pane PaneCuotaExtraordinaria;
    @FXML
    private Pane PaneUsuario;
    @FXML
    private Pane PaneInicio;
    
    @FXML
    private Pane AdvertenciaTelefono;
    @FXML
    private Pane AdvertenciaTelefono1;
    @FXML
    private Pane AdvertenciaCorreo;
    @FXML
    private Pane AdvertenciaCorreo1;
    @FXML
    private Pane AdvertenciaDireccionPagoCuota;
    @FXML
    private Pane AdvertenciaCedulaPagoCuota;
    @FXML
    private Pane PaneEnterCedulaPropietario;
    @FXML
    private Pane PaneInfoNombreUsuarioLogin;
    
    @FXML
    private ComboBox < String > cbxPrefijoTelefonico2;
    @FXML
    private ComboBox < String > cbxPrefijoTelefonico3;
    
    
    @FXML
    private ComboBox < Integer > cbxCasaPago;
    @FXML
    private ComboBox < Integer > cbxCasaVenta;
    @FXML
    private ComboBox < Integer > cbxFecha1Ordinaria;
    @FXML
    private ComboBox < Integer > cbxFecha2Ordinaria;
    @FXML
    private ComboBox < Integer > cbxFecha1Extraordinaria;
    @FXML
    private ComboBox < Integer > cbxFecha2Extraordinaria;
    
    
    @FXML
    private Pane PaneMenuCuotas;
    @FXML
    private Pane PaneMenuInmuebles;
    @FXML
    private Pane PaneMenuArticulos;
    @FXML
    private Pane PanePagoCuotaOrdinaria;
    @FXML
    private Pane PanePagoCuotaExtraordinaria;
    @FXML
    private Pane PaneAdministrarArticulo;
    @FXML
    private Pane PaneVentaArticulo;
    
    @FXML
    private Pane PaneCubiertaCuotaOrdinaria;
    @FXML
    private Pane PaneCubiertaPagoCuotaOrdinaria;
    @FXML
    private Pane PaneCubiertaCuotaExtraordinaria;
    @FXML
    private Pane PaneCubiertaPagoDeCuotas;
    @FXML
    private Pane PaneVerificacionObligatoriaArticulos;
    @FXML
    private Pane PaneDatosAutorizado;
    @FXML
    private Pane PaneInformacionAutorizacion;
    @FXML
    private Pane PanePreguntaDatosModificar;
    @FXML
    private Pane PanePreguntaDatosEliminar;
    @FXML
    private Pane PaneExistencia;
    @FXML
    private Pane PaneEnterCedulaUsuario;
    @FXML
    private Pane PaneCerrarSesion;
    @FXML
    private AnchorPane PaneExito;
    @FXML
    private AnchorPane PaneExito1;
    @FXML
    private AnchorPane PaneExito11;
    @FXML
    private AnchorPane PaneExitoAgregarUsuario;
    @FXML
    private AnchorPane PaneExitoModificarUsuario;
    @FXML
    private AnchorPane PaneExitoEliminarUsuario;
    @FXML
    private AnchorPane PaneExitoAgregarServicio;
    @FXML
    private AnchorPane PaneExitoModificarServicio;
    @FXML
    private AnchorPane PaneExitoEliminarServicio;
    @FXML
    private AnchorPane PaneExitoPagoCuotas;
    @FXML
    private AnchorPane PaneExitoModificarArticulo;
    @FXML
    private AnchorPane PaneExitoAgregarArticulo;
    @FXML
    private AnchorPane PaneExitoVentaArticulo;
    @FXML
    private Pane PanePregunta;
    @FXML
    private Pane PaneCubiertaDatosPropietario;
    @FXML
    private Pane PanePreguntaAgregarArticulo;
    @FXML
    private Pane PanePreguntaModificarArticulo;
    @FXML
    private Pane PanePreguntaEliminarArticulo;
    @FXML
    private Pane PaneCubiertaArticulo;
    @FXML
    private Pane PaneCantidadDisponible;
    @FXML
    private Pane TotalGeneralCuotaOrdinaria;
    @FXML
    private Pane TotalGeneralCuotaExtraordinaria;
    @FXML
    private Pane TotalGeneralArticulos;
    @FXML
    private Pane TotalGeneralAlCierredelAño;
    @FXML
    private Pane AdvertenciaCedula;
    @FXML
    private Pane AdvertenciaPrimerNombre;
    @FXML
    private Pane AdvertenciaPrimerApellido;
    @FXML
    private Pane AdvertenciaFechaNacimiento;
    @FXML
    private Pane AdvertenciaCbxSexo;
    @FXML
    private Pane AdvertenciaCbxEstadoCivil;
    @FXML
    private Pane AdvertenciaNombredeUsuario;
    @FXML
    private Pane AdvertenciaContraseñaUsuario;
    @FXML
    private Pane AdvertenciaCargoUsuario;
    @FXML
    private Pane AdvertenciaPreguntaUsuario;
    @FXML
    private Pane AdvertenciaRespuestaUsuario;
    @FXML
    private Pane AdvertenciaCedulaPropietario;
    @FXML
    private Pane AdvertenciaPrimerNombrePropietario;
    @FXML
    private Pane AdvertenciaPrimerApellidoPropietario;
    @FXML
    private Pane AdvertenciaFechaNacimientoPropietario;
    @FXML
    private Pane AdvertenciaSexoPropietario;
    @FXML
    private Pane AdvertenciaEstadoCivilPropietario;
    @FXML
    private Pane AdvertenciaCorreoPropietario;
    @FXML
    private Pane AdvertenciaCorreo1Propietario;
    @FXML
    private Pane AdvertenciaTelefonoPropietario;
    @FXML
    private Pane AdvertenciaTelefono1Propietario;
    @FXML
    private Pane AdvertenciaCasaPropietario;
    @FXML
    private Pane AdvertenciaFechaAdquisicion;
    @FXML
    private Pane PaneEnterCedulaPagoCuota;
    @FXML
    private Pane PaneDatosUsuario_login;
    @FXML
    private Pane Advertencia_Respuesta_Login;
    @FXML
    private Pane Advertencia_ID_Login;
    @FXML
    private Pane Advertencia_Contraseña_Login;
    @FXML
    private Pane Advertencia_Cargo_Login;
    @FXML
    private Pane Advertencia_Pregunta_Login;
    
    
    //DATOS DEL PROPIETARIO
    
    @FXML
    private TextField Cedula;
    @FXML
    private TextField Primer_Nombre;
    @FXML
    private TextField Segundo_Nombre;
    @FXML
    private TextField Primer_Apellido;
    @FXML
    private TextField Segundo_Apellido;
    @FXML
    private DatePicker Fecha_de_Nacimiento;
    @FXML
    public ComboBox < String > cbxNacionalidad;
    @FXML
    public ComboBox < String > cbxSexo;
    @FXML
    public ComboBox < String > cbxEstadoCivil;
    
    //DATOS ADICIONALES DEL PROPIETARIO
    
    //correo
    
    @FXML
    private TextField Usuario;
    @FXML
    private ComboBox < String > cbxDominio;
    @FXML
    private TextField Usuario1;
    @FXML
    private ComboBox < String > cbxDominio1;
    @FXML
    private ComboBox < Integer > cbxIdsCasaPago;
    
    //telefono
    
    @FXML
    private ComboBox < String > cbxPrefijoTelefonico;
    @FXML
    private TextField Telefono;
    @FXML
    private ComboBox < String > cbxPrefijoTelefonico1;
    @FXML
    private TextField Telefono1;
    
    
    //DIRECCION DEL NUEVO INMUEBLE
    
    @FXML
    private ComboBox < Integer > cbxTerraza;
    @FXML
    private TextField Casa;
    @FXML
    private DatePicker Fecha_de_Adquisicion;
    
    
    @FXML
    private TextField Telefono2;
    @FXML
    private TextField Telefono3;
    
    //INFORMACION DE SERVICIOS PARA ESTABLECER CUOTA ORDINARIA
    
    @FXML
    private TextField ID_Servicio;
    @FXML
    private TextField Nombre_del_Servicio;
    @FXML
    private TextField Descripcion;
    @FXML
    private TextField Costo;
    @FXML
    private TextField Busqueda_Servicios;
    
    //INFORMACION CUOTA EXTRAORDINARIA
    
    @FXML
    private TextField ID_Cuota_Extraordinaria;
    @FXML
    private TextField Nombre_Cuota_Extraordinaria;
    @FXML
    private DatePicker Fecha_Cuota_Extraordinaria;
    @FXML
    private TextField Descripcion_Cuota_Extraordinaria;
    @FXML
    private TextField Costo_Cuota_Extraordinaria;
    @FXML
    private TextField Busqueda_Cuota_Extraordinaria;
    
    
    @FXML
    private TextField IDCuota;
    @FXML
    private ImageView Circulo;
    @FXML
    private ImageView LogoOrganizacion;
    @FXML
    private ImageView ImagenUsuario;
    @FXML
    private TextField Id_casa;
    
    @FXML
    private TextField Nombre_de_Articulo;
    @FXML
    private TextField Valor_de_Articulo;
    @FXML
    private TextField Cantidad_Articulo;
    @FXML
    private TextField IDUsuario;
    @FXML
    private JFXPasswordField ContraseñaUsuario;
    @FXML
    private Label LabelFechaCuotaOrdinaria;
    @FXML
    private Label LabelMontoEstablecido;
    @FXML
    private Label Label_Monto_Establecido_Cuota_Ordinaria;
    @FXML
    private Label numero_terraza;
    @FXML
    private Label LabelSolvente;
    @FXML
    private Label LabelSolventeCuota_Ordinaria;
    @FXML
    private Label HistorialDePagoPorInmueble;
    @FXML
    private Label LabelExistencia;
    @FXML
    private TextField Fecha;
    @FXML
    private Label LabelTerrazaVenta;
    @FXML
    private Label LabelNombreUsuario;
    @FXML
    private Label LabelN_Casa;
    @FXML
    private Label  LabelAdvertenciaLogin;
    
    //DATOS ADMINISTRADOR
    
    @FXML
    private TextField Cedula_Administrador;
    @FXML
    private TextField Primer_Nombre_Administrador;
    @FXML
    private TextField Segundo_Nombre_Administrador;
    @FXML
    private TextField Primer_Apellido_Administrador;
    @FXML
    private TextField Segundo_Apellido_Administrador;
    @FXML
    private DatePicker Fecha_de_Nacimiento_Administrador;
    @FXML
    private ComboBox < String > cbxNacionalidad_Administrador;
    @FXML
    private ComboBox < String > cbxSexo_Administrador;
    @FXML
    private ComboBox < String > cbxEstado_Civil_Administrador;
    
    //
    
    @FXML
    private TextField Usuario2;
    @FXML
    private TextField Usuario3;
    @FXML
    private ComboBox < String > cbxDominio2;
    @FXML
    private ComboBox < String > cbxDominio3;
    @FXML
    private TextField Nombre_de_Usuario;
    @FXML
    private PasswordField Contraseña_de_Usuario;
    @FXML
    private ComboBox < String > cbxCargo_Administrador;
    @FXML
    private ComboBox < String > cbxPregunta_de_Seguridad_Administrador;
    @FXML
    private TextField Respuesta_de_Seguridad;
    
    
    //CUOTA ORDINARIA
    
    @FXML
    private DatePicker Fecha_Establecida_Cuota_Ordinaria;
    
    @FXML
    private TextField CedulaPago;
    @FXML
    private TextField MontoPago;
    
    
    @FXML
    private DatePicker Fecha_de_Autorizacion;
    @FXML
    private DatePicker Fecha_de_Pago;
    
    @FXML
    private ComboBox < String > cbxNacionalidadLogin;
    @FXML
    private TextField CedulaLogin;
    @FXML
    private TextField Primer_Nombre_Login;
    @FXML
    private TextField Segundo_Nombre_Login;
    @FXML
    private TextField Primer_Apellido_Login;
    @FXML
    private TextField Segundo_Apellido_Login;
    @FXML
    private DatePicker Fecha_de_Nacimiento_Login;
    @FXML
    private ComboBox < String > cbxSexoLogin;
    @FXML
    private ComboBox < String > cbxEstado_Civil_Login;
    @FXML
    private TextField Nombre_Usuario_Login;
    @FXML
    private PasswordField Contraseña_Login;
    @FXML
    private ComboBox < String > cbxCargo_Login;
    @FXML
    private ComboBox < String > cbxPregunta_Login;
    @FXML
    private TextField Respuesta_Login;
    
    @FXML
    private AnchorPane PaneInmuebleOcupado;
    @FXML
    private AnchorPane PaneActualizacion1;
    @FXML
    private AnchorPane PanePreguntaServicios;
    @FXML
    private AnchorPane PaneExitoServicios;
    @FXML
    private AnchorPane PanePreguntaServiciosModificar;
    @FXML
    private AnchorPane PaneExitoServiciosModificado;
    @FXML
    private AnchorPane PanePreguntaServiciosEliminar;
    @FXML
    private AnchorPane PanePreguntaAgregarCuotaExtraordinaria;
    @FXML
    private AnchorPane PanePreguntaModificarCuotaExtraordinaria;
    @FXML
    private AnchorPane PanePreguntaAgregarPagoCuotaOrdinaria;
    @FXML
    private AnchorPane PaneExitoModificacion;
    @FXML
    private AnchorPane PaneInmuebleNoExiste;
    @FXML
    private AnchorPane PaneVerificacionObligatoriaPropietario;
    @FXML
    private AnchorPane PanePreguntaAgregarCuota_Ordinaria;
    @FXML
    private AnchorPane PaneVerificacionObligatoriaCuota_Ordinaria;
    @FXML
    private AnchorPane PaneExitoRegistroCuota_Ordinaria;
    @FXML
    private AnchorPane PaneVerificacionObligatoriaServicios;
    @FXML
    private AnchorPane PaneNotificacionCuotaOrdinaria;
    @FXML
    private AnchorPane PaneVerificacionObligatoriaCuotaExtraordinaria;
    @FXML
    private AnchorPane PaneExitoCuotaExtraordinaria;
    @FXML
    private AnchorPane PanePreguntaAgregarUsuario;
    @FXML
    private AnchorPane PaneVerificacionObligatoriaAdministrador;
    @FXML
    private AnchorPane PanePreguntaModificarUsuario;
    @FXML
    private AnchorPane PanePreguntaEliminarUsuario;
    @FXML
    private AnchorPane PanePreguntaAgregarPagoArticulo;
    @FXML
    private AnchorPane PaneExitoArticulo;
    @FXML
    private AnchorPane PaneExitoEliminarArticulo;
    @FXML
    private AnchorPane PaneNotificacionCuotaOrdinaria1;
    @FXML
    private AnchorPane PaneVerificacionObligatoriaPago;
    @FXML
    private AnchorPane AnchorPaneLogin;
    @FXML private Pane PanePrincipal;
    @FXML private  Pane PaneMenu;
    @FXML private  Pane PaneBarra;
    @FXML private  Pane PaneDatosPersonalesLogin;
    @FXML
    private Pane PaneCubiertaVentaArticulo;
    @FXML
    private Pane PaneCubiertaUsuario;
    @FXML
    private Pane ListaMorosos;
    
    @FXML
    private ComboBox < String > cbxListaArticulos;
    @FXML
    private ComboBox < Integer > cbxTerrazaGestion_de_Recursos;
    @FXML
    private ComboBox < String  > cbxTipoHistorico;
    @FXML
    private TextField Cedula_Comprador;
    @FXML
    private TextField Cantidad_Articulos;
    @FXML
    private TextField Id_servicio;
    @FXML
    private Button BotonEliminarServiciosCorrespondienteACuota;
    @FXML
    private Button BotonAgregarPago;
   
    @FXML
    private Button MenuInmuebles;
    @FXML
    private Button BotonUsuario;
    @FXML
    private Button BotonInicio;
    @FXML
    private ImageView IconoEnter;
    @FXML
    private ImageView IconoClick;
    @FXML
    private TextField Pago_Articulo;
    @FXML
    private TextField BusquedaArticulo;
    @FXML
    private TextField ID_Articulo;
    
    Conexion obj = new Conexion ( );
    Propietario Propietario = new Propietario ( );
    Validaciones_Datos_Persona V = new Validaciones_Datos_Persona ( );
    Validaciones_Modulo_Cuota_Ordinaria C = new Validaciones_Modulo_Cuota_Ordinaria ( );
    Validaciones_Modulo_Cuota_Extraordinaria E = new Validaciones_Modulo_Cuota_Extraordinaria ( );
    Validaciones_de_Usuario U = new Validaciones_de_Usuario ( );
    
    
    public void enMenuInmueblesVisible ( MouseEvent event ) {
        
        PaneMenuInmuebles.setVisible ( true );
        PaneMenuCuotas.setVisible ( false );
        PaneMenuArticulos.setVisible ( false );
        
    }
    
    
    public void enMenuInmueblesInvisible ( MouseEvent event ) {
    
    
    }
    
    
    public void enMenuCuotasVisible ( MouseEvent event ) {
        
        
        PaneMenuCuotas.setVisible ( true );
        PaneMenuInmuebles.setVisible ( false );
        PaneMenuArticulos.setVisible ( false );
        
    }
    
    public void enInfoContraseñaLogin(MouseEvent event){
    
    
    
    
    }
    
    public void enInfoNombreUsuarioLogin(MouseEvent event){
        
        PaneInfoNombreUsuarioLogin.setVisible ( true );
        
    }
    
    public void enInfoNombreUsuarioLoginDesaparecer(MouseEvent event){
    
        PaneInfoNombreUsuarioLogin.setVisible ( false );
        
    }
    
    public void enMenuArticulosVisible ( MouseEvent event ) {
        
        PaneMenuArticulos.setVisible ( true );
        PaneMenuInmuebles.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        
    }
    
    public void enSubMenuCuotasVisible ( MouseEvent event ) {
        
        PaneMenuCuotas.setVisible ( true );
        
    }
    
    public void enSubMenuCuotasInvisible ( MouseEvent event ) {
        
        PaneMenuCuotas.setVisible ( false );
        
    }
    
    public void enSubMenuArticulosVisible ( MouseEvent event ) {
        
        PaneMenuArticulos.setVisible ( true );
    }
    
    public void enSubMenuArticulosInvisible ( MouseEvent event ) {
        
        PaneMenuArticulos.setVisible ( false );
        
    }
    
    public void enBotonVerListaMorosos ( ActionEvent event ) {
        
        ListaMorosos.setVisible ( true );
        PaneInicio.setVisible ( false );
        
    }
    
    public void enBotonDatosPropietario ( ActionEvent event ) {
        
        PaneDatosPropietario.setVisible ( true );
        PaneCuotaOrdinaria.setVisible ( false );
        PaneCuotaExtraordinaria.setVisible ( false );
        PaneMenuInmuebles.setVisible ( false );
        PanePagoCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneInicio.setVisible ( false );
        PaneGestionRecursos.setVisible ( false );
        PaneVentaArticulo.setVisible ( false );
        PaneAdministrarArticulo.setVisible ( false );
        ListaMorosos.setVisible ( false );
        LogoOrganizacion.setVisible ( false );
        ImagenUsuario.setVisible ( true );
        
    }
    
    public void enBotonCuotaOrdinaria ( ActionEvent event ) {
        
        PaneCuotaOrdinaria.setVisible ( true );
        PaneDatosPropietario.setVisible ( false );
        PaneCuotaExtraordinaria.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PanePagoCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneVentaArticulo.setVisible ( false );
        PaneAdministrarArticulo.setVisible ( false );
        PaneInicio.setVisible ( false );
        PaneGestionRecursos.setVisible ( false );
        PaneVentaArticulo.setVisible ( false );
        PaneAdministrarArticulo.setVisible ( false );
        ListaMorosos.setVisible ( false );
        LogoOrganizacion.setVisible ( false );
        ImagenUsuario.setVisible ( true );
        
    }
    
    public void enBotonCuotaExtraordinaria ( MouseEvent event ) {
        
        
        PaneCuotaExtraordinaria.setVisible ( true );
        PaneDatosPropietario.setVisible ( false );
        PaneCuotaOrdinaria.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PanePagoCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneVentaArticulo.setVisible ( false );
        PaneAdministrarArticulo.setVisible ( false );
        PaneInicio.setVisible ( false );
        PaneGestionRecursos.setVisible ( false );
        PaneVentaArticulo.setVisible ( false );
        PaneAdministrarArticulo.setVisible ( false );
        ListaMorosos.setVisible ( false );
        LogoOrganizacion.setVisible ( false );
        ImagenUsuario.setVisible ( true );
        
    }
    
    public void enBotonUsuario ( MouseEvent event ) {
        
        PaneDatosPropietario.setVisible ( false );
        PaneCuotaOrdinaria.setVisible ( false );
        PaneCuotaExtraordinaria.setVisible ( false );
        PaneMenuInmuebles.setVisible ( false );
        PanePagoCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( true );
        PaneInicio.setVisible ( false );
        PaneGestionRecursos.setVisible ( false );
        PaneVentaArticulo.setVisible ( false );
        PaneAdministrarArticulo.setVisible ( false );
        ListaMorosos.setVisible ( false );
        LogoOrganizacion.setVisible ( false );
        ImagenUsuario.setVisible ( true );
        
    }
    
    public void enBotonUsuarioEntrada ( MouseEvent event ) {
        
        PaneMenuArticulos.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PaneMenuInmuebles.setVisible ( false );
        
    }
    
    public void enBotonPagoCuotaOrdinaria ( MouseEvent event ) {
        
        PanePagoCuotaOrdinaria.setVisible ( true );
        PaneCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneCuotaExtraordinaria.setVisible ( false );
        PaneDatosPropietario.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneMenuInmuebles.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PaneMenuArticulos.setVisible ( false );
        PaneInicio.setVisible ( false );
        PaneGestionRecursos.setVisible ( false );
        PaneVentaArticulo.setVisible ( false );
        PaneAdministrarArticulo.setVisible ( false );
        ListaMorosos.setVisible ( false );
        LogoOrganizacion.setVisible ( false );
        ImagenUsuario.setVisible ( true );
        
    }
    
    
    public void enBotonAdministrarArticulo ( MouseEvent event ) {
        
        PaneAdministrarArticulo.setVisible ( true );
        PaneVentaArticulo.setVisible ( false );
        PaneCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneCuotaExtraordinaria.setVisible ( false );
        PaneDatosPropietario.setVisible ( false );
        PanePagoCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneMenuInmuebles.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PaneMenuArticulos.setVisible ( false );
        PaneInicio.setVisible ( false );
        PaneGestionRecursos.setVisible ( false );
        ListaMorosos.setVisible ( false );
        LogoOrganizacion.setVisible ( false );
        ImagenUsuario.setVisible ( true );
        
    }
    
    public void enBotonVentaArticulo ( MouseEvent event ) {
        
        
        PaneVentaArticulo.setVisible ( true );
        PaneAdministrarArticulo.setVisible ( false );
        PaneCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneCuotaExtraordinaria.setVisible ( false );
        PaneDatosPropietario.setVisible ( false );
        PanePagoCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneMenuInmuebles.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PaneMenuArticulos.setVisible ( false );
        PaneInicio.setVisible ( false );
        PaneGestionRecursos.setVisible ( false );
        ListaMorosos.setVisible ( false );
        LogoOrganizacion.setVisible ( false );
        ImagenUsuario.setVisible ( true );
        
    }
    
    public void enBotonInicio ( MouseEvent event ) {
        
        PaneInicio.setVisible ( true );
        PaneVentaArticulo.setVisible ( false );
        PaneAdministrarArticulo.setVisible ( false );
        PaneCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneCuotaExtraordinaria.setVisible ( false );
        PaneDatosPropietario.setVisible ( false );
        PanePagoCuotaOrdinaria.setVisible ( false );
        PaneGestionRecursos.setVisible ( false );
        ListaMorosos.setVisible ( false );
        
    }
    
    public void enBotonInicioOcultar ( MouseEvent event ) {
        
        PaneMenuInmuebles.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PaneMenuArticulos.setVisible ( false );
        
    }
    
    public void enBotonGestionDeRecursos ( MouseEvent event ) {
        
        PaneAdministrarArticulo.setVisible ( false );
        PaneVentaArticulo.setVisible ( false );
        PaneCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneCuotaExtraordinaria.setVisible ( false );
        PaneDatosPropietario.setVisible ( false );
        PanePagoCuotaOrdinaria.setVisible ( false );
        PaneUsuario.setVisible ( false );
        PaneMenuInmuebles.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PaneMenuArticulos.setVisible ( false );
        PaneInicio.setVisible ( false );
        PaneGestionRecursos.setVisible ( true );
        ListaMorosos.setVisible ( false );
        
    }
    
    public void enBotonGestionDeRecursosEntrada ( MouseEvent event ) {
        
        PaneMenuInmuebles.setVisible ( false );
        PaneMenuCuotas.setVisible ( false );
        PaneMenuArticulos.setVisible ( false );
        
    }
    
    public void Registro_Principal_Atras(ActionEvent event){
    
        PaneDatosPersonalesLogin.setVisible(true);
        PaneDatosUsuario_login.setVisible ( false );
        
    }
    
    public void Registro_Principal(ActionEvent event) {
    
        Advertencias a = new Advertencias ( );
        a.Advertencia_Nombre_Usuario ( U.Id_Usuario ( Nombre_Usuario_Login ) , Advertencia_ID_Login );
        a.Advertencia_Contraseña ( U.Contraseña_Usuario ( Contraseña_Login ) , Advertencia_Contraseña_Login );
        a.Advertencia_Sexo ( cbxCargo_Login , Advertencia_Cargo_Login );
        a.Advertencia_Pregunta_Usuario ( cbxPregunta_Login , Advertencia_Pregunta_Login );
        a.Advertencia_Primer_Apellido ( Respuesta_Login , Advertencia_Respuesta_Login );
    
        if ( (! ( Advertencia_ID_Login.isVisible ( ) && Advertencia_Contraseña_Login.isVisible ( ) && Advertencia_Cargo_Login.isVisible ( ) && Advertencia_Pregunta_Login.isVisible ( ) && Advertencia_Respuesta_Login.isVisible ( )) ) || ( ! ( Advertencia_ID_Login.isVisible ( ) || Advertencia_Contraseña_Login.isVisible ( ) || Advertencia_Cargo_Login.isVisible ( ) || Advertencia_Pregunta_Login.isVisible ( ) || Advertencia_Respuesta_Login.isVisible ( ) ) ) ) {
        
            Usuario nuevo_usuario = new Usuario ( cbxNacionalidadLogin.getSelectionModel ( ).getSelectedItem ( ) , Integer.parseInt ( CedulaLogin.getText ( ) ) , Primer_Nombre_Login.getText ( ) , Segundo_Nombre_Login.getText ( ) , Primer_Apellido_Login.getText ( ) , Segundo_Apellido_Login.getText ( ) , Date.valueOf ( Fecha_de_Nacimiento_Login.getValue ( ) ) , cbxSexoLogin.getSelectionModel ( ).getSelectedItem ( ) , cbxEstado_Civil_Login.getSelectionModel ( ).getSelectedItem ( ) , Nombre_Usuario_Login.getText ( ) , Contraseña_Login.getText ( ) , cbxCargo_Login.getSelectionModel ( ).getSelectedItem ( ) , cbxPregunta_Login.getSelectionModel ( ).getSelectedItem ( ) , Respuesta_Login.getText ( ) );
        
            int Verificacion = nuevo_usuario.Verificar_Existencia_de_Usuario ( obj.getConnection ( ) );
        
            if ( Verificacion == 0 ) {
            
                nuevo_usuario.Registrar ( obj.getConnection ( ) );
            
                LabelAdvertenciaLogin.setText ( "El usuario ha sido registrado con exito" );
            
            } else {
            
                LabelAdvertenciaLogin.setText ( "Ya existe un " + cbxCargo_Login.getSelectionModel ( ).getSelectedItem ( ) );
            
            }
        }
    }
    
    public void enBotonDatosPersonales_login(ActionEvent event){
        
        Advertencias a = new Advertencias();
        a.Advertencia_Primer_Nombre ( Primer_Nombre_Login, Advertencia_Primer_Nombre_Login );
        a.Advertencia_Primer_Apellido ( Primer_Apellido_Login, Advertencia_Primer_Apellido_Login );
        a.Advertencia_Fecha_de_Nacimiento ( Fecha_de_Nacimiento_Login, Advertencia_Fecha_de_Nacimiento_Login );
        a.Advertencia_Sexo ( cbxSexoLogin, Advertencia_Sexo_Login );
        a.Advertencia_Estado_Civil ( cbxEstado_Civil_Login, Advertencia_Estado_Civil_Login );
        
        if((!(Advertencia_Primer_Nombre_Login.isVisible () && Advertencia_Primer_Apellido_Login.isVisible () && Advertencia_Fecha_de_Nacimiento_Login.isVisible () && Advertencia_Sexo_Login.isVisible () && Advertencia_Estado_Civil_Login.isVisible () ) || (!(Advertencia_Primer_Nombre_Login.isVisible () || Advertencia_Primer_Apellido_Login.isVisible () || Advertencia_Fecha_de_Nacimiento_Login.isVisible () || Advertencia_Sexo_Login.isVisible () || Advertencia_Estado_Civil_Login.isVisible ())))){
            
            PaneDatosUsuario_login.setVisible ( true );
      
        }
        
    }
    
    public void enBotonRecibo ( ActionEvent event ) {
        
        Advertencias ad = new Advertencias ();
         ad.Advertencia_Estado_Civil ( cbxIdsCasaPago, AdvertenciaDireccionPagoCuota );
        if((AdvertenciaCedulaPagoCuota.isVisible () && CedulaPago.getText ().length () < 7 && AdvertenciaDireccionPagoCuota.isVisible () && cbxIdsCasaPago.getSelectionModel ().getSelectedItem () == null) || (AdvertenciaCedulaPagoCuota.isVisible () || CedulaPago.getText ().length () < 7 || AdvertenciaDireccionPagoCuota.isVisible () || cbxIdsCasaPago.getSelectionModel ().getSelectedItem () == null) ){
            
            PaneCubiertaPagoDeCuotas.setVisible ( true );
            PaneVerificacionObligatoriaPago.setVisible ( true );
            
        }else{
            
            cbxTipoHistorico.setVisible ( true );
            
        }
     /*   Reportes reporte = new Reportes ( );
        try {
            
            reporte.Reporte_Pago_Cuota_Ordinaria ( CedulaPago.getText ( ) );
            
        } catch ( JRException e ) {
            e.printStackTrace ( );
        }
        Reportes historico = new Reportes ();
        Propietario_Tiene_Inmueble id_casa = new Propietario_Tiene_Inmueble(cbxCasaPago.getSelectionModel().getSelectedItem(), numero_terraza.getText());
        int id = id_casa.Buscar_ID_del_Inmueble(obj.getConnection());
        System.out.println ( id );
        
        try {
            
            historico.reporteHistoricoCuotaExtraordinaria(id, cbxCasaPago.getSelectionModel ().getSelectedItem (), Integer.valueOf ( numero_terraza.getText() ) );
            
        } catch (JRException e) {
            e.printStackTrace();
        }
        
        try {
        
            historico.reporteHistoricoCuotaOrdinaria(id, cbxCasaPago.getSelectionModel ().getSelectedItem (), Integer.valueOf ( numero_terraza.getText() ) );
        
        } catch (JRException e) {
            e.printStackTrace();
        }*/
     
    }
    
    public void ReporteHistoricoCuotaExtraordinaria(){
    
        Reportes historico = new Reportes ();
        Propietario_Tiene_Inmueble id_casa = new Propietario_Tiene_Inmueble(cbxCasaPago.getSelectionModel().getSelectedItem(), numero_terraza.getText());
        int id = id_casa.Buscar_ID_del_Inmueble(obj.getConnection());
        System.out.println ( id );
        
        try {
        
            historico.reporteHistoricoCuotaExtraordinaria(id, cbxCasaPago.getSelectionModel ().getSelectedItem (), Integer.valueOf ( numero_terraza.getText() ) );
        
        } catch (JRException e) {
            e.printStackTrace();
        }
        
    }
    
    public void ReporteHistoricoCuotaOrdinaria(){
    
        Reportes historico = new Reportes ();
        Propietario_Tiene_Inmueble id_casa = new Propietario_Tiene_Inmueble(cbxCasaPago.getSelectionModel().getSelectedItem(), numero_terraza.getText());
        int id = id_casa.Buscar_ID_del_Inmueble(obj.getConnection());
        System.out.println ( id );
        
        try {
        
            historico.reporteHistoricoCuotaOrdinaria(id, cbxCasaPago.getSelectionModel ().getSelectedItem (), Integer.valueOf ( numero_terraza.getText() ) );
        
        } catch (JRException e) {
            e.printStackTrace();
        }
        
    }
    
    public void enCbxHistoricos(ActionEvent event){
        
        if(cbxTipoHistorico.getSelectionModel ().getSelectedItem ().equals ( "Historico Cuota Ordinaria" )){
    
            ReporteHistoricoCuotaOrdinaria();
            
        }else if(cbxTipoHistorico.getSelectionModel ().getSelectedItem ().equals ( "Historico Cuota Extraordinaria" )){
    
            ReporteHistoricoCuotaExtraordinaria();
            
        }
    }
    
    public void enSubMenuDatosPropietarioVisible ( MouseEvent event ) {
        
        PaneMenuInmuebles.setVisible ( true );
    }
    
    public void enSubMenuDatosPropietarioInvisible ( MouseEvent event ) {
        
        PaneMenuInmuebles.setVisible ( false );
    }
    
    public void enBotonNOVentanaAgregarUsuario ( ActionEvent event ) {
        
        PaneCubiertaUsuario.setVisible ( false );
        PanePreguntaAgregarUsuario.setVisible ( false );
        
    }
    
    public void Busqueda_Usuario ( KeyEvent event ) {
        
        Cedula_Administrador.setOnKeyPressed ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                if ( Cedula_Administrador.getText ( ).length ( ) >= 7 ) {
                    
                    PaneEnterCedulaUsuario.setVisible ( true );
                    AdvertenciaCedula.setVisible ( false );
                    
                    if ( event.getCode ( ).equals ( KeyCode.ENTER ) ) {
                        
                        PaneEnterCedulaUsuario.setVisible ( false );
                        Llenar_Campos_Modulo_Usuario ( );
                        
                    } else {
                        
                        if ( event.getCode ( ).equals ( KeyCode.BACK_SPACE ) )
                            
                            AdvertenciaCedula.setVisible ( true );
                        Limpiar_Campos_Formulario ( Primer_Nombre_Administrador , Segundo_Nombre_Administrador , Primer_Apellido_Administrador , Segundo_Apellido_Administrador , Fecha_de_Nacimiento_Administrador , cbxSexo_Administrador , cbxNacionalidad_Administrador , cbxEstado_Civil_Administrador , cbxPrefijoTelefonico2 , cbxPrefijoTelefonico3 , Telefono2 , Telefono3 , cbxDominio2 , cbxDominio3 , Usuario2 , Usuario3 , dominios , usuarios , prefijo_telefonico0 , numero_telefonico0 );
                        
                    }
                    
                } else {
                    
                    AdvertenciaCedula.setVisible ( true );
                    
                }
            }
        } );
    }
    
    public void Llenar_Campos_Modulo_Usuario ( ) {
        
        Usuario Usuario0 = new Usuario ( parseInt ( Cedula_Administrador.getText ( ) ) );
        BotonModificarUsuario.setDisable ( false );
        int Verificacion_existe_como_usuario = Usuario0.Llenar_Campos_Usuario ( obj.getConnection ( ) , Primer_Nombre_Administrador , Segundo_Nombre_Administrador , Primer_Apellido_Administrador , Segundo_Apellido_Administrador , cbxSexo_Administrador , cbxNacionalidad_Administrador , cbxEstado_Civil_Administrador , Fecha_de_Nacimiento_Administrador , Nombre_de_Usuario , Contraseña_de_Usuario , cbxCargo_Administrador , cbxPregunta_de_Seguridad_Administrador , Respuesta_de_Seguridad );
        
        if ( Verificacion_existe_como_usuario == 1 ) {
            
            Llenar_Campos_Telefono ( Cedula_Administrador , prefijo_telefonico0 , numero_telefonico0 , cbxPrefijoTelefonico2 , cbxPrefijoTelefonico3 , Telefono2 , Telefono3 );
            Llenar_Campos_Correo ( Cedula_Administrador , usuarios , Usuario2 , Usuario3 );
            
            
        } else {
            
            Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble ( );
            
            int Propietario_Existe = Propietario_Inmueble.Llenar_Campos ( obj.getConnection ( ) , parseInt ( Cedula_Administrador.getText ( ) ) , Primer_Nombre_Administrador , Segundo_Nombre_Administrador , Primer_Apellido_Administrador , Segundo_Apellido_Administrador , cbxSexo_Administrador , cbxNacionalidad_Administrador , cbxEstado_Civil_Administrador , Fecha_de_Nacimiento_Administrador );
            
            if ( Propietario_Existe == 1 ) {
                
                Llenar_Campos_Telefono ( Cedula_Administrador , prefijo_telefonico0 , numero_telefonico0 , cbxPrefijoTelefonico2 , cbxPrefijoTelefonico3 , Telefono2 , Telefono3 );
                Llenar_Campos_Correo ( Cedula_Administrador , usuarios , Usuario2 , Usuario3 );
                
            }
        }
    }
    
    public void Limpiar_Campos_Formulario ( TextField Primer_Nombre ,
                                            TextField Segundo_Nombre ,
                                            TextField Primer_Apellido ,
                                            TextField Segundo_Apellido ,
                                            DatePicker Fecha_de_Nacimiento ,
                                            ComboBox < String > cbxSexo ,
                                            ComboBox < String > cbxNacionalidad ,
                                            ComboBox < String > cbxEstado_Civil ,
                                            ComboBox < String > cbxPrefijoTelefonico ,
                                            ComboBox < String > cbxPrefijoTelefonico0 ,
                                            TextField Telefono ,
                                            TextField Telefono0 ,
                                            ComboBox < String > cbxDominio ,
                                            ComboBox < String > cbxDominio0 ,
                                            TextField Usuario ,
                                            TextField Usuario0 ,
                                            ObservableList dominios ,
                                            ObservableList usuarios ,
                                            ObservableList prefijo_telefonico ,
                                            ObservableList numero_telefonico0
    
    ) {
        
        Primer_Nombre.clear ( );
        Segundo_Nombre.clear ( );
        Primer_Apellido.clear ( );
        Segundo_Apellido.clear ( );
        Fecha_de_Nacimiento.setValue ( null );
        cbxSexo.setValue ( null );
        cbxNacionalidad.setValue ( null );
        cbxEstado_Civil.setValue ( null );
        dominios.clear ( );
        usuarios.clear ( );
        prefijo_telefonico.clear ( );
        numero_telefonico0.clear ( );
        cbxPrefijoTelefonico.setValue ( null );
        cbxPrefijoTelefonico0.setValue ( null );
        Telefono.clear ( );
        Telefono0.clear ( );
        Usuario.clear ( );
        Usuario0.clear ( );
        TablaPropiedades.setItems ( null );
        BotonModificarPropietario.setDisable ( true );
        
    }
    
    public void Llenar_Campos_Telefono ( TextField cedula , ObservableList prefijo_telefonico0 , ObservableList numero_telefonico0 , ComboBox < String > cbxPrefijoTelefonico , ComboBox < String > cbxPrefijoTelefonico1 , TextField Telefono , TextField Telefono1 ) {
        
        Persona_Puede_Poseer_Telefono Persona_Telefono = new Persona_Puede_Poseer_Telefono ( );
        Persona_Telefono.Buscar_Numero ( obj.getConnection ( ) , Integer.valueOf ( cedula.getText ( ) ) , prefijo_telefonico0 , numero_telefonico0 , cbxPrefijoTelefonico , cbxPrefijoTelefonico1 , Telefono , Telefono1 );
        
        
    }
    
    public void Llenar_Campos_Correo ( TextField cedula , ObservableList usuarios , TextField Usuario , TextField Usuario1 ) {
        
        Persona_Puede_Tener_Correo Persona_Correo = new Persona_Puede_Tener_Correo ( );
        Persona_Correo.BuscarCorreo ( obj.getConnection ( ) , parseInt ( cedula.getText ( ) ) , usuarios , Usuario , Usuario1 );
        
    }
    
    public void enBotonAgregarUsuario ( ActionEvent event ) {
        
        Advertencias Advertencias = new Advertencias ( );
        Usuario usuario = new Usuario ( Nombre_de_Usuario.getText () );
        Advertencias ( Primer_Nombre_Administrador , AdvertenciaPrimerNombre , Primer_Apellido_Administrador , AdvertenciaPrimerApellido , cbxNacionalidad_Administrador , AdvertenciaCedula , cbxSexo_Administrador , AdvertenciaCbxSexo , cbxEstado_Civil_Administrador , AdvertenciaCbxEstadoCivil , Fecha_de_Nacimiento_Administrador , AdvertenciaFechaNacimiento , Telefono2 , AdvertenciaTelefono , Telefono3 , AdvertenciaTelefono1 , Usuario2 , AdvertenciaCorreo , Usuario3 , AdvertenciaCorreo1 , cbxPrefijoTelefonico2 , cbxPrefijoTelefonico3 );
        Advertencias.Advertencia_Nombre_Usuario ( U.Id_Usuario ( Nombre_de_Usuario ) , AdvertenciaNombredeUsuario );
        Advertencias.Advertencia_Contraseña ( U.Contraseña_Usuario ( Contraseña_de_Usuario ) , AdvertenciaContraseñaUsuario );
        Advertencias.Advertencia_Cargo_Usuario ( cbxCargo_Administrador , AdvertenciaCargoUsuario );
        Advertencias.Advertencia_Pregunta_Usuario ( cbxPregunta_de_Seguridad_Administrador , AdvertenciaPreguntaUsuario );
        Advertencias.Advertencia_Existe_Nombre_Usuario (usuario.Verificacion_existencia_nombre_de_usuario ( obj.getConnection () ), AdvertenciaNombredeUsuario );
        
        if ( ( Cedula_Administrador.getText ( ).isEmpty ( ) && Primer_Nombre_Administrador.getText ( ).isEmpty ( ) && Primer_Apellido_Administrador.getText ( ).isEmpty ( ) && Fecha_de_Nacimiento_Administrador.getValue ( ) == null && cbxSexo_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null && cbxEstado_Civil_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null && cbxNacionalidad_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null && AdvertenciaTelefono.isVisible ( ) && AdvertenciaTelefono1.isVisible ( ) && AdvertenciaCorreo.isVisible ( ) && AdvertenciaCorreo1.isVisible ( ) && AdvertenciaNombredeUsuario.isVisible ( ) && AdvertenciaContraseñaUsuario.isVisible ( ) && AdvertenciaCargoUsuario.isVisible ( ) && AdvertenciaPreguntaUsuario.isVisible ( ) && AdvertenciaRespuestaUsuario.isVisible ( ) ) || ( Cedula_Administrador.getText ( ).isEmpty ( ) || Cedula_Administrador.getText ( ).length ( ) < 7 || Primer_Nombre_Administrador.getText ( ).isEmpty ( ) || Primer_Apellido_Administrador.getText ( ).isEmpty ( ) || Fecha_de_Nacimiento_Administrador.getValue ( ) == null || cbxSexo_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null || cbxEstado_Civil_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null || cbxNacionalidad_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null || AdvertenciaTelefono.isVisible ( ) || AdvertenciaTelefono1.isVisible ( ) || AdvertenciaCorreo.isVisible ( ) || AdvertenciaCorreo1.isVisible ( ) || AdvertenciaNombredeUsuario.isVisible ( ) || AdvertenciaContraseñaUsuario.isVisible ( ) || AdvertenciaCargoUsuario.isVisible ( ) || AdvertenciaPreguntaUsuario.isVisible ( ) || AdvertenciaRespuestaUsuario.isVisible ( ) ) ) {
            
            PaneCubiertaUsuario.setVisible ( true );
            PaneVerificacionObligatoriaAdministrador.setVisible ( true );
            
        } else {
            
            PanePreguntaAgregarUsuario.setVisible ( true );
            PaneCubiertaUsuario.setVisible ( true );
            
        }
        
    }
    
    
    public void Advertencias ( TextField Primer_Nombre , Pane AdvertenciaPrimerNombre , TextField Primer_Apellido , Pane AdvertenciaPrimerApellido , ComboBox cbxNacionalidad , Pane AdvertenciaCedula , ComboBox cbxSexo , Pane AdvertenciaCbxSexo , ComboBox cbxEstado_Civil , Pane AdvertenciaCbxEstadoCivil , DatePicker Fecha_de_Nacimiento , Pane AdvertenciaFechaNacimiento , TextField Telefono , Pane AdvertenciaTelefono , TextField Telefono1 , Pane AdvertenciaTelefono1 , TextField Usuario , Pane AdvertenciaCorreo , TextField Usuario1 , Pane AdvertenciaCorreo1 , ComboBox cbxPrefijoTelefonico , ComboBox cbxPrefijoTelefonico1 ) {
        
        Advertencias advertencias = new Advertencias ( );
        int Validez = V.Correo ( Usuario );
        int Validez1 = V.Correo ( Usuario1 );
        advertencias.Advertencia_Primer_Nombre ( Primer_Nombre , AdvertenciaPrimerNombre );
        advertencias.Advertencia_Primer_Apellido ( Primer_Apellido , AdvertenciaPrimerApellido );
        advertencias.Advertencia_Nacionalidad ( cbxNacionalidad , AdvertenciaCedula );
        advertencias.Advertencia_Sexo ( cbxSexo , AdvertenciaCbxSexo );
        advertencias.Advertencia_Estado_Civil ( cbxEstado_Civil , AdvertenciaCbxEstadoCivil );
        advertencias.Advertencia_Fecha_de_Nacimiento ( Fecha_de_Nacimiento , AdvertenciaFechaNacimiento );
        advertencias.Advertencia_Telefono ( Telefono , AdvertenciaTelefono );
        advertencias.Advertencia_Telefono ( Telefono1 , AdvertenciaTelefono1 );
        advertencias.Advertencia_Correo ( Usuario , Validez , AdvertenciaCorreo );
        advertencias.Advertencia_Correo ( Usuario1 , Validez1 , AdvertenciaCorreo1 );
        advertencias.Advertencia_Prefijo_Telefonico ( cbxPrefijoTelefonico , Telefono , AdvertenciaTelefono );
        advertencias.Advertencia_Prefijo_Telefonico ( cbxPrefijoTelefonico1 , Telefono1 , AdvertenciaTelefono1 );
        
    }
    
    public void enBotonSIDatosAdministrador ( ActionEvent event ) {
        
        Usuario Usuario = new Usuario ( cbxNacionalidad_Administrador.getSelectionModel ( ).getSelectedItem ( ) , parseInt ( Cedula_Administrador.getText ( ) ) , Primer_Nombre_Administrador.getText ( ) , Segundo_Nombre_Administrador.getText ( ) , Primer_Apellido_Administrador.getText ( ) , Segundo_Apellido_Administrador.getText ( ) , Date.valueOf ( Fecha_de_Nacimiento_Administrador.getValue ( ) ) , cbxSexo_Administrador.getSelectionModel ( ).getSelectedItem ( ) , cbxEstado_Civil_Administrador.getSelectionModel ( ).getSelectedItem ( ) , Nombre_de_Usuario.getText ( ) , getMD5 ( ContraseñaUsuario.getText () ) , cbxCargo_Administrador.getSelectionModel ( ).getSelectedItem ( ) , cbxPregunta_de_Seguridad_Administrador.getSelectionModel ( ).getSelectedItem ( ) , Respuesta_de_Seguridad.getText ( ) );
        
        int Verificar_Existencia_Usuario = Usuario.Verificar_Existencia_de_Usuario ( obj.getConnection ( ) );
        
        if ( Verificar_Existencia_Usuario == 0 ) {
            
            int Registrar_Usuario = Usuario.Registrar ( obj.getConnection ( ) );
            
            if ( Registrar_Usuario == 1 ) {
                
                Registro_de_Telefonos ( obj.getConnection ( ) , Cedula_Administrador , cbxPrefijoTelefonico2 , Telefono2 , cbxPrefijoTelefonico3 , Telefono3 );
                Registro_de_Correos ( obj.getConnection ( ) , Cedula_Administrador , Usuario2 , Usuario3 );
                PanePreguntaAgregarUsuario.setVisible ( false );
                PaneCubiertaUsuario.setVisible ( false );
                PaneCubiertaUsuario.setVisible ( true );
                PaneExitoAgregarUsuario.setVisible ( true );
                
            }
            
        } else {
            
            LabelExistencia.setText ( "Ya existe un " + cbxCargo_Administrador.getSelectionModel ( ).getSelectedItem ( ) + " registrado" );
            PaneExistencia.setVisible ( true );
            
        }
        
    }
    
    public void enBotonModificarDatosUsuario ( ActionEvent event ) {
        
        PanePreguntaModificarUsuario.setVisible ( true );
        PaneCubiertaUsuario.setVisible ( true );
        
    }
    
    public void enBotonSIModificarDatosAdministrador ( ActionEvent event ) {
        
        
        Conexion obj = new Conexion ( );
        Usuario Usuario = new Usuario (
                cbxNacionalidad_Administrador.getSelectionModel ( ).getSelectedItem ( ) ,
                Integer.parseInt ( Cedula_Administrador.getText ( ) ) ,
                Primer_Nombre_Administrador.getText ( ) ,
                Segundo_Nombre_Administrador.getText ( ) ,
                Primer_Apellido_Administrador.getText ( ) ,
                Segundo_Apellido_Administrador.getText ( ) ,
                Date.valueOf ( Fecha_de_Nacimiento_Administrador.getValue ( ) ) ,
                cbxSexo_Administrador.getSelectionModel ( ).getSelectedItem ( ) ,
                cbxEstado_Civil_Administrador.getSelectionModel ( ).getSelectedItem ( ) ,
                Nombre_de_Usuario.getText ( ) ,
                Contraseña_de_Usuario.getText ( ) ,
                cbxCargo_Administrador.getSelectionModel ( ).getSelectedItem ( ) ,
                cbxPregunta_de_Seguridad_Administrador.getSelectionModel ( ).getSelectedItem ( ) ,
                Respuesta_de_Seguridad.getText ( )
        
        );
        
        
        int ResultadoDeLaModificacion = Usuario.Modificar ( obj.getConnection ( ) );
        
        if ( ResultadoDeLaModificacion == 1 ) {
            
            Modificar_Telefono ( Cedula_Administrador , cbxPrefijoTelefonico2 , cbxPrefijoTelefonico3 , Telefono2 , Telefono3 );
            Modificar_Correo ( Cedula_Administrador , cbxDominio2 , cbxDominio3 , Usuario2 , Usuario3 );
            PanePreguntaModificarUsuario.setVisible ( false );
            PaneCubiertaUsuario.setVisible ( false );
            PaneExitoModificarUsuario.setVisible ( true );
            PaneCubiertaUsuario.setVisible ( true );
            
        }
        
    }
    
    public void Modificar_Telefono ( TextField cedula , ComboBox < String > cbxPrefijoTelefonico , ComboBox < String > cbxPrefijoTelefonico0 , TextField Telefono , TextField Telefono0 ) {
        
        Persona_Puede_Poseer_Telefono Persona_Telefono = new Persona_Puede_Poseer_Telefono ( Integer.parseInt ( cedula.getText ( ) ) );
        Persona_Telefono.Buscar_Telefono ( obj.getConnection ( ) , ids_Telefonos );
        Telefono Modificar_Telefono = new Telefono ( cbxPrefijoTelefonico.getSelectionModel ( ).getSelectedItem ( ) , Telefono.getText ( ) );
        Modificar_Telefono.Modificar ( obj.getConnection ( ) , ids_Telefonos.get ( 0 ) );
        Telefono Modificar_Telefono1 = new Telefono ( cbxPrefijoTelefonico0.getSelectionModel ( ).getSelectedItem ( ) , Telefono0.getText ( ) );
        Modificar_Telefono1.Modificar ( obj.getConnection ( ) , ids_Telefonos.get ( 1 ) );
        
    }
    
    public void Modificar_Correo ( TextField cedula , ComboBox < String > cbxDominio , ComboBox < String > cbxDominio0 , TextField Usuario , TextField Usuario0 ) {
        
        Persona_Puede_Tener_Correo Persona_Correo = new Persona_Puede_Tener_Correo ( Integer.parseInt ( cedula.getText ( ) ) );
        Persona_Correo.Buscar_Correo ( obj.getConnection ( ) , ids_Correos );
        Correo Modificar_Correo0 = new Correo ( Usuario.getText ( ) );
        Modificar_Correo0.Modificar ( obj.getConnection ( ) , ids_Correos.get ( 0 ) );
        Correo Modificar_Correo = new Correo ( Usuario0.getText ( ) );
        Modificar_Correo.Modificar ( obj.getConnection ( ) , ids_Correos.get ( 1 ) );
        
    }
    
    public void enBotonNOVentanaModificarUsuario ( ActionEvent event ) {
        
        PanePreguntaModificarUsuario.setVisible ( false );
        PaneCubiertaUsuario.setVisible ( false );
        
    }
    
    public void enBotonEliminarUsuario ( ActionEvent event ) {
        
        if ( ( Cedula_Administrador.getText ( ).isEmpty ( ) && Primer_Nombre_Administrador.getText ( ).isEmpty ( ) && Primer_Apellido_Administrador.getText ( ).isEmpty ( ) && Fecha_de_Nacimiento_Administrador.getValue ( ) == null && cbxSexo_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null && cbxEstado_Civil_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null && cbxNacionalidad_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null && Nombre_de_Usuario.getText ( ).isEmpty ( ) && Contraseña_de_Usuario.getText ( ).isEmpty ( ) && cbxCargo_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null && cbxPregunta_de_Seguridad_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null && Respuesta_de_Seguridad.getText ().length () < 2 && Respuesta_de_Seguridad.getText ( ).isEmpty ( ) ) || ( Cedula_Administrador.getText ( ).isEmpty ( ) || Primer_Nombre_Administrador.getText ( ).isEmpty ( ) || Primer_Apellido_Administrador.getText ( ).isEmpty ( ) || Fecha_de_Nacimiento_Administrador.getValue ( ) == null || cbxSexo_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null || cbxEstado_Civil_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null || cbxNacionalidad_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null || Nombre_de_Usuario.getText ( ).isEmpty ( ) || Contraseña_de_Usuario.getText ( ).isEmpty ( ) || cbxCargo_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null || cbxPregunta_de_Seguridad_Administrador.getSelectionModel ( ).getSelectedItem ( ) == null ||Respuesta_de_Seguridad.getText ().length () < 2 || Respuesta_de_Seguridad.getText ( ).isEmpty ( ) ) ) {
            
            PaneCubiertaUsuario.setVisible ( true );
            PaneVerificacionObligatoriaAdministrador.setVisible ( true );
            
        } else {
            
            PanePreguntaEliminarUsuario.setVisible ( true );
            PaneCubiertaUsuario.setVisible ( true );
            
        }
    }
    
    public void enBotonSIEliminarDatosAdministrador ( ActionEvent event ) {
        
        Propietario Propietario = new Propietario ( Integer.parseInt ( Cedula_Administrador.getText ( ) ) );
        Usuario Usuario = new Usuario ( Integer.parseInt ( Cedula_Administrador.getText ( ) ) );
        
        int Existencia = Propietario.Verificar_Existencia_de_Propietario ( obj.getConnection ( ) );
        
        if ( Existencia == 1 ) {
            
            boolean Verificacion = Usuario.Eliminar ( obj.getConnection ( ) );
            
            if ( Verificacion == false ) {
                
                PanePreguntaEliminarUsuario.setVisible ( false );
                PaneCubiertaUsuario.setVisible ( true );
                PaneExitoEliminarUsuario.setVisible ( true );
                
            }
            
        } else {
            
            boolean Verificacion = Usuario.Eliminar_Datos ( obj.getConnection ( ) );
            Usuario.Eliminar ( obj.getConnection ( ) );
            
            if ( Verificacion == false ) {
                
                PanePreguntaEliminarUsuario.setVisible ( false );
                PaneCubiertaUsuario.setVisible ( true );
                PaneExitoEliminarUsuario.setVisible ( true );
                
            }
        }
    }
    
    public void enBotonNOVentanaEliminarUsuario ( ActionEvent event ) {
        
        PanePreguntaEliminarUsuario.setVisible ( false );
        PaneCubiertaUsuario.setVisible ( false );
        
    }
    
    @FXML
    public void enBotonRegistrar ( ActionEvent event ) {
        
        Advertencias advertencias = new Advertencias ( );
        Advertencias ( Primer_Nombre , AdvertenciaPrimerNombrePropietario , Primer_Apellido , AdvertenciaPrimerApellidoPropietario , cbxNacionalidad , AdvertenciaCedulaPropietario , cbxSexo , AdvertenciaSexoPropietario , cbxEstadoCivil , AdvertenciaEstadoCivilPropietario , Fecha_de_Nacimiento , AdvertenciaFechaNacimientoPropietario , Telefono , AdvertenciaTelefonoPropietario , Telefono1 , AdvertenciaTelefono1Propietario , Usuario , AdvertenciaCorreoPropietario , Usuario1 , AdvertenciaCorreo1Propietario , cbxPrefijoTelefonico , cbxPrefijoTelefonico1 );
        advertencias.Advertencia_Direccion_ ( cbxTerraza , AdvertenciaCasaPropietario );
        advertencias.Advertencia_Direccion_Casa ( Casa , AdvertenciaCasaPropietario );
        advertencias.Advertencia_Fecha_de_Nacimiento ( Fecha_de_Adquisicion , AdvertenciaFechaAdquisicion );
        
        if ( ( Cedula.getText ( ).isEmpty ( ) && AdvertenciaCedulaPropietario.isVisible ( ) && Cedula.getText ( ).length ( ) < 7 && Primer_Nombre.getText ( ).isEmpty ( ) && Primer_Apellido.getText ( ).isEmpty ( ) && Fecha_de_Nacimiento.getValue ( ) == null && cbxSexo.getSelectionModel ( ).getSelectedItem ( ) == null && cbxEstadoCivil.getSelectionModel ( ).getSelectedItem ( ) == null && cbxNacionalidad.getSelectionModel ( ).getSelectedItem ( ) == null && cbxTerraza.getSelectionModel ( ).getSelectedItem ( ) == null && Fecha_de_Adquisicion.getValue ( ) == null && AdvertenciaTelefonoPropietario.isVisible ( ) && AdvertenciaTelefono1Propietario.isVisible ( ) && AdvertenciaCorreoPropietario.isVisible ( ) && AdvertenciaCorreo1Propietario.isVisible ( ) && AdvertenciaCasaPropietario.isVisible ( ) && AdvertenciaFechaAdquisicion.isVisible ( ) ) || ( Cedula.getText ( ).isEmpty ( ) || Primer_Nombre.getText ( ).isEmpty ( ) || Primer_Apellido.getText ( ).isEmpty ( ) || Fecha_de_Nacimiento.getValue ( ) == null || cbxSexo.getSelectionModel ( ).getSelectedItem ( ) == null || cbxEstadoCivil.getSelectionModel ( ).getSelectedItem ( ) == null || cbxNacionalidad.getSelectionModel ( ).getSelectedItem ( ) == null || cbxTerraza.getSelectionModel ( ).getSelectedItem ( ) == null || Fecha_de_Adquisicion.getValue ( ) == null || AdvertenciaTelefonoPropietario.isVisible ( ) || AdvertenciaTelefono1Propietario.isVisible ( ) || AdvertenciaCorreoPropietario.isVisible ( ) || AdvertenciaCorreo1Propietario.isVisible ( ) || AdvertenciaCasaPropietario.isVisible ( ) || AdvertenciaFechaAdquisicion.isVisible ( ) || AdvertenciaCedulaPropietario.isVisible ( ) || Cedula.getText ( ).length ( ) < 7 ) ) {
            
            PaneVerificacionObligatoriaPropietario.setVisible ( true );
            PaneCubiertaDatosPropietario.setVisible ( true );
            
        } else {
            
            PanePregunta.setVisible ( true );
            PaneCubiertaDatosPropietario.setVisible ( true );
            
        }
        
    }
    
    public void Fecha_De_Nacimiento ( DatePicker Fecha_de_Nacimiento ) {
        
        Fecha_de_Nacimiento.setOnAction ( new EventHandler < ActionEvent > ( ) {
            @Override
            public void handle ( ActionEvent event ) {
                
                LocalDate ahora = LocalDate.now ( );
                Period periodo = Period.between ( Fecha_de_Nacimiento.getValue ( ) , ahora );
                
                V.Permitido ( periodo.getYears ( ) , Fecha_de_Nacimiento );
                
            }
        } );
    }
    
    public void Fecha_De_Adquisicion ( ) {
        
        Fecha_de_Adquisicion.setOnAction ( new EventHandler < ActionEvent > ( ) {
            @Override
            public void handle ( ActionEvent event ) {
                
                LocalDate inicio = LocalDate.of ( 2010 , 01 , 01 );
                boolean valido = inicio.isAfter ( Fecha_de_Adquisicion.getValue ( ) );
                
                LocalDate ahora = LocalDate.now ( );
                boolean valido1 = ahora.isBefore ( Fecha_de_Adquisicion.getValue ( ) );
                
                V.Permitido_Fecha_Adquisicion ( valido , valido1 , Fecha_de_Adquisicion );
                
            }
        } );
        
    }
    
    public void Fecha_De_Cuota_Ordinaria ( ) {
        
        Fecha_Establecida_Cuota_Ordinaria.setOnAction ( new EventHandler < ActionEvent > ( ) {
            @Override
            public void handle ( ActionEvent event ) {
                
                Cuota_Ordinaria Cuota_Ordinaria = new Cuota_Ordinaria ( );
                java.util.Date Ultima_Fecha_Establecida = Cuota_Ordinaria.Ultima_Cuota ( obj.getConnection ( ) , Date.valueOf ( Fecha_Establecida_Cuota_Ordinaria.getValue ( ) ) , Fecha_Establecida_Cuota_Ordinaria , PaneNotificacionCuotaOrdinaria );
                
                if ( Ultima_Fecha_Establecida != null ) {
                    
                    LocalDate ahora = LocalDate.now ( );
                    boolean valido1 = ahora.isBefore ( Fecha_Establecida_Cuota_Ordinaria.getValue ( ) );
                    
                    
                    float Diferencia = Cuota_Ordinaria.Diferencia_entre_Fechas ( obj.getConnection ( ) , Date.valueOf ( Fecha_Establecida_Cuota_Ordinaria.getValue ( ) ) );
                    
                    if ( Diferencia < 0.987 ) {
                        
                        Fecha_Establecida_Cuota_Ordinaria.setValue ( null );
                        PaneNotificacionCuotaOrdinaria.setVisible ( true );
                        
                    }
                    
                    C.Permitido ( valido1 , Fecha_Establecida_Cuota_Ordinaria , PaneNotificacionCuotaOrdinaria1 );
                    
                }
            }
        } );
    }
    
    public void Fecha_De_Cuota_Extraordinaria ( ) {
        
        Fecha_Cuota_Extraordinaria.setOnAction ( new EventHandler < ActionEvent > ( ) {
            @Override
            public void handle ( ActionEvent event ) {
                
                Cuota_Extraordinaria Cuota_Extraordinaria = new Cuota_Extraordinaria ( );
                java.util.Date Ultima_Fecha_Establecida = Cuota_Extraordinaria.Ultima_Cuota_Establecida ( obj.getConnection ( ) , Date.valueOf ( Fecha_Cuota_Extraordinaria.getValue ( ) ) , Fecha_Cuota_Extraordinaria );
                
                if ( Ultima_Fecha_Establecida != null ) {
                    
                    LocalDate ahora = LocalDate.now ( );
                    boolean valido1 = ahora.isBefore ( Fecha_Cuota_Extraordinaria.getValue ( ) );
                    
                    E.Permitido ( valido1 , Fecha_Cuota_Extraordinaria );
                    
                }
            }
        } );
    }
    
    @FXML
    public void enBotonSIDatosPropietario ( ActionEvent event ) {
        
        //apertura de conexion con base de datos basecon
        Conexion obj = new Conexion ( );//instancia un objeto de la clase Conexion
        Connection Conexion = obj.getConnection ( );//instancia un metodo de tipo Conexion el cual proviene de la interfaz Connection -> libreria java.SQL
        
        Verificar_Existencia_Propietario ( obj.getConnection ( ) );
        Verificacion_Existencia_Persona ( obj.getConnection ( ) );
        
        PanePregunta.setVisible ( false );
        PaneCubiertaDatosPropietario.setVisible ( false );
        PaneExito.setVisible ( true );
        
        TablaPropiedades ( Integer.valueOf ( Cedula.getText ( ) ) );
        RegistroSeleccionado ( );
        
        
    }
    
    public void Verificacion_Existencia_De_Inmueble ( Connection Conexion ) {
        
        Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble (
                
                parseInt ( Cedula.getText ( ) ) ,
                Date.valueOf ( Fecha_de_Adquisicion.getValue ( ) ) ,
                parseInt ( Casa.getText ( ) ) ,
                cbxTerraza.getSelectionModel ( ).getSelectedItem ( )
        
        );//se intancia un objeto de la clase Propietario_Tiene_Inmueble
        
        Inmueble_Esta_Ubicado_En_Terraza Inmueble_Terraza = new Inmueble_Esta_Ubicado_En_Terraza (
                
                Propietario_Inmueble.Buscar_ID_del_Inmueble ( obj.getConnection ( ) ) ,
                cbxTerraza.getSelectionModel ( ).getSelectedItem ( )
        
        );
        
        int Resultado_Verificacion_Inmueble_Existe_En_Terraza = Inmueble_Terraza.Verificacion_Inmueble_Existe_En_Terraza ( obj.getConnection ( ) );
        
        if ( Resultado_Verificacion_Inmueble_Existe_En_Terraza == 0 ) {
            
            PaneInmuebleNoExiste.setVisible ( true );
            
        }
        
    }
    
    public void Verificacion_Inmueble_Dueño ( Connection Conexion ) {
        
        Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble (
                
                parseInt ( Cedula.getText ( ) ) ,
                Date.valueOf ( Fecha_de_Adquisicion.getValue ( ) ) ,
                parseInt ( Casa.getText ( ) ) ,
                cbxTerraza.getSelectionModel ( ).getSelectedItem ( )
        
        );//se intancia un objeto de la clase Propietario_Tiene_Inmueble
        
        int Resultado_Verificacion = Propietario_Inmueble.Verificar_si_el_inmueble_ingresado_ya_tiene_dueño ( Conexion );
        
        if ( Resultado_Verificacion == 1 ) {
            
            PaneInmuebleOcupado.setVisible ( true );
            PaneActualizacion1.setVisible ( false );
            PaneExito.setVisible ( false );
            PanePregunta.setVisible ( false );
            
        }
        
    }
    
    
    public void Verificar_Existencia_Propietario ( Connection Conexion ) {
        
        Propietario Datos_del_Propietario = new Propietario (
                cbxNacionalidad.getSelectionModel ( ).getSelectedItem ( ) ,
                parseInt ( Cedula.getText ( ) ) ,
                Primer_Nombre.getText ( ) ,
                Segundo_Nombre.getText ( ) ,
                Primer_Apellido.getText ( ) ,
                Segundo_Apellido.getText ( ) ,
                Date.valueOf ( Fecha_de_Nacimiento.getValue ( ) ) ,
                cbxSexo.getSelectionModel ( ).getSelectedItem ( ) ,
                cbxEstadoCivil.getSelectionModel ( ).getSelectedItem ( ) ,
                0
        
        );//se instancia un objeto de la clase Propietario
        
        Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble (
                
                parseInt ( Cedula.getText ( ) ) ,
                Date.valueOf ( Fecha_de_Adquisicion.getValue ( ) ) ,
                parseInt ( Casa.getText ( ) ) ,
                cbxTerraza.getSelectionModel ( ).getSelectedItem ( )
        
        );//se intancia un objeto de la clase Propietario_Tiene_Inmueble
        
        
        Inmueble_Esta_Ubicado_En_Terraza Inmueble_Terraza = new Inmueble_Esta_Ubicado_En_Terraza (
                
                Propietario_Inmueble.Buscar_ID_del_Inmueble ( obj.getConnection ( ) ) ,
                cbxTerraza.getSelectionModel ( ).getSelectedItem ( )
        
        );
        
        int Verificacion = Datos_del_Propietario.Verificar_Existencia_de_Propietario ( Conexion );
        
        if ( Verificacion == 1 ) {
            
            Propietario_Inmueble.Registrar ( obj.getConnection ( ) );
            Actualizar_Cantidad_De_Casas ( obj.getConnection ( ) );
            
        } else {
            
            int ResultadoRegistrarPropietario = Datos_del_Propietario.Registrar ( obj.getConnection ( ) );
            
            if ( ResultadoRegistrarPropietario == 1 ) {
                
                Propietario_Inmueble.Registrar ( obj.getConnection ( ) );
                Datos_del_Propietario.Actualizacion_De_Cantidad_De_Casas ( obj.getConnection ( ) );
                
            }
        }
    }
    
    public void Verificacion_Existencia_Persona ( Connection Conexion ) {
        
        ArrayList < Integer > ids_Telefonos = new ArrayList <> ( );
        ObservableList < Integer > Ids_Telefonos = FXCollections.observableArrayList ( ids_Telefonos );
        
        Propietario Datos_del_Propietario = new Propietario (
                cbxNacionalidad.getSelectionModel ( ).getSelectedItem ( ) ,
                parseInt ( Cedula.getText ( ) ) ,
                Primer_Nombre.getText ( ) ,
                Segundo_Nombre.getText ( ) ,
                Primer_Apellido.getText ( ) ,
                Segundo_Apellido.getText ( ) ,
                Date.valueOf ( Fecha_de_Nacimiento.getValue ( ) ) ,
                cbxSexo.getSelectionModel ( ).getSelectedItem ( ) ,
                cbxEstadoCivil.getSelectionModel ( ).getSelectedItem ( ) ,
                0
        
        );//se instancia un objeto de la clase Propietario
        
        Persona_Puede_Poseer_Telefono Persona_Telefono = new Persona_Puede_Poseer_Telefono ( Integer.parseInt ( Cedula.getText ( ) ) );
        
        int ResultadoVerificacionDePersonaPropietario = Datos_del_Propietario.Verificar_Existencia_de_Propietario ( obj.getConnection ( ) );
        
        if ( ResultadoVerificacionDePersonaPropietario == 1 ) {
            
            Persona_Telefono.Buscar_Telefono ( obj.getConnection ( ) , Ids_Telefonos );
            
            if ( Ids_Telefonos.isEmpty ( ) ) {
                
                Registro_de_Telefonos ( obj.getConnection ( ) , Cedula , cbxPrefijoTelefonico , Telefono , cbxPrefijoTelefonico1 , Telefono1 );
                Registro_de_Correos ( obj.getConnection ( ) , Cedula , Usuario , Usuario1 );
                
            }
        }
    }
    
    public void Actualizar_Cantidad_De_Casas ( Connection Conexion ) {
        
        Propietario Datos_del_Propietario = new Propietario (
                cbxNacionalidad.getSelectionModel ( ).getSelectedItem ( ) ,
                parseInt ( Cedula.getText ( ) ) ,
                Primer_Nombre.getText ( ) ,
                Segundo_Nombre.getText ( ) ,
                Primer_Apellido.getText ( ) ,
                Segundo_Apellido.getText ( ) ,
                Date.valueOf ( Fecha_de_Nacimiento.getValue ( ) ) ,
                cbxSexo.getSelectionModel ( ).getSelectedItem ( ) ,
                cbxEstadoCivil.getSelectionModel ( ).getSelectedItem ( ) ,
                0
        
        );//se instancia un objeto de la clase Propietario
        
        int Actualizacion = Datos_del_Propietario.Actualizacion_De_Cantidad_De_Casas ( obj.getConnection ( ) );
        
        if ( Actualizacion == 1 ) {
            
            TablaPropiedades ( Integer.valueOf ( Cedula.getText ( ) ) );
            PaneActualizacion1.setVisible ( true );
            PanePregunta.setVisible ( false );
            
        }
    }
    
    public void Registro_de_Telefonos ( Connection Conexion , TextField Cedula , ComboBox < String > Prefijo , TextField Telefono , ComboBox < String > Prefijo1 , TextField Telefono1 ) {
        
        Telefono TLF1_Datos_Propietario = new Telefono (
                Prefijo.getSelectionModel ( ).getSelectedItem ( ) ,
                Telefono.getText ( )
        );
        
        int ResultadoRegistroTelefono1 = TLF1_Datos_Propietario.RegistrarTelefono ( Conexion );
        
        if ( ResultadoRegistroTelefono1 == 1 ) {
            
            Persona_Puede_Poseer_Telefono Persona_Telefono1 = new Persona_Puede_Poseer_Telefono ( parseInt ( Cedula.getText ( ) ) );
            Persona_Telefono1.Registrar ( Conexion );
            
        }
        
        Telefono TLF2_Datos_Propietario = new Telefono (
                Prefijo1.getSelectionModel ( ).getSelectedItem ( ) ,
                Telefono1.getText ( )
        );
        
        int ResultadoRegistroTelefono2 = TLF2_Datos_Propietario.RegistrarTelefono ( Conexion );
        
        if ( ResultadoRegistroTelefono2 == 1 ) {
            
            Persona_Puede_Poseer_Telefono Persona_Telefono2 = new Persona_Puede_Poseer_Telefono (
                    parseInt ( Cedula.getText ( ) )
            );
            
            Persona_Telefono2.Registrar ( Conexion );
            
        }
    }
    
    public void Registro_de_Correos ( Connection Conexion , TextField Cedula , TextField Usuario , TextField Usuario1 ) {
        
        Correo Correo_Electronico1_Datos_Propietario = new Correo (
                Usuario.getText ( )
        );
        
        int ResultadoRegistroCorreo1 = Correo_Electronico1_Datos_Propietario.RegistrarCorreo ( Conexion );
        
        if ( ResultadoRegistroCorreo1 == 1 ) {
            
            Persona_Puede_Tener_Correo Persona_Correo1 = new Persona_Puede_Tener_Correo (
                    parseInt ( Cedula.getText ( ) )
            );
            
            Persona_Correo1.Registrar ( Conexion );
            
        }
        
        Correo Correo_Electronico2_Datos_Propietario = new Correo (
                Usuario1.getText ( )
        );
        
        int ResultadoRegistroCorreo2 = Correo_Electronico2_Datos_Propietario.RegistrarCorreo ( Conexion );
        
        if ( ResultadoRegistroCorreo2 == 1 ) {
            
            Persona_Puede_Tener_Correo Persona_Correo2 = new Persona_Puede_Tener_Correo (
                    parseInt ( Cedula.getText ( ) )
            );
            
            Persona_Correo2.Registrar ( Conexion );
            
        }
        
    }
    
    @FXML
    public void enBotonAceptar ( ActionEvent event ) {
        
        PaneExito.setVisible ( false );
        PaneInmuebleOcupado.setVisible ( false );
        PaneActualizacion1.setVisible ( false );
        PaneExito1.setVisible ( false );
        PaneExito11.setVisible ( false );
        PaneInmuebleNoExiste.setVisible ( false );
        PaneVerificacionObligatoriaCuota_Ordinaria.setVisible ( false );
        PaneExitoRegistroCuota_Ordinaria.setVisible ( false );
        PaneVerificacionObligatoriaServicios.setVisible ( false );
        PaneNotificacionCuotaOrdinaria.setVisible ( false );
        PaneVerificacionObligatoriaCuotaExtraordinaria.setVisible ( false );
        PaneExitoCuotaExtraordinaria.setVisible ( false );
        PaneVerificacionObligatoriaAdministrador.setVisible ( false );
        PaneExitoPagoCuotas.setVisible ( false );
        PaneCubiertaPagoDeCuotas.setVisible ( false );
        PaneExitoAgregarServicio.setVisible ( false );
        PaneExitoModificarServicio.setVisible ( false );
        PaneExitoEliminarServicio.setVisible ( false );
        PaneCubiertaCuotaOrdinaria.setVisible ( false );
        PaneCubiertaCuotaExtraordinaria.setVisible ( false );
        PaneExitoEliminarArticulo.setVisible ( false );
        PaneExitoModificarArticulo.setVisible ( false );
        PaneExitoAgregarArticulo.setVisible ( false );
        PaneCubiertaArticulo.setVisible ( false );
        PaneVerificacionObligatoriaArticulos.setVisible ( false );
        PaneCantidadDisponible.setVisible ( false );
        PaneNotificacionCuotaOrdinaria1.setVisible ( false );
        PaneCubiertaUsuario.setVisible ( false );
        PaneExitoAgregarUsuario.setVisible ( false );
        PaneExitoModificarUsuario.setVisible ( false );
        PaneExitoEliminarUsuario.setVisible ( false );
        PaneCubiertaDatosPropietario.setVisible ( false );
        PaneVerificacionObligatoriaPago.setVisible ( false );
        PaneVerificacionObligatoriaPropietario.setVisible ( false );
        
    }
    
    @FXML
    public void enBotonNODatosPropietario ( ActionEvent event ) {
        
        PanePregunta.setVisible ( false );
        PaneCubiertaDatosPropietario.setVisible ( false );
        
    }
    
    @FXML
    public void enBotonAgregarServicio ( ActionEvent event ) {
        
        if ( Costo.getLength ( ) != 0 && Nombre_del_Servicio.getLength ( ) != 0 ) {
            
            PanePreguntaServicios.setVisible ( true );
            PaneCubiertaCuotaOrdinaria.setVisible ( true );
            
        } else {
            
            PaneVerificacionObligatoriaServicios.setVisible ( true );
            
            
        }
    }
    
    @FXML
    public void enBotonAceptarServicios ( ActionEvent event ) {
        
        PaneExitoServicios.setVisible ( false );
        PaneCubiertaCuotaOrdinaria.setVisible ( false );
        
    }
    
    @FXML
    public void enBotonModificarServicio ( ActionEvent event ) {
        
        PanePreguntaServiciosModificar.setVisible ( true );
        PaneCubiertaCuotaOrdinaria.setVisible ( true );
        
    }
    
    @FXML
    public void enBotonSIServiciosModificar ( ActionEvent event ) {
        
        Conexion obj = new Conexion ( );
        
        Servicios Servicio = new Servicios ( parseInt ( ID_Servicio.getText ( ) ) , Nombre_del_Servicio.getText ( ) , Descripcion.getText ( ) , parseInt ( Costo.getText ( ) ) );
        
        int ResultadoDeModificacionDelServicio = Servicio.Modificar ( obj.getConnection ( ) );
        
        if ( ResultadoDeModificacionDelServicio == 1 ) {
            
            PanePreguntaServiciosModificar.setVisible ( false );
            PaneExitoModificarServicio.setVisible ( true );
            
            TablaServicios ( );
            
            Label_Monto_Establecido_Cuota_Ordinaria.setText ( String.valueOf ( Servicio.CalcularTotal ( obj.getConnection ( ) ) ) );
            
        }
        
    }
    
    @FXML
    public void enBotonNOServiciosModificar ( ActionEvent event ) {
        
        PanePreguntaServiciosModificar.setVisible ( false );
        PaneCubiertaCuotaOrdinaria.setVisible ( false );
        
    }
    
    @FXML
    public void enBotonSIServicios ( ActionEvent event ) {
        
        Conexion obj = new Conexion ( );
        
        Servicios Servicio = new Servicios ( 0 , Nombre_del_Servicio.getText ( ) , Descripcion.getText ( ) , parseInt ( Costo.getText ( ) ) );
        
        int ResultadoDelRegistroDeServicio = Servicio.Agregar ( obj.getConnection ( ) );
        
        if ( ResultadoDelRegistroDeServicio == 1 ) {
            
            PanePreguntaServicios.setVisible ( false );
            PaneExitoAgregarServicio.setVisible ( true );
            
            TablaServicios ( );
            Label_Monto_Establecido_Cuota_Ordinaria.setText ( String.valueOf ( Servicio.CalcularTotal ( obj.getConnection ( ) ) ) );
            
        }
        
    }
    
    @FXML
    void enBotonAceptarServiciosModificado ( ActionEvent event ) {
        
        PaneExitoServiciosModificado.setVisible ( false );
        PaneCubiertaCuotaOrdinaria.setVisible ( false );
    }
    
    @FXML
    public void enBotonNOServicios ( ActionEvent event ) {
        
        PanePreguntaServicios.setVisible ( false );
        PaneCubiertaCuotaOrdinaria.setVisible ( false );
    }
    
    @FXML
    public void enBotonEliminarServicio ( ActionEvent event ) {
        
        
        PanePreguntaServiciosEliminar.setVisible ( true );
        PaneCubiertaCuotaOrdinaria.setVisible ( true );
        
    }
    
    
    @FXML
    public void enBotonSIServiciosEliminar ( ActionEvent event ) {
        
        PanePreguntaServiciosEliminar.setVisible ( false );
        
        Conexion obj = new Conexion ( );
        
        Servicios Servicio = new Servicios ( parseInt ( ID_Servicio.getText ( ) ) );
        
        int ResultadoDeEliminacionDelServicio = Servicio.Eliminar ( obj.getConnection ( ) );
        
        if ( ResultadoDeEliminacionDelServicio == 1 ) {
            
            PanePreguntaServiciosEliminar.setVisible ( false );
            PaneExitoEliminarServicio.setVisible ( true );
            
            TablaServicios ( );
            
            Label_Monto_Establecido_Cuota_Ordinaria.setText ( String.valueOf ( Servicio.CalcularTotal ( obj.getConnection ( ) ) ) );
            
        }
        
    }
    
    @FXML
    public void enBotonNOServiciosEliminar ( ActionEvent event ) {
        
        PanePreguntaServiciosEliminar.setVisible ( false );
        PaneCubiertaCuotaOrdinaria.setVisible ( false );
    }
    
    @FXML
    private void enBotonAgregarCuotaOrdinaria ( ActionEvent event ) {
        
        if ( Fecha_Establecida_Cuota_Ordinaria.getValue ( ) == null ) {
            
            PaneVerificacionObligatoriaCuota_Ordinaria.setVisible ( true );
            
        } else {
            
            PanePreguntaAgregarCuota_Ordinaria.setVisible ( true );
            PaneCubiertaCuotaOrdinaria.setVisible ( true );
            
        }
    }
    
    
    public void enBotonSIAgregarCuota_Ordinaria ( ActionEvent event ) {
        
        Conexion obj = new Conexion ( );
        Cuota_Ordinaria cuota_ordinaria = new Cuota_Ordinaria ( );
        int Verificacion = cuota_ordinaria.Verificar_Existencia_de_Cuota ( obj.getConnection ( ) , Date.valueOf ( Fecha_Establecida_Cuota_Ordinaria.getValue ( ) ) );
        
        if ( Verificacion == 0 ) {
            
            int Resultado_Registro = cuota_ordinaria.Registrar ( obj.getConnection ( ) , Date.valueOf ( Fecha_Establecida_Cuota_Ordinaria.getValue ( ) ) );
            
            if ( Resultado_Registro == 1 ) {
                
                Cuota_Ordinaria_Corresponde_A_Servicios cuota_servicios = new Cuota_Ordinaria_Corresponde_A_Servicios ( cuota_ordinaria.getId_CuotaOrdinaria ( ) );
                cuota_servicios.Registrar ( obj.getConnection ( ) );
                PaneExitoRegistroCuota_Ordinaria.setVisible ( true );
                TablaCuotaOrdinaria ( );
                TablaTotalCuotaOrdinaria ( );
                
            }
        }
        
        PanePreguntaAgregarCuota_Ordinaria.setVisible ( false );
        PaneCubiertaCuotaOrdinaria.setVisible ( false );
    }
    
    @FXML
    public void enBotonAgregarCuotaExtraordinaria ( ActionEvent event ) {
        
        if ( ( Nombre_Cuota_Extraordinaria.getText ( ).isEmpty ( ) && Fecha_Cuota_Extraordinaria.getValue ( ) == null && Costo_Cuota_Extraordinaria.getText ( ).isEmpty ( ) ) || ( Nombre_Cuota_Extraordinaria.getText ( ).isEmpty ( ) || Fecha_Cuota_Extraordinaria.getValue ( ) == null || Costo_Cuota_Extraordinaria.getText ( ).isEmpty ( ) ) ) {
            
            PaneVerificacionObligatoriaCuotaExtraordinaria.setVisible ( true );
            
        } else {
            
            PaneCubiertaCuotaExtraordinaria.setVisible ( true );
            PanePreguntaAgregarCuotaExtraordinaria.setVisible ( true );
            
        }
    }
    
    @FXML
    public void enBusquedaCuotaOrdinaria ( KeyEvent event ) {
    
    
    }
    
    @FXML
    public void enBusquedaServicios ( KeyEvent event ) {
        
        Conexion obj = new Conexion ( );
        
        ArrayList < Servicios > listaServicios = new ArrayList <> ( );
        
        ObservableList < Servicios > listaObservable = FXCollections.observableArrayList ( listaServicios );
        
        
        Servicios s = new Servicios ( );
        s.LLenarTablaDeServicios ( obj.getConnection ( ) , listaObservable );
        listaServicios.add ( s );
        
        
        FilteredList filtro = new FilteredList ( listaObservable , v -> true );
        
        
        Busqueda_Servicios.textProperty ( ).addListener ( ( ( observable , oldValue , newValue ) -> {
            
            
            filtro.setPredicate ( ( Predicate < ? super Servicios > ) ( Servicios servicios ) -> {
                
                if ( newValue.isEmpty ( ) || newValue == null ) {
                    
                    return true;
                    
                }
                
                if ( servicios.getTipo_de_Servicio ( ).contains ( newValue ) ) {
                    
                    return true;
                }
                return false;
                
                
            } );
            
        } ) );
        
        SortedList sort = new SortedList ( filtro );
        sort.comparatorProperty ( ).bind ( TablaServicios.comparatorProperty ( ) );
        TablaServicios.setItems ( sort );
    }
    
    @FXML
    public void enBusquedaCuotaExtraordinaria ( KeyEvent event ) {
        
        Conexion obj = new Conexion ( );
        
        ArrayList < Cuota_Extraordinaria > listaCuotaExtraordinaria = new ArrayList < Cuota_Extraordinaria > ( );
        
        ObservableList < Cuota_Extraordinaria > listaObservable = FXCollections.observableArrayList ( listaCuotaExtraordinaria );
        
        
        Cuota_Extraordinaria e = new Cuota_Extraordinaria ( );
        e.LlenarTablaCuotaExtraordinaria ( obj.getConnection ( ) , listaObservable );
        listaCuotaExtraordinaria.add ( e );
        
        
        FilteredList filtro = new FilteredList ( listaObservable , v -> true );
        
        
        Busqueda_Cuota_Extraordinaria.textProperty ( ).addListener ( ( ( observable , oldValue , newValue ) -> {
            
            
            filtro.setPredicate ( ( Predicate < ? super Cuota_Extraordinaria > ) ( Cuota_Extraordinaria cuota_extraordinaria ) -> {
                
                if ( newValue.isEmpty ( ) || newValue == null ) {
                    
                    return true;
                    
                }
                
                if ( cuota_extraordinaria.getTipo_de_CuotaExtraordinaria ( ).contains ( newValue ) ) {
                    
                    return true;
                }
                return false;
                
                
            } );
            
        } ) );
        
        SortedList sort = new SortedList ( filtro );
        sort.comparatorProperty ( ).bind ( TablaCuotaExtraordinaria.comparatorProperty ( ) );
        TablaCuotaExtraordinaria.setItems ( sort );
        
    }
    
    @FXML
    public void enBotonSIAgregarCuotaExtraordinaria ( ActionEvent event ) {
        
        PanePreguntaAgregarCuotaExtraordinaria.setVisible ( false );
        
        Conexion obj = new Conexion ( );
        
        Cuota_Extraordinaria cuota = new Cuota_Extraordinaria ( 0 , Nombre_Cuota_Extraordinaria.getText ( ) , Descripcion_Cuota_Extraordinaria.getText ( ) , Date.valueOf ( Fecha_Cuota_Extraordinaria.getValue ( ) ) , parseInt ( Costo_Cuota_Extraordinaria.getText ( ) ) );
        
        int ResultadoDeRegistroDeCuotaExtraordinaria = cuota.Agregar ( obj.getConnection ( ) );
        
        if ( ResultadoDeRegistroDeCuotaExtraordinaria == 1 ) {
            
            PaneExitoCuotaExtraordinaria.setVisible ( true );
            TablaCuotaExtraordinaria ( );
            TablaTotalCuotaExtraordinaria ( );
            
        }
    }
    
    public void enBotonNOAgregarCuotaExtraordinaria ( ActionEvent event ) {
        
        PanePreguntaAgregarCuotaExtraordinaria.setVisible ( false );
        
    }
    
    public void enBotonAgregarPago ( ActionEvent event ) {
        
        Advertencias advertencia = new Advertencias ( );
        advertencia.Advertencia_Estado_Civil ( cbxIdsCasaPago , AdvertenciaDireccionPagoCuota );
        if ( CedulaPago.getText ( ).isEmpty ( ) && AdvertenciaDireccionPagoCuota.isVisible ( ) && CedulaPago.getText ( ).length ( ) < 7 && cbxIdsCasaPago.getSelectionModel ( ).getSelectedItem ( ) == null || CedulaPago.getText ( ).isEmpty ( ) && cbxIdsCasaPago.getSelectionModel ( ).getSelectedItem ( ) == null || AdvertenciaDireccionPagoCuota.isVisible ( ) || CedulaPago.getText ( ).length ( ) < 7 ) {
            
            PaneCubiertaPagoDeCuotas.setVisible ( true );
            PaneVerificacionObligatoriaPago.setVisible ( true );
            
        } else {
            
            PanePreguntaAgregarPagoCuotaOrdinaria.setVisible ( true );
            PaneCubiertaPagoDeCuotas.setVisible ( true );
            
        }
    }
    
    public void enBotonSIAgregarPago ( ObservableList < Integer > id_cuota , ObservableList < Date > cuotas , ObservableList < Long > montos , ObservableList < Integer > id_extra , ObservableList < Date > fecha_extra , ObservableList < Long > montos_extra , Integer id_casa ) {
        
        
        BotonAgregarPago.setOnMouseClicked ( new EventHandler < MouseEvent > ( ) {
            
            int Registro = 0;
            
            @Override
            public void handle ( MouseEvent event ) {
                
                PaneCubiertaPagoDeCuotas.setVisible ( false );
                PanePreguntaAgregarPagoCuotaOrdinaria.setVisible ( false );
                int Registro1 = 0;
                Conexion obj = new Conexion ( );
                Propietario_Cancela_Cuota_Ordinaria Propietario_Cuota = new Propietario_Cancela_Cuota_Ordinaria ( parseInt ( CedulaPago.getText ( ) ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , parseInt ( numero_terraza.getText ( ) ) , id_casa );
                Propietario_Paga_Cuota_Extraordinaria Propietario_Extra = new Propietario_Paga_Cuota_Extraordinaria ( parseInt ( CedulaPago.getText ( ) ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , parseInt ( numero_terraza.getText ( ) ) , id_casa );
                
                for ( int i = 0; i < id_cuota.size ( ); i++ ) {
                    
                    Registro = Propietario_Cuota.Registrar ( obj.getConnection ( ) , id_cuota.get ( i ) , cuotas.get ( i ) , montos.get ( i ) );
                    Long suma = montos.get ( i );
                    long resta = parseInt ( LabelTotalAPagar.getText ( ) ) - suma;
                    
                    LabelTotalAPagar.setText ( String.valueOf ( resta ) );
                    
                    System.out.println ( id_cuota.size ( ) );
                    
                }
                
                
                if ( Registro == 1 ) {
                    
                    TablaHistorialCanceladoCuotaOrdinaria ( cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , Integer.parseInt ( numero_terraza.getText ( ) ) );
                    LabelDeudaCuotaOrdinaria.setVisible ( false );
                    LabelSolventeCuota_Ordinaria.setVisible ( true );
                    LabelSolventeCuota_Ordinaria.setText ( "SOLVENTE" );
                    Visualizar.setVisible ( false );
                    Visualizar2.setVisible ( false );
                    PanePreguntaAgregarPagoCuotaOrdinaria.setVisible ( false );
                    PaneExitoPagoCuotas.setVisible ( true );
                    PaneCubiertaPagoDeCuotas.setVisible ( true );
                    Reportes reportePago = new Reportes ();
                    try {
                        
                        String cuotaHasta = String.valueOf (cuotas.get ( cuotas.size () - 1) );
                        String cuotadesde = String.valueOf ( cuotas.get ( 0 ) );
                        reportePago.reportePagoDeCuotas (  cuotadesde, cuotaHasta, Integer.valueOf ( CedulaPago.getText () ) );
                        System.out.println ( cuotadesde + cuotaHasta );
                        
                    } catch ( JRException e ) {
                        e.printStackTrace ( );
                    }
                    
                }
                
                for ( int i = 0; i < id_extra.size ( ); i++ ) {
                    
                    Registro1 = Propietario_Extra.Registrar ( obj.getConnection ( ) , id_extra.get ( i ) , fecha_extra.get ( i ) , montos_extra.get ( i ) );
                    Long suma1 = montos_extra.get ( i );
                    long resta1 = parseInt ( LabelTotalAPagar.getText ( ) ) - suma1;
                    LabelTotalAPagar.setText ( String.valueOf ( resta1 ) );
                    
                    
                    if ( Registro1 == 1 ) {
                        
                        TablaHistorialCanceladoCuotaExtra ( cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , Integer.parseInt ( numero_terraza.getText ( ) ) );
                        LabelDeudaCuotaExtraordinaria.setVisible ( false );
                        LabelSolvente.setVisible ( true );
                        LabelSolvente.setText ( "SOLVENTE" );
                        PaneExitoPagoCuotas.setVisible ( true );
                        PaneCubiertaPagoDeCuotas.setVisible ( true );
                        
                    } else {
                        
                        System.out.println ( "nada" );
                        
                        
                    }
                }
                
                if ( Registro == 1 && Registro1 == 1 ) {
                    
                    PaneExitoPagoCuotas.setVisible ( true );
                    PaneCubiertaPagoDeCuotas.setVisible ( true );
                    
                }
    
    
                
            }
        } );
    
        
    }
    
    
    public void enBotonNOAgregarPago ( ActionEvent event ) {
        
        PanePreguntaAgregarPagoCuotaOrdinaria.setVisible ( false );
        PaneCubiertaPagoDeCuotas.setVisible ( false );
        
    }
    
    public void enBotonModificarDatosDelPropietario ( MouseEvent event ) {
        
        PanePreguntaDatosModificar.setVisible ( true );
        PaneCubiertaDatosPropietario.setVisible ( true );
        
    }
    
    public void enBotonSIDatosPropietarioModificar ( ActionEvent event ) {
        
        Conexion obj = new Conexion ( );
        Propietario Propietario = new Propietario (
                cbxNacionalidad.getSelectionModel ( ).getSelectedItem ( ) ,
                parseInt ( Cedula.getText ( ) ) ,
                Primer_Nombre.getText ( ) ,
                Segundo_Nombre.getText ( ) ,
                Primer_Apellido.getText ( ) ,
                Segundo_Apellido.getText ( ) ,
                Date.valueOf ( Fecha_de_Nacimiento.getValue ( ) ) ,
                cbxSexo.getSelectionModel ( ).getSelectedItem ( ) ,
                cbxEstadoCivil.getSelectionModel ( ).getSelectedItem ( ) ,
                0
        );
        
        int ResultadoDeLaModificacion = Propietario.Modificar_Datos ( obj.getConnection ( ) );
        
        if ( ResultadoDeLaModificacion == 1 ) {
            
            Modificar_Telefono ( Cedula , cbxPrefijoTelefonico , cbxPrefijoTelefonico1 , Telefono , Telefono1 );
            Modificar_Correo ( Cedula , cbxDominio , cbxDominio1 , Usuario , Usuario1 );
            PanePreguntaDatosModificar.setVisible ( false );
            PaneCubiertaDatosPropietario.setVisible ( false );
            PaneExito1.setVisible ( true );
            
        }
    }
    
    public void enBotonNODatosPropietarioModificar ( ActionEvent event ) {
        
        PaneCubiertaDatosPropietario.setVisible ( false );
        PanePreguntaDatosModificar.setVisible ( false );
        
    }
    
    public void enBotonDeshabilitarPropietario ( ActionEvent event ) {
        
        if ( ( Cedula.getText ( ).isEmpty ( ) && Primer_Nombre.getText ( ).isEmpty ( ) && Primer_Apellido.getText ( ).isEmpty ( ) && Fecha_de_Nacimiento.getValue ( ) == null && cbxSexo.getSelectionModel ( ).getSelectedItem ( ) == null && cbxEstadoCivil.getSelectionModel ( ).getSelectedItem ( ) == null && cbxNacionalidad.getSelectionModel ( ).getSelectedItem ( ) == null && cbxTerraza.getSelectionModel ( ).getSelectedItem ( ) == null && Fecha_de_Adquisicion.getValue ( ) == null ) || ( Cedula.getText ( ).isEmpty ( ) || Primer_Nombre.getText ( ).isEmpty ( ) || Primer_Apellido.getText ( ).isEmpty ( ) || Fecha_de_Nacimiento.getValue ( ) == null || cbxSexo.getSelectionModel ( ).getSelectedItem ( ) == null || cbxEstadoCivil.getSelectionModel ( ).getSelectedItem ( ) == null || cbxNacionalidad.getSelectionModel ( ).getSelectedItem ( ) == null || cbxTerraza.getSelectionModel ( ).getSelectedItem ( ) == null || Fecha_de_Adquisicion.getValue ( ) == null ) ) {
            
            PaneVerificacionObligatoriaPropietario.setVisible ( true );
            PaneCubiertaDatosPropietario.setVisible ( true );
            
        } else {
            
            PanePreguntaDatosEliminar.setVisible ( true );
            PaneCubiertaDatosPropietario.setVisible ( true );
            
        }
    }
    
    public void enBotonSIDatosPropietarioEliminar ( ActionEvent event ) {
        
        PanePreguntaDatosEliminar.setVisible ( false );
        PaneCubiertaDatosPropietario.setVisible ( false );
        
        Conexion obj = new Conexion ( );
        Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble ( parseInt ( Cedula.getText ( ) ) , parseInt ( Id_casa.getText ( ) ) );
        int ResultadoDeshabilitarPropietario = Propietario_Inmueble.Deshabilitar_Propietario ( obj.getConnection ( ) );
        
        if ( ResultadoDeshabilitarPropietario == 1 ) {
            
            PaneExito11.setVisible ( true );
            TablaPropiedades ( Integer.valueOf ( Cedula.getText ( ) ) );
            Actualizar_Cantidad_De_Casas ( obj.getConnection ( ) );
            
        } else {
            
            PaneExito11.setVisible ( true );
            TablaPropiedades ( Integer.valueOf ( Cedula.getText ( ) ) );
            Actualizar_Cantidad_De_Casas ( obj.getConnection ( ) );
            
        }
    }
    
    public void enBotonNODatosPropietarioEliminar ( ActionEvent event ) {
        
        PanePreguntaDatosEliminar.setVisible ( false );
        PaneCubiertaDatosPropietario.setVisible ( false );
        
        
    }
    
    public void Busqueda_Propietario ( KeyEvent event ) {
        
        Cedula.setOnKeyPressed ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                if ( Cedula.getText ( ).length ( ) >= 7 ) {
                    
                    PaneEnterCedulaPropietario.setVisible ( true );
                    AdvertenciaCedulaPropietario.setVisible ( false );
                    
                    if ( event.getCode ( ).equals ( KeyCode.ENTER ) ) {
                        
                        PaneEnterCedulaPropietario.setVisible ( false );
                        Llenar_Datos_Formulario_Propietario ( );
                        
                    } else {
                        
                        if ( event.getCode ( ).equals ( KeyCode.BACK_SPACE ) ) {
                            
                            PaneEnterCedulaPropietario.setVisible ( false );
                            Limpiar_Campos_Formulario ( Primer_Nombre , Segundo_Nombre , Primer_Apellido , Segundo_Apellido , Fecha_de_Nacimiento , cbxSexo , cbxNacionalidad , cbxEstadoCivil , cbxPrefijoTelefonico , cbxPrefijoTelefonico1 , Telefono , Telefono1 , cbxDominio , cbxDominio1 , Usuario , Usuario1 , dominios , usuarios , prefijo_telefonico0 , numero_telefonico0 );
                            
                        }
                    }
                } else {
                    
                    AdvertenciaCedulaPropietario.setVisible ( true );
                    
                }
            }
        } );
    }
    
    public void Llenar_Datos_Formulario_Propietario ( ) {
        
        Propietario Propietario = new Propietario ( parseInt ( Cedula.getText ( ) ) );
        Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble ( Cedula , Id_casa.getText ( ) );
        int Propietario_Existe = Propietario_Inmueble.Llenar_Campos ( obj.getConnection ( ) , parseInt ( Cedula.getText ( ) ) , Primer_Nombre , Segundo_Nombre , Primer_Apellido , Segundo_Apellido , cbxSexo , cbxNacionalidad , cbxEstadoCivil , Fecha_de_Nacimiento );
        
        if ( Propietario_Existe == 1 ) {
            
            Llenar_Campos_Telefono ( Cedula , prefijo_telefonico0 , numero_telefonico0 , cbxPrefijoTelefonico , cbxPrefijoTelefonico1 , Telefono , Telefono1 );
            Llenar_Campos_Correo ( Cedula , usuarios , Usuario , Usuario1 );
            
            TablaPropiedades ( Integer.valueOf ( Cedula.getText ( ) ) );
            RegistroSeleccionado ( );
            
            if ( ! ( Primer_Nombre.getText ( ).equals ( null ) && Primer_Apellido.getText ( ).equals ( null ) && Fecha_de_Nacimiento.getValue ( ).equals ( null ) ) ) {
                
                BotonModificarPropietario.setDisable ( false );
                
            }
            
        } else if ( Propietario_Existe == 0 ) {
            
            int Verificacion_Propietario_Existio = Propietario.Verificar_Existencia_de_Propietario ( obj.getConnection ( ) );
            
            if ( Verificacion_Propietario_Existio == 1 ) {
                
                Llenar_Campos_Telefono ( Cedula , prefijo_telefonico0 , numero_telefonico0 , cbxPrefijoTelefonico , cbxPrefijoTelefonico1 , Telefono , Telefono1 );
                Llenar_Campos_Correo ( Cedula , usuarios , Usuario , Usuario1 );
                
            } else {
                
                int Verificar_Usuario = Verificar_Existencia_Usuario ( );
                
                if ( Verificar_Usuario == 0 ) {
                    
                    Limpiar_Campos_Formulario ( Primer_Nombre , Segundo_Nombre , Primer_Apellido , Segundo_Apellido , Fecha_de_Nacimiento , cbxSexo , cbxNacionalidad , cbxEstadoCivil , cbxPrefijoTelefonico , cbxPrefijoTelefonico1 , Telefono , Telefono1 , cbxDominio , cbxDominio1 , Usuario , Usuario1 , dominios , usuarios , prefijo_telefonico0 , numero_telefonico0 );
                    
                }
            }
        }
    }
    
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
}
    
    public int Verificar_Existencia_Usuario ( ) {
        
        Usuario Usuario_Persona = new Usuario ( parseInt ( Cedula.getText ( ) ) );
        
        int Verificacion_existe_como_usuario = Usuario_Persona.Busqueda_Usuario ( obj.getConnection ( ) , Primer_Nombre , Segundo_Nombre , Primer_Apellido , Segundo_Apellido , cbxSexo , cbxNacionalidad , cbxEstadoCivil , Fecha_de_Nacimiento );
        
        if ( Verificacion_existe_como_usuario == 1 ) {
            
            Llenar_Campos_Telefono ( Cedula , prefijo_telefonico0 , numero_telefonico0 , cbxPrefijoTelefonico , cbxPrefijoTelefonico1 , Telefono , Telefono1 );
            Llenar_Campos_Correo ( Cedula , usuarios , Usuario , Usuario1 );
            return 1;
            
        }
        
        return 0;
    }
    
    
    public void enCampoCedulaPago ( KeyEvent event ) {
        
        Propietario_Tiene_Inmueble p = new Propietario_Tiene_Inmueble ( Cedula , Id_casa.getText ( ) );
        
        CedulaPago.setOnKeyPressed ( new EventHandler < KeyEvent > ( ) {
            @Override
            public void handle ( KeyEvent event ) {
                
                if ( CedulaPago.getText ( ).length ( ) > 6 ) {
                    
                    AdvertenciaCedulaPagoCuota.setVisible ( false );
                    PaneEnterCedulaPagoCuota.setVisible ( true );
                    
                    if ( event.getCode ( ).equals ( KeyCode.ENTER ) ) {
                        
                        PaneEnterCedulaPagoCuota.setVisible ( true );
                        p.Direccion_de_las_propiedades ( obj.getConnection ( ) , CedulaPago.getText ( ) , numero_de_casa, ids_casas );
                        
                        
                    } else if ( event.getCode ( ).equals ( KeyCode.BACK_SPACE ) ) {
                        
                        PaneEnterCedulaPagoCuota.setVisible ( false );
                        AdvertenciaCedulaPagoCuota.setVisible ( true );
                        cbxTipoHistorico.setVisible ( false );
                        cbxTipoHistorico.setValue ( null );
                        
                        Limpiar_Campos_Modulo_Pago_Cuotas ( cbxCasaPago , cbxIdsCasaPago , numero_terraza , LabelDeudaCuotaOrdinaria , LabelDeudaCuotaExtraordinaria );
                        
                    }
                    
                    if ( numero_de_casa.size ( ) != 0 ) {
                        
                        cbxCasaPago.setItems ( numero_de_casa );
                        cbxIdsCasaPago.setItems ( ids_casas );
                        
                    }
                    
                } else {
                    
                    AdvertenciaCedulaPagoCuota.setVisible ( true );
                    
                }
            }
        } );
        
    }
    
    public void Limpiar_Campos_Modulo_Pago_Cuotas ( ComboBox < Integer > casa , ComboBox < Integer > cbxIdCasa , Label terraza , Label deuda_ordinaria , Label deuda_extraordinaria ) {
        
        casa.setItems ( null );
        cbxIdCasa.setItems ( null );
        terraza.setText ( null );
        numero_de_casa.clear ( );
        ids_casas.clear ();
        observable.clear ( );
        observable2.clear ( );
        observable3.clear ( );
        observable4.clear ( );
        observable5.clear ( );
        observable6.clear ( );
        observable7.clear ( );
        observable8.clear ( );
        observable9.clear ( );
        observable10.clear ( );
        deuda_ordinaria.setText ( null );
        deuda_extraordinaria.setText ( null );
        
    }
    
    public void Calculo_Cuotas_A_Pagar_Por_Inmueble ( ActionEvent event ) {
    
        int index = cbxIdsCasaPago.getSelectionModel ( ).getSelectedIndex ( );
        System.out.println ( index );
        cbxCasaPago.getSelectionModel ( ).select ( index );
        LabelN_Casa.setText ( String.valueOf ( cbxCasaPago.getSelectionModel ().getSelectedItem () ) );
        
        if ( cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) != null ) {
    
            observable.clear ();
            observable2.clear ();
            observable3.clear ();
            observable4.clear ();
            observable5.clear ();
            observable6.clear ();
            observable7.clear ();
            observable8.clear ();
            observable9.clear ();
            observable10.clear ();
            LabelTotalAPagar.setText ( null );
           
            LabelSolventeCuota_Ordinaria.setVisible ( false );
            LabelSolvente.setVisible ( false );
            Visualizar.setVisible ( true );
            Visualizar2.setVisible ( true );
            
            int terraza = TerrazaCorrespondiente.TerrazaCorrespondienteACasa ( obj.getConnection ( ) , Integer.valueOf ( CedulaPago.getText ( ) ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ), cbxIdsCasaPago.getSelectionModel ().getSelectedItem () );
            numero_terraza.setText ( String.valueOf ( terraza ) );
            
            TablaHistorialCanceladoCuotaOrdinaria ( cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , Integer.parseInt ( numero_terraza.getText ( ) ) );
            TablaHistorialCanceladoCuotaExtra ( cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , Integer.parseInt ( numero_terraza.getText ( ) ) );
            
            Propietario_Cancela_Cuota_Ordinaria Propietario_Cuota = new Propietario_Cancela_Cuota_Ordinaria ( CedulaPago.getText ( ) );
            Date fecha1 = Propietario_Cuota.UltimaCuotaPagada ( obj.getConnection ( ) , parseInt ( CedulaPago.getText ( ) ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ), Integer.valueOf ( numero_terraza.getText () ) );
            Cuota_Ordinaria Cuota_Establecida = new Cuota_Ordinaria ( );
            
            if ( fecha1 == null ) {
                
                int Deuda1 = 0;
                Inmueble_Esta_Ubicado_En_Terraza id = new Inmueble_Esta_Ubicado_En_Terraza ( );
                int ID = id.IDCasa ( obj.getConnection ( ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , Integer.valueOf ( numero_terraza.getText ( ) ) );
                Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble ( Cedula , Id_casa.getText ( ) );
                Date fecha = Propietario_Inmueble.Fecha_De_Adquisicion ( obj.getConnection ( ) , Integer.valueOf ( CedulaPago.getText ( ) ) , ID );
                Propietario_Cuota.cbxCuotasAPagarCuandoFechaNula ( obj.getConnection ( ) , Integer.valueOf ( CedulaPago.getText ( ) ) , fecha , observable5 , observable3 , observable4 , observable6 , observable , Propietario.Cantidad_de_casas ( obj.getConnection ( ) ) , LabelDeudaCuotaOrdinaria );
                
                ListaDeDeuda.setItems ( observable );
                
                if ( observable5.size ( ) == 0 ) {
                    
                    LabelSolventeCuota_Ordinaria.setVisible ( true );
                    LabelSolventeCuota_Ordinaria.setText ( "SOLVENTE" );
                    
                } else {
                    
                    LabelDeudaCuotaOrdinaria.setVisible ( true );
                    
                }
                
            } else {
                
                int Deuda1 = 0;
                Propietario_Cuota.cbxCuotasAPagar ( obj.getConnection ( ) , Integer.valueOf ( CedulaPago.getText ( ) ) , fecha1 , observable5 , observable3 , observable4 , observable6 , observable , Propietario.Cantidad_de_casas ( obj.getConnection ( ) ) , LabelDeudaCuotaOrdinaria );
                Inmueble_Esta_Ubicado_En_Terraza id = new Inmueble_Esta_Ubicado_En_Terraza ( );
                LabelTotalAPagar.setText ( String.valueOf ( observable6.get ( 0 ) ) );
                enBotonSIAgregarPago ( observable5 , observable3 , observable4 , observable7 , observable8 , observable9 , id.IDCasa ( obj.getConnection ( ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , Integer.valueOf ( numero_terraza.getText ( ) ) ) );
                ListaDeDeuda.setItems ( observable );
                
                if ( observable5.size ( ) == 0 ) {
                    
                    LabelSolventeCuota_Ordinaria.setVisible ( true );
                    LabelSolventeCuota_Ordinaria.setText ( "SOLVENTE" );
                    
                } else {
                    
                    LabelDeudaCuotaOrdinaria.setVisible ( true );
                    
                }
                
            }
            
            Propietario_Paga_Cuota_Extraordinaria Propietario_CuotaExtraordinaria = new Propietario_Paga_Cuota_Extraordinaria ( );
            Date fecha1_1 = Propietario_CuotaExtraordinaria.UltimaCuotaPagada ( obj.getConnection ( ) , Integer.valueOf ( CedulaPago.getText ( ) ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ), Integer.valueOf ( numero_terraza.getText () ) );
            
            if ( fecha1_1 == null ) {
                
                Inmueble_Esta_Ubicado_En_Terraza id = new Inmueble_Esta_Ubicado_En_Terraza ( );
                int ID = id.IDCasa ( obj.getConnection ( ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , Integer.valueOf ( numero_terraza.getText ( ) ) );
                Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble ( Cedula , Id_casa.getText ( ) );
                Date fecha = Propietario_Inmueble.Fecha_De_Adquisicion_Para_Cuota_Extraordinaria ( obj.getConnection ( ) , Integer.valueOf ( CedulaPago.getText ( ) ) , ID );
                
                Propietario_CuotaExtraordinaria.CuotasAPagarSIUltimaFechaNula ( obj.getConnection ( ) , fecha , observable7 , observable8 , observable9 , observable2 , Propietario.Cantidad_de_casas ( obj.getConnection ( ) ) , LabelDeudaCuotaExtraordinaria , observable10 );
                ListaDeDeudaCuotaExtraordinaria.setItems ( observable2 );
                LabelTotalAPagar.setText ( String.valueOf ( observable10.get ( 0 ) + observable6.get ( 0 ) ) );
                
                if ( observable7.size ( ) == 0 ) {
                    
                    LabelSolvente.setVisible ( true );
                    LabelSolvente.setText ( "SOLVENTE" );
                    
                } else {
                    
                    LabelDeudaCuotaExtraordinaria.setVisible ( true );
                    
                }
                
            } else {
                
                Propietario_CuotaExtraordinaria.CuotasAPagar ( obj.getConnection ( ) , fecha1_1 , observable7 , observable8 , observable9 , observable2 , Propietario.Cantidad_de_casas( obj.getConnection ( ) ) , LabelDeudaCuotaExtraordinaria , observable10 );
                ListaDeDeudaCuotaExtraordinaria.setItems ( observable2 );
                
                if ( observable2.size ( ) == 0 ) {
                    
                    LabelSolvente.setVisible ( true );
                    LabelSolvente.setText ( "SOLVENTE" );
                    
                } else {
                    
                    LabelDeudaCuotaExtraordinaria.setVisible ( true );
                    LabelTotalAPagar.setText ( String.valueOf ( observable10.get ( 0 ) + observable6.get ( 0 ) ) );
                    
                }
                
            }
            
            LabelTotalAPagar.setText ( String.valueOf ( observable10.get ( 0 ) + observable6.get ( 0 ) ) );
            enBotonSIAgregarPago ( observable5 , observable3 , observable4 , observable7 , observable8 , observable9 , Id.IDCasa ( obj.getConnection ( ) , cbxCasaPago.getSelectionModel ( ).getSelectedItem ( ) , Integer.valueOf ( numero_terraza.getText ( ) ) ) );
            
        } else {
            
            numero_terraza.setText ( " " );
            LabelDeudaCuotaOrdinaria.setText ( " " );
            LabelDeudaCuotaExtraordinaria.setText ( " " );
            LabelTotalAPagar.setText ( " " );
            Visualizar.setVisible ( false );
            Visualizar2.setVisible ( false );
            LabelSolventeCuota_Ordinaria.setVisible ( false );
            LabelSolvente.setVisible ( false );
            
        }
        
    }
    
    @FXML
    public void enBusquedaArticulo ( KeyEvent event ) {
        
        
        ArrayList < Articulo > listaArticulo = new ArrayList < Articulo > ( );
        
        ObservableList < Articulo > listaObservable = FXCollections.observableArrayList ( listaArticulo );
        
        Articulo a = new Articulo ( );
        a.LLenarTablaInventario ( obj.getConnection ( ) , listaObservable );
        listaArticulo.add ( a );
        
        
        FilteredList filtro = new FilteredList ( listaObservable , v -> true );
        
        
        BusquedaArticulo.textProperty ( ).addListener ( ( ( observable , oldValue , newValue ) -> {
            
            
            filtro.setPredicate ( ( Predicate < ? super Articulo > ) ( Articulo articulo ) -> {
                
                if ( newValue.isEmpty ( ) || newValue == null ) {
                    
                    return true;
                    
                }
                
                if ( articulo.getNombre_de_Articulo ( ).contains ( newValue ) ) {
                    
                    return true;
                }
                return false;
                
                
            } );
            
        } ) );
        
        SortedList sort = new SortedList ( filtro );
        sort.comparatorProperty ( ).bind ( TablaInventarioArticulos.comparatorProperty ( ) );
        TablaInventarioArticulos.setItems ( sort );
        
    }
    
    public void enBotonAgregarArticulo ( ActionEvent event ) {
        
        if ( ( Nombre_de_Articulo.getText ( ).isEmpty ( ) && Cantidad_Articulo.getText ( ).isEmpty ( ) && Valor_de_Articulo.getText ( ).isEmpty ( ) ) || ( ( Nombre_de_Articulo.getText ( ).isEmpty ( ) || Cantidad_Articulo.getText ( ).isEmpty ( ) || Valor_de_Articulo.getText ( ).isEmpty ( ) ) ) ) {
            
            PaneVerificacionObligatoriaArticulos.setVisible ( true );
            PaneCubiertaArticulo.setVisible ( true );
            
        } else {
            
            PanePreguntaAgregarArticulo.setVisible ( true );
            PaneCubiertaArticulo.setVisible ( true );
            
        }
    }
    
    public void enBotonSIAgregarArticulo ( ActionEvent event ) {
        
        Articulo Articulo = new Articulo ( Nombre_de_Articulo.getText ( ) , Integer.parseInt ( Valor_de_Articulo.getText ( ) ) , Integer.parseInt ( Cantidad_Articulo.getText ( ) ) );
        
        int Registro = Articulo.Registrar ( obj.getConnection ( ) );
        
        if ( Registro == 1 ) {
            
            PanePreguntaAgregarArticulo.setVisible ( false );
            PaneExitoAgregarArticulo.setVisible ( true );
            PaneCubiertaArticulo.setVisible ( true );
            TablaInventarioArticulos ( );
            ID_Articulo.setText ( null );
            cbxListaArticulos.getItems ( ).add ( Nombre_de_Articulo.getText ( ) );
            
        }
    }
    
    public void enBotonNOAgregarArticulo ( ActionEvent event ) {
        
        PanePreguntaAgregarArticulo.setVisible ( false );
        PaneCubiertaArticulo.setVisible ( false );
        
    }
    
    public void enBotonModificarArticulo ( ActionEvent event ) {
        
        if ( ( Nombre_de_Articulo.getText ( ).isEmpty ( ) && Cantidad_Articulo.getText ( ).isEmpty ( ) && Valor_de_Articulo.getText ( ).isEmpty ( ) ) || ( ( Nombre_de_Articulo.getText ( ).isEmpty ( ) || Cantidad_Articulo.getText ( ).isEmpty ( ) || Valor_de_Articulo.getText ( ).isEmpty ( ) ) ) ) {
            
            PaneVerificacionObligatoriaArticulos.setVisible ( true );
            PaneCubiertaArticulo.setVisible ( true );
            
        } else {
            
            PanePreguntaModificarArticulo.setVisible ( true );
            PaneCubiertaArticulo.setVisible ( true );
            
        }
    }
    
    public void enBotonNOModificarArticulo ( ActionEvent event ) {
        
        PanePreguntaModificarArticulo.setVisible ( false );
        PaneCubiertaArticulo.setVisible ( false );
        
    }
    
    public void enBotonDeshabilitarArticulo ( ActionEvent event ) {
        
        if ( ( Nombre_de_Articulo.getText ( ).isEmpty ( ) && Cantidad_Articulo.getText ( ).isEmpty ( ) && Valor_de_Articulo.getText ( ).isEmpty ( ) ) || ( ( Nombre_de_Articulo.getText ( ).isEmpty ( ) || Cantidad_Articulo.getText ( ).isEmpty ( ) || Valor_de_Articulo.getText ( ).isEmpty ( ) ) ) ) {
            
            PaneVerificacionObligatoriaArticulos.setVisible ( true );
            PaneCubiertaArticulo.setVisible ( true );
            
        } else {
            
            PanePreguntaEliminarArticulo.setVisible ( true );
            PaneCubiertaArticulo.setVisible ( true );
            
        }
        
    }
    
    public void enBotonNOEliminarArticulo ( ActionEvent event ) {
        
        PanePreguntaEliminarArticulo.setVisible ( false );
        PaneCubiertaArticulo.setVisible ( false );
        
    }
    
    
    public void enBotonAgregarPagoArticulo ( ActionEvent event ) {
        
        PanePreguntaAgregarPagoArticulo.setVisible ( true );
        PaneCubiertaVentaArticulo.setVisible ( true );
        
    }
    
    public void enCampoCantidad_ArticuloP ( MouseEvent event ) {
    
    
    }
    
    public void enCampoCedulaCompradorArticulo ( KeyEvent event ) {
        
        ArrayList < Integer > a = new ArrayList ( );
        ObservableList < Integer > b = FXCollections.observableArrayList ( a );
        
        if ( event.getCode ( ).equals ( KeyCode.BACK_SPACE ) ) {
            
            cbxListaArticulos.setValue ( null );
            Cantidad_Articulos.setText ( null );
            Pago_Articulo.setText ( null );
            cbxCasaVenta.setItems ( null );
            LabelTerrazaVenta.setText ( null );
            
        }
        if ( event.getCode ( ).equals ( KeyCode.ENTER ) ) {
            
            Propietario_Tiene_Inmueble p = new Propietario_Tiene_Inmueble ( );
            p.Direccion_de_las_propiedades ( obj.getConnection ( ) , Cedula_Comprador.getText ( ) , b , ids_casas);
            cbxCasaVenta.setItems ( b );
            
        }
    }
    
    public void TerrazaVenta ( ActionEvent event ) {
        
        if ( cbxCasaVenta.getSelectionModel ( ).getSelectedItem ( ) != null ) {
            
            int terraza = TerrazaCorrespondiente.TerrazaCorrespondienteACasa ( obj.getConnection ( ) , Integer.valueOf ( Cedula_Comprador.getText ( ) ) , cbxCasaVenta.getSelectionModel ( ).getSelectedItem ( ) , cbxIdsCasaPago.getSelectionModel ().getSelectedItem ());
            LabelTerrazaVenta.setText ( String.valueOf ( terraza ) );
            
        }
        
    }
    
    public void enCampoCantidad_Articulo ( KeyEvent event ) {
        
        Articulo Articulo = new Articulo ( cbxListaArticulos.getSelectionModel ( ).getSelectedItem ( ) );
        Propietario_Puede_Comprar_Articulo P_A = new Propietario_Puede_Comprar_Articulo ( );
        int venta = P_A.Buscar_Id_Articulo ( obj.getConnection ( ) , cbxListaArticulos.getSelectionModel ( ).getSelectedItem ( ) );
        Articulo Art = new Articulo ( venta );
        int Precio = Art.Precio_Articulo ( obj.getConnection ( ) );
        IconoEnter.setVisible ( true );
        IconoClick.setVisible ( true );
        
        if ( event.getCode ( ).equals ( KeyCode.ENTER ) ) {
            
            IconoEnter.setVisible ( false );
            IconoClick.setVisible ( false );
            System.out.println ( Cantidad_Articulos.getText ( ) );
            int a = Integer.parseInt ( Cantidad_Articulos.getText ( ) );
            int b = Articulo.Cantidad_existente ( obj.getConnection ( ) );
            
            if ( a < b ) {
                
                Precio = Precio * a;
                Pago_Articulo.setText ( String.valueOf ( Precio ) );
                System.out.println ( "ES MENOR, PUEDE COMPRAR" );
                
            } else {
                
                Pago_Articulo.setText ( String.valueOf ( 0 ) );
                PaneCantidadDisponible.setVisible ( true );
                
            }
        } else if ( event.getCode ( ).equals ( KeyCode.BACK_SPACE ) ) {
            
            if ( Cantidad_Articulos.getText ( ).length ( ) == 1 || Cantidad_Articulos.getText ( ).length ( ) == 0 ) {
                
                IconoEnter.setVisible ( false );
                IconoClick.setVisible ( false );
                
            }
        }
    }
    
    public void enBotonSIAgregarPagoArticulo ( ActionEvent event ) {
        
        Propietario_Puede_Comprar_Articulo Compra = new Propietario_Puede_Comprar_Articulo ( );
        int Id = Compra.Buscar_Id_Articulo ( obj.getConnection ( ) , cbxListaArticulos.getSelectionModel ( ).getSelectedItem ( ) );
        System.out.println ( Id );
        Propietario_Tiene_Inmueble p = new Propietario_Tiene_Inmueble ( 0 , cbxCasaVenta.getSelectionModel ( ).getSelectedItem ( ) , Integer.parseInt ( LabelTerrazaVenta.getText ( ) ) );
        Propietario_Puede_Comprar_Articulo Propietario_Articulo = new Propietario_Puede_Comprar_Articulo ( Integer.parseInt ( Cedula_Comprador.getText ( ) ) , Id , Integer.parseInt ( Cantidad_Articulos.getText ( ) ) , Integer.parseInt ( Pago_Articulo.getText ( ) ) , p.Buscar_ID_del_Inmueble ( obj.getConnection ( ) ) , cbxCasaVenta.getSelectionModel ( ).getSelectedItem ( ) , Integer.parseInt ( LabelTerrazaVenta.getText ( ) ) );
        
        int RegistrarCompra = Propietario_Articulo.Registrar ( obj.getConnection ( ) );
        
        if ( RegistrarCompra == 1 ) {
            
            Modificar_Cantidad_Existente_Articulo ( );
            System.out.println ( "Exitosamente realizada la compra" );
            
        }
        
    }
    
    public void enBotonSIModificarArticulo ( MouseEvent event ) {
        
        Articulo art = new Articulo ( Integer.parseInt ( Valor_de_Articulo.getText ( ) ) , Integer.parseInt ( Cantidad_Articulo.getText ( ) ) , Integer.parseInt ( ID_Articulo.getText ( ) ) );
        int Modificar = art.Modificar ( obj.getConnection ( ) );
        
        if ( Modificar == 1 ) {
            
            PanePreguntaModificarArticulo.setVisible ( false );
            PaneExitoModificarArticulo.setVisible ( true );
            LabelExitoArticulo.setText ( "La informacion ha sido modificada con exito" );
            TablaInventarioArticulos ( );
            ID_Articulo.setText ( null );
            Nombre_de_Articulo.setText ( null );
            Valor_de_Articulo.setText ( null );
            Cantidad_Articulo.setText ( null );
            
        }
    }
    
    public void Modificar_Cantidad_Existente_Articulo ( ) {
        
        Propietario_Puede_Comprar_Articulo p_a = new Propietario_Puede_Comprar_Articulo ( );
        Articulo Art = new Articulo ( cbxListaArticulos.getSelectionModel ( ).getSelectedItem ( ) );
        int a = Art.Cantidad_existente ( obj.getConnection ( ) );
        a = a - Integer.parseInt ( Cantidad_Articulos.getText ( ) );
        Articulo Art0 = new Articulo ( a , p_a.Buscar_Id_Articulo ( obj.getConnection ( ) , cbxListaArticulos.getSelectionModel ( ).getSelectedItem ( ) ) );
        Art0.Modificar_Cantidad ( obj.getConnection ( ) );
        
    }
    
    public void enBotonSIDeshabilitarArticulo ( MouseEvent event ) {
        
        Articulo art = new Articulo ( Integer.parseInt ( ID_Articulo.getText ( ) ) );
        
        int Eliminar = art.Eliminar ( obj.getConnection ( ) );
        
        if ( Eliminar == 1 ) {
            
            PanePreguntaEliminarArticulo.setVisible ( false );
            
            LabelExitoArticulo.setText ( "La informacion ha sido eliminada con exito" );
            PaneExitoEliminarArticulo.setVisible ( true );
            ID_Articulo.setText ( null );
            Nombre_de_Articulo.setText ( null );
            Valor_de_Articulo.setText ( null );
            Cantidad_Articulo.setText ( null );
            TablaInventarioArticulos ( );
            
        }
    }
    
    public void enBotonNOAgregarPagoArticulo ( ActionEvent event ) {
        
        PanePreguntaAgregarPagoArticulo.setVisible ( false );
        PaneCubiertaVentaArticulo.setVisible ( false );
        
    }
    
    public void handleExit ( ) {
        System.exit ( 0 );
    }
    
    public void enIconoVisualizar ( MouseEvent event ) {
        
        PaneListaHistorialCuotas.setVisible ( true );
        ListaDeDeuda.setVisible ( true );
        TablaHistorialAlicuotaOrdinaria.setVisible ( false );
        TablaHistorialAlicuotaExtraordinaria.setVisible ( false );
        HistorialDePagoPorInmueble.setText ( "DEUDA ALICUOTA ORDINARIA" );
        Visualizar1.setVisible ( true );
        
        if ( Visualizar2.isVisible ( ) ) {
            
            if ( Visualizar11.isVisible ( ) ) {
                
                ListaDeDeudaCuotaExtraordinaria.setVisible ( false );
                Visualizar11.setVisible ( false );
                
            }
        }
    }
    
    public void enIconoVisualizar2 ( MouseEvent event ) {
        
        PaneListaHistorialCuotas.setVisible ( true );
        ListaDeDeudaCuotaExtraordinaria.setVisible ( true );
        TablaHistorialAlicuotaOrdinaria.setVisible ( false );
        TablaHistorialAlicuotaExtraordinaria.setVisible ( false );
        HistorialDePagoPorInmueble.setText ( "DEUDA ALICUOTA EXTRAORDINARIA" );
        Visualizar11.setVisible ( true );
        
        if ( Visualizar11.isVisible ( ) ) {
            
            ListaDeDeuda.setVisible ( false );
            Visualizar1.setVisible ( false );
            
        }
        
    }
    
    public void enIconoVisualizar1 ( MouseEvent event ) {
        
        PaneListaHistorialCuotas.setVisible ( false );
        ListaDeDeuda.setVisible ( false );
        Visualizar1.setVisible ( false );
        TablaHistorialAlicuotaOrdinaria.setVisible ( true );
        TablaHistorialAlicuotaExtraordinaria.setVisible ( true );
        HistorialDePagoPorInmueble.setText ( "HISTORIAL DE PAGOS POR INMUEBLE" );
        
    }
    
    public void enIconoVisualizar12 ( MouseEvent event ) {
        
        PaneListaHistorialCuotas.setVisible ( false );
        ListaDeDeudaCuotaExtraordinaria.setVisible ( false );
        Visualizar11.setVisible ( false );
        TablaHistorialAlicuotaOrdinaria.setVisible ( true );
        TablaHistorialAlicuotaExtraordinaria.setVisible ( true );
        HistorialDePagoPorInmueble.setText ( "HISTORIAL DE PAGOS POR INMUEBLE" );
        
    }
    
    public void enBotonCerrarSesion(ActionEvent event){
    
        PanePrincipal.setVisible ( false );
        PaneMenu.setVisible ( false);
        PaneBarra.setVisible ( false );
        PaneCerrarSesion.setVisible ( false );
        AnchorPaneLogin.setVisible ( true );
    
    }
    
    public void enBotonEntrar(ActionEvent event){
        
        PanePrincipal.setVisible ( true );
        PaneMenu.setVisible ( true );
        PaneBarra.setVisible ( true );
        Limpiar_Campos_Formulario ( Primer_Nombre, Segundo_Nombre, Primer_Apellido, Segundo_Apellido,Fecha_de_Nacimiento, cbxSexo, cbxNacionalidad,cbxEstadoCivil, cbxPrefijoTelefonico, cbxPrefijoTelefonico1, Telefono,Telefono1, cbxDominio,cbxDominio1,Usuario, Usuario1,dominios,usuarios,prefijo_telefonico0, numero_telefonico0);
        LimpiarCampoCedula ( Cedula );
        AnchorPaneLogin.setVisible ( false );
        Usuario acceso = new Usuario ( );
        String Privilegio = acceso.Privilegio_Acceso ( obj.getConnection ( ) , IDUsuario.getText ( ) , ContraseñaUsuario.getText ( ) );
    
        if ( Privilegio.equals ( "Secretario" ) ) {
        
            BotonAgregarUsuario.setDisable ( true );
            BotonEliminarUsuario.setDisable ( true );
            PaneCubiertaCuotaOrdinaria.setVisible ( true );
            PaneCubiertaCuotaExtraordinaria.setVisible ( true );
            LabelCargo.setText ( "Secretario" );
            LabelNombreUsuario.setText ( acceso.Nombre_de_Usuario ( obj.getConnection ( ) , "Secretario" ).toUpperCase ( ) );
        
        }
        
    }
    
    public void LimpiarCampoCedula(TextField cedula){
        
        cedula.clear ();
        
    }
    
    @Override
    public void initialize ( URL location , ResourceBundle resources ) {
    
        PanePrincipal.setVisible ( false );
        PaneMenu.setVisible ( false );
        PaneBarra.setVisible ( false );
        AnchorPaneLogin.setVisible ( true );
        
        Servicios Servicios = new Servicios ( );
        Label_Monto_Establecido_Cuota_Ordinaria.setText ( String.valueOf ( Servicios.CalcularTotal ( obj.getConnection ( ) ) ) );
        BotonAgregarServicio.setDisable ( false );
        BotonModificarServicios.setDisable ( true );
        BotonDeshabilitarServicios.setDisable ( true );
        BotonModificarUsuario.setDisable ( true );
        BotonModificarArticulo.setDisable ( true );
        BotonModificarPropietario.setDisable ( true );
        
        
        Carga_ComboBox ( );
        Parpadeo ( );
        Validaciones ( );
        Fecha_De_Nacimiento ( Fecha_de_Nacimiento );
        Fecha_De_Nacimiento ( Fecha_de_Nacimiento_Administrador );
        Fecha_De_Nacimiento ( Fecha_de_Nacimiento_Login);
        Fecha_De_Adquisicion ( );
        Fecha_De_Cuota_Ordinaria ( );
        Fecha_De_Cuota_Extraordinaria ( );
        TablaServicios ( );
        LLenarCamposServicios ( );
        TablaCuotaOrdinaria ( );
        TablaMorososCuotaOrdinaria ( );
        TablaCuotaExtraordinaria ( );
        TablaInventarioArticulos ( );
        TablaTotalCuotaOrdinaria ( );
        LLenarCamposCuotaExtraordinaria ( );
        LLenarCamposArticulos ( );
        TablaTotalCuotaExtraordinaria ( );
        TablaTotalArticulo ( );
        
        
    }
    
    public void Validaciones ( ) {
        
        V.Validaciones_Int_cedula ( Cedula );
        V.Validaciones_String_nombreObligatorio ( Primer_Nombre );
        V.Validaciones_String_nombre ( Segundo_Nombre );
        V.Validaciones_String_nombreObligatorio ( Primer_Apellido );
        V.Validaciones_String_nombre ( Segundo_Apellido );
        V.Validaciones_Int_cedula ( CedulaLogin );
        V.Validaciones_String_nombreObligatorio ( Primer_Nombre_Login );
        V.Validaciones_String_nombre ( Segundo_Nombre_Login );
        V.Validaciones_String_nombreObligatorio ( Primer_Apellido_Login );
        V.Validaciones_String_nombre ( Segundo_Apellido_Login );
        V.Validaciones_Int_Telefono ( Telefono );
        V.Validaciones_Int_Telefono ( Telefono1 );
        V.Validaciones_Int_Numero_Casa ( Casa );
        C.Nombre_de_servicio ( Nombre_del_Servicio );
        C.Descripcion_de_servicio ( Descripcion );
        C.Nombre_de_servicio ( Nombre_Cuota_Extraordinaria );
        C.Descripcion_de_servicio ( Descripcion_Cuota_Extraordinaria );
        C.Costo ( Costo_Cuota_Extraordinaria );
        C.Costo ( Valor_de_Articulo );
        C.Costo ( Costo );
        V.Validaciones_Int_cedula ( Cedula_Administrador );
        V.Validaciones_String_nombreObligatorio ( Primer_Nombre_Administrador );
        V.Validaciones_String_nombre ( Segundo_Nombre_Administrador );
        V.Validaciones_String_nombreObligatorio ( Primer_Apellido_Administrador );
        V.Validaciones_String_nombre ( Segundo_Apellido_Administrador );
        U.Longitud_Nombre_de_Usuario ( Nombre_de_Usuario );
        U.Longitud_Contraseña_Usuario ( Contraseña_de_Usuario );
        U.Validaciones_String_Respuesta ( Respuesta_de_Seguridad );
        U.Longitud_Nombre_de_Usuario ( Nombre_Usuario_Login );
        U.Longitud_Contraseña_Usuario ( Contraseña_Login );
        U.Validaciones_String_Respuesta ( Respuesta_Login);
        V.Validaciones_Int_Telefono ( Telefono2 );
        V.Validaciones_Int_Telefono ( Telefono3 );
        Visualizar.setVisible ( false );
        Visualizar2.setVisible ( false );
        
    }
    
    public void Carga_ComboBox ( ) {
        
        Articulo Articulo = new Articulo ( );
    
        cbxNacionalidadLogin.getItems ( ).addAll ( "V-" , "J-" , "E-" );
        cbxNacionalidad.getItems ( ).addAll ( "V-" , "J-" , "E-" );
        cbxNacionalidad_Administrador.getItems ( ).addAll ( "V-" , "J-" , "E-" );
        cbxSexo.getItems ( ).addAll ( "Femenino" , "Masculino" );
        cbxSexoLogin.getItems ( ).addAll ( "Femenino" , "Masculino" );
        cbxSexo_Administrador.getItems ( ).addAll ( "Femenino" , "Masculino" );
        cbxEstadoCivil.getItems ( ).addAll ( "Casado" , "Soltero" , "Divorciado" , "Viudo" );
        cbxEstado_Civil_Login.getItems ( ).addAll ( "Casado" , "Soltero" , "Divorciado" , "Viudo" );
        cbxEstado_Civil_Administrador.getItems ( ).addAll ( "Casado" , "Soltero" , "Divorciado" , "Viudo" );
        cbxPrefijoTelefonico.getItems ( ).addAll ( "0414" , "0424" , "0416" , "0426" , "0412" , "0283" );
        cbxPrefijoTelefonico1.getItems ( ).addAll ( "0414" , "0424" , "0416" , "0426" , "0412" , "0283" );
        cbxPrefijoTelefonico2.getItems ( ).addAll ( "0414" , "0424" , "0416" , "0426" , "0412" , "0283" );
        cbxPrefijoTelefonico3.getItems ( ).addAll ( "0414" , "0424" , "0416" , "0426" , "0412" , "0283" );
        cbxTerraza.getItems ( ).addAll ( 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 );
        cbxPregunta_de_Seguridad_Administrador.getItems ( ).addAll ( "¿Color favorito?" );
        cbxPregunta_Login.getItems ( ).addAll ( "¿Color favorito?" );
        cbxCargo_Administrador.getItems ( ).addAll ( "Tesorero" , "Secretario" );
        cbxCargo_Login.getItems ( ).addAll ( "Tesorero" , "Secretario" );
        Articulo.Articulos_Existentes ( obj.getConnection ( ) , cbxListaArticulos );
        cbxTerrazaGestion_de_Recursos.getItems ( ).addAll ( 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 , 10 , 11 , 12 );
        cbxTipoHistorico.getItems ().addAll("Historico Cuota Ordinaria", "Historico Cuota Extraordinaria");
        
    }
    
    public void Parpadeo ( ) {
        
        FadeTransition[] fx = new FadeTransition[ 1 ];
        
        for ( int i = 0; i <= 1; i++ ) {
            
            fx[ 0 ] = new FadeTransition ( Duration.seconds ( 1 ) );
            fx[ 0 ].setFromValue ( 1 );
            fx[ 0 ].setToValue ( 0 );
            fx[ 0 ].setCycleCount ( Timeline.INDEFINITE );
            
        }
        
        fx[ 0 ].setNode ( Circulo );
        
        for ( FadeTransition f : fx ) {
            
            f.play ( );
        }
    }
    
    public void TablaPropiedades ( Integer cedula ) {
        
        Conexion obj = new Conexion ( );
        
        ArrayList < Propietario_Tiene_Inmueble > listaPropiedades = new ArrayList <> ( );
        ObservableList < Propietario_Tiene_Inmueble > listaObservable = FXCollections.observableArrayList ( listaPropiedades );
        Propietario_Tiene_Inmueble Propietario_Inmueble = new Propietario_Tiene_Inmueble ( Cedula , Id_casa.getText ( ) );
        Propietario_Inmueble.Llenar_Tabla_Propiedades ( obj.getConnection ( ) , cedula , listaObservable );
        listaPropiedades.add ( Propietario_Inmueble );
        
        ColumnaIDCasa.setCellValueFactory ( new PropertyValueFactory <> ( "id" ) );
        ColumnaCasa.setCellValueFactory ( new PropertyValueFactory <> ( "n_casa" ) );
        ColumnaTerraza.setCellValueFactory ( new PropertyValueFactory <> ( "id_terraza" ) );
        
        TablaPropiedades.setItems ( listaObservable );
        
    }
    
    public void RegistroSeleccionado ( ) {
        
        TablaPropiedades.setOnMouseClicked ( new EventHandler < MouseEvent > ( ) {
            @Override
            public void handle ( MouseEvent event ) {
                
                Propietario_Tiene_Inmueble index = TablaPropiedades.getItems ( ).get ( TablaPropiedades.getSelectionModel ( ).getSelectedIndex ( ) );
                Id_casa.setText ( valueOf ( index.getId ( ) ) );
                BotonDeshabilitarPropietario.setDisable ( false );
                
            }
        } );
    }
    
    public void enBotonDeshabilitado ( MouseEvent event ) {
        
        
        BotonDeshabilitarPropietario.setDisable ( true );
        
        
    }
    
    public void TablaServicios ( ) {
        
        Conexion obj = new Conexion ( );
        
        ArrayList < Servicios > listaServicios = new ArrayList < Servicios > ( );
        
        ObservableList < Servicios > listaObservable = FXCollections.observableArrayList ( listaServicios );
        Servicios s = new Servicios ( );
        s.LLenarTablaDeServicios ( obj.getConnection ( ) , listaObservable );
        listaServicios.add ( s );
        
        ColumnaID.setCellValueFactory ( new PropertyValueFactory <> ( "Id_Servicio" ) );
        ColumnaTipoS.setCellValueFactory ( new PropertyValueFactory <> ( "Tipo_de_Servicio" ) );
        ColumnaDescripcion.setCellValueFactory ( new PropertyValueFactory <> ( "Descripcion" ) );
        ColumnaCosto.setCellValueFactory ( new PropertyValueFactory <> ( "Costo" ) );
        
        TablaServicios.setItems ( listaObservable );
        
    }
    
    public void LLenarCamposServicios ( ) {
        
        TablaServicios.setOnMouseClicked ( new EventHandler < MouseEvent > ( ) {
            @Override
            public void handle ( MouseEvent event ) {
                Servicios s = TablaServicios.getItems ( ).get ( TablaServicios.getSelectionModel ( ).getSelectedIndex ( ) );
                ID_Servicio.setText ( valueOf ( s.getId_Servicio ( ) ) );
                Nombre_del_Servicio.setText ( s.getTipo_de_Servicio ( ) );
                Descripcion.setText ( s.getDescripcion ( ) );
                Costo.setText ( valueOf ( s.getCosto ( ) ) );
                BotonDeshabilitarServicios.setDisable ( false );
                BotonModificarServicios.setDisable ( false );
                
            }
        } );
    }
    
    public void TablaCuotaOrdinaria ( ) {
        
        Conexion obj = new Conexion ( );
        
        ArrayList < Cuota_Ordinaria > listaCuotaOrdinaria = new ArrayList < Cuota_Ordinaria > ( );
        
        ObservableList < Cuota_Ordinaria > listaObservable = FXCollections.observableArrayList ( listaCuotaOrdinaria );
        Cuota_Ordinaria c = new Cuota_Ordinaria ( );
        c.LLenarTablaDeCuotaOrdinaria ( obj.getConnection ( ) , listaObservable );
        listaCuotaOrdinaria.add ( c );
        
        ColumnaIDCuotaOrdinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Id_CuotaOrdinaria" ) );
        ColumnaFechaEstablecidaCuotaOrdinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Fecha_Establecida" ) );
        ColumnaMontoEstablecido.setCellValueFactory ( new PropertyValueFactory <> ( "Monto_Establecido" ) );
        
        
        TablaCuotaOrdinaria.setItems ( listaObservable );
        
    }
    
    public void TablaCuotaExtraordinaria ( ) {
        
        Conexion obj = new Conexion ( );
        
        ArrayList < Cuota_Extraordinaria > listaCuotaExtraordinaria = new ArrayList < Cuota_Extraordinaria > ( );
        
        ObservableList < Cuota_Extraordinaria > listaObservable = FXCollections.observableArrayList ( listaCuotaExtraordinaria );
        
        
        Cuota_Extraordinaria e = new Cuota_Extraordinaria ( );
        e.LlenarTablaCuotaExtraordinaria ( obj.getConnection ( ) , listaObservable );
        listaCuotaExtraordinaria.add ( e );
        
        ColumnaIDCuotaExtraordinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Id_CuotaExtraordinaria" ) );
        ColumnaNombreCuotaExtraordinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Tipo_de_CuotaExtraordinaria" ) );
        ColumnaDescripcionCuotaExtraordinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Descripcion_CuotaExtraordinaria" ) );
        ColumnaFechaCuotaExtraordinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Fecha_CuotaExtraordinaria" ) );
        ColumnaCostoCuotaExtraordinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Costo_CuotaExtraordinaria" ) );
        
        TablaCuotaExtraordinaria.setItems ( listaObservable );
        
    }
    
    public void TablaInventarioArticulos ( ) {
        
        
        ArrayList < Articulo > listaArticulos = new ArrayList < Articulo > ( );
        
        ObservableList < Articulo > listaObservable = FXCollections.observableArrayList ( listaArticulos );
        
        
        Articulo a = new Articulo ( );
        a.LLenarTablaInventario ( obj.getConnection ( ) , listaObservable );
        listaArticulos.add ( a );
        
        ColumnaIDArticulo.setCellValueFactory ( new PropertyValueFactory <> ( "Id_Articulo" ) );
        ColumnaNombreArticulo.setCellValueFactory ( new PropertyValueFactory <> ( "Nombre_de_Articulo" ) );
        ColumnaPrecioArticulo.setCellValueFactory ( new PropertyValueFactory <> ( "Precio_Articulo" ) );
        ColumnaCantidadArticulo.setCellValueFactory ( new PropertyValueFactory <> ( "Cantidad" ) );
        
        TablaInventarioArticulos.setItems ( listaObservable );
        
    }
    
    public void TablaMorososCuotaOrdinaria ( ) {
        
        
        ArrayList < Gestion_de_Recursos > listaMorosos = new ArrayList <> ( );
        ObservableList < Gestion_de_Recursos > listaObservable = FXCollections.observableArrayList ( listaMorosos );
        ArrayList < Gestion_de_Recursos > listaMorosos1 = new ArrayList <> ( );
        ObservableList < Gestion_de_Recursos > listaObservable1 = FXCollections.observableArrayList ( listaMorosos1 );
        ArrayList < Gestion_de_Recursos > listaMorosos2 = new ArrayList <> ( );
        ObservableList < Gestion_de_Recursos > listaObservable2 = FXCollections.observableArrayList ( listaMorosos2 );
        
        
        Gestion_de_Recursos a = new Gestion_de_Recursos ( );
        a.Llenar_Gestion_de_recursos_lista_morosos_cuota_ordinaria ( obj.getConnection ( ) , listaObservable , a.Llenar_Gestion_de_recursos_lista_morosos_cuota ( obj.getConnection ( ) , listaObservable1 ) , listaObservable2 );
        listaMorosos.add ( a );
        
        ColumnaCasaMoroso.setCellValueFactory ( new PropertyValueFactory <> ( "N_Casa" ) );
        ColumnaTerrazaMoroso.setCellValueFactory ( new PropertyValueFactory <> ( "terraza" ) );
        ColumnaNombreMoroso.setCellValueFactory ( new PropertyValueFactory <> ( "Nombre" ) );
        ColumnaMesesMoroso.setCellValueFactory ( new PropertyValueFactory <> ( "Total_Cuota_Ordinaria" ) );
        
        TablaListaMorososCuotaOrdinaria.setItems ( listaObservable );
        TablaListaMorososCuotaOrdinaria.getItems ( ).addAll ( listaObservable1 );
        
    }
    
    public void TablaTotalCuotaOrdinaria ( ) {
        
        ArrayList < Gestion_de_Recursos > lista = new ArrayList <> ( );
        
        ObservableList < Gestion_de_Recursos > listaObservable = FXCollections.observableArrayList ( lista );
        
        Gestion_de_Recursos g = new Gestion_de_Recursos ( );
        g.Llenar_Gestion_de_recursos_ordinaria ( obj.getConnection ( ) , listaObservable );
        lista.add ( g );
        
        ColumnaCasaGestion.setCellValueFactory ( new PropertyValueFactory <> ( "N_Casa" ) );
        ColumnaNombreGestion.setCellValueFactory ( new PropertyValueFactory <> ( "Nombre" ) );
        ColumnaOrdinariaGestion.setCellValueFactory ( new PropertyValueFactory <> ( "Total_Cuota_Ordinaria" ) );
        
        TablaGestionRecursos.setItems ( listaObservable );
        
    }
    
    public void enCbxTerrazaGestion ( ActionEvent event ) {
        
        if ( cbxTerrazaGestion_de_Recursos.getValue ( ) != null ) {
            
            
            ArrayList < Gestion_de_Recursos > lista = new ArrayList <> ( );
            
            ObservableList < Gestion_de_Recursos > listaObservable = FXCollections.observableArrayList ( lista );
            
            Gestion_de_Recursos g = new Gestion_de_Recursos ( );
            g.Busqueda_por_terraza ( obj.getConnection ( ) , listaObservable , cbxTerrazaGestion_de_Recursos.getSelectionModel ( ).getSelectedItem ( ) );
            lista.add ( g );
            
            ColumnaCasaGestion.setCellValueFactory ( new PropertyValueFactory <> ( "N_Casa" ) );
            ColumnaNombreGestion.setCellValueFactory ( new PropertyValueFactory <> ( "Nombre" ) );
            ColumnaOrdinariaGestion.setCellValueFactory ( new PropertyValueFactory <> ( "Total_Cuota_Ordinaria" ) );
            
            TablaGestionRecursos.setItems ( listaObservable );
            
            ArrayList < Gestion_de_Recursos > lista0 = new ArrayList <> ( );
            
            ObservableList < Gestion_de_Recursos > listaObservable0 = FXCollections.observableArrayList ( lista0 );
            
            g.Busqueda_por_terrazaExtra ( obj.getConnection ( ) , listaObservable0 , cbxTerrazaGestion_de_Recursos.getSelectionModel ( ).getSelectedItem ( ) );
            lista0.add ( g );
            
            ColumnaCasaGestionCuotaExtra.setCellValueFactory ( new PropertyValueFactory <> ( "N_Casa" ) );
            ColumnaNombreGestionCuotaExtra.setCellValueFactory ( new PropertyValueFactory <> ( "Nombre" ) );
            ColumnaExtraGestion.setCellValueFactory ( new PropertyValueFactory <> ( "Total_Cuota_Ordinaria" ) );
            
            TablaGestionRecursosExtra.setItems ( listaObservable0 );
            
            ArrayList < Gestion_de_Recursos > lista1 = new ArrayList <> ( );
            
            ObservableList < Gestion_de_Recursos > listaObservable1 = FXCollections.observableArrayList ( lista1 );
            
            g.Busqueda_por_terrazaArticulo ( obj.getConnection ( ) , listaObservable1 , cbxTerrazaGestion_de_Recursos.getSelectionModel ( ).getSelectedItem ( ) );
            lista1.add ( g );
            
            ColumnaCasaGestionArticulo.setCellValueFactory ( new PropertyValueFactory <> ( "N_Casa" ) );
            ColumnaNombreGestionArticulo.setCellValueFactory ( new PropertyValueFactory <> ( "Nombre" ) );
            ColumnaArticuloGestion.setCellValueFactory ( new PropertyValueFactory <> ( "Total_Cuota_Ordinaria" ) );
            
            TablaGestionRecursosArticulos.setItems ( listaObservable1 );
            
        }
        
    }
    
    public void TablaHistorialCanceladoCuotaOrdinaria ( int n_casa , int n_terraza ) {
        
        ArrayList < Propietario_Cancela_Cuota_Ordinaria > lista = new ArrayList <> ( );
        ObservableList < Propietario_Cancela_Cuota_Ordinaria > listaObservable = FXCollections.observableArrayList ( lista );
        
        Propietario_Cancela_Cuota_Ordinaria p = new Propietario_Cancela_Cuota_Ordinaria ( );
        p.HistorialAlicuotaOrdinaria ( obj.getConnection ( ) , listaObservable , n_casa , n_terraza );
        lista.add ( p );
        
        ColumnaAlicuotaOrdinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Fecha_en_que_pago" ) );
        ColumnaMontoAlicuotaOrdinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Monto" ) );
        
        TablaHistorialAlicuotaOrdinaria.setItems ( listaObservable );
        
    }
    
    public void TablaHistorialCanceladoCuotaExtra ( int n_casa , int n_terraza ) {
        
        ArrayList < Propietario_Paga_Cuota_Extraordinaria > lista = new ArrayList <> ( );
        ObservableList < Propietario_Paga_Cuota_Extraordinaria > listaObservable = FXCollections.observableArrayList ( lista );
        
        Propietario_Paga_Cuota_Extraordinaria p = new Propietario_Paga_Cuota_Extraordinaria ( );
        p.HistorialAlicuotaExtraordinaria ( obj.getConnection ( ) , listaObservable , n_casa , n_terraza );
        lista.add ( p );
        
        ColumnaAlicuotaExtraordinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Fecha_de_CuotaExtraordinaria" ) );
        ColumnaAlicuotaMontoExtraordinaria.setCellValueFactory ( new PropertyValueFactory <> ( "Monto" ) );
        
        TablaHistorialAlicuotaExtraordinaria.setItems ( listaObservable );
        
    }
    
    public void TablaTotalCuotaExtraordinaria ( ) {
        
        ArrayList < Gestion_de_Recursos > lista = new ArrayList <> ( );
        
        ObservableList < Gestion_de_Recursos > listaObservable = FXCollections.observableArrayList ( lista );
        
        Gestion_de_Recursos g = new Gestion_de_Recursos ( );
        g.Llenar_Gestion_de_recursos_extraordinaria ( obj.getConnection ( ) , listaObservable );
        lista.add ( g );
        
        ColumnaCasaGestionCuotaExtra.setCellValueFactory ( new PropertyValueFactory <> ( "N_Casa" ) );
        ColumnaNombreGestionCuotaExtra.setCellValueFactory ( new PropertyValueFactory <> ( "Nombre" ) );
        ColumnaExtraGestion.setCellValueFactory ( new PropertyValueFactory <> ( "Total_Cuota_Ordinaria" ) );
        
        TablaGestionRecursosExtra.setItems ( listaObservable );
        
    }
    
    public void TablaTotalArticulo ( ) {
        
        ArrayList < Gestion_de_Recursos > lista = new ArrayList <> ( );
        
        ObservableList < Gestion_de_Recursos > listaObservable = FXCollections.observableArrayList ( lista );
        
        Gestion_de_Recursos g = new Gestion_de_Recursos ( );
        g.Llenar_Gestion_de_recursos_Articulo ( obj.getConnection ( ) , listaObservable );
        lista.add ( g );
        
        ColumnaCasaGestionArticulo.setCellValueFactory ( new PropertyValueFactory <> ( "N_Casa" ) );
        ColumnaNombreGestionArticulo.setCellValueFactory ( new PropertyValueFactory <> ( "Nombre" ) );
        ColumnaArticuloGestion.setCellValueFactory ( new PropertyValueFactory <> ( "Total_Cuota_Ordinaria" ) );
        
        TablaGestionRecursosArticulos.setItems ( listaObservable );
        
    }
    
    public void LLenarCamposArticulos ( ) {
        
        TablaInventarioArticulos.setOnMouseClicked ( new EventHandler < MouseEvent > ( ) {
            
            @Override
            public void handle ( MouseEvent event ) {
                Articulo a = TablaInventarioArticulos.getItems ( ).get ( TablaInventarioArticulos.getSelectionModel ( ).getSelectedIndex ( ) );
                ID_Articulo.setText ( valueOf ( a.getId_Articulo ( ) ) );
                Nombre_de_Articulo.setText ( a.getNombre_de_Articulo ( ) );
                Valor_de_Articulo.setText ( String.valueOf ( a.getPrecio_Articulo ( ) ) );
                Cantidad_Articulo.setText ( String.valueOf ( a.getCantidad ( ) ) );
                BotonModificarArticulo.setDisable ( false );
                
            }
        } );
    }
    
    
    public void LLenarCamposCuotaExtraordinaria ( ) {
        
        TablaCuotaExtraordinaria.setOnMouseClicked ( new EventHandler < MouseEvent > ( ) {
            
            
            @Override
            public void handle ( MouseEvent event ) {
                Cuota_Extraordinaria c = TablaCuotaExtraordinaria.getItems ( ).get ( TablaCuotaExtraordinaria.getSelectionModel ( ).getSelectedIndex ( ) );
                ID_Cuota_Extraordinaria.setText ( valueOf ( c.getId_CuotaExtraordinaria ( ) ) );
                Nombre_Cuota_Extraordinaria.setText ( c.getTipo_de_CuotaExtraordinaria ( ) );
                Descripcion_Cuota_Extraordinaria.setText ( c.getDescripcion_CuotaExtraordinaria ( ) );
                Fecha_Cuota_Extraordinaria.setValue ( LocalDate.parse ( valueOf ( c.getFecha_CuotaExtraordinaria ( ) ) ) );
                Costo_Cuota_Extraordinaria.setText ( valueOf ( c.getCosto_CuotaExtraordinaria ( ) ) );
                
            }
        } );
    }
    
    public void LlenarTablaInventarioArticulo ( ) {
    
    
    }
}






