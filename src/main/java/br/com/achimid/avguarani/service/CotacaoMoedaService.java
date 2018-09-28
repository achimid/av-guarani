package br.com.achimid.avguarani.service;

import br.com.achimid.avguarani.client.MoedaAwesomeClient;
import br.com.achimid.avguarani.dto.moeda.AwesomeMoedaDTO;
import br.com.achimid.avguarani.model.Empresa;
import br.com.achimid.avguarani.model.Moeda;
import br.com.achimid.avguarani.repository.MoedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
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
        return moedaRepository.findByCodigo(codigo) != null;
    }

    private Moeda parse(AwesomeMoedaDTO awesomeMoedaDTO) {
        Moeda moeda = new Moeda();
        moeda.setDataAtualizacao(GregorianCalendar.getInstance().getTime());
        moeda.setCodigo(awesomeMoedaDTO.getCode(), awesomeMoedaDTO.getCodein());
        moeda.setNome(awesomeMoedaDTO.getName());
        moeda.setValorCompra(awesomeMoedaDTO.getBid());
        moeda.setValorVenda(awesomeMoedaDTO.getAsk());
        return moeda;
    }

    public Moeda buscarMoeda(String codigo) {
        // TODO: remover, apenas para teste
        //moedaRepository.deleteAll();

        Moeda moeda = moedaRepository.findByCodigo(codigo);

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

    public void atualizaMoedaEmpresa(@NotNull final Collection<Empresa> empresas){
        empresas.forEach(e -> atualizaMoedaEmpresa(e));
    }

    public void atualizaMoedaEmpresa(@NotNull final Empresa empresa){
        if(empresa.getMoeda() != null) empresa.setMoeda(buscarMoeda(empresa.getMoeda().getCodigo()));
    }

    public boolean validarCodigo(String codigo){
        return Arrays.asList("USD-BRL","USD-BRLT","CAD-BRL","EUR-BRL","GBP-BRL","ARS-BRL","BTC-BRL").contains(codigo);
    }
}
