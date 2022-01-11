package classes;

public class Car {
    private String model;
    private String price;
    private String img;

    public Car(String model, String price, String img) {
        this.model = model;
        this.price = price;
        this.img = img;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
