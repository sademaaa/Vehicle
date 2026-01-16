import java.sql.*;
import java.util.ArrayList;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/vehicle";
    private static final String USER = "javauser";
    private static final String PASSWORD = "12345";
    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void insertVehicle(Vehicle v) {
        String sql = "INSERT INTO vehicles (vehicle_type, vehicle_brand, price) VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, v.getClass().getSimpleName()); // например "Car"
            stmt.setString(2, v.getBrand());
            stmt.setInt(3, v.getPrice());
            stmt.executeUpdate();
            System.out.println("Vehicle added to DB: " + v);
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR");
            e.printStackTrace();
        }
    }

    public ArrayList<Vehicle> readVehicles() {
        ArrayList<Vehicle> list = new ArrayList<>();
        String sql = "SELECT vehicle_type, vehicle_brand, price FROM vehicles";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String type = rs.getString("vehicle_type");
                String brand = rs.getString("vehicle_brand");
                int price = rs.getInt("price");

                Vehicle v;
                if (type.equals("Car")) {
                    v = new Car(brand, price);
                } else {
                    v = new Vehicle(type, brand, price);
                }
                list.add(v);
            }
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR");
            e.printStackTrace();
        }
        return list;
    }

    public void updatePrice(String brand, int newPrice) {
        String sql = "UPDATE vehicles SET price = ? WHERE vehicle_brand = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newPrice);
            stmt.setString(2, brand);
            int rows = stmt.executeUpdate();
            System.out.println("Updated " + rows + " rows.");
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR");
            e.printStackTrace();
        }
    }

    public void deleteVehicle(String brand) {
        String sql = "DELETE FROM vehicles WHERE vehicle_brand = ?";
        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, brand);
            int rows = stmt.executeUpdate();
            System.out.println("Deleted " + rows + " rows.");
        } catch (SQLException e) {
            System.out.println("DATABASE ERROR");
            e.printStackTrace();
        }
    }

}
