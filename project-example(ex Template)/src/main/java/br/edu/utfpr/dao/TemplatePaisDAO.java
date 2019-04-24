package br.edu.utfpr.dao;

import br.edu.utfpr.dto.PaisDTO;

import java.util.List;

public abstract class TemplatePaisDAO {

    protected abstract boolean incluir(PaisDTO pais);
    protected abstract boolean excluir(int id);
    protected abstract boolean alterar(PaisDTO pais);
    protected abstract List<PaisDTO> listarTodos();
    protected abstract PaisDTO getPaisById(int id);
    protected abstract PaisDTO getPaisByNomeSigla(String nome, String sigla);

}
