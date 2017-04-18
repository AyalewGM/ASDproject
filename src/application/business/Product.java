package application.business;

import java.io.Serializable;
import java.util.Date;
/**
 * Created by Ayu on 4/18/2017.
 */




public class Product<D extends ProductMetadata> implements Serializable {
    private D metaData;
    private String name;
    private String id;
    private Date availableFrom;
    private boolean availability;


    public Product(String name, String iSBN, Date availableFrom, boolean availability, Manufacture authors, int numCopies) {
        this.name = name;
        this.id = iSBN;
        this.availableFrom = availableFrom;
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public void setMetaData(D metaData) {
        this.metaData = metaData;
    }

    public D getMetaData() {
        return metaData;
    }
}