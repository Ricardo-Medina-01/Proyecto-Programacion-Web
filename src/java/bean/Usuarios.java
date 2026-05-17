/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;
//import com.itextpdf.text.BaseColor;
import java.sql.*;
import java.io.File;
import java.io.FileWriter;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.Image;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PlotOrientation;
//import org.jfree.chart.*;
//import org.jfree.data.general.DefaultPieDataset;
//import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class Usuarios {
    private String usuario, password;
    private String respuesta, opcion;
    private String nombre, materno, paterno, email, tipo;
    
    //Crear citas
    
    private String nombres,cpaterno, cmaterno, fechacita, horacita, genero;
    private int edad;

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechacita() {
        return fechacita;
    }

    public void setFechacita(String fechacita) {
        this.fechacita = fechacita;
    }

    public String getHoracita() {
        return horacita;
    }

    public void setHoracita(String horacita) {
        this.horacita = horacita;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCpaterno() {
        return cpaterno;
    }

    public void setCpaterno(String cpaterno) {
        this.cpaterno = cpaterno;
    }

    public String getCmaterno() {
        return cmaterno;
    }

    public void setCmaterno(String cmaterno) {
        this.cmaterno = cmaterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
        if (opcion.equals("verifica")) {
            buscaUsuario();
        }
        if (opcion.equals("registro")) {
            obtenerUsuario();
        }
        if (opcion.equals("llenaS")){
            llenarSelector();
        }
        if (opcion.equals("modificaT")){
            modificarTipo();
        }
    }
    
    
    public void buscaUsuario() {
        try {
            Connection con = Conexion.conectar();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("select * from Usuarios where usuario=? and pass=?");
                ps.setString(1, usuario);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    String tipo = rs.getString("tipo");
                    if (tipo.equals("I")) {
                        respuesta = "Invitado/MenuInvitado.html";
                    }
                    if (tipo.equals("A")) {
                        respuesta = "Administrador/MenuAdmin.html";
                    }
                    if (tipo.equals("M")) {
                        respuesta = "Medico/MenuMedico.html";
                    }
                    if (tipo.equals("E")) {
                        respuesta = "Empleado/MenuEmpleado.html";
                    }
                } else {
                    respuesta = "Error.jsp?respuesta='No encontro dato'";
                }
            } else {
                respuesta = "Error.jsp?respuesta='No hay conexion en la base'";
            }
        } catch (Exception e) {
            respuesta = "Error.jsp?respuesta='Error al buscar'" + e;
        }
    }
    public int aleatorio(){
        return((int)(Math.random()*100));
    }
    
    public void obtenerUsuario(){
        int y= aleatorio();

        String r = nombre.substring(0,3);
        r+= paterno.substring(0,3);
        r+= materno.substring(0,3);
        if(y<10) r+= "00"+y;
        else if(y<100) r+= "0"+y;

        else r+= y;
        usuario = r;
        password = EncriptarMD5.encripta(r);
        respuesta = "<H1> Tu usuario es " + usuario +" y tu password es "+password+"<br></H1>";
        guardarRegistro();
    }
    
    public void guardarRegistro(){
        try{
        Connection c = Conexion.conectar();
        if (c != null) {
            CallableStatement cs = c.prepareCall("{call AltaUsuario(?,?,?,?,?,?,?)}");
            
            cs.setString(1,nombre);
            cs.setString(2,paterno);
            cs.setString(3,materno);
            cs.setString(4,email);
            cs.setString(5,usuario);
            cs.setString(6,password);
            cs.registerOutParameter(7, Types.VARCHAR);
            cs.execute();
            respuesta += cs.getString(7);        
    }
    }catch(Exception er){
        respuesta = "Error al agregar usario"+ er;
        }
    }
    public void llenarSelector() {
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("select * from Usuarios");
                ResultSet rs = ps.executeQuery();
                respuesta = "<select name='usuario'>";
                while (rs.next()) {
                    String usuario = rs.getString("usuario");
                    respuesta += "<option value='" + usuario + "'>" + usuario + "</option>";
 
                }
                respuesta += "</select>";
            } else {
                respuesta = "Error.jsp?respuesta=No hay conexion en la base";
            }
        } catch (Exception e) {
            respuesta = "Error.jsp?respuesta=Error al llenar Selector " + e;
        }
    }
    public void modificarTipo(){
        try {
            Connection c = Conexion.conectar();
            if (c != null) {
                PreparedStatement ps = c.prepareStatement("update Usuarios set tipo=? where usuario=?");
                ps.setString(1, tipo);
                ps.setString(2, usuario);
                ps.execute();
                respuesta="Tipo modificado";
            }else{
             respuesta="No hay conexion a la base";        
            }
        }catch(Exception er){
            respuesta="Error al modificar tipo "+er;
            }
    }
        public void consultarUsuario() {
        try {
            Connection c = Conexion.conectar();
 
            if (c != null) {PreparedStatement ps = c.prepareStatement("SELECT * FROM Usuarios WHERE usuario = ?");
                ps.setString(1, getUsuario());
                ResultSet rs = ps.executeQuery();
 
                if (rs.next()) {
 
                    this.nombre =rs.getString("nombre");
                    this.paterno = rs.getString("aPaterno");
                    this.materno =rs.getString("aMaterno");
                    this.email = rs.getString("email");
                    this.tipo =rs.getString("tipo");
                    
                    String tipoTexto = "";

    if (tipo.equals("A")) {
        tipoTexto = "Administrador";
    }

    if (tipo.equals("I")) {
        tipoTexto = "Invitado";
    }

    if (tipo.equals("M")) {
        tipoTexto = "Medico";
    }

    if (tipo.equals("E")) {
        tipoTexto = "Empleado";
    }
 
                    respuesta =
                    "<h1>Datos del Usuario</h1>"
                    + "<br>"
                    + "Usuario: " + getUsuario() + "<br>"
                    + "Nombre: " + nombre + " "
                    + paterno + " "
                    + materno + "<br>"
                    + "Email: " + email + "<br>"
                    + "Tipo: " + tipoTexto + "<br><br>" +
                            
                    "<a href='Usuarios.html'>Regresar</a>";
                } else {
                    respuesta = "No se encontró la persona con el nombre '" + getUsuario() + "'.";
                }
 
                rs.close();
                ps.close();
                c.close();
 
            } else {
                respuesta = "No hay conexión a la base.";
            }
 
        } catch (Exception e) {
            respuesta = "Error de ejecución en consulta(): " + e.getMessage();
        }
    }
    public void EliminarUsuario() {
        try {
            Connection c = Conexion.conectar(); 
 
            if (c != null) {
                PreparedStatement check = c.prepareStatement("SELECT * FROM Usuarios WHERE usuario = ?");
                check.setString(1, getUsuario());
                ResultSet rs = check.executeQuery();
 
                if (rs.next()) {
                    PreparedStatement ps = c.prepareStatement("DELETE FROM Usuarios WHERE usuario = ?");
                    ps.setString(1, getUsuario());
                    ps.executeUpdate();
                    ps.close();
 
                    respuesta = "Usuario eliminado correctamente.<br><a href='Usuarios.html'>Regresar</a>";
                } else {
                    respuesta = "No se encontró el Usuario '" + getUsuario() + "'.";
                }
 
                rs.close();
                check.close();
                c.close();
 
            } else {
                respuesta = "No hay conexión a la base.";
            }
 
        } catch (Exception e) {
            respuesta = "Error de ejecución en baja(): " + e.getMessage();
        }
    }
     public void Generacita() {
        try {
            Connection c = Conexion.conectar(); // usamos tu clase Conexion
 
            if (c != null) {PreparedStatement check = c.prepareStatement("SELECT * FROM Citas WHERE nombres = ?");
                check.setString(1, getNombres());
                ResultSet rs = check.executeQuery();
                if (rs.next()) {
                    respuesta = "El nombre '" + getNombres() + "' ya está registrado.";
                } else {PreparedStatement ps = c.prepareStatement( "INSERT INTO Citas VALUES (?,?,?,?,?,?,?)");
                    ps.setString(1, getNombres());
                    ps.setString(2, getCpaterno());
                    ps.setString(3, getCmaterno());
                    ps.setInt(4, getEdad());
                    ps.setString(5, getGenero());
                    ps.setDate(6, java.sql.Date.valueOf(getFechacita()));
                    ps.setTime(7, java.sql.Time.valueOf(getHoracita()));
    
                    ps.executeUpdate();
                    ps.close();
                    respuesta = "Cita agregada correctamente.<br><a href='MenuAdmin.html'>Regresar</a>";
                }
 
                rs.close();
                check.close();
                c.close();
 
            } else {
                respuesta = "No hay conexión a la base.";
            }
 
        } catch (Exception e) {
            respuesta = "Error de ejecución en alta(): " + e.getMessage();
        }
 
    }
 
}
