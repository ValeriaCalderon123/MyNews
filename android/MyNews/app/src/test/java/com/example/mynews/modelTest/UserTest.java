package com.example.mynews.modelTest;


import com.example.mynews.models.User;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

   User user=new User("Norma01","Figueroa","Norma","norma@gmail.com",false);
    @org.junit.jupiter.api.Test
    void getUsername() {
        assertEquals("Norma01",user.getUsername());
    }

    @org.junit.jupiter.api.Test
    void getFirst_name() {
        assertEquals("Norma",user.getFirst_name());
    }

    @org.junit.jupiter.api.Test
    void getLast_name() {
        assertEquals("Figueroa",user.getLast_name());
    }

    @org.junit.jupiter.api.Test
    void getEmail() {
        assertEquals("norma@gmail.com",user.getEmail());
    }

    @org.junit.jupiter.api.Test
    void getIs_superuser() {
        assertEquals(false,user.getIs_superuser());
    }

    @org.junit.jupiter.api.Test
    void setUserName() {
        user.setUsername("normita");
        assertEquals("normita",user.getUsername());
    }
    @org.junit.jupiter.api.Test
    void setFirstName() {
        user.setFirst_name("Normita");
        assertEquals("Normita",user.getFirst_name());
    }
    @org.junit.jupiter.api.Test
    void setLastName(){
        user.setLast_name("Filian");
        assertEquals("Filian",user.getLast_name());
    }
    @org.junit.jupiter.api.Test
    void setEmail(){
        user.setEmail("normafilian@gmail.com");
        assertEquals("normafilian@gmail.com",user.getEmail());
    }

}