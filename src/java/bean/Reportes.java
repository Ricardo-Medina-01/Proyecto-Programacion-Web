package bean;

import java.sql.*;
import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Reportes {

    private String periodo;
    private String respuesta;

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public void generarBarras(){
        try{
            DefaultCategoryDataset datos = new DefaultCategoryDataset();

            Connection c = Conexion.conectar();
            CallableStatement cs = c.prepareCall("{call MedicosSolicitadosPeriodo(?)}");
            cs.setString(1, periodo);

            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                datos.addValue(
                    rs.getInt("total"),
                    rs.getString("medico"),
                    ""
                );
            }

            JFreeChart grafico = ChartFactory.createBarChart3D(
                "Médicos más solicitados",
                "Médico",
                "Consultas",
                datos,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
            );

            ChartUtilities.saveChartAsJPEG(
                new File("C:\\Users\\Admin\\OneDrive\\TESE\\2026-1\\Programacion Web - Recurse\\Proyecto Tercer Parcial\\Proyecto3P\\documents\\barras.jpg"),
                grafico,
                600,
                400
            );

        }catch(Exception e){
            respuesta = "Error al generar barras " + e;
        }
    }

    public void generarPie(){
        try{
            DefaultPieDataset datos = new DefaultPieDataset();

            Connection c = Conexion.conectar();
            CallableStatement cs = c.prepareCall("{call MedicosSolicitadosPeriodo(?)}");
            cs.setString(1, periodo);

            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                datos.setValue(
                    rs.getString("medico"),
                    rs.getInt("total")
                );
            }

            JFreeChart grafico = ChartFactory.createPieChart(
                "Médicos más solicitados",
                datos,
                true,
                true,
                false
            );

            ChartUtilities.saveChartAsJPEG(
                new File("C:\\Users\\Admin\\OneDrive\\TESE\\2026-1\\Programacion Web - Recurse\\Proyecto Tercer Parcial\\Proyecto3P\\documents\\pie.jpg"),
                grafico,
                600,
                400
            );

        }catch(Exception e){
            respuesta = "Error al generar pie " + e;
        }
    }

    public void crearPdfBarras(){
        generarBarras();

        try{
            Document documento = new Document();

            PdfWriter.getInstance(
                documento,
                new FileOutputStream("C:\\Users\\Admin\\OneDrive\\TESE\\2026-1\\Programacion Web - Recurse\\Proyecto Tercer Parcial\\Proyecto3P\\documents\\ReporteBarras.pdf")
            );

            documento.open();

            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setFont(FontFactory.getFont("Arial",20,Font.BOLD,BaseColor.BLUE));
            titulo.add("REPORTE DE CONSULTAS - BARRAS");
            documento.add(titulo);

            documento.add(new Paragraph("Periodo: " + periodo));
            documento.add(new Paragraph(" "));

            Image imagen = Image.getInstance("C:\\Users\\Admin\\OneDrive\\TESE\\2026-1\\Programacion Web - Recurse\\Proyecto Tercer Parcial\\Proyecto3P\\documents\\barras.jpg");
            imagen.setAlignment(Image.ALIGN_CENTER);
            imagen.scalePercent(60);
            documento.add(imagen);

            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6);

            tabla.addCell("ID Cita");
            tabla.addCell("Paciente");
            tabla.addCell("Médico");
            tabla.addCell("Motivo");
            tabla.addCell("Medicamento");
            tabla.addCell("Fecha Consulta");

            Connection c = Conexion.conectar();
            CallableStatement cs = c.prepareCall("{call ReporteConsultasPeriodo(?)}");
            cs.setString(1, periodo);

            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                tabla.addCell(String.valueOf(rs.getInt("idCita")));
                tabla.addCell(rs.getString("paciente"));
                tabla.addCell(rs.getString("medico"));
                tabla.addCell(rs.getString("motivo"));
                tabla.addCell(rs.getString("medicamento"));
                tabla.addCell(rs.getString("fechaConsulta"));
            }

            documento.add(tabla);
            documento.close();

            respuesta = "PDF con gráfica de barras creado";

        }catch(Exception e){
            respuesta = "Error PDF barras " + e;
        }
    }

    public void crearPdfPie(){
        generarPie();

        try{
            Document documento = new Document();

            PdfWriter.getInstance(
                documento,
                new FileOutputStream("C:\\Users\\Admin\\OneDrive\\TESE\\2026-1\\Programacion Web - Recurse\\Proyecto Tercer Parcial\\Proyecto3P\\documents\\ReportePie.pdf")
            );

            documento.open();

            Paragraph titulo = new Paragraph();
            titulo.setAlignment(Paragraph.ALIGN_CENTER);
            titulo.setFont(FontFactory.getFont("Arial",20,Font.BOLD,BaseColor.BLUE));
            titulo.add("REPORTE DE CONSULTAS - PIE");
            documento.add(titulo);

            documento.add(new Paragraph("Periodo: " + periodo));
            documento.add(new Paragraph(" "));

            Image imagen = Image.getInstance("C:\\Users\\Admin\\OneDrive\\TESE\\2026-1\\Programacion Web - Recurse\\Proyecto Tercer Parcial\\Proyecto3P\\documents\\pie.jpg");
            imagen.setAlignment(Image.ALIGN_CENTER);
            imagen.scalePercent(60);
            documento.add(imagen);

            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6);

            tabla.addCell("ID Cita");
            tabla.addCell("Paciente");
            tabla.addCell("Médico");
            tabla.addCell("Motivo");
            tabla.addCell("Medicamento");
            tabla.addCell("Fecha Consulta");

            Connection c = Conexion.conectar();
            CallableStatement cs = c.prepareCall("{call ReporteConsultasPeriodo(?)}");
            cs.setString(1, periodo);

            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                tabla.addCell(String.valueOf(rs.getInt("idCita")));
                tabla.addCell(rs.getString("paciente"));
                tabla.addCell(rs.getString("medico"));
                tabla.addCell(rs.getString("motivo"));
                tabla.addCell(rs.getString("medicamento"));
                tabla.addCell(rs.getString("fechaConsulta"));
            }

            documento.add(tabla);
            documento.close();

            respuesta = "PDF con gráfica de pie creado";

        }catch(Exception e){
            respuesta = "Error PDF pie " + e;
        }
    }

    public void exportarExcel(){
        try{
            File f = new File("C:\\Users\\Admin\\OneDrive\\TESE\\2026-1\\Programacion Web - Recurse\\Proyecto Tercer Parcial\\Proyecto3P\\documents\\consultas.xls");
            FileWriter fw = new FileWriter(f,false);

            fw.write("ID Cita\tPaciente\tMedico\tMotivo\tMedicamento\tFecha Consulta\n");

            Connection c = Conexion.conectar();
            CallableStatement cs = c.prepareCall("{call ReporteConsultasPeriodo(?)}");
            cs.setString(1, periodo);

            ResultSet rs = cs.executeQuery();

            while(rs.next()){
                fw.write(
                    rs.getInt("idCita") + "\t" +
                    rs.getString("paciente") + "\t" +
                    rs.getString("medico") + "\t" +
                    rs.getString("motivo") + "\t" +
                    rs.getString("medicamento") + "\t" +
                    rs.getString("fechaConsulta") + "\n"
                );
            }

            fw.close();

            respuesta = "Excel creado";

        }catch(Exception e){
            respuesta = "Error Excel " + e;
        }
    }
}