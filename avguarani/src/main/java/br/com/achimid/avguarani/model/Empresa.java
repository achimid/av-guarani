package br.com.achimid.avguarani.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Empresa extends ModelBase{

    @Id
    @GeneratedValue
    private Long id;
    private String cnpj;
    private String email;

    @OneToOne
    private Endereco endereco;

    @OneToOne
    private Moeda meoda;


}
