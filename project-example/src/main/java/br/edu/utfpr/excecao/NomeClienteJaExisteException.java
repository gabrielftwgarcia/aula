package br.edu.utfpr.excecao;

//utilizado no método Incluir de ClienteNegocio.java
public class NomeClienteJaExisteException extends Exception {
    public NomeClienteJaExisteException (String descricao) {
        super(descricao);
    }
}