package domain;

import java.util.Objects;

public abstract class Vehicle {
    protected String brand;
    protected int price;

    protected Vehicle(String brand, int price) {
        this.brand = brand;
        this.price = price;
    }

    public String getBrand() { return brand; }
    public int getPrice() { return price; }
    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s] %s - $%d", getType(), brand, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return price == vehicle.price && Objects.equals(brand, vehicle.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, price);
    }
}