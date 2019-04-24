package br.edu.utfpr.dto;

import br.edu.utfpr.excecao.NomePaisNuloException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class PaisDTO {
    private int id;
    private String nome;
    private String sigla;
    private int codigoTelefone;

    // "Não é permitido mais de um país com o mesmo nome;"
    public void setNome(String nome) throws NomePaisNuloException {
        if(nome.isEmpty())
            throw new NomePaisNuloException(nome);

        this.nome = nome;
    }
}