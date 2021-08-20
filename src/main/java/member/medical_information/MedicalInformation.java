package member.medical_information;

import java.util.ArrayList;
import java.util.List;

public class MedicalInformation {
    private int age;
    private double weight;
    private double height;
    private List<Disease> diseases;

    public MedicalInformation(int age, double weight, double height) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.diseases = new ArrayList<>();
    }

    public MedicalInformation(int age, double weight, double height, List<Disease> diseases) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.diseases = diseases;
    }

    private int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    private double getWeight() {
        return weight;
    }

    private void setWeight(double weight) {
        this.weight = weight;
    }

    private double getHeight() {
        return height;
    }

    private void setHeight(double height) {
        this.height = height;
    }

    private List<Disease> getDiseases() {
        return diseases;
    }

    private void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
