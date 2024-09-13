package uz.pdp.SpringSecurity.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Spring security, exception handler",
                description = "security, handler",
                termsOfService = "/termsOfApp",
                contact = @Contact(
                        name = "G40",
                        url = "https://pdp.uz",
                        email = "pdp@gmail.com"
                ),
                version = "v0.0.1"
        ),
        servers = {
                @Server(url = "http://localhost:8081",description = "For Local Mode"),

        }
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        description = "You have to add JWT Token",
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
