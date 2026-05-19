package bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

public class Pacientes {

    private String nombre, paterno, materno, telefono, email, fechaNacimiento, direccion;
    private String respuesta;
    private int idPaciente;
    private String aPaterno, aMaterno;

    public String getaPaterno() {
        return aPaterno;
    }

    public void setaPaterno(String aPaterno) {
        this.aPaterno = aPaterno;
    }

    public String getaMaterno() {
        return aMaterno;
    }

    public void setaMaterno(String aMaterno) {
        this.aMaterno = aMaterno;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void altaPaciente() {
        try {
            Connection c = Conexion.conectar();

            if (c != null) {

                CallableStatement cs = c.prepareCall("{call AltaPaciente(?,?,?,?,?,?,?,?)}");

                cs.setString(1, nombre);
                cs.setString(2, paterno);
                cs.setString(3, materno);
                cs.setString(4, telefono);
                cs.setString(5, email);
                cs.setDate(6, java.sql.Date.valueOf(fechaNacimiento));
                cs.setString(7, direccion);

                cs.registerOutParameter(8, Types.VARCHAR);

                cs.execute();

                respuesta = cs.getString(8);

            } else {
                respuesta = "No hay conexión a la base";
            }

        } catch (Exception e) {
            respuesta = "Error al registrar paciente: " + e;
        }
    }
    public void consultarPacientes(){
        try{
            Connection c = Conexion.conectar();

            if(c != null){

                CallableStatement cs = c.prepareCall("{call ConsultarPacientes}");
                ResultSet rs = cs.executeQuery();

                respuesta = "<table border='1'>";

                respuesta += "<tr>";
                respuesta += "<th>ID</th>";
                respuesta += "<th>Nombre</th>";
                respuesta += "<th>Paterno</th>";
                respuesta += "<th>Materno</th>";
                respuesta += "<th>Teléfono</th>";
                respuesta += "<th>Email</th>";
                respuesta += "<th>Fecha Nacimiento</th>";
                respuesta += "<th>Dirección</th>";
                respuesta += "<th>Status</th>";
                respuesta += "</tr>";

                while(rs.next()){
                    respuesta += "<tr>";
                    respuesta += "<td>" + rs.getInt("idPaciente") + "</td>";
                    respuesta += "<td>" + rs.getString("nombre") + "</td>";
                    respuesta += "<td>" + rs.getString("aPaterno") + "</td>";
                    respuesta += "<td>" + rs.getString("aMaterno") + "</td>";
                    respuesta += "<td>" + rs.getString("telefono") + "</td>";
                    respuesta += "<td>" + rs.getString("email") + "</td>";
                    respuesta += "<td>" + rs.getString("fechaNacimiento") + "</td>";
                    respuesta += "<td>" + rs.getString("direccion") + "</td>";
                    respuesta += "<td>" + rs.getString("status") + "</td>";
                    respuesta += "</tr>";
                }

                respuesta += "</table>";

            }else{
                respuesta = "No hay conexión a la base";
            }

        }catch(Exception e){
            respuesta = "Error al consultar pacientes: " + e;
        }
    }
    public void llenarPacientes(){
        try{
            Connection c = Conexion.conectar();
            if(c != null){
                CallableStatement cs =c.prepareCall("{call SelectorPacientes}");
                ResultSet rs = cs.executeQuery();
                respuesta = "";
                while(rs.next()){
                    respuesta +="<option value='" +rs.getInt("idPaciente") +"'>" +rs.getInt("idPaciente") +" - " +rs.getString("nombre") +" " +rs.getString("aPaterno") +"</option>";
                }
            }else{
                respuesta = "No hay conexión";
            }
        }catch(Exception e){
            respuesta = "Error " + e;
        }
    }
    public void modificarPaciente(){

        try{

            Connection c = Conexion.conectar();

            if(c != null){

                CallableStatement cs =
                c.prepareCall("{call ModificarPaciente(?,?,?,?,?,?,?,?)}");

                cs.setInt(1, idPaciente);
                cs.setString(2, nombre);
                cs.setString(3, paterno);
                cs.setString(4, materno);
                cs.setString(5, telefono);
                cs.setString(6, email);
                cs.setString(7, direccion);

                cs.registerOutParameter(8, Types.VARCHAR);

                cs.execute();

                respuesta = cs.getString(8);

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error al modificar paciente " + e;
        }
    }
    public void desactivarPaciente(){
        try{
            Connection c = Conexion.conectar();
            if(c != null){
                CallableStatement cs = c.prepareCall("{call DesactivarPaciente(?,?)}");
                cs.setInt(1, idPaciente);
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.execute();
                respuesta = cs.getString(2);
            }else{
                respuesta = "No hay conexión";
            }
        }catch(Exception e){
            respuesta = "Error " + e;
        }
    }
    public void activarPaciente(){
        try{
            Connection c = Conexion.conectar();
            if(c != null){CallableStatement cs =c.prepareCall("{call ActivarPaciente(?,?)}");
                cs.setInt(1, idPaciente);
                cs.registerOutParameter(2, Types.VARCHAR);
                cs.execute();
                respuesta = cs.getString(2);
            }else{
                respuesta = "No hay conexión";
            }
        }catch(Exception e){
            respuesta = "Error al activar paciente " + e;
        }
    }
    public void llenarPacientesTodos(){
        try{
            Connection c = Conexion.conectar();
            if(c != null){
                CallableStatement cs =c.prepareCall("{call SelectorPacientesTodos}");
                ResultSet rs = cs.executeQuery();
                respuesta = "";
                while(rs.next()){
                    respuesta +=
                    "<option value='" + rs.getInt("idPaciente") + "'>" +
                    rs.getInt("idPaciente") + " - " +
                    rs.getString("nombre") + " " +
                    rs.getString("aPaterno") +
                    " (" + rs.getString("status") + ")" +
                    "</option>";
                }
            }else{
                respuesta = "No hay conexión";
            }
        }catch(Exception e){
            respuesta = "Error " + e;
        }
    }
    public void buscarPaciente(){
        try{
            Connection c = Conexion.conectar();
            if(c != null){
                CallableStatement cs = c.prepareCall("{call BuscarPaciente(?)}");
                cs.setInt(1, idPaciente);
                ResultSet rs = cs.executeQuery();
                if(rs.next()){
                    nombre = rs.getString("nombre");
                    paterno = rs.getString("aPaterno");
                    materno = rs.getString("aMaterno");
                    telefono = rs.getString("telefono");
                    email = rs.getString("email");
                    fechaNacimiento = rs.getString("fechaNacimiento");
                    direccion = rs.getString("direccion");
                }else{
                    respuesta = "Paciente no encontrado";
                }
            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error al buscar paciente " + e;
        }
    }
}