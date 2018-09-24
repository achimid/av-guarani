package br.com.achimid.avguarani.service;

import br.com.achimid.avguarani.client.MoedaAwesomeClient;
import br.com.achimid.avguarani.dto.moeda.AwesomeMoedaDTO;
import br.com.achimid.avguarani.model.Moeda;
import br.com.achimid.avguarani.model.MoedaCodigoEnum;
import br.com.achimid.avguarani.repository.MoedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CotacaoMoedaService {

    @Value("${moeda.threshold}")
    private int moedaThreshold;

    @Autowired
    private MoedaRepository moedaRepository;

    @Autowired
    private MoedaAwesomeClient moedaAwesomeClient;

    public Moeda save(Moeda moeda){
        return moedaRepository.save(moeda);
    }

    public Collection<Moeda> findAll(){
        return (Collection<Moeda>) moedaRepository.findAll();
    }

    public Optional<Moeda> findById(Long id){
        return moedaRepository.findById(id);
    }

    public Moeda findByCodigo(MoedaCodigoEnum codigo){
        return moedaRepository.findByCodigo(codigo.toString());
    }

    public void delete(Long id){
        moedaRepository.deleteById(id);
    }

    public boolean exists(Long id){
        return moedaRepository.existsById(id);
    }

    private Moeda parse(AwesomeMoedaDTO awesomeMoedaDTO){
        Moeda moeda = new Moeda();
        moeda.setDataAtualizacao(GregorianCalendar.getInstance().getTime());
        moeda.setCodigo(MoedaCodigoEnum.valueOf(awesomeMoedaDTO.getCode().concat("-").concat(awesomeMoedaDTO.getCodein())));
        return moeda;
    }

    @Cacheable("buscarMoedaAwesome")
    public Moeda buscarMoeda(MoedaCodigoEnum codigo){
        Moeda moeda = moedaRepository.findByCodigo(codigo.toString());

        if(moeda == null){
            AwesomeMoedaDTO awesomeMoedaDTO = moedaAwesomeClient.buscarMoeda(codigo);
            if(awesomeMoedaDTO != null){
                moeda = parse(awesomeMoedaDTO);

                Moeda moedaTmp = moedaRepository.findByCodigo(moeda.getCodigo().toString());
                moeda.setId(moedaTmp.getId());

                moedaRepository.save(moeda);
            }
        }

        return moeda;
    }

    public boolean isDesatualizado(Date dataAtualizacao){
        if(dataAtualizacao == null) return true;

        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(dataAtualizacao);
        cal.add(Calendar.MINUTE, moedaThreshold);

        Date now = GregorianCalendar.getInstance().getTime();

        return now.after(cal.getTime());
    }
}
