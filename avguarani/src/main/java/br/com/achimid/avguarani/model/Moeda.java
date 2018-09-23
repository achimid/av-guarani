package br.com.achimid.avguarani.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Moeda extends ModelBase {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private MoedaCodigoEnum codigo;

    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public boolean isDesatualizado(){
        if(dataAtualizacao == null) return true;
        
    }

}
