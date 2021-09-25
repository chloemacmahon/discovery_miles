package za.ac.nwu.ac.domain.dto.admin;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@Entity
@Component
public class Admin {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long adminId;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    public Admin() {
    }

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
