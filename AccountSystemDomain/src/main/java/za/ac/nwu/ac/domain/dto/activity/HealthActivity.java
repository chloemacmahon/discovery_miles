package za.ac.nwu.ac.domain.dto.activity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@Component
@DiscriminatorValue("Health")
public class HealthActivity extends Activity{

    public HealthActivity() {
    }

    public HealthActivity(String description, int pointsEarned) {
        super(description, pointsEarned);
    }

    public String activityType(){
        return "Health";
    }
}
