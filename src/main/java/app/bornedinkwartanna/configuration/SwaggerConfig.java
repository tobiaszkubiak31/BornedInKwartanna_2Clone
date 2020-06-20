package app.bornedinkwartanna.configuration;

import static com.google.common.base.Predicates.or;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.builders.PathSelectors.regex;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket SwaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.groupName("Tax-Calculator-Api")
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.any())
			.paths(getSwaggerPaths())
			.build()
			.globalResponseMessage(RequestMethod.GET,
				newArrayList(new ResponseMessageBuilder()
					.code(500)
					.message("500 message")
					.responseModel(new ModelRef("Error"))
					.build()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Tax-Calculator-Api")
			.version("1.0.0")
			.build();
	}

	private Predicate<String> getSwaggerPaths() {
		return or(
			regex("/products.*"),
			regex("/states.*"));
	}
}
