package br.com.achimid.avguarani.controller.documentation;

import br.com.achimid.avguarani.model.Empresa;
import br.com.achimid.avguarani.model.Endereco;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;

public interface EnderecoControllerDoc {

    @ApiOperation("Listagem de todos os enderecos")
    HttpEntity<Collection<Endereco>> all();

    @ApiOperation("Busca endereco por id")
    HttpEntity<Endereco> get(@PathVariable Long id);

    @ApiOperation("Registra novo endereço")
    HttpEntity<Endereco> create(@RequestBody Endereco endereco);

    @ApiOperation("Atualiza os dados do endereco")
    HttpEntity<?> update(@RequestBody Endereco endereco);

    @ApiOperation("Exlusão de um endereco por id")
    HttpEntity<?> delete(@PathVariable Long id);

}
