package tim10.project.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class UserDetailsDTO {
    @Size(min = 1, message = "First name must not be empty")
    private String firstName;
    @Size(min = 1, message = "Last name must not be empty")
    private String lastName;
    @Email
    private String email;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
}