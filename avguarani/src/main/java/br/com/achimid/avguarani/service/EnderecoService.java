package br.com.achimid.avguarani.service;

import br.com.achimid.avguarani.model.Endereco;
import br.com.achimid.avguarani.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco save(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public Collection<Endereco> findAll(){
        return (Collection<Endereco>) enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id){
        return enderecoRepository.findById(id);
    }

    public void delete(Long id){
        enderecoRepository.deleteById(id);
    }

    public boolean exists(Long id){
        return enderecoRepository.existsById(id);
    }


}
