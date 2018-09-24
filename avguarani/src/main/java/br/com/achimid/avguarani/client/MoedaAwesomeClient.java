package br.com.achimid.avguarani.client;

import br.com.achimid.avguarani.dto.moeda.AwesomeMoedaDTO;
import br.com.achimid.avguarani.model.MoedaCodigoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

@Component
public class MoedaAwesomeClient {

    private final static String QUERY_TODAS = "all";

    @Value("${moedaAwesome.api.url}")
    private String moedaAwesomeApiUrl;

    @Autowired
    private RestTemplate restTemplate;

    public Collection<AwesomeMoedaDTO> buscarTodasMoedas() {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(moedaAwesomeApiUrl.concat(QUERY_TODAS), List.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity.getBody();
        }
        throw new IllegalStateException("API de terceiros indisponivel no momento.");
    }

    public AwesomeMoedaDTO buscarMoeda(MoedaCodigoEnum codigo) {
        ResponseEntity<AwesomeMoedaDTO> responseEntity = restTemplate.getForEntity(moedaAwesomeApiUrl.concat(codigo.toString()), AwesomeMoedaDTO.class);
        if (HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            return responseEntity.getBody();
        }
        throw new IllegalStateException("API de terceiros indisponivel no momento.");
    }


}
