package alatoo.softwareEngineering.FilmVista.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "tmdb.api")
public class TMDbConfig {
    private String key;
    private String url;
}
