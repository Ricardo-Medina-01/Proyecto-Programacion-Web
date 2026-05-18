package bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

public class Citas {

    private int idPaciente;

    private String usuarioMedico;
    private String fecha;
    private String hora;
    private String motivo;

    private String respuesta;
    private int idCita;

    public int getIdCita() {
        return idCita;
    }
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }
    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getUsuarioMedico() {
        return usuarioMedico;
    }

    public void setUsuarioMedico(String usuarioMedico) {
        this.usuarioMedico = usuarioMedico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void altaCita(){
        try{
            Connection c = Conexion.conectar();
            if(c != null){CallableStatement cs =c.prepareCall("{call AltaCita(?,?,?,?,?,?)}");
                cs.setInt(1, idPaciente);
                cs.setString(2, usuarioMedico);
                cs.setDate(3, java.sql.Date.valueOf(fecha));
                cs.setTime(4, java.sql.Time.valueOf(hora + ":00"));
                cs.setString(5, motivo);
                cs.registerOutParameter(6, Types.VARCHAR);
                cs.execute();
                respuesta = cs.getString(6);
            }else{
                respuesta = "No hay conexión";
            }
        }catch(Exception e){
            respuesta = "Error al registrar cita " + e;
        }
    }
    public void llenarMedicos(){
        try{
            Connection c = Conexion.conectar();

            if(c != null){
                CallableStatement cs = c.prepareCall("{call SelectorMedicos}");
                ResultSet rs = cs.executeQuery();

                respuesta = "";

                while(rs.next()){
                    String usuario = rs.getString("usuario");
                    respuesta += "<option value='" + usuario + "'>" + usuario + "</option>";
                }

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error médicos " + e;
        }
    }
    public void consultarCitas(){
        try{
            Connection c = Conexion.conectar();

            if(c != null){
                CallableStatement cs = c.prepareCall("{call ConsultarCitas}");
                ResultSet rs = cs.executeQuery();

                respuesta = "<table border='1'>";
                respuesta += "<tr>";
                respuesta += "<th>ID Cita</th>";
                respuesta += "<th>Paciente</th>";
                respuesta += "<th>Médico</th>";
                respuesta += "<th>Fecha</th>";
                respuesta += "<th>Hora</th>";
                respuesta += "<th>Motivo</th>";
                respuesta += "<th>Status</th>";
                respuesta += "</tr>";

                while(rs.next()){
                    respuesta += "<tr>";
                    respuesta += "<td>" + rs.getInt("idCita") + "</td>";
                    respuesta += "<td>" + rs.getString("paciente") + "</td>";
                    respuesta += "<td>" + rs.getString("usuarioMedico") + "</td>";
                    respuesta += "<td>" + rs.getString("fecha") + "</td>";
                    respuesta += "<td>" + rs.getString("hora") + "</td>";
                    respuesta += "<td>" + rs.getString("motivo") + "</td>";
                    respuesta += "<td>" + rs.getString("status") + "</td>";
                    respuesta += "</tr>";
                }

                respuesta += "</table>";

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error al consultar citas " + e;
        }
    }
    public void llenarCitasActivas(){
        try{
            Connection c = Conexion.conectar();

            if(c != null){
                CallableStatement cs = c.prepareCall("{call SelectorCitasActivas}");
                ResultSet rs = cs.executeQuery();

                respuesta = "";

                while(rs.next()){
                    int id = rs.getInt("idCita");
                    respuesta += "<option value='" + id + "'>" + id + "</option>";
                }

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error citas " + e;
        }
    }
    public void cancelarCita(){
        try{
            Connection c = Conexion.conectar();

            if(c != null){
                CallableStatement cs = c.prepareCall("{call CancelarCita(?,?)}");

                cs.setInt(1, idCita);
                cs.registerOutParameter(2, Types.VARCHAR);

                cs.execute();

                respuesta = cs.getString(2);

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error al cancelar cita " + e;
        }
    }
    public void modificarCita(){
        try{
            Connection c = Conexion.conectar();

            if(c != null){
                CallableStatement cs = c.prepareCall("{call ModificarCita(?,?,?,?,?)}");

                cs.setInt(1, idCita);
                cs.setDate(2, java.sql.Date.valueOf(fecha));
                cs.setTime(3, java.sql.Time.valueOf(hora + ":00"));
                cs.setString(4, motivo);
                cs.registerOutParameter(5, Types.VARCHAR);

                cs.execute();

                respuesta = cs.getString(5);

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error al modificar cita " + e;
        }
    }
}