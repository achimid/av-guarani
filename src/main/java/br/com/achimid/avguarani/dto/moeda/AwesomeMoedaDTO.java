package br.com.achimid.avguarani.dto.moeda;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AwesomeMoedaDTO {

    private String code;
    private String codein;
    private String name;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal pctChange;
    private Integer open;
    private BigDecimal bid;
    private BigDecimal ask;
    private BigDecimal varBid;
    private Long timestamp;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date create_date;

}
