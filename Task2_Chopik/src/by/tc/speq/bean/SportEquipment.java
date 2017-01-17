package by.tc.speq.bean;

/**
 * Created by User on 14.01.2017.
 */
public class SportEquipment {
    private Category category;
    private String title;
    private int price;

    public SportEquipment(String title, Category category, int price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public String getInfo() {
        return getTitle() + ", category: " + getCategory() + ", price: " + getPrice();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SportEquipment sportEquipment = (SportEquipment) obj;
        if (this.getPrice() != sportEquipment.getPrice()) {
            return false;
        }
        if (this.getCategory() != sportEquipment.getCategory()) {
            return false;
        }
        if (null == title) {
            return (title == sportEquipment.title);
        } else {
            if(!title.equals(sportEquipment.title)) {
                return false;
            }
        }

        return true;
    }

}
