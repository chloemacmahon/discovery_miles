package za.ac.nwu.ac.repository.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("za.ac.nwu.ac.repository")
@EntityScan({"za.ac.nwu.ac.domain.dto"})
public class RepositoryConfig {
}
