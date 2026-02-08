package factory;

import domain.Car;
import domain.Vehicle;

public class VehicleFactory {
    public static Vehicle create(String type, String brand, int price) {

        if ("Car".equalsIgnoreCase(type)) {

            return new Car.Builder().setBrand(brand).setPrice(price).build();
        }
        throw new IllegalArgumentException("Unknown type");
    }
}