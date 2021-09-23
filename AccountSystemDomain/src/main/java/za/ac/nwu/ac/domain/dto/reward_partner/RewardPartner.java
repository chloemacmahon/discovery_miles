package za.ac.nwu.ac.domain.dto.reward_partner;
import za.ac.nwu.ac.domain.dto.helper_classes.ReceiveInputs;
import lombok.Data;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.reward.Reward;
import za.ac.nwu.ac.domain.dto.reward.SubscriptionReward;
import za.ac.nwu.ac.domain.dto.reward.VoucherReward;

import javax.persistence.*;

@Data
@Entity
@Table (name = "reward_partner")
@Component
public class RewardPartner {

    @Id
    @Column (name = "reward_partner_id")
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long rewardPartnerId;

    @Column (name = "company_name", unique = true)
    private String companyName;

    @Column (unique = true)
    private String email;

    @Column (name = "admin_password")
    private String adminPassword;

    public RewardPartner() {
    }

    public RewardPartner(String companyName, String adminPassword, String email) {
        this.companyName = companyName;
        this.email = email;
        this.adminPassword = adminPassword;
    }

    private Reward createReward() {
        if (ReceiveInputs.getIntInput("Enter 1 for subscription za.ac.nwu.ac.domain.dto.reward") == 1) {
            SubscriptionReward subscriptionReward = new SubscriptionReward(ReceiveInputs.getStringInput("Enter the item description"),
                    ReceiveInputs.getIntInput("Enter the mile cost"), this, ReceiveInputs.getIntInput("How many months subscription is this reward for"));
            //db.insertReward((Reward)subscriptionReward);
            return subscriptionReward;
        } else {
            VoucherReward voucherReward = new VoucherReward(ReceiveInputs.getStringInput("Enter the item description"),
                    ReceiveInputs.getIntInput("Enter the mile cost"), this, ReceiveInputs.getIntInput("What is the monetary value of this reward?"));
            //db.insertReward((Reward)voucherReward);
            return voucherReward;
        }
    }
}
