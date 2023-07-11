package br.ufma.sppg.configuracoes;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    /**
     *   Permite que o Keycloak seja configurado através do arquivo application.properties.
     * 
     * 
     * @return
     */
    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() { 
        return new KeycloakSpringBootConfigResolver();
    }
}