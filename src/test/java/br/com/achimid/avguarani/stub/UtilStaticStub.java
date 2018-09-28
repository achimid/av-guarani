package br.com.achimid.avguarani.stub;

import br.com.achimid.avguarani.model.Empresa;
import br.com.achimid.avguarani.model.Endereco;
import br.com.achimid.avguarani.model.Moeda;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

public final class UtilStaticStub {

    public static Empresa getEmpresa(){
        Empresa e = new Empresa();
        e.setCnpj("03.679.726/0001-90");
        e.setEmail("email@empresa.com");
        return e;
    }

    public static Endereco getEndereco(){
        Endereco e = new Endereco();
        e.setNumero("123");
        e.setLogradouro("Logradouro");
        e.setComplemento("Complemento");
        e.setCidade("Cidade");
        e.setCep("11111-000");
        e.setBairro("Bairro");
        return e;
    }

    public static Moeda getMoeda(){
        Moeda m = new Moeda();
        m.setCodigo("BTC-BRL");
        m.setValorVenda(new BigDecimal(1.23));
        m.setValorCompra(new BigDecimal(1.24));
        m.setNome("Bitcoin");
        m.setDataAtualizacao(GregorianCalendar.getInstance().getTime());
        return m;
    }


}
