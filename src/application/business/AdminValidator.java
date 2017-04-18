package application.business;
import application.ui.AdminController;

/**
 * Created by Ayu on 4/17/2017.
 */
public class AdminValidator implements  MyAuthenticator {
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

       //if(this.nextAuthenticate.validateRequest(request) )

        if (request.getName().equals("Ayu") &&  request.getPassword().equals("myName")){

            AdminController sac = new AdminController();
            sac.loadAdminWindow();
            return;

        }else {
           System.out.println("Username or password incorrect. Please try again");
          //  nextAuthenticate.setNextAuthonticator(Authenticator);
        }

    }


}

