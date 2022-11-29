/***************************************************************************
 Author: Aaron Corona
 Date: November 29, 2022
 CS56 Project #9 - Database GUI
 ***************************************************************************/

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
    }
}
