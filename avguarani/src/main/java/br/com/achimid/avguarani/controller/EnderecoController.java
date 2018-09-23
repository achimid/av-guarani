package br.com.achimid.avguarani.controller;

import br.com.achimid.avguarani.controller.documentation.EnderecoControllerDoc;
import br.com.achimid.avguarani.model.Endereco;
import br.com.achimid.avguarani.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/endereco")
public class EnderecoController implements EnderecoControllerDoc{

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public HttpEntity<Collection<Endereco>> all() {
        return ResponseEntity.ok(enderecoService.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Endereco> get(@PathVariable Long id){
        Optional<Endereco> endereco = enderecoService.findById(id);
        if(!endereco.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(endereco.get());
    }

    @PostMapping
    public HttpEntity<Endereco> create(@RequestBody Endereco endereco){
        enderecoService.save(endereco);
        return ResponseEntity.ok(endereco);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody Endereco endereco){
        return create(endereco);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        if(!enderecoService.exists(id)) return ResponseEntity.notFound().build();
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
