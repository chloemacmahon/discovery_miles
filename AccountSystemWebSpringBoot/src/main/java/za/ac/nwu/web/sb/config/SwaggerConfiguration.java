//package za.ac.nwu.web.sb.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//@Configuration
//@EnableSwagger2
//@Import(springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class)
//public class SwaggerConfiguration {
//    @Value("${swagger.application.version}")
//    private String applicationVersion;
//    @Value("${swagger.application.name}")
//    private String applicationName;
//    @Value("${swagger.application.description}")
//    private String applicationDescription;
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) .paths(PathSelectors.any())
//                .build()
//                .pathMapping("/")
//                .apiInfo(apiInfo());
//    }
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
//                applicationName,
//                applicationDescription,
//                applicationVersion,
//                "",
//                new Contact("Your Name or Team Name", "", "email address"),
//                "",
//                "",
//                Collections.emptyList());
//    }
//}