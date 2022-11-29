
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

    // Helper to get all the databases in the given MySQL instance
    private List<String> getDatabases() throws SQLException {
        List<String> dbNames = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("show databases");
        while (rs.next()) {
            dbNames.add(rs.getString(1));
        }
        return dbNames;
    }

    // Helper to create the given DB if it doesn't already exist
    private void createDatabase(String dbName) throws SQLException {
        // Create DB if it doesn't already exist
        if (!getDatabases().contains(dbName)) {
            stmt.execute("create database " + dbName);
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
                "create table staff (id char(11) not null, firstName varchar(15) not null, lastName varchar(15) not null, mi char(1), address varchar(25), city varchar(20), phone char(10), email varchar(40), primary key (id));"
        );
    }

    // Deletes a given table
    protected void dropTable(String tableName) throws SQLException {
        stmt.execute("delete * from table " + tableName);
        stmt.execute("drop table " + tableName);
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

    // Updates 1 Staff record // TODO
    protected void updateStaffRecord(Staff staff) throws SQLException {
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

    // Returns all Staff records
    protected Map<Integer, Staff> getAllStaffRecords() throws SQLException {
        Map<Integer, Staff> staff = new HashMap();
        ResultSet rs = stmt.executeQuery("select * from staff");
        while (rs.next()) {
            staff.put(rs.getInt(1), new Staff(rs.getInt(1), rs.getString(2),
                    rs.getString(3)));
        }
        return staff;
    }

    // Returns 1 Staff record using the ID
    protected Staff getStaffRecordByID(int id) throws SQLException {
        Map<Integer, Staff> staffMap = getAllStaffRecords();
        Staff staff = staffMap.get(id);
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