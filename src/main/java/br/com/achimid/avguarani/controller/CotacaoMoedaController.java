package br.com.achimid.avguarani.controller;

import br.com.achimid.avguarani.controller.documentation.CotacaoMoedaControllerDoc;
import br.com.achimid.avguarani.model.Moeda;
import br.com.achimid.avguarani.service.CotacaoMoedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/cotacao")
public class CotacaoMoedaController implements CotacaoMoedaControllerDoc{

    @Autowired
    private CotacaoMoedaService cotacaoMoedaService;

    @GetMapping
    public HttpEntity<Collection<Moeda>> all() {
        return ResponseEntity.ok(cotacaoMoedaService.findAll());
    }

    @GetMapping("/{codigo}")
    public HttpEntity<Moeda> get(@PathVariable String codigo){
        if(!cotacaoMoedaService.validarCodigo(codigo))
            return ResponseEntity.badRequest().build();

        Moeda moeda = cotacaoMoedaService.buscarMoeda(codigo);
        if(moeda == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(moeda);
    }


}
