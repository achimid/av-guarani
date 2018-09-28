package br.com.achimid.avguarani.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Endereco extends ModelBase {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;

}
