package com.example.aaroncorona_cs56_proj9.repository;

import com.example.aaroncorona_cs56_proj9.model.Staff;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Acts as a layer between the View and the Database to perform Staff CRUD operations with safeguards
public final class StaffService {

    private static Database staffDb;

    // Private constructor - Utility design
    private StaffService(){}

    public static void createStaffDatabase() {
        try {
            staffDb = new Database("staff");
            System.out.println("Success - Staff DB Exists");
        } catch (Exception e) {
            staffDb.close();
            System.out.println(e);
        }
    }

    // Get all tables in the Staff db
    public static List<String> getAllStaffTables() throws SQLException {
        return staffDb.getAllTables();
    }

    public static void createStaffTable() {
        try {
            if(!getAllStaffTables().contains("staff")) {
                staffDb.createStaffTable();
            }
            System.out.println("Success - Staff Table Exists");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Drop the table if it exists
    public static void dropTable(String tableNameInput) {
        try {
            if (getAllStaffTables().contains(tableNameInput)) {
                staffDb.dropTable(tableNameInput);
                System.out.println("Success - Table Dropped");
            } else {
                System.out.println("Not Dropped - Table does not exist");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // Insert the Staff if they don't already exist
    public static Staff insertStaffRecord(Staff staffInput) {
        try {
            if(getStaffRecordByID(staffInput.getId()) == null) {
                staffDb.insertStaffRecord(staffInput);
                System.out.println("Success - Staff Inserted");
                return getStaffRecordByID(staffInput.getId()); // return result
            } else {
                System.out.println("Not Inserted - Staff already exists");
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    // Update the Staff if it exists
    public static Staff updateStaffRecord(Staff staffInput) {
        if(getStaffRecordByID(staffInput.getId()) == null) {
            System.out.println("No Staff Updated - ID not found");
            return null;
        }
        try {
            staffDb.updateStaffRecord(staffInput);
            System.out.println("Success - Staff Updated");
            return getStaffRecordByID(staffInput.getId());
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    // Return all Staff records
    public static Map<Integer, Staff> getAllStaffRecords() {
        Map<Integer, Staff> allStaff = new HashMap();
        try {
            allStaff = staffDb.getAllStaffRecords();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allStaff;
    }

    // Return the Staff record if it exists
    public static Staff getStaffRecordByID(int id) {
        try {
            if(staffDb.getStaffRecordByID(id) == null) {
                System.out.println("No Staff Returned - ID not found");
                return null;
            } else {
                return staffDb.getStaffRecordByID(id);
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
