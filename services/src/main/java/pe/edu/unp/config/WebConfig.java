package pe.edu.unp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
                MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();

                ObjectMapper mapper = new ObjectMapper();
                //Registering Hibernate4Module to support lazy objects
                mapper.registerModule(new Hibernate5Module());

                messageConverter.setObjectMapper(mapper);
                return messageConverter;

            }

            @Override
            public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
                //Here we add our custom-configured HttpMessageConverter
                converters.add(jacksonMessageConverter());
                super.configureMessageConverters(converters);
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }

        };
    }

//    @Bean
//    public Module hibernate4Module() {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.registerModule(new Hibernate4Module());
//        return new Hibernate4Module();
//    }
}
