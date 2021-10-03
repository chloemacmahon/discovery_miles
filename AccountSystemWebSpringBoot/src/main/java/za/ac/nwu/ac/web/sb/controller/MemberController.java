package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.ac.nwu.ac.domain.dto.activity.Activity;
import za.ac.nwu.ac.domain.dto.activity.DrivingActivity;
import za.ac.nwu.ac.domain.dto.activity.HealthActivity;
import za.ac.nwu.ac.domain.dto.activity.SpendingActivity;
import za.ac.nwu.ac.domain.dto.member.Member;
import za.ac.nwu.ac.domain.dto.user.User;
import za.ac.nwu.ac.logic.AddMilesService;
import za.ac.nwu.ac.logic.MemberService;
import za.ac.nwu.ac.repository.ActivityRepository;
import za.ac.nwu.ac.repository.MemberRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/member")
class MemberController {

    private final MemberRepository memberRepository;

    private final ActivityRepository activityRepository;

    private final MemberService memberService;

    private final AddMilesService addMilesService;

    @Autowired
    MemberController(MemberRepository memberRepository, ActivityRepository activityRepository, MemberService memberService, AddMilesService addMilesService) {
        this.memberRepository = memberRepository;
        this.activityRepository = activityRepository;
        this.memberService = memberService;
        this.addMilesService = addMilesService;
    }

    @RequestMapping(value = "/log-in", method = RequestMethod.GET)
    public String showLogInToMemberAccount(Model model) {
        model.addAttribute("user", new User());
        return "member/log-in";
    }

    @RequestMapping(value = "/log-in", method = RequestMethod.POST)
    public String logInToMemberAccount(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid details entered");
            return "error/account-error";
        }
        try {
            Member memberFromDatabase = memberService.logInMember(user.getEmail(), user.getPassword());
            model.addAttribute("member", memberFromDatabase);
            return "member/member-info";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
    }

    @RequestMapping(value = "member-info", method = RequestMethod.GET)
    public String showMemberInfo(Model model) {
        if (model.containsAttribute("member")) {
            return "member/member-info";
        }
        model.addAttribute("errorMessage", "No account signed in");
        return "error/account-error";
    }

    @RequestMapping(value = "member-info", method = RequestMethod.POST)
    public String postMemberInfo(Model model) {
        if (model.containsAttribute("member")) {
            return "member/member-info";
        }
        model.addAttribute("errorMessage", "No account signed in");
        return "error/account-error";
    }

    @RequestMapping(value = "add-activity-to-goal/{id}", method = RequestMethod.GET)
    public String showAddActivity(@PathVariable Long id, Model model) {
        try{
            Member member = memberRepository.findById(id).get();
            model.addAttribute("member",member);
            model.addAttribute("activities", memberService.viewActivities());
            Long activityId = 0L;
            model.addAttribute("activityId", activityId);
            return "member/add-activity-to-goal";
        } catch (RuntimeException e){
            model.addAttribute("errorMessage", "No account signed in");
            return "error/account-error";
        }
    }

    @RequestMapping(value = "add-activity-to-goal/{id}",method = RequestMethod.POST)
    public String addActivity(@PathVariable Long id, Long activityId, Model model){
        if (activityId != null){
            if (id != null){
                Member member = memberRepository.findById(id).get();
                Activity activity = activityRepository.findById(activityId).get();
                if (activity instanceof HealthActivity){
                    if(addMilesService.addPointsToHealthGoal((HealthActivity) activity, member)){
                        return "/member/choose-game-tile";
                    }
                } else if (activity instanceof DrivingActivity){
                    if(addMilesService.addPointsToDrivingGoal((DrivingActivity) activity, member)){
                        return "/member/choose-game-tile";
                    }
                } else if (activity instanceof SpendingActivity){
                    if(addMilesService.addPointsToSpendingGoal((SpendingActivity) activity, member)){
                        return "/member/choose-game-tile";
                    }
                } else {
                    model.addAttribute("errorMessage", "No activity selected");
                    return "error/account-error";
                }
            } else {
                model.addAttribute("errorMessage", "No active account");
                return "error/account-error";
            }
        } else {
            model.addAttribute("errorMessage", "No activity selected");
            return "error/account-error";
        }
        model.addAttribute("member",memberRepository.findById(id).get());
        model.addAttribute("activities", memberService.viewActivities());
        return "member/add-activity-to-goal";
    }

}