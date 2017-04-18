package application.business;

/**
 * Created by Ayu on 4/17/2017.
 */
public interface MyAuthenticator {
    //private Authenticator nextAuthonticator;

    public void setNextAuthonticator(MyAuthenticator nextAuth);

    public void validateRequest(Validator request);


}
