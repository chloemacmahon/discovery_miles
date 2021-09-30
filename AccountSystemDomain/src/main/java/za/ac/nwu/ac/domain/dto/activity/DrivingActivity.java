package za.ac.nwu.ac.domain.dto.activity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
@DiscriminatorValue("Drive")
public class DrivingActivity extends Activity{

    public DrivingActivity() {
    }

    public DrivingActivity(String name, int pointsEarned) {
        super(name, pointsEarned);
    }
}
