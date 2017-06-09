package by.epam.webauction.bean;

public class Thing {
    private String name;
    private String description;
    private String ownerId;
    private String lotId;

    public Thing(String name, String description, String ownerId) {
        this(name, description, ownerId, null);
    }

    public Thing(String name, String description, String ownerId, String lotId) {
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
        this.lotId = lotId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }
}
