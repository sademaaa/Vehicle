import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Renting service = new Renting();
        DBConnection db = new DBConnection();

        Vehicle car1 = new Car("Toyota", 12000);
        Vehicle car2 = new Car("BMW", 15000);
        Vehicle car3 = new Car("Hendai", 24000);

        Client client = new Client("Adema", "+77089949485");

        service.addVehicle(car1);
        service.addVehicle(car2);
        service.addVehicle(car3);

        System.out.println("All vehicles:");
        service.showAllVehicles();

        System.out.println("\nCheap vehicles:");
        service.showCheapVehicles(12000);

        System.out.println("\nSorted vehicles:");
        service.sortByPrice();
        service.showAllVehicles();

        System.out.println("\nClient info:");
        System.out.println(client);

        System.out.println("\nDATABASE");

        db.insertVehicle(car1);
        db.insertVehicle(car2);
        db.insertVehicle(car3);

        ArrayList<Vehicle> vehiclesFromDB = db.readVehicles();
        System.out.println("From DB:");
        for (Vehicle v : vehiclesFromDB) {
            System.out.println(v);
        }

        db.updatePrice("BMW", 16000);

        db.deleteVehicle("Hendai");
    }
}
