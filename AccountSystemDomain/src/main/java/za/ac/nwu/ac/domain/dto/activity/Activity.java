package za.ac.nwu.ac.domain.dto.activity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Component
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name= "activity_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Activity {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String description;

    @NotNull
    private int pointsEarned;

    public Activity() {
    }

    public Activity(String description, int pointsEarned) {
        this.description = description;
        this.pointsEarned = pointsEarned;
    }

    public abstract String activityType();
}
