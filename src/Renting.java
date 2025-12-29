import java.util.ArrayList;

public class Renting {

    private ArrayList<Vehicle> vehicles = new ArrayList<>();

    public void addVehicle(Vehicle v) {
        vehicles.add(v);


    }

    public void showAllVehicles() {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    public void showCheapVehicles(int maxPrice) {
        for (Vehicle v : vehicles) {
            if (v.getPrice() <= maxPrice) {
                System.out.println(v);
            }
        }
    }

    public void sortByPrice() {
        for (int i = 0; i < vehicles.size() - 1; i++) {
            for (int j = i + 1; j < vehicles.size(); j++) {
                if (vehicles.get(i).getPrice() > vehicles.get(j).getPrice()) {
                    Vehicle temp = vehicles.get(i);
                    vehicles.set(i, vehicles.get(j));
                    vehicles.set(j, temp);
                }
            }
        }
    }
}
