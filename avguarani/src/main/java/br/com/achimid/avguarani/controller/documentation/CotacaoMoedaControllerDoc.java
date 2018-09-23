package br.com.achimid.avguarani.controller.documentation;

import br.com.achimid.avguarani.model.Moeda;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public interface CotacaoMoedaControllerDoc {

    @ApiOperation("Cotação de todas as moedas")
    HttpEntity<Collection<Moeda>> all();

    @ApiOperation("Buscar cotação por id")
    HttpEntity<Moeda> get(@PathVariable Long id);

    @ApiOperation("Buscar cotação por codigo")
    HttpEntity<Collection<Moeda>> getByCodigo(@PathVariable String codigo);
}
