package bean;

import java.sql.*;

public class Consultas {

    private int idCita;

    private String medicamento;
    private String observaciones;

    private String respuesta;

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    // =====================================
    // SELECTOR CITAS ACTIVAS
    // =====================================

    public void llenarCitas(){

        try{

            Connection c = Conexion.conectar();

            if(c != null){

                CallableStatement cs =
                c.prepareCall("{call SelectorCitasActivas}");

                ResultSet rs = cs.executeQuery();

                respuesta = "";

                while(rs.next()){

                    int id = rs.getInt("idCita");

                    respuesta +=
                    "<option value='" +
                    id +
                    "'>" +
                    id +
                    "</option>";
                }

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error " + e;
        }
    }

    // =====================================
    // DATOS DE CITA
    // =====================================

    public void datosCita(){

        try{

            Connection c = Conexion.conectar();

            if(c != null){

                CallableStatement cs =
                c.prepareCall("{call DatosCita(?)}");

                cs.setInt(1, idCita);

                ResultSet rs = cs.executeQuery();

                if(rs.next()){

                    respuesta = "";

                    respuesta +=
                    "<h3>DATOS DE LA CITA</h3>";

                    respuesta +=
                    "<b>ID CITA:</b> " +
                    rs.getInt("idCita") +
                    "<br>";

                    respuesta +=
                    "<b>PACIENTE:</b> " +
                    rs.getString("paciente") +
                    "<br>";

                    respuesta +=
                    "<b>MÉDICO:</b> " +
                    rs.getString("usuarioMedico") +
                    "<br>";

                    respuesta +=
                    "<b>FECHA:</b> " +
                    rs.getString("fecha") +
                    "<br>";

                    respuesta +=
                    "<b>HORA:</b> " +
                    rs.getString("hora") +
                    "<br>";

                    respuesta +=
                    "<b>MOTIVO:</b> " +
                    rs.getString("motivo") +
                    "<br><br>";
                }

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error " + e;
        }
    }

    // =====================================
    // REGISTRAR CONSULTA
    // =====================================

    public void registrarConsulta(){

        try{

            Connection c = Conexion.conectar();

            if(c != null){

                CallableStatement cs =
                c.prepareCall(
                "{call RegistrarConsulta(?,?,?,?)}");

                cs.setInt(1, idCita);

                cs.setString(2, medicamento);

                cs.setString(3, observaciones);

                cs.registerOutParameter(4, Types.VARCHAR);

                cs.execute();

                respuesta = cs.getString(4);

            }else{
                respuesta = "No hay conexión";
            }

        }catch(Exception e){
            respuesta = "Error " + e;
        }
    }
}
