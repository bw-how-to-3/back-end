package local.heftyb.howto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors
                .basePackage("local.heftyb.howto"))
            .paths(PathSelectors.regex("/.*"))
            .build()
            .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("How-To Model Example")
            .description("How-To Model Example")
            .contact(new Contact("Andrew Shields",
                "http://www.github.com/heftyb",
                "Ashields0923@Gmail.com"))
            .license("MIT")
            .licenseUrl("https://github.com/bw-how-to-3/back-end/blob/master/LICENSE")
            .version("1.0.0")
            .build();
    }
}