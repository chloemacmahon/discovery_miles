package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.ac.nwu.ac.domain.dto.admin.Admin;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;
import za.ac.nwu.ac.logic.AdminService;
import za.ac.nwu.ac.logic.MemberService;
import za.ac.nwu.ac.logic.RewardPartnerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class CreateAccountController {
    private RewardPartnerService rewardPartnerService;

    private AdminService adminService;

    private MemberService memberService;

    @Autowired
    public CreateAccountController(RewardPartnerService rewardPartnerService, AdminService adminService, MemberService memberService) {
        this.rewardPartnerService = rewardPartnerService;
        this.adminService = adminService;
        this.memberService = memberService;
    }

    @RequestMapping(value = "/admin/create-account", method = RequestMethod.GET)
    public String showCreateAdminAccount(Model model){
        model.addAttribute("admin", new Admin());
        return "admin/create-account";
    }

    @RequestMapping(value ="/admin/create-account", method = RequestMethod.POST)
    public String createAdminAccount(@Valid Admin admin, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", new String("Invalid details entered"));
            return "error/account-error";
        }
        try {
            model.addAttribute("activeAdmin",adminService.registerAdmin(admin.getEmail(), admin.getPassword(), 3879));
            model.addAttribute("activeAccountType","admin");
        } catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
        return "admin/create-account";
    }

    @RequestMapping(value = "/reward-partner/create-account", method = RequestMethod.GET)
    public String showCreateRewardPartnerAccount(Model model){
        model.addAttribute("rewardPartner", new RewardPartner());
        return "reward-partner/create-account";
    }

    @RequestMapping(value ="/reward-partner/create-account", method = RequestMethod.POST)
    public String createRewardPartnerAccount(@Valid RewardPartner rewardPartner, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", new String("Invalid details entered"));
            return "error/account-error";
        }
        try {
            model.addAttribute("rewardPartner", rewardPartnerService.registerRewardPartner(rewardPartner.getCompanyName(), rewardPartner.getEmail(), rewardPartner.getAdminPassword(), 6523));
        } catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
//        model.addAttribute("voucher-reward",new VoucherReward());
        return "reward/create-voucher-reward";
    }

    @RequestMapping(value = "/member/create-account", method = RequestMethod.GET)
    public String showCreateMemberAccount(Model model){
        model.addAttribute("member", new Member());
        return "member/create-account";
    }

    @RequestMapping(value ="/member/create-account", method = RequestMethod.POST)
    public String createMemberAccount(@Valid Member member, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", new String("Invalid details entered"));
            return "error/account-error";
        }
        try {
            Member memberFromDatabase = memberService.registerMember(member.getName(), member.getSurname(), member.getIdNumber(), member.getEmail(), member.getPassword());
            model.addAttribute("member", memberFromDatabase);
            return "member/member-info";
        } catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
    }
}
