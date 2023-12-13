package csw_endsem_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class Modify implements ActionListener {
    Connection connection = null;
    JFrame mf = new JFrame();
    ImageIcon icon = new ImageIcon("img/modify.png");
    JButton backButton = new JButton("< BACK");
    JButton saveButton = new JButton("SAVE");
    JLabel mainLabel = new JLabel("MODIFY INFORMATION OF THE DATABASE");
    JLabel currentLabel = new JLabel("CURRENT ENTRIES");
    JLabel newEntryLabel = new JLabel("MODIFY AN ENTRY");
    JLabel idLabel = new JLabel("ID:");
    JLabel nameLabel = new JLabel("Name:");
    JLabel dobLabel = new JLabel("DOB(YYYY-MM-DD):");
    JLabel ocLabel = new JLabel("OCCUPATION:");
    JLabel ftLabel = new JLabel("FAMILY TYPE:");
    JLabel statusLabel = new JLabel("STATUS:");

    JTextField idField = new JTextField();
    JTextField nameField = new JTextField();
    JTextField dobField = new JTextField();
    JTextField ocField = new JTextField();

    String statusArr[] = {"Active", "Deceased"};
    JComboBox<String> statusBox = new JComboBox<>(statusArr);

    String famArr[] = {"Single", "Joint"};
    JComboBox<String> famBox = new JComboBox<>(famArr);

    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();

    JSeparator lineSep = new JSeparator();

    public Modify() {
        mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mf.setUndecorated(true);
        mf.setResizable(false);
        mf.setSize(1200, 560);
        mf.setVisible(true);
        mf.setLocationRelativeTo(null);
        mf.setTitle("MODIFY");
        mf.getContentPane().setBackground(new Color(0x1d1d1d));
        mf.setLayout(null);
        mf.setIconImage(icon.getImage());

        backButton.setBounds(50, 473, 90, 36);
        backButton.setBackground(new Color(0x1d1b1b));
        backButton.setFocusable(false);
        backButton.setFont(new Font("Calibri", Font.PLAIN, 19));
        backButton.setForeground(new Color(0xEEEDE7));
        backButton.addActionListener(this);

        saveButton.setBounds(390, 473, 90, 36);
        saveButton.setBackground(new Color(0x1d1b1b));
        saveButton.setFocusable(false);
        saveButton.setFont(new Font("Calibri", Font.PLAIN, 19));
        saveButton.setForeground(new Color(0xEEEDE7));
        saveButton.addActionListener(this);

        mainLabel.setBounds(290, 27, 700, 40);
        mainLabel.setFont(new Font("Calibri", Font.PLAIN, 36));
        mainLabel.setForeground(new Color(0xEEEDE7));

        currentLabel.setBounds(720, 127, 270, 31);
        currentLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        currentLabel.setForeground(new Color(0xEEEDE7));

        newEntryLabel.setBounds(196, 127, 270, 31);
        newEntryLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        newEntryLabel.setForeground(new Color(0xEEEDE7));

        idLabel.setBounds(100, 200, 30, 21);
        idLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        idLabel.setForeground(new Color(0xEEEDE7));

        nameLabel.setBounds(100, 240, 90, 21);
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        nameLabel.setForeground(new Color(0xEEEDE7));

        dobLabel.setBounds(100, 280, 165, 21);
        dobLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        dobLabel.setForeground(new Color(0xEEEDE7));

        ocLabel.setBounds(100, 320, 125, 21);
        ocLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        ocLabel.setForeground(new Color(0xEEEDE7));

        ftLabel.setBounds(100, 360, 120, 21);
        ftLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        ftLabel.setForeground(new Color(0xEEEDE7));

        statusLabel.setBounds(100, 400, 99, 21);
        statusLabel.setFont(new Font("Calibri", Font.PLAIN, 21));
        statusLabel.setForeground(new Color(0xEEEDE7));

        idField.setBounds(277, 195, 200, 21);
        idField.setFont(new Font("Calibri", Font.PLAIN, 21));
        idField.setBorder(null);

        nameField.setBounds(277, 235, 200, 21);
        nameField.setFont(new Font("Calibri", Font.PLAIN, 21));
        nameField.setBorder(null);

        dobField.setBounds(277, 275, 200, 21);
        dobField.setFont(new Font("Calibri", Font.PLAIN, 21));
        dobField.setBorder(null);

        ocField.setBounds(277, 315, 200, 21);
        ocField.setFont(new Font("Calibri", Font.PLAIN, 21));
        ocField.setBorder(null);

        famBox.setBounds(277, 355, 200, 21);
        statusBox.setBounds(277, 397, 200, 21);

        table.setBackground(new Color(0x121212));
        table.setForeground(new Color(0xEEEDE7));
        scrollPane.setBounds(520, 180, 650, 330);
        scrollPane.getViewport().setBackground(new Color(0x404040));
        scrollPane.setViewportView(table);

        lineSep.setOrientation(SwingConstants.VERTICAL);
        lineSep.setBounds(500, 115, 120, 420);

        mf.add(lineSep);
        mf.add(scrollPane);
        mf.add(mainLabel);
        mf.add(currentLabel);
        mf.add(newEntryLabel);
        mf.add(backButton);
        mf.add(saveButton);
        mf.add(idLabel);
        mf.add(nameLabel);
        mf.add(dobLabel);
        mf.add(ocLabel);
        mf.add(ftLabel);
        mf.add(statusLabel);
        mf.add(idField);
        mf.add(nameField);
        mf.add(dobField);
        mf.add(ocField);
        mf.add(statusBox);
        mf.add(famBox);

        connection = getMySQLConnection();
        try {
            String query = "SELECT * FROM Information";
            PreparedStatement pst = connection.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            table.setModel(buildTableModel(rs));

        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            mf.setVisible(false);
            new Admin();
        }
        if (e.getSource() == saveButton) {
            if (idField.getText().length() != 5) {
                JOptionPane.showMessageDialog(null, "Please enter a valid ID from the database!");
            } else if (nameField.getText().equals("") || dobField.getText().equals("") || (ocField.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Please fill all the particulars");
            } else {
                try {
                    String query = "UPDATE Information SET Name=?, DOB=?, Occupation=?, FamilyType=?, Status=? WHERE ID=?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, nameField.getText());
                    pst.setString(2, dobField.getText());
                    pst.setString(3, ocField.getText());
                    pst.setString(4, (String) famBox.getSelectedItem());
                    pst.setString(5, (String) statusBox.getSelectedItem());
                    pst.setString(6, idField.getText());

                    pst.executeUpdate();

                    String query2 = "SELECT * FROM Information";
                    PreparedStatement pst2 = connection.prepareStatement(query2);
                    ResultSet rs = pst2.executeQuery();
                    table.setModel(buildTableModel(rs));

                    JOptionPane.showMessageDialog(null, "Data was modified Successfully!");
                } catch (Exception g) {
                    JOptionPane.showMessageDialog(null, "Failed to modify data");
                    g.printStackTrace();
                }
            }
        }
    }

    private Connection getMySQLConnection() {
        // Provide your MySQL database information (url, username, password)
        String url = "jdbc:mysql://localhost:3306/MunicipalDB";
        String username = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Inside the Modify class
private DefaultTableModel buildTableModel(ResultSet rs) {
    try {
        return ResultSetTableModel.buildTableModel(rs);
    } catch (SQLException e) {
        e.printStackTrace();
        return null;
    }
}
}
