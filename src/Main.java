public class Main {
    public static void main(String[] args) {

        Vehicle car1 = new Vehicle("Car", "Toyota", 10000);
        Vehicle car2 = new Vehicle("Car", "BMW", 15000);

        Client client1 = new Client("Adema", "+77089949485");

        System.out.println(car1);
        System.out.println(car2);
        System.out.println(client1);

        if (car1.price() < car2.price()) {
            System.out.println("Toyota is cheaper than BMW.");
        } else {
            System.out.println("BMW is more expensive than Toyota");
        }
    }
}
