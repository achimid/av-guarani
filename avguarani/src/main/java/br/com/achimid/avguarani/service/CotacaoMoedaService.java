package br.com.achimid.avguarani.service;

import br.com.achimid.avguarani.model.Moeda;
import br.com.achimid.avguarani.repository.MoedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CotacaoMoedaService {

    @Autowired
    private MoedaRepository moedaRepository;

    public Moeda save(Moeda moeda){
        return moedaRepository.save(moeda);
    }

    public Collection<Moeda> findAll(){
        return (Collection<Moeda>) moedaRepository.findAll();
    }

    public Optional<Moeda> findById(Long id){
        return moedaRepository.findById(id);
    }

    public Collection<Moeda> findByCodigo(String codigo){
        return moedaRepository.findByCodigo(codigo);
    }

    public void delete(Long id){
        moedaRepository.deleteById(id);
    }

    public boolean exists(Long id){
        return moedaRepository.existsById(id);
    }


}
