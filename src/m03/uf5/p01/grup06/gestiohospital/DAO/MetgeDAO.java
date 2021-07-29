package m03.uf5.p01.grup06.gestiohospital.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import m03.uf5.p01.grup06.gestiohospital.modelo.Adreca;
import m03.uf5.p01.grup06.gestiohospital.modelo.Metge;
import m03.uf5.p01.grup06.gestiohospital.utils.GestorConnexioJDBC;

public class MetgeDAO {

    public static ResultSet getAllMetgesRS() {
        try {
            Connection conn = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM METGES;");
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static Metge[] getAllMetges() {
        try {
            ArrayList<Metge> listMalalties = new ArrayList<>();

            ResultSet rs = getAllMetgesRS();

            while (rs.next()) {
                listMalalties.add(createMetgeObj(rs));
            }
            Metge[] arrayMalalties = new Metge[listMalalties.size()];
            return listMalalties.toArray(arrayMalalties);

        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static ResultSet getMetgeByDNIRS(String dni) {
        try {
            Connection conn = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM METGES WHERE nifMetge = ?;");
            sentencia.setString(1, dni);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static Metge getMetgeByDNI(String dni) {
        try {
            ResultSet rs = getMetgeByDNIRS(dni);
            rs.next();
            return createMetgeObj(rs);
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    private static Metge createMetgeObj(ResultSet rs) throws SQLException {
        int numEmpleat = rs.getInt("numEmpleat");
        String nifMetge = rs.getString("nifMetge");
        String nomMetge = rs.getString("nomMetge");
        String cognom1Metge = rs.getString("cognom1Metge");
        String cognom2Metge = rs.getString("cognom2Metge");
        String numSegSoc = rs.getString("numSegSoc");
        String telefon = rs.getString("telefon");
        int salariMensual = rs.getInt("salariMensual");
        String codiCompte = rs.getString("codiCompte");
        String ciutat = rs.getString("ciutat");
        long codiPostal = rs.getLong("codiPostal");
        String carrer = rs.getString("carrer");
        int numero = rs.getInt("numero");
        String planta = rs.getString("planta");
        String porta = rs.getString("porta");

        Adreca adreca = new Adreca(ciutat, codiPostal, carrer, numero, planta, porta);
        return new Metge(nomMetge, cognom1Metge, cognom2Metge, numSegSoc, nifMetge, telefon, adreca, numEmpleat, salariMensual, codiCompte);           
    }

    public static ResultSet getMetgeBySSRS(String numSS) {
        try {
            Connection conn = GestorConnexioJDBC.getConnection();
            PreparedStatement sentencia = conn.prepareStatement("SELECT * FROM METGES WHERE numSegSoc = ?;");
            sentencia.setString(1, numSS);
            sentencia.executeQuery();
            return sentencia.getResultSet();
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return null;
        }
    }

    public static boolean updateMetge(Metge metge) {
        try {
            Connection conn = GestorConnexioJDBC.getConnection();
            CallableStatement cStmt = conn.prepareCall("{call actualizaMetge(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            cStmt.setString(1, metge.getNif());
            cStmt.setInt(2, metge.getNumEmpleat());
            cStmt.setString(3, metge.getNom());
            cStmt.setString(4, metge.getCognom1());
            cStmt.setString(5, metge.getCognom2());
            cStmt.setString(6, metge.getNumSegSocial());
            cStmt.setString(7, metge.getTelefon());
            cStmt.setInt(8, metge.getSalariMensual());
            cStmt.setString(9, metge.getCodiCompteCorrent());
            cStmt.setString(10, metge.getAdreca().getCiutat());
            cStmt.setLong(11, metge.getAdreca().getCodiPostal());
            cStmt.setString(12, metge.getAdreca().getCarrer());
            cStmt.setInt(13, metge.getAdreca().getNumero());
            cStmt.setString(14, metge.getAdreca().getPlanta());
            cStmt.setString(15, metge.getAdreca().getPorta());
            
            cStmt.execute();
            return true;
        } catch (SQLException ex) {
            System.out.println("ERROR CONSULTA SQL: " + ex.getMessage());
            return false;
        }
    }

    public static boolean createMetge(Metge metge) throws SQLException {
        Connection conn = GestorConnexioJDBC.getConnection();
        PreparedStatement sentencia = conn.prepareStatement("INSERT INTO METGES "
                + "(nomMetge, cognom1Metge, cognom2Metge, numSegSoc, nifMetge, telefon, ciutat, codiPostal, carrer, numero, planta, porta, numEmpleat, salariMensual, codiCompte) "
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        sentencia.setString(1, metge.getNom());
        sentencia.setString(2, metge.getCognom1());
        sentencia.setString(3, metge.getCognom2());
        sentencia.setString(4, metge.getNumSegSocial());
        sentencia.setString(5, metge.getNif());
        sentencia.setString(6, metge.getTelefon());
        sentencia.setString(7, metge.getAdreca().getCiutat());
        sentencia.setLong(8, metge.getAdreca().getCodiPostal());
        sentencia.setString(9, metge.getAdreca().getCarrer());
        sentencia.setInt(10, metge.getAdreca().getNumero());
        sentencia.setString(11, metge.getAdreca().getPlanta());
        sentencia.setString(12, metge.getAdreca().getPorta());
        sentencia.setInt(13, metge.getNumEmpleat());
        sentencia.setInt(14, metge.getSalariMensual());
        sentencia.setString(15, metge.getCodiCompteCorrent());

        sentencia.executeUpdate();
        return true;
    }
}
