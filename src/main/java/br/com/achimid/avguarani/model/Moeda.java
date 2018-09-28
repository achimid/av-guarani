package br.com.achimid.avguarani.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class Moeda extends ModelBase {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column(unique = true)
    private String codigo;

    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private Date dataAtualizacao;

    private BigDecimal valorCompra;

    private BigDecimal valorVenda;

    public void setCodigo(String code, String codeIn){
        this.codigo = code + '-' + codeIn;
    }


}
