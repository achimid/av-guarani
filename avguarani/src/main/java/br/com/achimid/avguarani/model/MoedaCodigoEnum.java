package br.com.achimid.avguarani.model;

public enum MoedaCodigoEnum {

    USD_BRL("USD-BRL"),
    USD_BRLT("USD-BRLT"),
    CAD_BRL("CAD-BRL"),
    EUR_BRL("EUR-BRL"),
    GBP_BRL("GBP-BRL"),
    ARS_BRL("ARS-BRL"),
    BTC_BRL("BTC-BRL");

    private String codigo;

    MoedaCodigoEnum(String codigo){
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo;
    }
}
