package member;

import member.medical_information.MedicalInformation;
import reward.Reward;

import java.util.List;

public class Member {
    private String name;
    private String surname;
    private String idNumber;
    private MedicalInformation medicalInformation;
    private int miles;
    private List<Reward> rewards;
    //Remember to refactor medical info
    public Member(String name, String surname, String idNumber, MedicalInformation medicalInformation, int miles, List<Reward> rewards) {
        this.name = name;
        this.surname = surname;
        this.idNumber = idNumber;
        this.medicalInformation = medicalInformation;
        this.miles = miles;
        this.rewards = rewards;
    }

    public void purchaseReward(Reward reward){
        try{
            subtractMiles(reward.getMileCost());
            addReward(reward);
        } catch (Exception e){
            System.out.println("Not enough points available for this award");
        }
    }

    private void addReward(Reward reward){
        getRewards().add(reward);
    }

    public void addMiles(int miles){
        setMiles(getMiles() + miles);
    }

    public void subtractMiles(int miles) throws Exception {
        if (getMiles() - miles < 0)
            throw new Exception("Not enough miles available");
        else
            setMiles(getMiles() - miles);
    }

    private Boolean isValidID(String idNumber){
        //to-do
        return true;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private String getSurname() {
        return surname;
    }

    private void setSurname(String surname) {
        this.surname = surname;
    }

    private String getIdNumber() {
        return idNumber;
    }

    private void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    private MedicalInformation getMedicalInformation() {
        return medicalInformation;
    }

    private void setMedicalInformation(MedicalInformation medicalInformation) {
        this.medicalInformation = medicalInformation;
    }

    private int getMiles() {
        return miles;
    }

    private void setMiles(int miles) {
        this.miles = miles;
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }
}
