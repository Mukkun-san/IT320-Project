
import java.sql.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mukkun
 */
public class ClientArea extends javax.swing.JFrame {

    /**
     * Creates new form ClientArea
     */
    private Client client;
    final VenuesList Venues = new VenuesList();
    final BookingsList Bookings = new BookingsList();
    Connection conn = null;

    public ClientArea(String username) {
        initComponents();

        this.getContentPane().setBackground(new Color(225, 255, 225));

        fetchFromDB(username);

        Image img = new ImageIcon(client.getImgPath()).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));
        displayName.setText(client.getFname() + " " + client.getLname());
        age.setText(client.getAge() + " years old.");
        date.setText("Joined on: " + client.getClientSince());

        TableColumnModel tcm = venuesTab.getColumnModel();
        tcm.removeColumn(tcm.getColumn(7));
        venuesTab.setRowHeight(120);
        for (Venue v : Venues.getList()) {
            addVenueToGui(v);
        }
        tcm = bookingsTab.getColumnModel();
        tcm.removeColumn(tcm.getColumn(7));
        bookingsTab.setRowHeight(50);
        for (Booking b : Bookings.getList()) {
            addBookingToGui(b);
        }
        nbookings.setText(String.valueOf(Bookings.getList().size()));
    }

    void fetchFromDB(String username) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?user=root&password=");
            String query = "select * from clients where username=\"" + username + "\"";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            client = new Client(rs.getString("username"), rs.getString("password"),
                    rs.getString("fname"), rs.getString("lname"), rs.getInt("age"),
                    rs.getString("gender"), rs.getString("picture"), rs.getDate("clientSince"));
            query = "select * from venues";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Venues.add(new Venue(rs.getString("uuid"), rs.getString("siteName"),
                        rs.getString("city"), rs.getString("address"), rs.getString("type"), rs.getInt("capacity"),
                        rs.getString("picture"), rs.getTimestamp("addedOn")));
            }
            query = "select * from bookings where clientId=\"" + username + "\"";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Bookings.add(new Booking(rs.getString("uuid"), rs.getString("clientId"), Venues.get(rs.getString("venueId")),
                        rs.getString("eventName"), rs.getInt("noGuests"), (rs.getDate("startDate")),
                        (rs.getDate("endDate")), rs.getTimestamp("addedOn")));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, "Could not connect to the database!", "SQL Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addVenueToGui(Venue v) {
        JLabel imgLabel = new JLabel();
        ImageIcon icon = new ImageIcon(v.getImgPath());
        Image ic = icon.getImage().getScaledInstance(venuesTab.getColumn("Picture").getWidth(), 120, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(ic));
        venuesTab.getColumn("Picture").setCellRenderer(new myTableCellRenderer());
        DefaultTableModel model = (DefaultTableModel) venuesTab.getModel();
        model.addRow(new Object[]{imgLabel, v.getSiteName(), v.getCity(), v.getAddress(), v.getType(), v.getCapacity(), v.getAddedOn(), v.getUuid()});
    }

    public void addBookingToGui(Booking b) {
        DefaultTableModel model = (DefaultTableModel) bookingsTab.getModel();
        model.addRow(new Object[]{b.getEventName(), b.getVenue().getSiteName(), b.getVenue().getCity(), b.getNoGuests(), b.getStartDate(), b.getEndDate(), b.getAddedOn(), b.getUuid()});
        nbookings.setText(String.valueOf(Bookings.getList().size()));
    }

    public void editBookingInGui(Booking b) {
        DefaultTableModel model = (DefaultTableModel) bookingsTab.getModel();
        model.setValueAt(b.getEventName(), bookingsTab.getSelectedRow(), 0);
        model.setValueAt(b.getNoGuests(), bookingsTab.getSelectedRow(), 3);
        model.setValueAt(b.getStartDate(), bookingsTab.getSelectedRow(), 4);
        model.setValueAt(b.getEndDate(), bookingsTab.getSelectedRow(), 5);
    }

    class myTableCellRenderer implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return (Component) value;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        displayName = new javax.swing.JLabel();
        age = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        nbookings = new javax.swing.JLabel();
        bookingslabel = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        venuesTab = new javax.swing.JTable();
        bookNow = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        bookingsTab = new javax.swing.JTable();
        editBooking = new javax.swing.JButton();
        delBooking = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(51, 0, 0), new java.awt.Color(0, 51, 51), new java.awt.Color(51, 0, 51), new java.awt.Color(0, 51, 51)));

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(0, 204, 204), new java.awt.Color(0, 102, 102)));

        displayName.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N

        age.setFont(new java.awt.Font("Leelawadee", 0, 20)); // NOI18N

        date.setFont(new java.awt.Font("Leelawadee", 0, 14)); // NOI18N
        date.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        nbookings.setFont(new java.awt.Font("Footlight MT Light", 0, 48)); // NOI18N
        nbookings.setForeground(new java.awt.Color(0, 51, 51));
        nbookings.setText("0");
        nbookings.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        bookingslabel.setFont(new java.awt.Font("Leelawadee", 0, 24)); // NOI18N
        bookingslabel.setText("Total Bookings : ");
        bookingslabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        venuesTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Picture", "Site Name", "City", "Address", "Type", "Capacity", "Added On", "Uuid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        venuesTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        venuesTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        venuesTab.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(venuesTab);

        bookNow.setBackground(new java.awt.Color(0, 204, 102));
        bookNow.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        bookNow.setForeground(new java.awt.Color(255, 255, 255));
        bookNow.setText("BOOK NOW!");
        bookNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookNowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 953, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(bookNow)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(bookNow)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("                              Event Venues                              ", jPanel2);

        bookingsTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event Name", "Venue", "City", "N° of Guests", "Start Date", "End Date", "Added On", "uuid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookingsTab.setCellSelectionEnabled(false);
        bookingsTab.setRowSelectionAllowed(true);
        bookingsTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookingsTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookingsTab.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(bookingsTab);
        bookingsTab.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        editBooking.setBackground(new java.awt.Color(255, 153, 0));
        editBooking.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        editBooking.setForeground(new java.awt.Color(255, 255, 255));
        editBooking.setText("Edit");
        editBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBookingActionPerformed(evt);
            }
        });

        delBooking.setBackground(new java.awt.Color(204, 0, 51));
        delBooking.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        delBooking.setForeground(new java.awt.Color(255, 255, 255));
        delBooking.setText("Delete");
        delBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBookingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 929, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(editBooking)
                        .addGap(71, 71, 71)
                        .addComponent(delBooking)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editBooking)
                    .addComponent(delBooking))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("                                My Bookings                              ", jPanel3);

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(displayName, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(291, 291, 291)
                        .addComponent(bookingslabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nbookings, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(29, 29, 29))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(displayName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bookingslabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nbookings))))))
                .addGap(51, 51, 51)
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bookNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookNowActionPerformed
        // TODO add your handling code here:
        int row = venuesTab.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a venue to book!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JFrame parent = this;
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new BookingPopup((ClientArea) parent, String.valueOf(venuesTab.getModel().getValueAt(row, 7)), client).setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_bookNowActionPerformed

    private void editBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBookingActionPerformed
        // TODO add your handling code here:
        int row = bookingsTab.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a booking to edit!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JFrame parent = this;
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new BookingPopup((ClientArea) parent, String.valueOf(bookingsTab.getModel().getValueAt(row, 7)), client, true).setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_editBookingActionPerformed

    private void delBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBookingActionPerformed
        // TODO add your handling code here:
        int row = bookingsTab.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a booking to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM bookings WHERE uuid=\"" + String.valueOf(bookingsTab.getModel().getValueAt(row, 7)) + "\"");
            } catch (Exception e) {
            }
            Bookings.remove(Bookings.get(String.valueOf(bookingsTab.getModel().getValueAt(row, 7))));
            JOptionPane.showMessageDialog(this, "Booking Deleted!");
            ((DefaultTableModel) bookingsTab.getModel()).removeRow(row);
            nbookings.setText(String.valueOf(Bookings.getList().size()));
        }
    }//GEN-LAST:event_delBookingActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel age;
    private javax.swing.JButton bookNow;
    private javax.swing.JTable bookingsTab;
    private javax.swing.JLabel bookingslabel;
    private javax.swing.JLabel date;
    private javax.swing.JButton delBooking;
    private javax.swing.JLabel displayName;
    private javax.swing.JButton editBooking;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel nbookings;
    private javax.swing.JTable venuesTab;
    // End of variables declaration//GEN-END:variables
}
