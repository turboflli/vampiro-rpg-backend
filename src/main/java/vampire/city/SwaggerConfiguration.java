package vampire.city;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Collections;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration

public class SwaggerConfiguration {

    @Bean
    public Docket retornaSwagger() {
        return new Docket(DocumentationType.OAS_30) // <- troca aqui
            .select()
            .apis(RequestHandlerSelectors.basePackage("vampire.city.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(informacoesApi());
    }

    private ApiInfo informacoesApi() {
        return new ApiInfo("Api REST para npcs de vampiro a máscara",
                "controla os npcs e suas rotinas, bem como locais da cidade",
                "V1", null,
                null,
                null, null, Collections.emptyList());
    }
}
