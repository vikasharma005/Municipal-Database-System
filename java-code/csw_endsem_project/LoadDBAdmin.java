package csw_endsem_project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class LoadDBAdmin implements ActionListener {
    // Adding required swing/awt components
    Connection connection = null; // Setting the initial connection to null
    JFrame af = new JFrame();
    ImageIcon icon = new ImageIcon("img/logo.png");
    JButton exitButton = new JButton("EXIT");
    JButton backButton = new JButton("< BACK");
    JButton loadButton = new JButton("LOAD DATA");
    JButton exportButton = new JButton("EXPORT DATA");
    JScrollPane scrollPane = new JScrollPane();
    JTable table = new JTable();
    JLabel showlLabel = new JLabel();
    ImageIcon showImage = new ImageIcon("img/show.png");
    JLabel exportLabel = new JLabel();
    ImageIcon exportImage = new ImageIcon("img/export.png");

    LoadDBAdmin(Connection connection) {
        // Setting up the JFRAME
        af.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        af.setUndecorated(true);
        af.setResizable(false);
        af.setSize(700, 500);
        af.setVisible(true);
        af.setLocationRelativeTo(null);
        af.setTitle("Load DB(Admin)");
        af.getContentPane().setBackground(new Color(0x1d1d1d));
        af.setLayout(null);
        af.setIconImage(icon.getImage());

        // Setting up the exit button
        exitButton.setBounds(590, 450, 90, 36);
        exitButton.setBackground(new Color(0x1d1b1b));
        exitButton.setFocusable(false);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 19));
        exitButton.setForeground(new Color(0xEEEDE7));
        exitButton.addActionListener(this);

        // Setting up the back button
        backButton.setBounds(20, 450, 110, 36);
        backButton.setBackground(new Color(0x1d1b1b));
        backButton.setFocusable(false);
        backButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        backButton.setForeground(new Color(0xEEEDE7));
        backButton.addActionListener(this);

        // Setting up the load button
        loadButton.setBounds(20, 20, 200, 45);
        loadButton.setBackground(new Color(0x1d1b1b));
        loadButton.setFocusable(false);
        loadButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        loadButton.setForeground(new Color(0xEEEDE7));
        loadButton.addActionListener(this);

        // Setting up the export button
        exportButton.setBounds(445, 20, 200, 45);
        exportButton.setBackground(new Color(0x1d1b1b));
        exportButton.setFocusable(false);
        exportButton.setFont(new Font("Calibri", Font.PLAIN, 20));
        exportButton.setForeground(new Color(0xEEEDE7));
        exportButton.addActionListener(this);

        // Setting up the JTABLE and JScrollpane
        table.setBackground(new Color(0x121212));
        table.setForeground(new Color(0xEEEDE7));
        scrollPane.setBounds(20, 90, 659, 350);
        scrollPane.getViewport().setBackground(new Color(0x404040));
        scrollPane.setViewportView(table);

        // Setting up showlabel/exoortlabel image
        showlLabel.setBounds(227, 27, 32, 32);
        showlLabel.setIcon(showImage);
        exportLabel.setBounds(650, 27, 32, 32);
        exportLabel.setIcon(exportImage);

        // Adding components to the JFRAME
        af.add(showlLabel);
        af.add(exitButton);
        af.add(backButton);
        af.add(loadButton);
        af.add(scrollPane);
        af.add(showlLabel);
        af.add(exportLabel);
        af.add(exportButton);

        this.connection = connection; // Getting the MySQL connection

        // Below, When the frame loads, pop up message is shown to the user, indicating to click the load button to show the DB
        JOptionPane.showMessageDialog(null, "Click LOAD DATA to view the DATABASE");
    }

    @Override
    // Button onclick events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        if (e.getSource() == backButton) {
            af.setVisible(false);
            new Admin();
        }
        if (e.getSource() == loadButton) {
            try {
                String query = "SELECT * FROM Information";
                PreparedStatement pst = connection.prepareStatement(query);
                ResultSet rs = pst.executeQuery();

                table.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception a) {
                a.printStackTrace();
            }
        }
        if (e.getSource() == exportButton) {
            if (table.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "LOAD DATABASE FIRST!");
            } else {
                String osname = System.getProperty("os.name");
                String username = System.getProperty("user.name");
                if (osname.charAt(0) == 'W') { // W for windows, if L(inux) then file path changes
                    try {
                        BufferedWriter bw = new BufferedWriter(
                                new FileWriter("C:\\Users\\" + username + "\\Desktop\\output.txt"));
                        bw.write(
                                "--------------------------------------------------------------------------------------------------------------\n");
                        bw.write("ID     |     " + "Name          |" + "        DOB        |" + "     Occupation      |"
                                + "     FamilyType      |" + "       Status \n");
                        bw.write(
                                "--------------------------------------------------------------------------------------------------------------\n");

                        for (int i = 0; i < table.getRowCount(); i++) {
                            String id = (table.getValueAt(i, 0).toString());
                            String name = (table.getValueAt(i, 1).toString());
                            String dob = (table.getValueAt(i, 2).toString());
                            String oc = (table.getValueAt(i, 3).toString());
                            String ft = (table.getValueAt(i, 4).toString());
                            String st = (table.getValueAt(i, 5).toString());
                            bw.write(id + "   " + name + "           " + dob + "          " + oc + "               " + ft
                                    + "             " + st + "\n");
                        }
                        JOptionPane.showMessageDialog(null, "SUCCESSFULLY EXPORTED DATA TO DESKTOP!");
                        bw.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else if (osname.charAt(0) == 'L') {
                    try {
                        BufferedWriter bw = new BufferedWriter(
                                new FileWriter("/home/" + username + "/Desktop/output.txt"));
                        bw.write(
                                "--------------------------------------------------------------------------------------------------------------\n");
                        bw.write("ID     |     " + "Name          |" + "        DOB        |" + "     Occupation      |"
                                + "     FamilyType      |" + "       Status \n");
                        bw.write(
                                "--------------------------------------------------------------------------------------------------------------\n");

                        for (int i = 0; i < table.getRowCount(); i++) {
                            String id = (table.getValueAt(i, 0).toString());
                            String name = (table.getValueAt(i, 1).toString());
                            String dob = (table.getValueAt(i, 2).toString());
                            String oc = (table.getValueAt(i, 3).toString());
                            String ft = (table.getValueAt(i, 4).toString());
                            String st = (table.getValueAt(i, 5).toString());
                            bw.write(id + "   " + name + "           " + dob + "          " + oc + "               " + ft
                                    + "             " + st + "\n");
                        }
                        JOptionPane.showMessageDialog(null, "SUCCESSFULLY EXPORTED DATA TO DESKTOP!");
                        bw.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cant export data on MacOS");
                }
            }
        }
    }
}
