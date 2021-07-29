package m03.uf5.p01.grup06.gestiohospital.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import m03.uf5.p01.grup06.gestiohospital.modelo.Adreca;
import m03.uf5.p01.grup06.gestiohospital.modelo.Pacient;
import m03.uf5.p01.grup06.gestiohospital.utils.GestorConnexioJDBC;

public class PacienteDAO {

    public static ResultSet getAllPacientsRS() {
        try {
            Connection conn = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM PACIENTS;");
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static Pacient[] getAllPacients() {
        try {
            ArrayList<Pacient> listPacients = new ArrayList<>();
            ResultSet rs = getAllPacientsRS();
            while (rs.next()) {
                listPacients.add(createPacientObj(rs));
            }
            Pacient[] arrayPacients = new Pacient[listPacients.size()];
            return listPacients.toArray(arrayPacients);

        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet pacienteByNifRS(String nif) {
        try {
            Connection con = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = null;
            String consulta = "SELECT * FROM PACIENTS WHERE nifPacient = ?";
            sentencia = con.prepareStatement(consulta);
            sentencia.setString(1, nif);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet pacienteByNSS(String nss) {
        try {
            Connection con = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = null;
            String consulta = "SELECT * FROM PACIENTS WHERE numSegSoc = ?";
            sentencia = con.prepareStatement(consulta);
            sentencia.setString(1, nss);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet pacienteByCodiHistorial(int codiHist) {
        try {
            Connection con = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = null;
            String consulta = "SELECT * FROM PACIENTS WHERE codiHistorial = ?";
            sentencia = con.prepareStatement(consulta);
            sentencia.setInt(1, codiHist);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }
    
    public static Pacient getPacientByNif(String DNI) {
        try {
            ResultSet rs = pacienteByNifRS(DNI);
            rs.next();
            return createPacientObj(rs);            
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static boolean modificaPacient(Pacient pacient) {
        try {
            Connection con = GestorConnexioJDBC.getConnection();
            CallableStatement sentencia = null;
            String consulta = "{call actualizaPacient(?,?,?,?,?,?,?,?,?,?,?,?)}";
            sentencia = con.prepareCall(consulta);
            sentencia.setString(1, pacient.getNif());
            sentencia.setString(2, pacient.getNom());
            sentencia.setString(3, pacient.getCognom1());
            sentencia.setString(4, pacient.getCognom2());
            sentencia.setString(5, pacient.getNumSegSocial());
            sentencia.setString(6, pacient.getTelefon());
            sentencia.setString(7, pacient.getAdreca().getCiutat());
            sentencia.setLong(8, pacient.getAdreca().getCodiPostal());
            sentencia.setString(9, pacient.getAdreca().getCarrer());
            sentencia.setInt(10, pacient.getAdreca().getNumero());
            sentencia.setString(11, pacient.getAdreca().getPlanta());
            sentencia.setString(12, pacient.getAdreca().getPorta());
            sentencia.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return false;
        }
    }

    private static Pacient createPacientObj(ResultSet rs) throws SQLException {
        String nifPacient = rs.getString("nifPacient");
        int codiHistorial = rs.getInt("codiHistorial");
        String nomPacient = rs.getString("nomPacient");
        String cognom1Pacient = rs.getString("cognom1Pacient");
        String cognom2Pacient = rs.getString("cognom2Pacient");
        String numSegSoc = rs.getString("numSegSoc");
        String telefon = rs.getString("telefon");
        String ciutat = rs.getString("ciutat");
        long codiPostal = rs.getLong("codiPostal");
        String carrer = rs.getString("carrer");
        int numero = rs.getInt("numero");
        String planta = rs.getString("planta");
        String porta = rs.getString("porta");

        Adreca adreca = new Adreca(ciutat, codiPostal, carrer, numero, planta, porta);
        return new Pacient(nomPacient, cognom1Pacient, cognom2Pacient, numSegSoc, nifPacient, telefon, adreca);
    }

    public static boolean createPaciente(Pacient pacient) throws SQLException {
        Connection con = GestorConnexioJDBC.getConnection();
        PreparedStatement sentencia = null;
        String consulta = "INSERT INTO PACIENTS (nifPacient, codiHistorial, numSegSoc, nomPacient, cognom1Pacient, cognom2Pacient, telefon, ciutat, codiPostal, carrer, numero, planta, porta)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        sentencia = con.prepareStatement(consulta);
        sentencia.setString(1, pacient.getNif());
        sentencia.setInt(2, pacient.getHistorial().getCodi());
        sentencia.setString(3, pacient.getNumSegSocial());
        sentencia.setString(4, pacient.getNom());
        sentencia.setString(5, pacient.getCognom1());
        sentencia.setString(6, pacient.getCognom2());
        sentencia.setString(7, pacient.getTelefon());
        sentencia.setString(8, pacient.getAdreca().getCiutat());
        sentencia.setLong(9, pacient.getAdreca().getCodiPostal());
        sentencia.setString(10, pacient.getAdreca().getCarrer());
        sentencia.setInt(11, pacient.getAdreca().getNumero());
        sentencia.setString(12, pacient.getAdreca().getPlanta());
        sentencia.setString(13, pacient.getAdreca().getPorta());
        sentencia.executeUpdate();
        return true;
    }
}
