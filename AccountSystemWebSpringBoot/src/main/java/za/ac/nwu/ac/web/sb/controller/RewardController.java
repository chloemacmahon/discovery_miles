package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import za.ac.nwu.ac.domain.dto.reward_partner.RewardPartner;
import za.ac.nwu.ac.logic.RewardPartnerService;
import za.ac.nwu.ac.repository.RewardPartnerRepository;

@Controller
@RequestMapping("/reward")
public class RewardController {

    private final RewardPartnerService rewardPartnerService;

    private final RewardPartnerRepository rewardPartnerRepository;

    @Autowired
    public RewardController(RewardPartnerService rewardPartnerService, RewardPartnerRepository rewardPartnerRepository) {
        this.rewardPartnerService = rewardPartnerService;
        this.rewardPartnerRepository = rewardPartnerRepository;
    }

    @RequestMapping(value = "/show-create-reward", method = RequestMethod.GET)
    public String showCreateRewardPage(Model model){
        model.addAttribute("rewardPartner", (RewardPartner)model.asMap().get("rewardPartner"));
        return "reward/show-create-reward";
    }

    @RequestMapping(value = "/create-voucher-reward/{rewardPartnerId}", method = RequestMethod.GET)
    public String showCreateVoucherReward(@PathVariable Long rewardPartnerId, Model model) {
        model.addAttribute("itemDescription", "");
        int mileCost = 0;
        model.addAttribute("mileCost", mileCost);
        double monetaryValue = 0.0;
        model.addAttribute("monetaryValue", monetaryValue);
        model.addAttribute("rewardPartner", rewardPartnerRepository.findById(rewardPartnerId).get());
        model.addAttribute("rewardPartnerId", rewardPartnerId);
        return "reward/create-voucher-reward";
    }

    @RequestMapping(value = "/create-voucher-reward", method = RequestMethod.POST)
    public String createVoucherReward(@RequestParam String itemDescription, @RequestParam int mileCost, @RequestParam double monetaryValue, @RequestParam Long rewardPartnerId, Model model) {
        try {
            RewardPartner rewardPartner = rewardPartnerRepository.findById(rewardPartnerId).get();
            rewardPartnerService.createVoucherReward(itemDescription, mileCost, monetaryValue, rewardPartner);
            model.addAttribute("rewardPartner",rewardPartner);
            return "reward/show-create-reward";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/account-error";
        }
    }

    @RequestMapping(value = "/create-subscription-reward/{rewardPartnerId}", method = RequestMethod.GET)
    public String showCreateSubscriptionReward(@PathVariable Long rewardPartnerId, Model model) {
        model.addAttribute("itemDescription", "");
        int mileCost = 0;
        model.addAttribute("mileCost", mileCost);
        int monthsSubscription = 0;
        model.addAttribute("monthsSubscription", monthsSubscription);
        model.addAttribute("rewardPartner", rewardPartnerRepository.findById(rewardPartnerId).get());
        model.addAttribute("rewardPartnerId", rewardPartnerId);
        return "reward/create-subscription-reward";
    }

    @RequestMapping(value = "/create-subscription-reward", method = RequestMethod.POST)
    public String createSubscriptionReward(@RequestParam String itemDescription, @RequestParam int mileCost, @RequestParam int monthsSubscription, @RequestParam Long rewardPartnerId, Model model) {
       try {
            RewardPartner rewardPartner = rewardPartnerRepository.findById(rewardPartnerId).get();
            rewardPartnerService.createSubscriptionReward(itemDescription, mileCost, monthsSubscription, rewardPartner);
            model.addAttribute("rewardPartner",rewardPartner);
            return "reward/show-create-reward";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error/account-error";
        }
    }
}
