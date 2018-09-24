package br.com.achimid.avguarani.service;

import br.com.achimid.avguarani.client.EmpresaReceitaClient;
import br.com.achimid.avguarani.dto.empresa.EmpresaReceitaDTO;
import br.com.achimid.avguarani.model.Empresa;
import br.com.achimid.avguarani.model.Endereco;
import br.com.achimid.avguarani.repository.EmpresaRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.SocketTimeoutException;
import java.util.Collection;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaReceitaClient empresaReceitaClient;


    public Empresa save(Empresa empresa) {
        if (empresa == null) throw new IllegalArgumentException("empresa nao pode ser null");

        return empresaRepository.save(empresa);
    }

    public Collection<Empresa> findAll() {
        return (Collection<Empresa>) empresaRepository.findAll();
    }

    public Optional<Empresa> findById(Long id) {
        return empresaRepository.findById(id);
    }

    public void delete(Long id) {
        empresaRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return empresaRepository.existsById(id);
    }


    private Empresa parser(final EmpresaReceitaDTO empresaDTO) {
        if (empresaDTO == null) throw new IllegalArgumentException("empresaDTO nao pode ser null");

        Empresa empresa = new Empresa();

        empresa.setCnpj(empresaDTO.getCNPJ());
        empresa.setEmail(empresaDTO.getEmail());

        Endereco endereco = new Endereco();
        empresa.setEndereco(endereco);
        endereco.setBairro(empresaDTO.getBairro());
        endereco.setCep(empresaDTO.getCep());
        endereco.setComplemento(empresaDTO.getComplemento());
        endereco.setLogradouro(empresaDTO.getLogradouro());
        endereco.setNumero(empresaDTO.getNumero());

        //API da receita nao retorna a cidade apenas o cep. Pesquisar cidade pelo cep...
        //endereco.setCidade(empresaDTO.get);

        return empresa;
    }

    @Cacheable("importarEmpresa")
    public Empresa importarEmpresa(String cnpj) throws IllegalStateException {
        if (cnpj == null || cnpj.isEmpty()) throw new IllegalArgumentException("cnpj nao pode ser nullo nem vazio");

        Empresa empresa = empresaRepository.findByCnpjUnmask(cnpj);

        if (empresa == null) {
            EmpresaReceitaDTO empresaReceitaDTO = empresaReceitaClient.buscarEmpresa(cnpj);

            if(empresaReceitaDTO != null) {
                empresa = this.parser(empresaReceitaDTO);
                this.save(empresa);
            }
        }

        return empresa;
    }

}

