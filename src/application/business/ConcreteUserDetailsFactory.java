package application.business;

/**
 * Created by Ayu on 4/17/2017.
 */
public class ConcreteUserDetailsFactory implements UserDetailsFactory {

    public ConcreteUserDetailsFactory(){

    }
    @Override
    public UserDetails createUserDetails(String name, String pass, String role) {
        return new UserDetails(name, pass, role);
    }
}
