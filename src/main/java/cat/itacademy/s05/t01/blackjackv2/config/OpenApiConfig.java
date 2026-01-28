package cat.itacademy.s05.t01.blackjackv2.config;



import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI blackjackOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Reactive BalckJack")
                        .description("Reactive BlackJack game with MySQL and MongoDB")
                        .version("2.0"));

    }
}