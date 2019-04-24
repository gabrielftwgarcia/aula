package br.edu.utfpr.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.dto.ClienteDTO;

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
    public boolean inserir(ClienteDTO cliente) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {
            String sql = "INSERT INTO cliente(nome, telefone, idade, limiteCredito, id_pais) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(sql);

            // Atribuindo valores p/ cliente
            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTelefone());
            pstm.setInt(3, cliente.getIdade());
            pstm.setDouble(4, cliente.getLimiteCredito());
            pstm.setInt(5, cliente.getPais().getId());

            log.info("Inserindo cliente ...");
            int rows = pstm.executeUpdate();
            if(rows > 0) {
                System.out.println("Cliente inserido !");
                return true;
            }

            //Encerrando conexão
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    public void update(ClienteDTO cliente) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {
            String sql = "UPDATE cliente SET nome=?, telefone=?, idade=?, limiteCredito=?, id_pais=? WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, cliente.getNome());
            pstm.setString(2, cliente.getTelefone());
            pstm.setInt(3, cliente.getIdade());
            pstm.setDouble(4, cliente.getLimiteCredito());
            pstm.setInt(5, cliente.getPais().getId());
            pstm.setInt(6, cliente.getId());

            log.info("Atualizando cliente ...");
            int rows = pstm.executeUpdate();
            if (rows > 0)
                System.out.println("Cliente atualizado !");

            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {
            String sql = "DELETE FROM cliente WHERE id=?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);

            log.info("Removendo cliente ...");
            int rows = pstm.executeUpdate();
            if (rows > 0) {
                System.out.println("Cliente excluido !");
            }
            pstm.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Método invocado em ClienteNegocio.java
    public List<ClienteDTO> listar() {
        List<ClienteDTO> listasClientes = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection("jdbc:derby:memory:database;create=true")) {

            PaisDAO paisDao = new PaisDAO();
            String sql = "SELECT * FROM cliente";

            Statement stm = conn.createStatement();
            log.info("Listando clientes ...");
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();

                cliente.setId(rs.getInt(1));
                cliente.setNome(rs.getString(2));
                cliente.setTelefone(rs.getString(3));
                cliente.setIdade(rs.getInt(4));
                cliente.setLimiteCredito(rs.getDouble(5));
                cliente.setPais(paisDao.getPaisById(rs.getInt(6)));

                listasClientes.add(cliente);
            }

            // Encerrando conexão
            rs.close();
            stm.close();


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return listasClientes;

    }
    
}