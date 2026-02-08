import api.SimpleHttpServer;
import domain.Vehicle;
import factory.VehicleFactory;
import repository.DBConnection;
import repository.Repository;
import service.RentingService;
import exception.InvalidVehicleException;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Java Reflection API Demo ===");
        try {
            Class<?> vehicleClass = Class.forName("domain.Vehicle");
            Method[] methods = vehicleClass.getDeclaredMethods();
            for (Method m : methods) {
                System.out.println("Found method: " + m.getName());
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found for reflection.");
        }
        System.out.println("================================\n");


        Repository<Vehicle> repository = DBConnection.getInstance();
        RentingService service = new RentingService(repository);


        Thread serverThread = new Thread(() -> {
            try {
                SimpleHttpServer.startServer(8080, service);
            } catch (Exception e) {
                System.err.println("Could not start server: " + e.getMessage());
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("System started. Web interface: http://localhost:8080");

        while (true) {
            try {
                System.out.println("\n--- VEHICLE MANAGEMENT ---");
                System.out.println("1. Add new vehicle into DB");
                System.out.println("2. Look at the vehicles into DB");
                System.out.println("0. Exit");
                System.out.print("Your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1" -> {
                        System.out.print("Brand: ");
                        String brand = scanner.nextLine();

                        System.out.print("Price: ");
                        String priceInput = scanner.nextLine();


                        int price;
                        try {
                            price = Integer.parseInt(priceInput);
                        } catch (NumberFormatException e) {
                            throw new InvalidVehicleException("Error, price must be an integer!");
                        }


                        Vehicle newVehicle = VehicleFactory.create("Car", brand, price);
                        service.add(newVehicle);
                        System.out.println("✅ Vehicle is saved in PostgreSQL!");
                    }
                    case "2" -> {
                        System.out.println("\n--- Current autopark (price sorting) ---");
                        service.getAllSorted().forEach(v ->
                                System.out.println("-> " + v.toString())
                        );
                    }
                    case "0" -> {
                        System.out.println("Shutdown...");
                        System.exit(0);
                    }
                    default -> System.out.println("❌ Wrong command, try again.");
                }
            } catch (InvalidVehicleException e) {
                System.out.println("⚠️ Validation error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("💥 Systematic error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}