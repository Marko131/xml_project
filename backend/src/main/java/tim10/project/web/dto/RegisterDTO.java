package tim10.project.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class RegisterDTO {
    @Size(min = 1, message = "First name must not be empty")
    private String name;
    @Size(min = 1, message = "Last name must not be empty")
    private String lastName;
    @Email
    private String email;
    @Size(max = 32, min = 6, message = "Password size should be between 6 and 32 characters")
    private String password1;
    @Size(max = 32, min = 6, message = "Password size should be between 6 and 32 characters")
    private String password2;
    @NotNull
    private String role;
    @NotNull
    private List<String> expertise;

    public RegisterDTO() {
    }

    public RegisterDTO(String name, String lastName, String email, String password1, String password2, String role, List<String> expertise) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password1 = password1;
        this.password2 = password2;
        this.role = role;
        this.expertise = expertise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public void setExpertise(List<String> expertise) {
        this.expertise = expertise;
    }
}
