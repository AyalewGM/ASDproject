package application.business;

/**
 * Created by Ayu on 4/18/2017.
 */
public class Manufacture extends Person {
    private int id;
    private boolean credentials;
    private String description;

    public Manufacture(String firstName, String lastName, String phoneNumber, Address address,
                       boolean credentials, String description) {
        super(firstName, lastName, phoneNumber, address);
        this.credentials = credentials;
        this.description = description;

    }


    public boolean isCredentials() {
        return credentials;
    }

    public void setCredentials(boolean credentials) {
        this.credentials = credentials;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}