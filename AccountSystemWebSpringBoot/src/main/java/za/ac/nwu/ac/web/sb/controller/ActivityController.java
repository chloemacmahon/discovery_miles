package za.ac.nwu.ac.web.sb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.ac.repository.ActivityRepository;

@Controller
//@RequestMapping("activity")
public class ActivityController {

    private ActivityRepository activityRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @RequestMapping(value="/activities")
    public String getActivities(Model model){
        model.addAttribute("activities", activityRepository.findAll());
        return "view-activities";
    }

    /*@GetMapping(value="/view-activities")
    public String getActivities(Model model){
        model.addAttribute("activities", activityRepository.findAll());
        return "view-activities";
    }*/


}
