package br.com.achimid.avguarani.service;

import br.com.achimid.avguarani.model.Empresa;
import br.com.achimid.avguarani.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa save(Empresa empresa){
        return empresaRepository.save(empresa);
    }

    public Collection<Empresa> findAll(){
        return (Collection<Empresa>) empresaRepository.findAll();
    }

    public Optional<Empresa> findById(Long id){
        return empresaRepository.findById(id);
    }

    public void delete(Long id){
        empresaRepository.deleteById(id);
    }

    public boolean exists(Long id){
        return empresaRepository.existsById(id);
    }


}
