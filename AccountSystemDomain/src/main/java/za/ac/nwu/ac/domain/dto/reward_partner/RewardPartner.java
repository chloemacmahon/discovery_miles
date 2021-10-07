package za.ac.nwu.ac.domain.dto.reward_partner;
import za.ac.nwu.ac.domain.dto.helper_classes.ReceiveInputs;
import lombok.Data;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.reward.Reward;
import za.ac.nwu.ac.domain.dto.reward.SubscriptionReward;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table (name = "reward_partner")
@Component
public class RewardPartner {

    @Id
    @Column (name = "reward_partner_id")
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long rewardPartnerId;

    @Column (name = "company_name", unique = true, nullable = false)
    @NotEmpty
    private String companyName;

    @Column (unique = true, nullable = false)
    @NotEmpty
    private String email;

    @Column (name = "admin_password")
    @NotEmpty
    private String adminPassword;

    public RewardPartner() {
    }

    public RewardPartner(String companyName, String email, String adminPassword) {
        this.companyName = companyName;
        this.email = email;
        this.adminPassword = adminPassword;
    }

    private Reward createReward() {
        if (ReceiveInputs.getIntInput("Enter 1 for subscription za.ac.nwu.ac.domain.dto.reward") == 1) {
            SubscriptionReward subscriptionReward = new SubscriptionReward(ReceiveInputs.getStringInput("Enter the item description"),
                    ReceiveInputs.getIntInput("Enter the mile cost"), this, ReceiveInputs.getIntInput("How many months subscription is this reward for"));
            return subscriptionReward;
        } else {
            VoucherReward voucherReward = new VoucherReward(ReceiveInputs.getStringInput("Enter the item description"),
                    ReceiveInputs.getIntInput("Enter the mile cost"), this, ReceiveInputs.getIntInput("What is the monetary value of this reward?"));
            return voucherReward;
        }
    }
}
