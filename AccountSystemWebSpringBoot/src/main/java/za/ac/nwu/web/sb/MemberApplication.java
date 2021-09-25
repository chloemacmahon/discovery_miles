package za.ac.nwu.web.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.logic.MemberService;
import za.ac.nwu.ac.repository.MemberRepository;

@SpringBootApplication
@EntityScan("za.ac.nwu.ac.domain")
@EnableJpaRepositories({"za.ac.nwu.ac.repository"})
@ComponentScan({"za.ac.nwu.ac.logic"})
public class MemberApplication{

    public static void main(String... args){
        ConfigurableApplicationContext context = SpringApplication.run(MemberApplication.class,args);
        MemberRepository memberRepository = context.getBean(MemberRepository.class);
        MemberService memberService = context.getBean(MemberService.class);
        Member member = memberService.logInMember("patterson.calvin@wordgate.co.za","fl0ral0!");
        System.out.println("member.getDrivingGoal() = " + member.getDrivingGoal());
    }
}
