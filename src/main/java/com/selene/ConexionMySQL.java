package com.selene;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    
    // Librería de MySQL
    public String driver = "com.mysql.jdbc.Driver";

    // Nombre de la base de datos
    public String database = "multisales";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://localhost:3306/multisales?serverTimezone=UTC"; 
    //+ hostname + ":" + port + "/" + database + "?allowPublicKeyRetrieval=true&useSSL=false";

    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "root";

    public Connection conectarMySQL() {

        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
