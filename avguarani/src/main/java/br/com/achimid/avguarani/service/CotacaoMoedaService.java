package br.com.achimid.avguarani.service;

import br.com.achimid.avguarani.client.MoedaAwesomeClient;
import br.com.achimid.avguarani.dto.moeda.AwesomeMoedaDTO;
import br.com.achimid.avguarani.model.Moeda;
import br.com.achimid.avguarani.repository.MoedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    public Moeda save(Moeda moeda) {
        moeda.setDataAtualizacao(GregorianCalendar.getInstance().getTime());
        return moedaRepository.save(moeda);
    }

    public Collection<Moeda> findAll() {
        Collection<Moeda> moedas = (Collection<Moeda>) moedaRepository.findAll();
        moedas.forEach(m -> m = isMoedaDesatualizada(m) ? buscarMoeda(m.getCodigo()) : m);
        return moedas;
    }

    public boolean exists(String codigo) {
        return moedaRepository.existsById(codigo);
    }

    private Moeda parse(AwesomeMoedaDTO awesomeMoedaDTO) {
        Moeda moeda = new Moeda();
        moeda.setDataAtualizacao(GregorianCalendar.getInstance().getTime());
        moeda.setCodigo(awesomeMoedaDTO.getCode(), awesomeMoedaDTO.getCodein());
        return moeda;
    }

    public Moeda buscarMoeda(String codigo) {
        Optional<Moeda> op = moedaRepository.findById(codigo);
        Moeda moeda = op.isPresent() ? op.get() : null;

        if (moeda == null || isMoedaDesatualizada(moeda)) {
            AwesomeMoedaDTO awesomeMoedaDTO = moedaAwesomeClient.buscarMoeda(codigo);
            if (awesomeMoedaDTO != null) {
                moeda = parse(awesomeMoedaDTO);
                moedaRepository.save(moeda);
            }
        }

        return moeda;
    }

    // Poderia ter utilizado o @Cacheable, porem acredito que desta maneira se adeque melhor,
    // mesmo sabendo que API do terceiro atualiza a cada 10 minutos
    public boolean isMoedaDesatualizada(final Moeda moeda) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(moeda.getDataAtualizacao());
        cal.add(Calendar.MINUTE, moedaThreshold);

        Date now = GregorianCalendar.getInstance().getTime();

        return now.after(cal.getTime());
    }
}
