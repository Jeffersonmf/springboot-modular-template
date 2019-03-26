package br.com.santander.telemetria.core.controller;

import br.com.santander.telemetria.api.v1_0.TelemetriaAPI;
import br.com.santander.telemetria.core.services.TelemetriaServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @author Jefferson Marchetti Ferreira
 */
@Api("API para ingestão da Telemetria do MBJ")
@RestController
@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = br.com.santander.telemetria.core.controller.TelemetriaController.class)

public class TelemetriaController implements TelemetriaAPI {

    private TelemetriaServices telemetriaServices = new TelemetriaServices();

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.santander.telemetria.core.controller"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API")
                .description("\"Spring Boot REST API for Online Store\"")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("John Thompson", "https://springframework.guru/about/", "john@springfrmework.guru"))
                .build();
    }


    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @RequestMapping("/")
    @ApiOperation(value = "Rota principal da aplicação.", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public String index() {
        return "Telemetria is Up!";
    }

    /**
     * Descrição: Realiza a ingestão dos dados de Telemetria para o serviço responsável do Big Data.
     *
     * @param inputAVRO
     * @return
     */
    @Override
    @ApiOperation(
            value = "Realiza a ingestão dos dados de Telemetria para o serviço responsável do Big Data",
            response = String.class,
            notes = "Essa operação salva um novo registro com as informações de pessoa.")
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Retorna um status assincrono de recebimento do pacote",
                    response = String.class
            ),
            @ApiResponse(code = 404, message = "Recurso não encontrado", response = String.class),
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(
                    code = 500,
                    message = "Caso ocorra algum erro vamos retornar um ResponseModel padonizada para tratar Exceções.",
                    response = String.class
            )

    })

    @ResponseBody
    @RequestMapping(
            method = POST,
            consumes = APPLICATION_JSON_VALUE,
            produces = TEXT_PLAIN_VALUE,
            name = "Telemetria Ingestor",
            path = "/telemetria/ingestor")
    public ResponseEntity<String> telemetriaCharger(@RequestBody String inputAVRO) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Tokens", "23456");

            telemetriaServices.startIngestorProcess(inputAVRO);

            String jsonResponse = "{result: \"OK\"}";

            return ResponseEntity.ok().header("Chave", "Valor1", "Valor 2").body(jsonResponse);
        }
        catch(Exception ex) {

            return ResponseEntity.badRequest().build();
        }
    }
}
