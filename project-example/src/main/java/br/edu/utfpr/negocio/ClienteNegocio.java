package br.edu.utfpr.negocio;

import br.edu.utfpr.dao.ClienteDAO;
import java.util.List;

import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteJaExisteException;
import java.sql.SQLException;

public class ClienteNegocio {

    public void incluir(ClienteDTO cliente) throws NomeClienteJaExisteException, SQLException {

        if (this.listar().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(cliente.getNome())))
            throw new NomeClienteJaExisteException(cliente.getNome());

        // Chamar ClienteDAO para realizar persistência
        ClienteDAO cd = new ClienteDAO();

        // Se o nome descrito ainda não existe no banco, então insere
        cd.inserirCliente(cliente);

    }

    public List<ClienteDTO> listar() throws SQLException {
        // Usar ClienteDAO para retornar valores no banco
        ClienteDAO cd = new ClienteDAO();
        cd.listarClientes();
        
        throw new UnsupportedOperationException();
        
    }
}