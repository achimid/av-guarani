package br.com.achimid.avguarani.client;

import br.com.achimid.avguarani.dto.empresa.EmpresaReceitaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.SocketTimeoutException;

@Component
public class EmpresaReceitaClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${receitaws.api.url}")
    private String receitawsApiUrl;

    @Value("${receitaws.api.token}")
    private String receitawsApiToken;

    @Cacheable("buscarEmpresaReceita")
    public EmpresaReceitaDTO buscarEmpresa(String cnpj) throws IllegalStateException {
        if(cnpj == null) throw new IllegalArgumentException("cnpj nao pode ser nullo");

        try {
            ResponseEntity<EmpresaReceitaDTO> responseEntity = restTemplate.exchange
                    (receitawsApiUrl.concat(cnpj), HttpMethod.GET, new HttpEntity<>(createHeaders()), EmpresaReceitaDTO.class);

            if (responseEntity != null && HttpStatus.OK.equals(responseEntity.getStatusCode())) {
                EmpresaReceitaDTO empresaReceitaDTO = responseEntity.getBody();
                if (HttpStatus.OK.getReasonPhrase().equals(empresaReceitaDTO.getStatus())) {
                    return empresaReceitaDTO;
                }
                throw new IllegalStateException(empresaReceitaDTO.getMessage());
            } else {
                throw new IllegalStateException("API de terceiros indisponivel no momento.");
            }
        }catch (RuntimeException se){
            throw new IllegalStateException("Nao foi possivel obter uma resposta da API da receitaws");
        }
    }

    HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            set( "Authorization", "Bearer " + receitawsApiToken );
        }};
    }

}
