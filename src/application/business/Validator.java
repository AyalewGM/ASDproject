package application.business;

/**
 * Created by Ayu on 4/17/2017.
 */
public class Validator {
    private String name;
    private String password;

    public Validator(String name, String password) {
        this.name = name;
        this.password = password;

    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }
}
