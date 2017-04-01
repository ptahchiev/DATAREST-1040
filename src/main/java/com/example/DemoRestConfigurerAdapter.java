package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.JacksonProperties;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
public class DemoRestConfigurerAdapter extends RepositoryRestConfigurerAdapter {

    @Autowired
    private JacksonProperties jacksonProperties;

    @Autowired(required = false)
    private Jackson2ObjectMapperBuilder objectMapperBuilder;

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {

        // Mimic the SpringBootRepositoryRestConfigurer because it is only loaded when @ConditionalOnMissingBean(RepositoryRestMvcConfiguration.class)
        // Please remove these lines once the RestServicesCoreConfig no longer extends RepositoryRestMvcConfiguration. Then we will let spring-boot configure
        // the jackson's objectMapper.
        if (this.objectMapperBuilder != null) {
            this.objectMapperBuilder.configure(objectMapper);
        }

        Hibernate5Module module = new Hibernate5Module();
        module.enable(Hibernate5Module.Feature.FORCE_LAZY_LOADING);
        module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        objectMapper.registerModule(module);

        jacksonProperties.getSerialization().entrySet().stream().forEach(e -> objectMapper.configure(e.getKey(), e.getValue()));
        jacksonProperties.getDeserialization().entrySet().stream().forEach(e -> objectMapper.configure(e.getKey(), e.getValue()));

        //        objectMapper.registerModule(new MoneyModule(monetaryAmountFormatFactory));
    }
}
