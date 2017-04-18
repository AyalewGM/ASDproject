package application.business;

/**
 * Created by Ayu on 4/18/2017.
 */
public class NullChecker implements MyAuthenticator {

    private MyAuthenticator authenticator;

    @Override
    public void setNextAuthonticator(MyAuthenticator nextAuth) {
        this.authenticator = nextAuth;
    }


    @Override
    public void validateRequest(Validator request) {
        if (request.getName().equals(" ") || request.getPassword().equals("") || request.getName() == null) {

            System.out.println("Please Enter Username or password. These fields are manadatory");
            return;

        }

    }
}
