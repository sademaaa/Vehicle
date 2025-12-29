public class Vehicle {

    protected String type;
    protected String brand;
    protected int price;

    public Vehicle(String type, String brand, int price) {
        this.type = type;
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return type + " " + brand + " " + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Vehicle)) return false;
        Vehicle v = (Vehicle) obj;
        return price == v.price && brand.equals(v.brand);
    }

    @Override
    public int hashCode() {
        return brand.hashCode() + price;
    }
}
