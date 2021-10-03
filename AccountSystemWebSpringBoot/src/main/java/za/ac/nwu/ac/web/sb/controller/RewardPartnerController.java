package za.ac.nwu.ac.web.sb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;
import za.ac.nwu.ac.domain.dto.user.User;
import za.ac.nwu.ac.logic.RewardPartnerService;
import za.ac.nwu.ac.repository.RewardPartnerRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/reward-partner")
public class RewardPartnerController {

    private final RewardPartnerRepository rewardPartnerRepository;

    private final RewardPartnerService rewardPartnerService;

    public RewardPartnerController(RewardPartnerRepository rewardPartnerRepository, RewardPartnerService rewardPartnerService) {
        this.rewardPartnerRepository = rewardPartnerRepository;
        this.rewardPartnerService = rewardPartnerService;
    }

    @RequestMapping(value = "/log-in", method = RequestMethod.GET)
    public String showLogInToRewardPartnerAccount(Model model) {
        model.addAttribute("user", new User());
        return "reward-partner/log-in";
    }

    @RequestMapping(value = "/log-in", method = RequestMethod.POST)
    public String logInToRewardPartnerAccount(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid details entered");
            return "error/account-error";
        }
        try {
            RewardPartner rewardPartnerFromDatabase = rewardPartnerService.logInRewardPartner(user.getEmail(), user.getPassword());
            model.addAttribute("reward-partner", rewardPartnerFromDatabase);
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "e.getLocalizedMessage()");
            return "error/account-error";
        }
        return "reward/create-voucher-reward";
    }

}
