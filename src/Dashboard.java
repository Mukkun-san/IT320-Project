
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
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
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form dashboard
     */
    static final ClientsList Clients = new ClientsList();
    static final VenuesList Venues = new VenuesList();
    final BookingsList Bookings = new BookingsList();
    static Connection conn = null;

    public Dashboard() {
        initComponents();
        this.getContentPane().setBackground(new Color(228, 228, 255));
        fetchFromDB();
        populateTabs();
    }

    void fetchFromDB() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?user=root&password=");
            String query = "select * from clients";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Clients.addClient(new Client(rs.getString("username"), rs.getString("password"),
                        rs.getString("fname"), rs.getString("lname"), rs.getInt("age"),
                        rs.getString("gender"), rs.getString("picture"), rs.getDate("clientSince")));
            }
            query = "select * from venues";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Venues.add(new Venue(rs.getString("uuid"), rs.getString("siteName"),
                        rs.getString("city"), rs.getString("address"), rs.getString("type"), rs.getInt("capacity"),
                        rs.getString("picture"), rs.getTimestamp("addedOn")));
            }

            query = "select * from bookings";
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

    void populateTabs() {
        clientsTab.setRowHeight(120);
        for (Client c : Clients.getList()) {
            JLabel imgLabel = new JLabel();
            ImageIcon icon = new ImageIcon(c.getImgPath());
            Image ic = icon.getImage().getScaledInstance(clientsTab.getColumn("Image").getWidth(), 120, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(ic));
            clientsTab.getColumn("Image").setCellRenderer(new myTableCellRenderer());
            DefaultTableModel model = (DefaultTableModel) clientsTab.getModel();
            model.addRow(new Object[]{imgLabel, c.getUsername(), c.getFname(), c.getLname(), c.getAge(), c.getGender(), c.getClientSince()});
        }

        TableColumnModel tcm = venuesTab.getColumnModel();
        tcm.removeColumn(tcm.getColumn(7));
        venuesTab.setRowHeight(120);
        for (Venue v : Venues.getList()) {
            addVenueToGui(v);
        }

        tcm = bookingsTab.getColumnModel();
        tcm.removeColumn(tcm.getColumn(8));
        bookingsTab.setRowHeight(50);
        for (Booking b : Bookings.getList()) {
            addBookingToGui(b);
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

    public void editVenueInGui(Venue v) {
        int row = venuesTab.getSelectedRow();
        JLabel imgLabel = new JLabel();
        ImageIcon icon = new ImageIcon(v.getImgPath());
        Image ic = icon.getImage().getScaledInstance(venuesTab.getColumn("Picture").getWidth(), 120, Image.SCALE_SMOOTH);
        imgLabel.setIcon(new ImageIcon(ic));
        venuesTab.getColumn("Picture").setCellRenderer(new myTableCellRenderer());
        DefaultTableModel model = (DefaultTableModel) venuesTab.getModel();
        model.setValueAt(imgLabel, row, 0);
        model.setValueAt(v.getSiteName(), row, 1);
        model.setValueAt(v.getCity(), row, 2);
        model.setValueAt(v.getAddress(), row, 3);
        model.setValueAt(v.getType(), row, 4);
        model.setValueAt(v.getCapacity(), row, 5);
        model.setValueAt(v.getAddedOn(), row, 6);
        model.setValueAt(v.getUuid(), row, 7);
    }

    public void addBookingToGui(Booking b) {
        DefaultTableModel model = (DefaultTableModel) bookingsTab.getModel();
        model.addRow(new Object[]{(Clients.getClient(b.getClientId()).getUsername()), b.getEventName(), b.getVenue().getSiteName(), b.getVenue().getCity(), b.getNoGuests(), b.getStartDate(), b.getEndDate(), b.getAddedOn(), b.getUuid()});
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clientsTab = new javax.swing.JTable();
        deleteUserBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        bookingsTab = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        editVenue = new javax.swing.JButton();
        deleteVenue = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        venuesTab = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin Dashboard");
        setAlwaysOnTop(true);
        setName("dashboard"); // NOI18N

        jTabbedPane1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new javax.swing.OverlayLayout(jPanel2));

        clientsTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Image", "Username", "First Name", "Last Name", "Age", "Gender", "Client Since"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clientsTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        clientsTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        clientsTab.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(clientsTab);
        clientsTab.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (clientsTab.getColumnModel().getColumnCount() > 0) {
            clientsTab.getColumnModel().getColumn(0).setResizable(false);
            clientsTab.getColumnModel().getColumn(0).setPreferredWidth(100);
            clientsTab.getColumnModel().getColumn(1).setResizable(false);
            clientsTab.getColumnModel().getColumn(4).setResizable(false);
            clientsTab.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        deleteUserBtn.setBackground(new java.awt.Color(204, 0, 51));
        deleteUserBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteUserBtn.setText("Remove Client");
        deleteUserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deleteUserBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deleteUserBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel3);

        jTabbedPane1.addTab("       Clients       ", jPanel2);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bookingsTab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Client ", "Event Name", "Venue", "City", "N° of Guests", "Start Date", "End Date", "Added On", "uuid"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookingsTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookingsTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookingsTab.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(bookingsTab);
        bookingsTab.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 929, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("       Bookings       ", jPanel1);

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton7.setBackground(new java.awt.Color(0, 153, 51));
        jButton7.setFont(new java.awt.Font("MS PGothic", 0, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Add");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        editVenue.setBackground(new java.awt.Color(204, 153, 0));
        editVenue.setFont(new java.awt.Font("MS PGothic", 0, 14)); // NOI18N
        editVenue.setForeground(new java.awt.Color(255, 255, 255));
        editVenue.setText("Edit");
        editVenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editVenueActionPerformed(evt);
            }
        });

        deleteVenue.setBackground(new java.awt.Color(204, 0, 51));
        deleteVenue.setFont(new java.awt.Font("MS PGothic", 0, 14)); // NOI18N
        deleteVenue.setForeground(new java.awt.Color(255, 255, 255));
        deleteVenue.setText("Delete");
        deleteVenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteVenueActionPerformed(evt);
            }
        });

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
        venuesTab.setCellSelectionEnabled(false);
        venuesTab.setRowSelectionAllowed(true);
        venuesTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        venuesTab.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        venuesTab.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(venuesTab);
        venuesTab.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (venuesTab.getColumnModel().getColumnCount() > 0) {
            venuesTab.getColumnModel().getColumn(0).setResizable(false);
            venuesTab.getColumnModel().getColumn(1).setResizable(false);
            venuesTab.getColumnModel().getColumn(7).setPreferredWidth(0);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100)
                .addComponent(editVenue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100)
                .addComponent(deleteVenue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(262, 262, 262))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editVenue, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteVenue, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("        Venues       ", jPanel8);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ADMIN DASHBOARD");

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("LOGOUT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jButton1)))
                .addGap(29, 29, 29)
                .addComponent(jTabbedPane1)
                .addGap(23, 23, 23))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JFrame parent = this;
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VenuePopup((Dashboard) parent).setVisible(true);
            }
        });
    }//GEN-LAST:event_jButton7ActionPerformed

    private void deleteVenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteVenueActionPerformed
        // TODO add your handling code here:
        int row = venuesTab.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a venue to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM venues WHERE uuid=\"" + String.valueOf(venuesTab.getModel().getValueAt(row, 7)) + "\"");
            } catch (Exception e) {
            }
            Venues.remove(Venues.get(String.valueOf(venuesTab.getModel().getValueAt(row, 7))));
            JOptionPane.showMessageDialog(this, "Venue Deleted!");
            ((DefaultTableModel) venuesTab.getModel()).removeRow(row);
        }
    }//GEN-LAST:event_deleteVenueActionPerformed

    private void editVenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editVenueActionPerformed
        // TODO add your handling code here:
        int row = venuesTab.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a venue to edit!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JFrame parent = this;
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new VenuePopup((Dashboard) parent, String.valueOf(venuesTab.getModel().getValueAt(row, 7))).setVisible(true);
                }
            });
        }
    }//GEN-LAST:event_editVenueActionPerformed

    private void deleteUserBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserBtnActionPerformed
        // TODO add your handling code here:
        int row = clientsTab.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a user to delete!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM clients WHERE username=\"" + String.valueOf(clientsTab.getModel().getValueAt(row, 1)) + "\"");
            } catch (Exception e) {
            }
            Clients.rmClient(Clients.getClient(String.valueOf(venuesTab.getModel().getValueAt(row, 7))));
            JOptionPane.showMessageDialog(this, "Client was Deleted!");
            ((DefaultTableModel) clientsTab.getModel()).removeRow(row);
        }
    }//GEN-LAST:event_deleteUserBtnActionPerformed

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
    private javax.swing.JTable bookingsTab;
    private javax.swing.JTable clientsTab;
    private javax.swing.JButton deleteUserBtn;
    private javax.swing.JButton deleteVenue;
    private javax.swing.JButton editVenue;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable venuesTab;
    // End of variables declaration//GEN-END:variables
}
