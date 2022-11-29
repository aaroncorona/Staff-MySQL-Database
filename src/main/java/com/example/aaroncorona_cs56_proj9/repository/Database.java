
package com.example.aaroncorona_cs56_proj9.repository;

import com.example.aaroncorona_cs56_proj9.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

// Interacts directly with the MySQL database
public final class Database implements AutoCloseable {

    private static Connection conn;
    private String dbName;
    private Statement stmt;

    protected Database(String dbName) throws SQLException {
        this.dbName = dbName;

        // Start connection with mysql
        connectToMySQL();

        // Create the db
        stmt = conn.createStatement();
        createDatabase(dbName);

        // Set the database as the default one for future statements
        stmt.execute("use " + dbName);
    }

    // Connect to the given MySQL instance using the Driver
    private static void connectToMySQL() throws SQLException {
        String connStr = "jdbc:mysql://localhost:3306";
        conn = DriverManager.getConnection(connStr, "root", "adminadmin");
    }

    // Helper to get all the databases in the given MySQL instance
    private List<String> getDatabases() throws SQLException {
        List<String> dbNames = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SHOW DATABASES");
        while (rs.next()) {
            dbNames.add(rs.getString(1));
        }
        return dbNames;
    }

    // Helper to create the given DB if it doesn't already exist
    private void createDatabase(String dbName) throws SQLException {
        // Create DB if it doesn't already exist
        if (!getDatabases().contains(dbName)) {
            stmt.execute("CREATE DATABASE " + dbName);
        }
    }

    // Returns all tables in the given db
    protected List<String> getAllTables() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("show tables");
        while (rs.next()) {
            tableNames.add(rs.getString(1));
        }
        return tableNames;
    }

    // Creates the Staff table
    protected void createStaffTable() throws SQLException {
        stmt.execute(
                "CREATE TABLE staff (id char(11) not null, firstName varchar(15) not null, lastName varchar(15) not null, mi char(1), address varchar(25), city varchar(20), phone char(10), email varchar(40), primary key (id));"
        );
    }

    // Deletes a given table
    protected void dropTable(String tableName) throws SQLException {
        stmt.execute("DELETE * FROM TABLE " + tableName);
        stmt.execute("DROP TABLE " + tableName);
    }

    // Inserts 1 Staff record
    protected void insertStaffRecord(Staff staff) throws SQLException {
        stmt.execute("INSERT INTO staff (id, firstName, lastName, mi, address, city, phone, email) "
                + "VALUES ("+ staff.getId()
                + ", \"" + staff.getFirstName()
                + "\",\"" + staff.getLastName()
                + "\",\"" + staff.getMi()
                + "\",\"" + staff.getAddress()
                + "\",\"" + staff.getCity()
                + "\",\"" + staff.getPhone()
                + "\",\"" + staff.getEmail()
                + "\")");
    }

    // Updates 1 Staff record with all the current info in the record
    protected Staff updateStaffRecord(Staff staff) throws SQLException {
        String query = "UPDATE staff SET firstName=?, lastName=?, mi=?, address=?, city=?, phone=?, email=? WHERE Id=? ";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, staff.getFirstName());
        ps.setString(2, staff.getLastName());
        ps.setString(3, staff.getMi());
        ps.setString(4, staff.getAddress());
        ps.setString(5, staff.getCity());
        ps.setInt(6, staff.getPhone());
        ps.setString(7, staff.getEmail());
        ps.setInt(8, staff.getId());
        ps.executeUpdate();
        return getStaffRecordByID(staff.getId());
    }

    // Returns all Staff records
    protected Map<Integer, Staff> getAllStaffRecords() throws SQLException {
        Map<Integer, Staff> allStaff = new HashMap();
        ResultSet rs = stmt.executeQuery("SELECT * FROM STAFF");
        while (rs.next()) {
            allStaff.put(rs.getInt(1), buildStaffObj(rs));
        }
        rs.close();
        return allStaff;
    }

    // Returns 1 Staff record using the ID
    protected Staff getStaffRecordByID(int id) throws SQLException {
        String query = "SELECT * FROM staff WHERE Id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        Staff staff = buildStaffObj(rs);
        rs.close();
        return staff;
    }

    // Helper method to translate a query result set to a Staff object with all its variables populated
    private static Staff buildStaffObj(ResultSet rs) {
        Staff staff = null;
        try {
            staff = new Staff(
                    rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getInt(7), rs.getString(8)
            );
        } catch (SQLException e) {
            System.out.println(e);
        }
        return staff;
    }


    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null,
                        ex);
            }
        }
    }
}