package m03.uf5.p01.grup06.gestiohospital.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import m03.uf5.p01.grup06.gestiohospital.modelo.Visita;
import m03.uf5.p01.grup06.gestiohospital.utils.GestorConnexioJDBC;

public class VisitaDAO {
    public static ResultSet getAllVisitesRS() {
        try {
            Connection conn = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM VISITES ORDER BY fecha DESC;");
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }
    
    public static ResultSet getVisitaByDNIRS(String dni) {
        try {
            Connection conn = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM VISITES WHERE dniPacient = ?;");
            sentencia.setString(1, dni);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }
    
    public static ResultSet getVisitaByCodiHistorialRS(int codiHistorial) {
        try {
            Connection conn = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM VISITES WHERE dniPacient = (SELECT codiHistorial FROM PACIENTS WHERE codiHistorial = ?);");
            sentencia.setInt(1, codiHistorial);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }
    
    public static boolean createVisita(Visita visita) throws SQLException {
        try {
            Connection con = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = null;
            String consulta = "INSERT INTO VISITES"
                    + " (fecha, codiMalaltia, dniMetge, dniPacient)"
                    + " VALUES (?,?,?,?)";
            sentencia = con.prepareStatement(consulta);
            sentencia.setString(1, visita.getData().format(DateTimeFormatter.ofPattern("uuuu-MM-d HH:mm:ss")));
            sentencia.setInt(2, visita.getMalaltia().getCodi());
            sentencia.setString(3, visita.getMetge().getNif());
            sentencia.setString(4, visita.getDni());
            sentencia.executeUpdate();
            return true;
            
        } catch (NullPointerException ex) {
            System.out.println("ERROR NULL OBJ VISITA: " + ex.getMessage());
            return false;
        }
    }
}
