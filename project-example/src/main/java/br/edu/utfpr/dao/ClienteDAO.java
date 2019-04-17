package br.edu.utfpr.dao;

import br.edu.utfpr.dto.ClienteDTO;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;
import lombok.extern.java.Log;

@Log
public class ClienteDAO {

    // Responsável por criar a tabela Cliente no banco.
    public ClienteDAO() {

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            log.info("Criando tabela cliente ...");
            conn.createStatement().executeUpdate(
            "CREATE TABLE cliente (" +
						"id int NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT id_cliente_pk PRIMARY KEY," +
						"nome varchar(255)," +
						"telefone varchar(30)," + 
						"idade int," + 
                        "limiteCredito double," +
                        "id_pais int)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // Método invocado em ClienteNegocio.java
    public ClienteDTO inserirCliente(ClienteDTO cliente) throws SQLException {
        
        // Referenciando a conexão em si com o banco
        Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true");

        log.info("Inserindo cliente ...");
        
        try{
            conn.createStatement().executeUpdate(
                "INSERT INTO cliente VALUES (?,?,?,?,?,?)"
            );
            
            return cliente;
        }
        catch (Exception e) {
            e.printStackTrace();
            // Em caso de exceção, disparo uma exceção em tempo de execução
            throw new RuntimeException(e);
        }
                
    }
    
    // Método invocado em ClienteNegocio.java
    public void listarClientes() throws SQLException {
        
        // De forma análoga ao método inserirCliente
        Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true");

        log.info("Listando clientes ...");
        
        try{
            conn.createStatement().executeUpdate("SELECT * FROM cliente");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
                
    }
    
}
