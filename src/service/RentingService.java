package service;

import domain.Vehicle;
import exception.InvalidVehicleException;
import repository.Repository;
import java.util.Comparator;
import java.util.List;

public class RentingService {

    private final Repository<Vehicle> repository;

    public RentingService(Repository<Vehicle> repository) {
        this.repository = repository;
    }

    public void add(Vehicle v) {
        if (v.getBrand() == null || v.getBrand().isBlank())
            throw new InvalidVehicleException("Brand is empty");
        repository.save(v);
    }

    public List<Vehicle> getAllSorted() {

        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(Vehicle::getPrice))
                .toList();
    }
}