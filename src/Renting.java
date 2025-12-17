public class Renting {

    public void showVehicle(Vehicle vehicle) {
        System.out.println(vehicle);
    }

    public void compareVehicles(Vehicle v1, Vehicle v2) {
        System.out.println(v1);
        System.out.println(v2);

        if (v1.price() < v2.price()) {
            System.out.println(v1.getBrand() + " is cheaper.");
        } else if (v1.price() > v2.price()) {
            System.out.println(v2.getBrand() + " is cheaper.");
        } else {
            System.out.println("Both vehicles have the same price.");
        }
    }
}
