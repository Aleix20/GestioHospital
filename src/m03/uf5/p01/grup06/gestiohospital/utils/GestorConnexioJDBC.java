package m03.uf5.p01.grup06.gestiohospital.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class GestorConnexioJDBC {

    private static final String DRIVER_CLASS_NAME;
    private static final String DRIVER_URL;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Properties prop = new Properties();
        try {
            InputStream entrada = new FileInputStream("config.properties");
            prop.load(entrada);
        } catch (IOException e) {
            System.out.println("Error en carregar l'arxiu de propietats.");
        }
        DRIVER_CLASS_NAME = prop.getProperty("driver_class_name");
        DRIVER_URL = prop.getProperty("driver_url");
        USER = prop.getProperty("user");
        PASSWORD = prop.getProperty("password");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DRIVER_URL, USER, PASSWORD);
    }
}
