package br.edu.utfpr.negocio;

import java.util.List;

import br.edu.utfpr.dao.PaisDAO;
import br.edu.utfpr.dto.PaisDTO;
import br.edu.utfpr.excecao.NomePaisJaExisteException;

public class PaisNegocio {

    PaisDAO pdao = new PaisDAO();

    public void incluir(PaisDTO pais) throws NomePaisJaExisteException {

        // De forma análoga feita em ClienteNegocio, verifico se pais já existe
        if (this.listar().stream().anyMatch(c -> c.getNome().equalsIgnoreCase(pais.getNome())))
            throw new NomePaisJaExisteException(pais.getNome());

        pdao.incluir(pais);
    }

    public List<PaisDTO> listar() {
        return pdao.listarTodos();
    }

}