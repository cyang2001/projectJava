package com.isep.eleve.javaproject.service.userServices;
import com.isep.eleve.javaproject.model.User;
import com.isep.eleve.javaproject.Tools.Constants;
import java.io.IOException;
import java.util.List;
import com.isep.eleve.javaproject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Registration service
 * @version V1.2
 * @Author Chen YANG
 */
@Service
public class RegistrationService {

    private final SecurityService securityService;
    private final UserRepository userRepository;
    @Autowired
    public RegistrationService(SecurityService securityService, UserRepository userRepository) {
        this.securityService = securityService;
        this.userRepository = userRepository;
    }
    /**
     * Register
     * @param userName
     * @param password
     * @param passwordEnsurance
     * @return RegistrationResult
     * @throws IOException
     */
    public RegistrationResult register(String userName, String password, String passwordEnsurance) throws IOException {
        // check if the password and passwordEnsurance are the same
        if (!password.equals(passwordEnsurance)) {
            return new RegistrationResult(false, Constants.REGISTRATION_FAILIURE_TYPE.PASSWORD_NOT_SAME, null);
        }

        // ToDo: check if the password is empty

        // check if the user name already exists
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return new RegistrationResult(false, Constants.REGISTRATION_FAILIURE_TYPE.USERNAME_ALREADY_EXIST, null);
            }
        }
        // create new user
        User newUser = new User(userName, securityService.encryptData(password, Constants.ENCRYPT_TYPE.MD5));
        // save new user
        userRepository.save(newUser);
        return new RegistrationResult(true, null, newUser);
    }


    public class RegistrationResult {
        private boolean success;
        private Constants.REGISTRATION_FAILIURE_TYPE failureType;
        private User user;

        public RegistrationResult(boolean success, Constants.REGISTRATION_FAILIURE_TYPE failureType, User user) {
            this.success = success;
            this.failureType = failureType;
            this.user = user;
        }

        public boolean isSuccess() {
            return success;
        }

        public Constants.REGISTRATION_FAILIURE_TYPE getFailureType() {
            return failureType;
        }

        public User getUser() {
            return user;
        }
    }
}
