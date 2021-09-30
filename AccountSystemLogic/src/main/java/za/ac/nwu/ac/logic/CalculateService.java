package za.ac.nwu.ac.logic;

import org.springframework.stereotype.Component;

@Component("calculateService")
public class CalculateService {

    public double percentageComplete(int pointsEarned, int pointsNecessary){
        return (double)pointsEarned / pointsNecessary *100;
    }

}