package za.ac.nwu.web.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EntityScan("za.ac.nwu.ac.domain")
@EnableJpaRepositories({"za.ac.nwu.repository"})
@ComponentScan({"za.ac.nwu.ac.sb.controller"})
public class MemberApplication extends SpringBootServletInitializer {

    public static void main(String... args){
        SpringApplication.run(MemberApplication.class,args);
    }
}
