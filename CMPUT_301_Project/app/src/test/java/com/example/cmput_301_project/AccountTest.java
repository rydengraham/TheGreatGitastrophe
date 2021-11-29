package com.example.cmput_301_project;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    /**
     * Example local unit test, which will execute on the development machine (host).
     *
     * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
     */

    @Test
    public void CreateAccount() {
    Account testAccount = new Account("Maxen", "maximreality@gmail.com", "129430");
    assertEquals(testAccount.getUserName(), "Maxen");
    }

    @Test
    public void ChangeAttributes() {
        Account testAccount = new Account("Maxen", "maximreality@gmail.com", "129430");
        assertEquals(testAccount.getUserName(), "Maxen");
        assertEquals(testAccount.getEmail(), "maximreality@gmail.com");
        assertEquals(testAccount.checkPassword("129430"), true);
        testAccount.setEmail("paint@tas.com");
        testAccount.setUserName("Paint");
        assertEquals(testAccount.getUserName(), "Paint");
        assertEquals(testAccount.getEmail(), "paint@tas.com");
    }



}