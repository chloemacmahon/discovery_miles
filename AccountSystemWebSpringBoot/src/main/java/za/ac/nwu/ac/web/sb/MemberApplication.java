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
/*@EntityScan("za.ac.nwu.ac.domain.dto")
@EnableJpaRepositories({"za.ac.nwu.ac.repository"})
@ComponentScan({"za.ac.nwu.ac.logic", "za.ac.nwu.ac.web.sb.controller"})*/
public class MemberApplication{

    public static void main(String... args){
        ConfigurableApplicationContext context = SpringApplication.run(MemberApplication.class,args);
        /*MemberRepository memberRepository = context.getBean(MemberRepository.class);
        MemberService memberService = context.getBean(MemberService.class);
        Member member = memberService.logInMember("jamie.van.wyk@candle.com","smoak");
        AddMilesService addMilesService = context.getBean(AddMilesService.class);
        addMilesService.chooseGameTile(2,3,member);
        memberService.updateMemberInDatabase(member);*/
        /*Member member = memberService.logInMember("patterson.calvin@wordgate.co.za","fl0ral0!");
        System.out.println("member.getDrivingGoal() = " + member.getDrivingGoal().getPointsNecessary());
        AdminService adminService = context.getBean(AdminService.class);
        //Admin admin = adminService.registerAdmin("ivy.may@vitality.co.za","BY31aIu?#",3879);
        //Admin admin = adminService.logInAdmin("ivy.may@vitality.co.za","BY31aIu?#");
        System.out.println("adminService = " + adminService.addNewActivity("Health","1 hour gym session",50).getPointsEarned());
        AddMilesService addMilesService = context.getBean(AddMilesService.class);
        List<Activity> activities = addMilesService.viewActivities();
        addMilesService.addPointsToHealthGoal((HealthActivity)activities.get(0),member);
        ActivityRepository activityRepository = context.getBean(ActivityRepository.class);
        //ActivityController activityController = context.getBean(ActivityController.class);*/
    }


}
