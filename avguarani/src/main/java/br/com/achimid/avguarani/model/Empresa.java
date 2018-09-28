package br.com.achimid.avguarani.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class Empresa extends ModelBase{

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String cnpj;
    private String cnpjUnmask;
    private String email;
    private String nome;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Endereco endereco;

    @OneToOne
    private Moeda moeda;

    public void setCnpj(String cnpj){
        if(cnpj != null) this.cnpjUnmask = cnpj.replaceAll("[^\\d.]", "");
    }


}
