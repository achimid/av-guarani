package br.com.achimid.avguarani.service;

import br.com.achimid.avguarani.model.Empresa;
import br.com.achimid.avguarani.repository.EmpresaRepository;
import br.com.achimid.avguarani.stub.UtilStaticStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TestEmpresaService {

    @Autowired
    EmpresaRepository repository;

    @Autowired
    EmpresaService service;

    Empresa empresaComp = UtilStaticStub.getEmpresa();

    @Test
    public void testSalvarEmpresaSuccess(){
        Empresa empresa = service.save(UtilStaticStub.getEmpresa());

        Assert.assertNotNull(empresa.getId());
        Assert.assertEquals(empresaComp.getCnpj(), empresa.getCnpj());
        Assert.assertEquals(empresaComp.getCnpjUnmask(), empresa.getCnpjUnmask());
        Assert.assertEquals(empresaComp.getEmail(), empresa.getEmail());
        Assert.assertEquals(empresaComp.getNome(), empresa.getNome());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSalvarEmpresaWithNull(){
        service.save(null);
    }

    @Test
    public void testFindAllSuccess(){
        Empresa empresa = service.save(UtilStaticStub.getEmpresa());

        Collection<Empresa> all = service.findAll();

        Assert.assertNotNull(all);
        Assert.assertFalse(all.isEmpty());
        Assert.assertTrue(all.contains(empresa));
    }

    @Test(expected = IllegalStateException.class)
    public void testFindByIdInvalid(){
        service.findById(999999l);
    }

    @Test
    public void testFindByIdSuccess(){
        Empresa empresa1 = service.save(UtilStaticStub.getEmpresa());
        Empresa empresa2 = service.findById(empresa1.getId());

        Assert.assertEquals(empresa1, empresa2);
    }

}
