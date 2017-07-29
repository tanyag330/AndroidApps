package com.example.tanya.androidtests;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Admin on 7/30/2016.
 */
public class FareTest {

    @Test
    public void testFareFromKmAndTime() {
        assertEquals(35, MainActivity.getFare(3, 16), 0.001);
    }
}
