
package com.example.aaroncorona_cs56_proj9.repository;

import com.example.aaroncorona_cs56_proj9.model.Staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    // Get all databases in the given MySQL instance
    private List<String> getDatabases() throws SQLException {
        List<String> dbNames = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("show databases");
        while (rs.next()) {
            dbNames.add(rs.getString(1));
        }
        return dbNames;
    }

    // Create the given DB if it doesn't already exist
    private void createDatabase(String dbName) throws SQLException {
        // Create DB if it doesn't already exist
        if (!getDatabases().contains(dbName)) {
            stmt.execute("create database " + dbName);
        }
    }

    // Returns all tables in the given db
    private List<String> getTables() throws SQLException {
        List<String> tableNames = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("show tables");
        while (rs.next()) {
            tableNames.add(rs.getString(1));
        }
        return tableNames;
    }

    // Creates the Staff table in the new DB if it doesn't already exist
    protected void createStaffTable() throws SQLException {
        // Create the Staff table if it doesn't already exist
        if (!getTables().contains("staff")) {
            stmt.execute(
                    "create table staff (id char(11) not null, lastName varchar(15), firstName varchar(15), mi char(1), address varchar(25), city varchar(20), state char(2), telephone char(10), email varchar(40), primary key (id));"
            );
        }
    }

    protected void insertStaff(Staff staff) throws SQLException {
        stmt.execute("INSERT INTO staff (id, firstName, lastName) "
                + "VALUES ("+ staff.getId()
                + ", \"" + staff.getFirstName()
                + "\",\"" + staff.getLastName()
                + "\",\"" + staff.getMi()
                + "\",\"" + staff.getAddress()
                + "\",\"" + staff.getState()
                + "\",\"" + staff.getPhone()
                + "\",\"" + staff.getEmail()
                + "\")");
    }

    protected List<Staff> getAllStaff() throws SQLException {
        ArrayList<Staff> staff = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select * from staff");
        while (rs.next()) {
            staff.add(new Staff(rs.getInt(1), rs.getString(2),
                    rs.getString(3)));
        }
        return staff;
    }

    protected Staff getStaffByID(int id) throws SQLException {
        Map<Integer, Staff> staffMap = new HashMap<>();
        ResultSet rs = stmt.executeQuery("select * from staff where id = " + id);
//        while (rs.next()) {
//            staff.add(new Staff(rs.getInt(1), rs.getString(2),
//                    rs.getString(3)));
//        }
//        while (staff.next()) {
//            staff.add(new Staff(rs.getInt(1), rs.getString(2),
//                    rs.getString(3)));
//        }
        return null;
//        return staff;
    }

    protected void updateStaff(Staff staff) throws SQLException {
        stmt.execute("INSERT INTO staff (id, firstName, lastName) "
                + "VALUES ("+ staff.getId()
                + ", \"" + staff.getFirstName()
                + "\",\"" + staff.getLastName()
                + "\",\"" + staff.getMi()
                + "\",\"" + staff.getAddress()
                + "\",\"" + staff.getState()
                + "\",\"" + staff.getPhone()
                + "\",\"" + staff.getEmail()
                + "\")");
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