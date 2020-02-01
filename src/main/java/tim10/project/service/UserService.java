package tim10.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import tim10.project.model.user.User;
import tim10.project.repository.ScientificPaperRepository;
import tim10.project.repository.UserRepository;
import tim10.project.service.exceptions.NotFoundException;
import tim10.project.service.exceptions.PasswordsDoNotMatchException;
import tim10.project.service.exceptions.UserAlreadyExistsException;
import tim10.project.service.exceptions.UserNotFoundException;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ScientificPaperRepository paperRepository;

    @Transactional
    public UserDetails loadUserByUsername(String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else{
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            if (user.getRole().equals("editor")) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_EDITOR"));
            } else if (user.getRole().equals("reviewer")) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_REVIEWER"));
            } else {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_AUTHOR"));
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    grantedAuthorities
            );
        }
    }

    public User createUser(User user){
        if (userRepository.findByEmail(user.getEmail()) != null) throw new UserAlreadyExistsException();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        if (user == null) throw new NotFoundException(String.format("User with email:%s does not exist", email));
        return user;
    }

    public User findUserById(Integer id){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) throw new NotFoundException(String.format("User with id:%s does not exist", id));
        return user;
    }

    public User changePassword(User user, String password1, String password2) {
        if (!password1.equals(password2)){
            throw new PasswordsDoNotMatchException("Password and confirmation password do not match");
        }
        user.setPassword(passwordEncoder.encode(password1));
        return userRepository.save(user);
    }

    public User updateProfile(User user, String newEmail, String newFirstName, String newLastName){
        if (userRepository.findByEmail(newEmail) != null && !user.getEmail().equals(newEmail)) throw new UserAlreadyExistsException();
        user.setEmail(newEmail);
        user.setName(newFirstName);
        user.setLastName(newLastName);
        return userRepository.save(user);
    }

    public User addReview(User user, String paperId){
        user.getReviews().add(paperId);
        return userRepository.save(user);
    }

    public List<User> getReviewersForPaper(String paperId) throws SAXException, ParserConfigurationException, XPathExpressionException, IOException, JAXBException, XMLDBException {
        ArrayList<String> keywords = (ArrayList<String>) paperRepository.getKeywordsFromPaper("/db/sample/library/paper", paperId);
        ArrayList<User> users = (ArrayList<User>) userRepository.findAll();
        ArrayList<User> reviewers = new ArrayList<>();
        for (User user: users) {
            for (String keyword: keywords) {
                if (user.getExpertise().contains(keyword) && (user.getRole().equals("reviewer") || user.getRole().equals("editor"))) {
                    reviewers.add(user);
                }
            }
        }
        if (reviewers.isEmpty()) return users;
        return reviewers;
    }
}
