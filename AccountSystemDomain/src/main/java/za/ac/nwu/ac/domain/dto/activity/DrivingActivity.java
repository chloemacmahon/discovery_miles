package za.ac.nwu.ac.domain.dto.activity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@DiscriminatorValue("Drive")
public class DrivingActivity extends Activity{

    /**
     * Completing activities of this type earn points towards the member's weekly driving goal
     */

    public DrivingActivity() {
    }

    public DrivingActivity(String description, int pointsEarned) {
        super(description, pointsEarned);
    }

    public String activityType(){
        return "Driving";
    }
}
