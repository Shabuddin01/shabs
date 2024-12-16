package com.crrm.crrm.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class configClass {

    @Bean
    public ModelMapper getmodelMapper(){

        return new ModelMapper();
    }
}
