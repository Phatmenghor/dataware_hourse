package cpb.dwh_bi_api.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("DWH BI API")
				.version("1.0.0")
				.description("Data Warehouse Business Intelligence REST API")
				.contact(new Contact()
					.name("DWH Team")
					.url("https://example.com")
					.email("contact@example.com")))
			.addSecurityItem(new SecurityRequirement().addList("Bearer"))
			.components(new io.swagger.v3.oas.models.Components()
				.addSecuritySchemes("Bearer", new SecurityScheme()
					.type(SecurityScheme.Type.HTTP)
					.scheme("bearer")
					.bearerFormat("JWT")
					.description("Enter JWT token")));
	}
}
