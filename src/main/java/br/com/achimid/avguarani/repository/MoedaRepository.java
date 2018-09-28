package br.com.achimid.avguarani.repository;

import br.com.achimid.avguarani.model.Moeda;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MoedaRepository extends CrudRepository<Moeda, Long> {

    Moeda findByCodigo(String codigo);

}
