package com.pilnyck;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;

import static java.lang.Integer.toBinaryString;

public class UTF8 {
    String value;


    byte [] encode (String value){ // повертає масив байт значення value
        int realStringLength = value.codePointCount(0, value.length());
        int valueLength = value.length();

        byte [] newByteArray = new byte [realStringLength*4];
        int codePointCount = 0;
        int codePointAt = 0;
        while(codePointCount < newByteArray.length){
            int codePoint = value.codePointAt(codePointAt);
            if (!Character.isSupplementaryCodePoint(codePoint)){
                if(codePoint >= 0 && codePoint <=127) {
                    newByteArray[codePointCount] = (byte) codePoint;
                    codePointCount++;
                    codePointAt++;
                }else if(codePoint >= 128 && codePoint <=256){
                    newByteArray[codePointCount] =  127;
                    newByteArray[codePointCount+1] = (byte) (codePoint % 127);
                    codePointCount+=2;
                    codePointAt++;
                }else if(codePoint >= 257 && codePoint <=512){
                    newByteArray[codePointCount] =  127;
                    newByteArray[codePointCount+1] =  127;
                    newByteArray[codePointCount+2] = (byte) (codePoint % 127);
                    codePointCount+=3;
                    codePointAt++;
                }else if(codePoint >= 513 && codePoint <=1024){
                    newByteArray[codePointCount] =  127;
                    newByteArray[codePointCount+1] =  127;
                    newByteArray[codePointCount+2] =  127;
                    newByteArray[codePointCount+3] = (byte) (codePoint % 127);
                    codePointCount+=4;
                    codePointAt++;
                }
            }
        }
        return newByteArray;
    }


    public String convertByteToBinary(byte[] array){
        StringBuilder binaryResult = new StringBuilder();
        for (byte b : array) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binaryResult.append((val & 128) == 0 ? 0 : 1);
                val <<=1;
            }
        }
        return binaryResult.toString();
    }




    //String decode(byte [] array);
    // перетворює value в бінарний код
    // перетворює бінарних в стрінгу.
    //Перебирати if-ами за рахунок перегляду кожного елемента,
    // якщо елемент починається на 111 - символ на 3(4) байти, якщо на 11 - символ на 2 байти і т.п

    boolean compare(byte[] array, String value){
        byte [] arrayFromValue = value.getBytes(StandardCharsets.UTF_8);
        if (arrayFromValue.length == array.length){
            for (int i = 0; i < array.length; i++) {
                if(arrayFromValue[i] == array[i]){
                    return true;
                }
            }
        }
        return false;
    }
}

