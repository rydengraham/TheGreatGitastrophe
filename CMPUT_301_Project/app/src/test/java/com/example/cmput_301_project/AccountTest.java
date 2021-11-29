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

    @Test
    public void GetFriends() {
        Account testAccount = new Account("Maxen", "maximreality@gmail.com", "129430");
        Account testAccount2 = new Account("Edson", "edsin@gmail.com", "1234");
        Account testAccount3 = new Account("Wolfgang", "wgang@gmail.com", "534534");
        testAccount.addFriend(testAccount2.getId());
        testAccount.addFriend(testAccount3.getId());
        assertEquals(testAccount.getFriendList().size(), 2);
        assertEquals(testAccount.getFriendList().get(1),testAccount3.getId());
        testAccount2.addPendingFriend(testAccount3.getId());
        assertEquals(testAccount2.getFriendPendingList().get(0),testAccount3.getId());
        testAccount.removeFriend(testAccount2.getId());
        assertEquals(testAccount.getFriendList().size(),1);
    }



}