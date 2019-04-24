package br.edu.utfpr.dto;

import br.edu.utfpr.excecao.IdadeClienteInvalidaException;
import br.edu.utfpr.excecao.NomePaisNuloException;
import lombok.Builder;
import lombok.Data;
import br.edu.utfpr.excecao.NomeClienteMenor5CaracteresException;

@Data
public class ClienteDTO {
    private int id;
    private String nome;
    private int idade;
    private String telefone;
    private double limiteCredito;
    private PaisDTO pais;

    // "O nome do cliente não pode ser menor que 5 caracteres"
    public void setNome(String nome) throws NomeClienteMenor5CaracteresException {
        if (nome.length() < 5)
            throw new NomeClienteMenor5CaracteresException(nome);

        this.nome = nome;
    }

    // "o campo país não pode ser nulo/vazio;"
    public void setPais(PaisDTO pais) throws NomePaisNuloException{
        if(pais.getNome().isEmpty())
            throw new NomePaisNuloException(pais.getNome());

        this.pais = pais;
    }

    // "O limite de crédito é dado automaticamente de acordo com a idade: Para clientes até 18 anos o limite é R$ 100,00.
    //  Entre 18 e 35 é R$ 300,00. Acima de 35 é R$ 500,00;"
    public void validarLimiteCredito() throws IdadeClienteInvalidaException {
        if (this.idade > 0 && this.idade < 19) {
            this.limiteCredito = 100;
        } else if (this.idade > 18 && this.idade < 35) {
            this.limiteCredito = 300;
        } else if (this.idade >= 35) {
            this.limiteCredito = 500;
        } else {
            throw new IdadeClienteInvalidaException(String.valueOf(this.idade));
        }

        // "Se o cliente morar no Brasil, ele terá um crédito adicional de R$ 100,00, independente da idade;"
        // "Cada país deverá ser identificado pelo nome e abreviação;"
        // Pegando o nome do pais e deixando e letras minúsculas para garantir que não haja inconsistências
        if (this.pais.getNome().toLowerCase().equals("brasil") && this.pais.getSigla().toLowerCase().equals("br")) {
            this.limiteCredito += 100;
        }
    }


}