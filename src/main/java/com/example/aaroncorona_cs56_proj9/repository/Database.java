
package com.example.aaroncorona_cs56_proj9.repository;

import com.example.aaroncorona_cs56_proj9.model.Staff;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// Interacts directly with the MySQL database
public class Database implements AutoCloseable {

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
        } else {
            System.out.println( dbName + " DB not created - It already exists");
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
    private void createStaffTable() throws SQLException {
        // Create the Staff table if it doesn't already exist
        if (!getTables().contains("staff")) {
            stmt.execute(
                    "create table staff (id char(11) not null, lastName varchar(15), firstName varchar(15), mi char(1), address varchar(25), city varchar(20), state char(2), telephone char(10), email varchar(40), primary key (id));"
            );
        } else {
            System.out.println("staff table not created - It already exists");
        }
    }

    protected void insertStaff(Staff staff) throws SQLException {
        Statement stmt = conn.createStatement();
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

    protected List<Staff> getStaff() throws SQLException {
        ArrayList<Staff> staff = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from staff");
        while (rs.next()) {
            staff.add(new Staff(rs.getInt(1), rs.getString(2),
                    rs.getString(3)));
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

    public static void main(String[] args) {
        // Create Staff DB and table
        Database db = null;
        try {
            db = new Database("staff");
            db.createStaffTable();
        } catch (Exception e) {
            System.out.println(e);
        }
        finally {
            db.close();
        }
    }
}