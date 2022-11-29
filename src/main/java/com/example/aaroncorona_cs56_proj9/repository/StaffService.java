package com.example.aaroncorona_cs56_proj9.repository;

import com.example.aaroncorona_cs56_proj9.model.Staff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Acts as a layer between the View and the Database to avoid exposing the Database layer publicly
public final class StaffService {

    private static Database staffDb;

    // Private constructor - Utility design
    private StaffService(){}

    public static void createStaffDatabase() {
        try {
            staffDb = new Database("staff");
            staffDb.createStaffTable();
            System.out.println("Success - Staff DB Exists");
        } catch (Exception e) {
            staffDb.close();
            System.out.println(e);
        }
    }

    public static void createStaffTable() {
        try { // TODO check if the table exists first
            staffDb.createStaffTable();
            System.out.println("Success - Staff Table Exists");
        } catch (Exception e) {
            staffDb.close();
            System.out.println(e);
        }
    }

    // Get all tables in the Staff db
    public static List<String> getAllStaffTables() throws SQLException {
        return staffDb.getAllTables();
    }

    // Drop the table if it exists
    public static void dropTable(String tableName) {
        try {
            if (getAllStaffTables().contains(tableName)) {
                staffDb.dropTable(tableName);
                System.out.println("Success - Table Dropped");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Insert the Staff if they don't already exist
    public static void insertStaffRecord(Staff staff) {
        try { // TODO check if the Staff exists first
            staffDb.insertStaffRecord(staff);
            System.out.println("Success - Staff Inserted");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static Map<Integer, Staff> getAllStaffRecords() {
        Map<Integer, Staff> allStaff = new HashMap();
        try {
            allStaff = staffDb.getAllStaffRecords();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allStaff;
    }

    public static Staff getStaffRecordByID(int id) {
        Staff staff = null; // TODO print notice if result is not found / null
        try {
            staff = staffDb.getStaffRecordByID(id);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return staff;
    }

    public static void updateStaffRecord(Staff staff) {
        try {
            staffDb.updateStaffRecord(staff);
            System.out.println("Success - Staff Updated");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
