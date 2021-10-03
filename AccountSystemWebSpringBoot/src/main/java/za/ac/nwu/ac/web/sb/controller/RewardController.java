package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;
import za.ac.nwu.ac.logic.RewardPartnerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/reward")
public class RewardController {

    private RewardPartnerService rewardPartnerService;

    @Autowired
    public RewardController(RewardPartnerService rewardPartnerService) {
        this.rewardPartnerService = rewardPartnerService;
    }

    @RequestMapping(value = "/create-voucher-reward", method = RequestMethod.GET)
    public String showCreateVoucherReward(Model model){ //@RequestParam(value = "itemDescription",required = true) String itemDescription, @RequestParam(value = "mileCost",required = true) int mileCost, @RequestParam(value = "monetaryValue",required = true) double monetaryValue){
        model.addAttribute("itemDescription", new String());
        int mileCost = 0;
        model.addAttribute("mileCost", mileCost);
        double monetaryValue = 0.0;
        model.addAttribute("monetaryValue", monetaryValue);
        return "reward-partner/create-voucher-reward";
    }

    @RequestMapping(value ="/create-voucher-reward", method = RequestMethod.POST)
    public String createVoucherReward(BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errorMessage", "Could not create reward");
            return "error/account-error";
        }
        try {
            rewardPartnerService.createVoucherReward((String) model.asMap().get("itemDescription"), (int) model.asMap().get("mileCost"), (double) model.asMap().get("monetaryValue"),(RewardPartner) model.asMap().get("reward-partner"));
        }catch(RuntimeException e){
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
        return "reward-partner/create-voucher-reward";
    }
}
