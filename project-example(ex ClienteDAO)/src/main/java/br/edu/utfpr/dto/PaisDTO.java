package br.edu.utfpr.dto;

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
}