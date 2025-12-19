public class Main {
    public static void main(String[] args) {

        Vehicle car1 = new Vehicle("Car", "Toyotaa", 10000);
        Vehicle car2 = new Vehicle("Car", "BMW", 15000);
        Vehicle car3 = new Vehicle("Car", "Honda", 20200);
        Client client1 = new Client("Adema", "+77089949485");

        System.out.println(car1);
        System.out.println(car2);
        System.out.println(client1);

        if (car1.price() < car2.price() & car1.price()<car3.price()) {
            System.out.println("Toyota is cheaper than BMW and Honda.");
        }
        else if (car2.price() < car3.price() && car2.price() < car1.price()) {
            System.out.println("BMW is cheaper than toyota and Honda.");
        }
        else if (car3.price() < car2.price() && car3.price() < car1.price()) {
            System.out.println("Honda is cheaper than toyota and BMW.");}
        else {
            System.out.println("prices are equal");
        }
    }
    }
