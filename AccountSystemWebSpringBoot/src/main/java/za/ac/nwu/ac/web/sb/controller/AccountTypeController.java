package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.logic.AddMilesService;
import za.ac.nwu.ac.logic.MemberService;
import za.ac.nwu.ac.repository.AdminRepository;
import za.ac.nwu.ac.repository.MemberRepository;
import za.ac.nwu.ac.repository.RewardPartnerRepository;

@Controller
//@RequestMapping("/log-in")
public class AccountTypeController {

    private MemberRepository memberRepository;

    private AdminRepository adminRepository;

    private RewardPartnerRepository rewardPartnerRepository;

    private AddMilesService addMilesService;

    private MemberService memberService;

    @Autowired
    public AccountTypeController(MemberRepository memberRepository, AdminRepository adminRepository, RewardPartnerRepository rewardPartnerRepository, AddMilesService addMilesService, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.adminRepository = adminRepository;
        this.rewardPartnerRepository = rewardPartnerRepository;
        this.addMilesService = addMilesService;
        this.memberService = memberService;
    }

    @GetMapping(value="/login")
    public String logIn() {
        return "log-in";
    }

}
