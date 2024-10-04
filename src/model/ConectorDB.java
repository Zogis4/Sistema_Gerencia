package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorDB {

    public static Connection getConnection() {
        Connection connection = null;

        String url = "jdbc:mysql://localhost:3306/Gestão-Produtos";
        String usuario = "root";
        String senha = "root";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Fazendo a conexão
            connection = DriverManager.getConnection(url, usuario, senha);

            System.out.println("Database conectada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro estabelecendo conexão com a database: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySql não foi encontrado: " + e.getMessage());
        }

        return connection;
    }

    //Futuras Querries
}
