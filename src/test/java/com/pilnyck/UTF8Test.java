package com.pilnyck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UTF8Test {

    @Test
    public void testCorrectlyEncode(){
        UTF8 utf8 = new UTF8();
        utf8.encode("A");
        byte[] expectedArray = {65};
        assertEquals(expectedArray, utf8.encode("A"));
    }

    @Test
    public void testCorrectlyCompare(){
        UTF8 utf8 = new UTF8();
        byte[] array = {65};

        assertTrue(utf8.compare(array, "A"));
    }
    @Test
    public void testCorrectlyConvertToBinary(){
        UTF8 utf8 = new UTF8();
        //byte[] encodedArray = utf8.encode("A");
        byte[] encodedArray = {65};
        utf8.convertByteToBinary(encodedArray);
        assertEquals(	"01000001",  utf8.convertByteToBinary(encodedArray));
    }



}
