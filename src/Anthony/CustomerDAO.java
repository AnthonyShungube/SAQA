package Anthony;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private Connection conn;

    public CustomerDAO() {
        try {
            // Connect to MySQL database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customer", "root", "Anthony@123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<customer> getAllCustomers() {
        List<customer> customers = new ArrayList<>();
        try {
            // Execute SQL query
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM customers");

            // Map result set to customer objects
            while (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phoneNumber = rs.getString("PhoneNumber");
                customer c = new customer(firstName, lastName, phoneNumber);
                customers.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


public List<customer> searchCustomers(List<customer> customers, String keyword) {
    List<customer> results = new ArrayList<>();
    for (customer c : customers) {
        if (c.getFirstname().toLowerCase().contains(keyword.toLowerCase())
                || c.getlastname().toLowerCase().contains(keyword.toLowerCase())) {
            results.add(c);
        }
    }
    return results;
}

public List<customer> sortCustomersByName(List<customer> customers) {
    customers.sort((c1, c2) -> {
        int result = c1.getFirstname().compareToIgnoreCase(c2.getFirstname()); 
        if (result == 0) {
        	result = c1.getlastname().compareToIgnoreCase(c2.getlastname());
        }
        return result;
    });
    return customers;
}
}

