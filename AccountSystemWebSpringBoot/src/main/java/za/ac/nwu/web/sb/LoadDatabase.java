package za.ac.nwu.web.sb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.repository.MemberRepository;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MemberRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Member("Jamie", "Van Wyk", "smoak", "6504067562083", "jamie.van.wyk@candle.com")));
            log.info("Preloading " + repository.save(new Member("Scarlet", "Fourie", "98Christmas", "9812020062083","sfourie7@gmail.com")));
        };
    }
}
