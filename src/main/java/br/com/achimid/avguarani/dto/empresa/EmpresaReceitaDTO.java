package br.com.achimid.avguarani.dto.empresa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpresaReceitaDTO {

    private String status;
    private String message;
    private BillingDTO billing;
    private String CNPJ;
    private String tipo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date abertura;

    private String nome;
    private String fantasia;

    @JsonProperty("atividade_principal")
    private Collection<AtividadeDTO> atividadePrincipal;

    @JsonProperty("atividades_secundarias")
    private Collection<AtividadeDTO> atividadesSecundarias;

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

    private Collection<QuadroSADTO> qsa;

    private Object extra;







}