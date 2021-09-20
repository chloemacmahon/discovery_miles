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
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @JoinColumn(referencedColumnName =  "reward.reward_partner_id",name = "reward_partner.reward_partner_id")
    @ManyToMany
    private Long rewardPartnerId;

    @Column (name = "company_name")
    private String companyName;

    @Column (name = "admin_password")
    private String adminPassword;

    public RewardPartner(String companyName, String adminPassword) {
        this.companyName = companyName;
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