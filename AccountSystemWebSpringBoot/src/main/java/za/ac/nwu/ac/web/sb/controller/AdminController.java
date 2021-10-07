package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import za.ac.nwu.ac.domain.dto.activity.Activity;
import za.ac.nwu.ac.domain.dto.activity.DrivingActivity;
import za.ac.nwu.ac.domain.dto.activity.HealthActivity;
import za.ac.nwu.ac.domain.dto.activity.SpendingActivity;
import za.ac.nwu.ac.domain.dto.admin.Admin;
import za.ac.nwu.ac.domain.dto.exchange_rate.ExchangeRate;
import za.ac.nwu.ac.domain.dto.user.User;
import za.ac.nwu.ac.logic.AdminService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

   /* private final CurrencyConverterService currencyConverterService;

    private final ExchangeRateRepository exchangeRateRepository;*/

    @Autowired
    public AdminController(AdminService adminService){//}, CurrencyConverterService currencyConverterService, ExchangeRateRepository exchangeRateRepository) {
        this.adminService = adminService;
        /*this.currencyConverterService = currencyConverterService;
        this.exchangeRateRepository = exchangeRateRepository;*/
    }

    @RequestMapping(value = "/log-in", method = RequestMethod.GET)
    public String showLogInToMemberAccount(Model model) {
        model.addAttribute("user", new User());
        return "admin/log-in";
    }

    @RequestMapping(value = "/log-in", method = RequestMethod.POST)
    public String logInToMemberAccount(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid details entered");
            return "error/account-error";
        }
        try {
            Admin adminFromDatabase = adminService.logInAdmin(user.getEmail(), user.getPassword());
            return "admin/show-create-activity";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
    }

    @RequestMapping(value = "/show-create-activity", method = RequestMethod.GET)
    public String showCreateActivity(){
        return "admin/show-create-activity";
    }

    @RequestMapping(value = "/show-create-health-activity", method = RequestMethod.GET)
    public String showCreateHealthActivity(Model model){
        model.addAttribute("healthActivity",new HealthActivity());
        return "admin/create-health-activity";
    }

    @RequestMapping(value = "/show-create-driving-activity", method = RequestMethod.GET)
    public String showCreateDrivingActivity(Model model){
        model.addAttribute("drivingActivity",new DrivingActivity());
        return "admin/create-driving-activity";
    }

    @RequestMapping(value = "/show-create-spending-activity", method = RequestMethod.GET)
    public String showCreateSpendingActivity(Model model){
        model.addAttribute("spendingActivity",new SpendingActivity());
        return "admin/create-spending-activity";
    }

    @RequestMapping(value = "/create-activity", method = RequestMethod.POST)
    public String createActivity(Activity activity, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid details entered");
            return "error/account-error";
        }
        try {
            if (activity instanceof HealthActivity)
                adminService.addNewActivity("Health",activity.getDescription(),activity.getPointsEarned());
            if (activity instanceof DrivingActivity)
                adminService.addNewActivity("Drive",activity.getDescription(),activity.getPointsEarned());
            if (activity instanceof SpendingActivity)
                adminService.addNewActivity("Spend",activity.getDescription(),activity.getPointsEarned());
            return "admin/show-create-activity";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
    }

    @RequestMapping(value = "/create-health-activity", method = RequestMethod.POST)
    public String createHealthActivity(HealthActivity healthActivity, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid details entered");
            return "error/account-error";
        }
        try {
                adminService.addNewActivity("Health",healthActivity.getDescription(),healthActivity.getPointsEarned());
            return "admin/show-create-activity";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
    }

    @RequestMapping(value = "/create-driving-activity", method = RequestMethod.POST)
    public String createDrivingActivity(DrivingActivity drivingActivity, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid details entered");
            return "error/account-error";
        }
        try {
            adminService.addNewActivity("Drive",drivingActivity.getDescription(),drivingActivity.getPointsEarned());
            return "admin/show-create-activity";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
    }

    @RequestMapping(value = "/create-spending-activity", method = RequestMethod.POST)
    public String createSpendingActivity(SpendingActivity spendingActivity, Model model, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid details entered");
            return "error/account-error";
        }
        try {
            adminService.addNewActivity("Spend",spendingActivity.getDescription(),spendingActivity.getPointsEarned());
            return "admin/show-create-activity";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
    }

    @RequestMapping(value = "/add-exchange-rate", method = RequestMethod.GET)
    public String showCreateExchangeRate(Model model) {
        model.addAttribute("exchangeRate",new ExchangeRate());
        return "/admin/create-exchange-rate";
    }

    @RequestMapping(value = "/add-exchange-rate", method = RequestMethod.POST)
    public String createExchangeRate(@Valid ExchangeRate exchangeRate,Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid details entered");
            return "error/account-error";
        }
        try {
            //exchangeRateRepository.save(exchangeRate);
            return "admin/show-create-activity";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getLocalizedMessage());
            return "error/account-error";
        }
    }
}
