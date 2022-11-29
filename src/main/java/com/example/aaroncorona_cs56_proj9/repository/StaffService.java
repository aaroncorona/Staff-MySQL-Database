package com.example.aaroncorona_cs56_proj9.repository;

import com.example.aaroncorona_cs56_proj9.model.Staff;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Acts as a layer between the View and the Database to avoid exposing the Database layer publicly
public final class StaffService {

    private static Database db;

    // Private constructor - Utility design
    private StaffService(){}

    public static void createStaffDatabase() {
        try {
            db = new Database("staff");
            db.createStaffTable();
            System.out.println("Success - Staff DB Exists");
        } catch (Exception e) {
            db.close();
            System.out.println(e);
        }
    }

    public static void createStaffTable() {
        try {
            db.createStaffTable();
            System.out.println("Success - Staff Table Exists");
        } catch (Exception e) {
            db.close();
            System.out.println(e);
        }
    }

    public static void insertStaff(Staff staff) {
        try {
            db.insertStaff(staff);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static List<Staff> getAllStaff() {
        List<Staff> allStaff = new ArrayList<>();
        try {
            allStaff = db.getAllStaff();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return allStaff;
    }

    public static Staff getStaffByID(int id) {
        Staff staff = null;
        try {
            staff = db.getStaffByID(id);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return staff;
    }

    public static void updateStaff(Staff staff) {
        try {
            db.updateStaff(staff);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
