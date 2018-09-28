package br.com.achimid.avguarani.client;

import br.com.achimid.avguarani.dto.empresa.EmpresaReceitaDTO;
import br.com.achimid.avguarani.dto.moeda.AwesomeMoedaDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    public AwesomeMoedaDTO buscarMoeda(String codigo) {
        String url = moedaAwesomeApiUrl.concat(codigo).concat("/1");
        try {
            ResponseEntity<List> responseEntity = restTemplate.exchange
                    (url, HttpMethod.GET, new HttpEntity<>(createHeaders()), List.class);

            if (responseEntity != null && HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                // algo de errado estou fazendo para precisar converter dessa meneira.
                return new ObjectMapper().convertValue((Map) responseEntity.getBody().iterator().next(), AwesomeMoedaDTO.class);
            }

            throw new IllegalStateException("API de terceiros indisponivel no momento.");
        }catch (RuntimeException se){
            throw new IllegalStateException("API de terceiros indisponivel no momento.", se);
        }

    }

    HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            set( "User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:62.0) Gecko/20100101 Firefox/62.0");
            set( "Content-Type", "application/json");
            set( "Accept", "application/json");
        }};
    }

}

