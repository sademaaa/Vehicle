package repository;

import domain.Car;
import domain.Vehicle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection implements Repository<Vehicle> {
    private static DBConnection instance;
    private Connection connection;

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/vehicle", "javauser", "12345");
        } catch (SQLException e) {
            throw new RuntimeException("DB Connection Error", e);
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) instance = new DBConnection();
        return instance;
    }

    @Override
    public void save(Vehicle v) {
        String sql = "INSERT INTO vehicles (type, brand, price) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, v.getType());
            ps.setString(2, v.getBrand());
            ps.setInt(3, v.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> list = new ArrayList<>();

        String sql = "SELECT type, brand, price FROM vehicles";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                String brand = rs.getString("brand");
                int price = rs.getInt("price");

                list.add(new Car(brand, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}