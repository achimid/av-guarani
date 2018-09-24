package br.com.achimid.avguarani.controller;

import br.com.achimid.avguarani.controller.documentation.CotacaoMoedaControllerDoc;
import br.com.achimid.avguarani.model.Empresa;
import br.com.achimid.avguarani.model.Moeda;
import br.com.achimid.avguarani.model.MoedaCodigoEnum;
import br.com.achimid.avguarani.service.CotacaoMoedaService;
import br.com.achimid.avguarani.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cotacao")
public class CotacaoMoedaController implements CotacaoMoedaControllerDoc{

    @Autowired
    private CotacaoMoedaService cotacaoMoedaService;

    @GetMapping
    public HttpEntity<Collection<Moeda>> all() {
        return ResponseEntity.ok(cotacaoMoedaService.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Moeda> get(@PathVariable Long id){
        Optional<Moeda> moeda = cotacaoMoedaService.findById(id);
        if(!moeda.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(moeda.get());
    }

    @GetMapping("/codigo/{codigo}")
    public HttpEntity<Moeda> getByCodigo(@PathVariable String codigo){
        MoedaCodigoEnum me = MoedaCodigoEnum.valueOf(codigo);
        Moeda moeda = cotacaoMoedaService.buscarMoeda(me);
        if(moeda == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(moeda);
    }


}
