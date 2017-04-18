package application.business;

/**
 * Created by Ayu on 4/18/2017.
 */
public class ChainBuilder {
    private MyAuthenticator authonticator;
    public void builChain(){

        // Authenticator htos = new AdminValidator();
        MyAuthenticator  auth= new AdminValidator();
        MyAuthenticator lib = new LibrarianValidator();
        MyAuthenticator nullAuth= new NullChecker();

        nullAuth.setNextAuthonticator(lib);
        lib.setNextAuthonticator(auth);
        authonticator= lib;
    }

    public MyAuthenticator getAuthonticator() {
        return authonticator;
    }
}
