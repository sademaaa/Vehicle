package domain;

public class Car extends Vehicle {
    public Car(String brand, int price) {
        super(brand, price);
    }

    @Override
    public String getType() {
        return "Car";
    }

    // Паттерн Builder (Требование №9)
    public static class Builder {
        private String brand;
        private int price;

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setPrice(int price) {
            this.price = price;
            return this;
        }

        public Car build() {
            return new Car(brand, price);
        }
    }
}