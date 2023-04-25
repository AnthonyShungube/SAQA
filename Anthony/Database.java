package Anthony;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    
    private String url;
    private String username;
   private String password;
    
    public Database(String url, String username) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public void insert(String customer) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO customers (name) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, customer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getAll() {
        ArrayList<String> content = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT name FROM customers";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String customer = result.getString("name");
                content.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return content;
    }
    
}
