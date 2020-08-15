package com.example.mynews.modelTest;

import com.example.mynews.models.Session;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionTest {

        Session session = new Session("qweasd465asda8asddd");
        @org.junit.jupiter.api.Test

        void getToken() {
            assertEquals("qweasd465asda8asddd",session.getToken());
        }
        @org.junit.jupiter.api.Test
        void setToken() {
            session.setToken("124qwewaqppoklk444");
            assertEquals("124qwewaqppoklk444",session.getToken());
        }

}
