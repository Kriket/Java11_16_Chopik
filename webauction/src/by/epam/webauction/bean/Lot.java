package by.epam.webauction.bean;

import java.util.ArrayList;
import java.util.List;

public class Lot {

    private String id;
    private String startPrice;
    private String currentPrice;
    private String finishPrice;
    private Boolean isSell;
    private String idSeller;
    private String idBuyer;
    private List<Thing> things;

    public Lot(String id, String startPrice, String currentPrice, String finishPrice, Boolean isSell, String idSeller, String idBuyer) {
        this.id = id;
        this.startPrice = startPrice;
        this.currentPrice = currentPrice;
        this.finishPrice = finishPrice;
        this.isSell = isSell;
        this.idSeller = idSeller;
        this.idBuyer = idBuyer;
        things = new ArrayList<>();
    }

    public Lot(String id, String startPrice, Boolean isSell, String idSeller) {
        this(id, startPrice, null, null, isSell, idSeller, null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getFinishPrice() {
        return finishPrice;
    }

    public void setFinishPrice(String finishPrice) {
        this.finishPrice = finishPrice;
    }

    public Boolean getSell() {
        return isSell;
    }

    public void setSell(Boolean sell) {
        isSell = sell;
    }

    public String getIdSeller() {
        return idSeller;
    }

    public void setIdSeller(String idSeller) {
        this.idSeller = idSeller;
    }

    public String getIdBuyer() {
        return idBuyer;
    }

    public void setIdBuyer(String idBuyer) {
        this.idBuyer = idBuyer;
    }

    public List<Thing> getThings() {
        return things;
    }

    public void setThings(List<Thing> things) {
        this.things = things;
    }

    public void addThing(Thing thing) {
        things.add(thing);
    }

}
