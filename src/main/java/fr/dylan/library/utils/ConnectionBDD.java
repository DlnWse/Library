package fr.dylan.library.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionBDD {

    private Connection databaseLink;

    public Connection getConnection(){
        String db = "library";
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/" + db;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");

        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Erreur de connection");
        }

        return databaseLink;
    }

}
