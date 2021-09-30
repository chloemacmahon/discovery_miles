package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;
import za.ac.nwu.ac.logic.RewardPartnerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/reward-partner")
public class RewardController {

    private RewardPartnerService rewardPartnerService;

    @Autowired
    public RewardController(RewardPartnerService rewardPartnerService) {
        this.rewardPartnerService = rewardPartnerService;
    }

    /*@GetMapping("/create-voucher-reward")
    public String showCreateVoucherReward(Model model){
        model.addAttribute("voucherReward", new VoucherReward());
        return "reward-partner/create-voucher-reward";
    }

    @RequestMapping(value ="/create-voucher-reward", method = RequestMethod.POST)
    public String createVoucherReward(@Valid VoucherReward voucherReward, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "reward-partner/create-voucher-reward";
        }
        return "reward-partner/create-voucher-reward";
    }*/
}
