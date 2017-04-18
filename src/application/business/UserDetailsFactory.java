package application.business;

/**
 * Created by Ayu on 4/17/2017.
 */
public interface UserDetailsFactory {
    public UserDetails createUserDetails(String name, String pass, String role );
}
