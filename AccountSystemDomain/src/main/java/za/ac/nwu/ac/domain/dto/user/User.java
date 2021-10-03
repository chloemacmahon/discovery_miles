package za.ac.nwu.ac.domain.dto.user;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class User {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    public User() {
    }

    public User(@NotEmpty String email, @NotEmpty String password) {
        this.email = email;
        this.password = password;
    }
}
