package tim10.project.web.dto;

import javax.validation.constraints.Size;

public class ChangePasswordDTO {
    @Size(max = 32, min = 6, message = "Password size should be between 6 and 32 characters")
    private String password1;
    @Size(max = 32, min = 6, message = "Password size should be between 6 and 32 characters")
    private String password2;

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
}
