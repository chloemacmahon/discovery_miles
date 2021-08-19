package member.medical_information;

import java.util.List;

public class Disease {
    private String name;
    private int threatToHealth;
    private List<String> chronicMedication;

    public Disease(String name, int threatToHealth, List<String> chronicMedication) {
        this.name = name;
        this.threatToHealth = threatToHealth;
        this.chronicMedication = chronicMedication;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getThreatToHealth() {
        return threatToHealth;
    }

    private void setThreatToHealth(int threatToHealth) {
        this.threatToHealth = threatToHealth;
    }

    public List<String> getChronicMedication() {
        return chronicMedication;
    }

    private void setChronicMedication(List<String> chronicMedication) {
        this.chronicMedication = chronicMedication;
    }
}
