package br.com.achimid.avguarani.controller.documentation;

import br.com.achimid.avguarani.model.Empresa;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface EmpresaControllerDoc {

    @ApiOperation("Listagem de todas as empresa")
    HttpEntity<Collection<Empresa>> all();

    @ApiOperation("Busca empresa por id")
    HttpEntity<?> get(@PathVariable Long id);

    @ApiOperation("Cria uma empresa")
    HttpEntity<Empresa> create(@RequestBody Empresa empresa);

    @ApiOperation("Atualiza os dados da empresa")
    HttpEntity<?> update(@RequestBody Empresa empresa);

    @ApiOperation("Exlusão de uma empresa por id")
    HttpEntity<?> delete(@PathVariable Long id);

}
