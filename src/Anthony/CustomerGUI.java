package Anthony;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
 

public class CustomerGUI extends JFrame {

    private CustomerDAO dao;
    private List<customer> customers;
    private JTable table;
    private JTextField searchField;

    public CustomerGUI() {
        dao = new CustomerDAO();
        customers = dao.getAllCustomers();
        table = new JTable();
        searchField = new JTextField();

        // Set up the table
        DefaultTableModel model = new DefaultTableModel(new Object[]{"First Name", "Last Name", "Phone Number"}, 0);
        table.setModel(model);

        // Set up the search field
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.addActionListener(e -> searchCustomers());

        // Set up the frame
        setTitle("Customer List");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Add components to the frame
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search:"));
        topPanel.add(searchField);

        JButton sortButton = new JButton("Sort by Name");
        sortButton.addActionListener(e -> sortCustomers());
        topPanel.add(sortButton);

        JButton showAllButton = new JButton("Show All Customers");
        showAllButton.addActionListener(e -> showAllCustomers());
        topPanel.add(showAllButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Load the data into the table
        loadCustomers();
    }

    private void loadCustomers() {
        for (customer c : customers) {
            Object[] row = new Object[]{c.getFirstname(), c.getlastname(), c.getPhoneNumber()};
            ((DefaultTableModel) table.getModel()).addRow(row);
        }
    }

    private void searchCustomers() {
        String keyword = searchField.getText();
        List<customer> results = dao.searchCustomers(customers, keyword);
        results = dao.sortCustomersByName(results);
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        for (customer c : results) {
            Object[] row = new Object[]{c.getFirstname(), c.getlastname(), c.getPhoneNumber()};
            ((DefaultTableModel) table.getModel()).addRow(row);
        }
    }

    private void sortCustomers() {
        customers = dao.sortCustomersByName(customers);
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        loadCustomers();
    }

    private void showAllCustomers() {
        customers = dao.getAllCustomers();
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        loadCustomers();
    }

    public static void main(String[] args) {
        CustomerGUI gui = new CustomerGUI();
        gui.setVisible(true);
    }
}

