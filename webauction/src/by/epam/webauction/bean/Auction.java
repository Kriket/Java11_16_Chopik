package by.epam.webauction.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction {

    private String id;
    private String step;
    private Currency currency;
    private Boolean isActive;
    private Date dateOfStart;
    private String name;
    private List<Lot> lots;

    public Auction(String id, String step, Currency currency, Boolean isActive, Date dateOfStart, String name) {
        this.id = id;
        this.step = step;
        this.currency = currency;
        this.isActive = isActive;
        this.dateOfStart = dateOfStart;
        this.name = name;
        lots = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Date getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(Date dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }

    public void addLot(Lot lot) {
        this.lots.add(lot);
    }
}
