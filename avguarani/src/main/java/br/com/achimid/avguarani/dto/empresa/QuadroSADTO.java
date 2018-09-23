package br.com.achimid.avguarani.dto.empresa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class QuadroSADTO {

    private String nome;
    private String qual;

    @JsonProperty("pais_origem")
    private String paisOrigem;

    @JsonProperty("nome_rep_legal")
    private String nomeRepLegal;

    @JsonProperty("qual_rep_legal")
    private String qualRepLegal;

}