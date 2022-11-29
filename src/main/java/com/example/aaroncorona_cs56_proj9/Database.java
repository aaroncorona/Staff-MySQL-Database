
package com.example.aaroncorona_cs56_proj9;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Database implements AutoCloseable {

    private static Connection conn;

    public Database() throws SQLException {
        String dbName = "employees";
        // connect to mysql
        connect(null);
        // create 'employees' database if it does not exist
        if (!getDatabases().contains(dbName)) {
            createDatabase();
        }
        Statement stmt = conn.createStatement();
        stmt.execute("use employees");
    }

    private void connect(String dbName) throws SQLException {
        dbName = (dbName == null) ? "" : "/" + dbName;
        String connStr = "jdbc:mysql://localhost:3306" + dbName + "?";
        conn = DriverManager.getConnection(connStr, "root", "adminadmin");
    }

    public void createDatabase() throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("create database employees");
        stmt.execute("use employees");
        stmt.execute("create table employee(id int primary key, firstName varchar(32), lastName varchar(32))");
    }

    public void insertEmployee(Staff e) throws SQLException {
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO employee (id, firstName, lastName) "
                + "VALUES ("+ e.getId()
                + ", \"" + e.getFirstName()
                + "\",\"" + e.getLastName() + "\")");
    }

    public List<Staff> getEmployees() throws SQLException {
        ArrayList<Staff> staff = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from employee");
        while (rs.next()) {
            staff.add(new Staff(rs.getInt(1), rs.getString(2),
                    rs.getString(3)));
        }
        return staff;
    }

    private Set<String> getDatabases() throws SQLException {
        HashSet<String> tableNames = new HashSet<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show databases");
        while (rs.next()) {
            tableNames.add(rs.getString(1));
        }
        return tableNames;
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
        try {
            // Connect to default DB
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "adminadmin");
            // Create employee table
            Statement stat = con.createStatement();
//            stat.execute("create table employees(id int primary key, firstName varchar(32), lastName varchar(32))");
            // Insert Employee record
//            Employee emp = new Employee(12345, "Aaron", "Corona");
//            stat.execute("INSERT INTO employee (id, firstName, lastName) "
//                    + "VALUES ("+ emp.getId()
//                    + ", \"" + emp.getFirstName()
//                    + "\",\"" + emp.getLastName() + "\")");
            // Get results
            ResultSet rs = stat.executeQuery("select * from employees");
//            while (result.next()) {
//                System.out.print(result.getInt(1) + result.getString(2) + result.getString(3));
//            }
            ArrayList<Staff> staff = new ArrayList<>();
            while (rs.next()) {
                staff.add(new Staff(rs.getInt(1), rs.getString(2),
                        rs.getString(3)));
            }
            System.out.print(staff);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}