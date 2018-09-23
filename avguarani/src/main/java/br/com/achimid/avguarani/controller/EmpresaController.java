package br.com.achimid.avguarani.controller;

import br.com.achimid.avguarani.controller.documentation.EmpresaControllerDoc;
import br.com.achimid.avguarani.model.Empresa;
import br.com.achimid.avguarani.repository.EmpresaRepository;
import br.com.achimid.avguarani.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController implements EmpresaControllerDoc{

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public HttpEntity<Collection<Empresa>> all() {
        return ResponseEntity.ok(empresaService.findAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<Empresa> get(@PathVariable Long id){
        Optional<Empresa> empresa = empresaService.findById(id);
        if(!empresa.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empresa.get());
    }

    @PostMapping
    public HttpEntity<Empresa> create(@RequestBody Empresa empresa){
        empresaService.save(empresa);
        return ResponseEntity.ok(empresa);
    }

    @PostMapping("/{cnpj}")
    public HttpEntity<?> importarEmpresa(@PathVariable String cnpj){
        Empresa empresa = empresaService.importarEmpresa(cnpj);
        return ResponseEntity.ok(empresa);
    }

    @PutMapping
    public HttpEntity<?> update(@RequestBody Empresa empresa){
        return create(empresa);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        if(!empresaService.exists(id)) return ResponseEntity.notFound().build();
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
