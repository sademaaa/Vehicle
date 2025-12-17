public class Vehicle {
    private String type;
    private String brand;
    private int price;

    public Vehicle(String type, String brand, int price) {
        this.type = type;
        this.brand = brand;
        this.price = price;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String model) {
        this.brand = brand;
    }

    public double price() {
        return price;
    }
    public void price(int price) {
        this.price = price;
    }

    public String toString() {
        return type + " " + brand + "  " + price;
    }

}
