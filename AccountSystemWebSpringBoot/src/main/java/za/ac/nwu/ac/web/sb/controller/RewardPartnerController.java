package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;
import za.ac.nwu.ac.logic.RewardPartnerService;

import javax.validation.Valid;

@Controller
@RequestMapping("/reward-partner")
public class RewardPartnerController {

    private RewardPartnerService rewardPartnerService;

    @Autowired
    public RewardPartnerController(RewardPartnerService rewardPartnerService) {
        this.rewardPartnerService = rewardPartnerService;
    }

    @GetMapping("/create-voucher-reward")
    public String showCreateVoucherReward(Model model){
        model.addAttribute("formData", new VoucherReward());
        return "reward-partner/create-voucher-reward";
    }

    @PostMapping("/create-voucher-reward")
    public String createVoucherReward(@Valid Model model){
        model.addAttribute("formData", new VoucherReward());
        return "reward-partner/create-voucher-reward";
    }
}
