package br.edu.utfpr.negocio;

import java.util.List;

import br.edu.utfpr.dao.ClienteDAO;
import br.edu.utfpr.dto.ClienteDTO;
import br.edu.utfpr.excecao.NomeClienteJaExisteException;

public class ClienteNegocio {

    public void incluir(ClienteDTO cliente) throws NomeClienteJaExisteException {

        if (this.listar().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(cliente.getNome())))
            throw new NomeClienteJaExisteException(cliente.getNome());

        // Chamar ClienteDAO para realizar persistência
        ClienteDAO cliDAO = new ClienteDAO();

        // Se o nome descrito ainda não existe no banco, então insere
        cliDAO.inserir(cliente);
    }

    public List<ClienteDTO> listar() {

        // Usar ClienteDAO para retornar valores no banco
        ClienteDAO cliDAO = new ClienteDAO();
        cliDAO.listar();

        throw new UnsupportedOperationException();
    }
}