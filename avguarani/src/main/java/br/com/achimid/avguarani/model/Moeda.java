package br.com.achimid.avguarani.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Moeda extends ModelBase {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String codigo;

    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    public void setCodigo(String code, String codeIn){
        this.codigo = code + '-' + codeIn;
    }


}
