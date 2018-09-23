package br.com.achimid.avguarani.repository;

import br.com.achimid.avguarani.model.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {

    Empresa findByCnpjUnmask(String cnpj);

}
