package za.ac.nwu.ac.web.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.logic.AddMilesService;
import za.ac.nwu.ac.logic.MemberService;
import za.ac.nwu.ac.repository.MemberRepository;
//import za.ac.nwu.web.sb.controller.ActivityController;


@SpringBootApplication
public class MemberApplication{

    public static void main(String... args){
        ConfigurableApplicationContext context = SpringApplication.run(MemberApplication.class,args);
    }
}
