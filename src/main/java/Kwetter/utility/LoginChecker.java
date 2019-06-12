package Kwetter.utility;

import Kwetter.dao.login.LoginDAO;
import Kwetter.dto.UserDTO;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginChecker implements LoginModule {

    @Inject
    private LoginDAO loginDAO;
    private Subject subject;
    private CallbackHandler callbackHandler;

    // not used in this simple LoginModule but can be useful when LoginModule are stacked
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
                           Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        final Callback[] callbacks = new Callback[]{
                new NameCallback("username"),
                new PasswordCallback("password", false)
        };
        try {
            callbackHandler.handle(callbacks);
        }
        catch(IOException e) {
            Logger.getLogger(LoginChecker.class.getName()).log(Level.SEVERE, e.getMessage());
        }
        catch(UnsupportedCallbackException e) {
            Logger.getLogger(LoginChecker.class.getName()).log(Level.SEVERE, e.getMessage());
        }

        final String username = NameCallback.class.cast(callbacks[0]).getName();
        final char[] password = PasswordCallback.class.cast(callbacks[1]).getPassword();

        loginDAO = CDI.current().select(LoginDAO.class).get();
        UserDTO user = loginDAO.login(username, new String(password));
        if(user == null) {
            return false;
        }
        else {
            subject.getPrincipals().add(user);
            return true;
        }
    }

    @Override
    public boolean commit() throws LoginException {
        return false;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
