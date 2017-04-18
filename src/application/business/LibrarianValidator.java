package application.business;

import application.ui.AdminController;
import application.ui.LibrarianController;

/**
 * Created by Ayu on 4/17/2017.
 */
public class LibrarianValidator implements MyAuthenticator {
    private MyAuthenticator nextAuthenticate;
//
//    public  AdminValidator( Authenticator next){
//this.nextAuthenticate= next;
//    }

    @Override
    public void setNextAuthonticator(MyAuthenticator next) {
        this.nextAuthenticate= next;
    }

    @Override
    public void validateRequest(Validator request) {
        if (request.getName().equals("Betka") &&  request.getPassword().equals("Betka")){

            LibrarianController sac = new LibrarianController();
           sac.loadAdminWindow();
           return;

        }else nextAuthenticate.validateRequest(request);

        }

    }

