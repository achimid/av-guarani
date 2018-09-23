package br.com.achimid.avguarani.dto.empresa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
public class ReceitaEmpresaDTO {

    private String status;
    private String message;
    private Billing billing;
    private String CNPJ;
    private String tipo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date abertura;

    private String nome;
    private String fantasia;

    @JsonProperty("atividade_principal")
    private Collection<Atividade> atividadePrincipal;

    @JsonProperty("atividades_secundarias")
    private Collection<Atividade> atividadesSecundarias;

    private String naturezaJuridica;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String uf;
    private String email;
    private String telefone;
    private String efr;
    private String situacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("data_situacao")
    private Date dataSituacao;

    @JsonProperty("motivo_situacao")
    private String motivoSituacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty("data_situacao_especial")
    private Date dataSituacaoEspecial;

    @JsonProperty("situacao_especial")
    private String situacaoEspecial;

    @JsonProperty("capital_social")
    private BigDecimal capitalSocial;

    private Collection<QuadroSA> qsa;

    private Object extra;

    @Data
    class Billing {
        private boolean free;
        private boolean database;
    }

    @Data
    class Atividade {
        private String code;
        private String text;
    }

    @Data
    class QuadroSA {
        private String nome;
        private String qual;

        @JsonProperty("pais_origem")
        private String paisOrigem;

        @JsonProperty("nome_rep_legal")
        private String nomeRepLegal;

        @JsonProperty("qual_rep_legal")
        private String qualRepLegal;
    }

}