/***************************************************************************
 Author: Aaron Corona
 Date: November 29, 2022
 CS56 Project #9 - Database GUI
 ***************************************************************************/

package com.example.aaroncorona_cs56_proj9.view;

import com.example.aaroncorona_cs56_proj9.model.Staff;
import com.example.aaroncorona_cs56_proj9.repository.StaffService;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Launch GUI with Thread
        GUI gui = new GUI();
        Thread thread = new Thread(gui);
        thread.start();
        // Create Staff DB and Staff Table
        StaffService.createStaffDatabase();
        StaffService.createStaffTable();
        // Test TODO delete
        int randomId = new Random().nextInt(100);
        Staff staff = new Staff(50, "Truffle", "Garcia", 'C', "home",
                "LA", 310, "teddy@gmail.com");
        StaffService.insertStaffRecord(staff);
//        System.out.println(StaffService.getAllStaff());
        System.out.println(StaffService.getStaffRecordByID(50));
    }
}
