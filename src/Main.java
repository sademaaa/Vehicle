import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Renting service = new Renting();
        DBConnection db = new DBConnection();
        Scanner sc = new Scanner(System.in);

        System.out.print("How many vehicles do you want to add? ");
        int count = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < count; i++) {

            System.out.println("\nVehicle " + (i + 1));

            System.out.print("Brand: ");
            String brand = sc.nextLine();

            System.out.print("Price: ");
            int price = sc.nextInt();
            sc.nextLine();

            Vehicle vehicle = new Car(brand, price);

            service.addVehicle(vehicle);
            db.insertVehicle(vehicle);
        }

        System.out.println("\nAll vehicles (from program):");
        service.showAllVehicles();

        System.out.println("\nAll vehicles (from database):");
        ArrayList<Vehicle> vehiclesFromDB = db.readVehicles();
        for (Vehicle v : vehiclesFromDB) {
            System.out.println(v);
        }

        sc.close();
    }
}
