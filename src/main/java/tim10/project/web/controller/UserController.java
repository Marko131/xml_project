package tim10.project.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tim10.project.model.user.User;
import tim10.project.security.TokenUtils;
import tim10.project.service.UserService;
import tim10.project.service.exceptions.InvalidPasswordException;
import tim10.project.service.exceptions.NotFoundException;
import tim10.project.service.exceptions.PasswordsDoNotMatchException;
import tim10.project.web.dto.ChangePasswordDTO;
import tim10.project.web.dto.LoginDTO;
import tim10.project.web.dto.RegisterDTO;
import tim10.project.web.dto.UserDetailsDTO;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    TokenUtils tokenUtils;

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    loginDTO.getEmail(), loginDTO.getPassword());
            authenticationManager.authenticate(token);
            UserDetails details = userService.loadUserByUsername(loginDTO.getEmail());
            return new ResponseEntity<String>(tokenUtils.generateToken(details), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<String>("Invalid login", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO registerDTO) {
        if (!registerDTO.getPassword1().equals(registerDTO.getPassword2())) throw new PasswordsDoNotMatchException();
        User user = new User(registerDTO.getName(), registerDTO.getLastName(), registerDTO.getEmail(), registerDTO.getPassword1(), registerDTO.getRole(), registerDTO.getExpertise());
        userService.createUser(user);
        return new ResponseEntity<String>("User successfully created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_AUTHOR') or hasRole('ROLE_REVIEWER') or hasRole('ROLE_EDITOR')")
    @GetMapping("/api/profile")
    public UserDetailsDTO profile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.findUserByEmail(email);
        if (u == null) throw new NotFoundException("Invalid user");
        System.out.println(u.getEmail());
        return new UserDetailsDTO(u.getName(), u.getLastName(), u.getEmail());
    }

    @PreAuthorize("hasRole('ROLE_AUTHOR') or hasRole('ROLE_REVIEWER') or hasRole('ROLE_EDITOR')")
    @PutMapping("/api/reset")
    public UserDetailsDTO changePassword(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) throws Exception {
        System.out.println(changePasswordDTO.getPassword1());
        System.out.println(changePasswordDTO.getPassword2());
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.findUserByEmail(email);
        User updatedUser = userService.changePassword(u, changePasswordDTO.getPassword1(), changePasswordDTO.getPassword2());
        return new UserDetailsDTO(updatedUser.getName(), updatedUser.getLastName(), updatedUser.getEmail());
    }

    @PreAuthorize("hasRole('ROLE_AUTHOR') or hasRole('ROLE_REVIEWER') or hasRole('ROLE_EDITOR')")
    @PutMapping("api/updateProfile")
    public UserDetailsDTO updateProfile(@Valid @RequestBody UserDetailsDTO userDetailsDTO){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User u = userService.findUserByEmail(email);
        User updatedUser = userService.updateProfile(u, userDetailsDTO.getEmail(), userDetailsDTO.getFirstName(), userDetailsDTO.getLastName());
        return new UserDetailsDTO(updatedUser.getName(), updatedUser.getLastName(), updatedUser.getEmail());
    }
}
