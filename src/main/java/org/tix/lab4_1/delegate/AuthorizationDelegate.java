package org.tix.lab4_1.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.tix.lab4_1.model.mainDB.User;
import org.tix.lab4_1.model.util.Role;
import org.tix.lab4_1.repo.mainDB.ExpertMessageRepository;
import org.tix.lab4_1.util.UserRepository;


@Component
public class AuthorizationDelegate implements JavaDelegate {
    private final UserRepository userRepository;
    private final ExpertMessageRepository expertMessageRepository;
    private final PasswordEncoder encoder;

    public AuthorizationDelegate(UserRepository userRepository, ExpertMessageRepository expertMessageRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.expertMessageRepository = expertMessageRepository;
        this.encoder = encoder;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        boolean isAdmin = false;
        boolean userExist = false;

        String login = (String) delegateExecution.getVariable("login");
        String password = (String) delegateExecution.getVariable("password");

        User user = userRepository.findByEmail(login).orElse(null);
        if ((user != null) && (encoder.matches(password,user.getPassword())))
            userExist = true;
        System.out.println(user.getRole());
        if (user.getRole() == Role.ROLE_ADMIN){
            isAdmin = true;
        }

        delegateExecution.setVariable("user_exist",userExist);
        delegateExecution.setVariable("is_admin", isAdmin);
    }
}

