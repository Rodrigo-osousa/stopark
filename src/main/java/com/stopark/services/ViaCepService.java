package com.stopark.services;

import com.stopark.models.entities.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ViaCepService {

    @Autowired
    ViaCep viaCep;

    private static Logger logger = Logger.getLogger(ViaCepService.class.getName());


    @Retryable(value = RuntimeException.class,
            maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public Endereco buscarEndereco(String cep){
        logger.info("chamando API do ViaCEP");
        return viaCep.consultarCep(cep);
    }

}
