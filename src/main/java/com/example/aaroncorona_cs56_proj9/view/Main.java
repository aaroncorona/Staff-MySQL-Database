package com.example.aaroncorona_cs56_proj9.view;

import com.example.aaroncorona_cs56_proj9.repository.StaffService;

public class Main {
    public static void main(String[] args) {
        // Launch GUI with Thread
        GUI gui = new GUI();
        Thread thread = new Thread(gui);
        thread.start();
        // Create Staff DB and Staff Table
        StaffService.createStaffDatabase();
        StaffService.createStaffTable();
//        // Test TODO delete
//        int randomId = new Random().nextInt(100);
//        Staff staff = new Staff(50, "Truffle", "Garcia", "C", "home",
//                "LA", 310, "teddy@gmail.com");
//        StaffService.insertStaffRecord(staff);
//        System.out.println(StaffService.getStaffRecordByID(50));
//        staff.setFirstName("Teddy #" + randomId);
//        StaffService.updateStaffRecord(staff);
//        System.out.println(StaffService.getStaffRecordByID(50));
    }
}
