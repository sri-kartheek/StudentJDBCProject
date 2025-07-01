import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class StudentManager{
    static Connection con;

    public static void main(String[] args) {
        try {
            // JDBC connection
            // Inside main()
            con = DBUtil.getConnection();
            
            // Create frame
            JFrame frame = new JFrame("Student Manager");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(6, 1));

            // Buttons
            JButton addBtn = new JButton("Add Student");
            JButton viewBtn = new JButton("View All");
            JButton updateBtn = new JButton("Update Student");
            JButton deleteBtn = new JButton("Delete Student");
            JButton searchBtn = new JButton("Search by ID");
            JButton exitBtn = new JButton("Exit");

            // Add actions
            addBtn.addActionListener(e -> addStudent());
            viewBtn.addActionListener(e -> viewStudents());
            updateBtn.addActionListener(e -> updateStudent());
            deleteBtn.addActionListener(e -> deleteStudent());
            searchBtn.addActionListener(e -> searchById());
            exitBtn.addActionListener(e -> System.exit(0));

            // Add buttons to frame
            frame.add(addBtn);
            frame.add(viewBtn);
            frame.add(updateBtn);
            frame.add(deleteBtn);
            frame.add(searchBtn);
            frame.add(exitBtn);


            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID:"));
            String name = JOptionPane.showInputDialog("Enter Name:");
            int marks = Integer.parseInt(JOptionPane.showInputDialog("Enter Marks:"));

            String sql = "INSERT INTO students VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, marks);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Student Added!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    static void viewStudents() {
        try {
            StringBuilder result = new StringBuilder("ID\tName\tMarks\n");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                result.append(rs.getInt("id")).append("\t")
                      .append(rs.getString("name")).append("\t")
                      .append(rs.getInt("marks")).append("\n");
            }

            JTextArea textArea = new JTextArea(result.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(350, 200));
            JOptionPane.showMessageDialog(null, scrollPane, "Student List", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    static void updateStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID of student to update:"));
            String newName = JOptionPane.showInputDialog("Enter new name:");
            int newMarks = Integer.parseInt(JOptionPane.showInputDialog("Enter new marks:"));

            String sql = "UPDATE students SET name=?, marks=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, newName);
            ps.setInt(2, newMarks);
            ps.setInt(3, id);
            int rows = ps.executeUpdate();

            if (rows > 0)
                JOptionPane.showMessageDialog(null, "Student Updated.");
            else
                JOptionPane.showMessageDialog(null, "Student not found.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    static void deleteStudent() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));

            String sql = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows > 0)
                JOptionPane.showMessageDialog(null, "Student Deleted.");
            else
                JOptionPane.showMessageDialog(null, "Student not found.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
    static void searchById() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to search:"));

            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int marks = rs.getInt("marks");
                JOptionPane.showMessageDialog(null,
                    "Student Found:\nID: " + id + "\nName: " + name + "\nMarks: " + marks);
            } else {
                JOptionPane.showMessageDialog(null, "Student not found.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
}
