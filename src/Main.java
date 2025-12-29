public class Main {

    public static void main(String[] args) {

        Renting service = new Renting();

        Vehicle car1 = new Car("Toyota", 10000);
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

        System.out.println("\n");
        System.out.println(client);
    }
}
