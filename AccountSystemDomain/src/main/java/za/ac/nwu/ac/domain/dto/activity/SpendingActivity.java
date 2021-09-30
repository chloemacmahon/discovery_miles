package za.ac.nwu.ac.domain.dto.activity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Data
@Entity
@Component
@DiscriminatorValue("Spend")
public class SpendingActivity extends Activity{

    public SpendingActivity() {
    }

    public SpendingActivity(String name, int pointsEarned) {
        super(name, pointsEarned);
    }
}
