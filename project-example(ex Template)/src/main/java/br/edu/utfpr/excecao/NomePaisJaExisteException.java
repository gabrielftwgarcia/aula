package br.edu.utfpr.excecao;

public class NomePaisJaExisteException extends Exception{
    public NomePaisJaExisteException (String descricao) {
        super(descricao);
    }
}
