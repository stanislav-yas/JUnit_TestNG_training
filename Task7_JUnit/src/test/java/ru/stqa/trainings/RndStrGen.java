package ru.stqa.trainings;

import java.util.Random;

public class RndStrGen {

    public static String latinCharsLowerCase = "abcdefghijklmnopqrstuvwxyz";
    public static String latinCharsUpperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String latinChars = latinCharsLowerCase + latinCharsUpperCase;
    public static String cyrillicCharsLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static String cyrillicCharsUpperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static String cyrillicChars = cyrillicCharsLowerCase + cyrillicCharsUpperCase;
    public static String numericChars = "0123456789";
    public static String specCharsAllowFN = "~@#$%^-_(){}'`";
    public static String specCharsNotAllowFN = "/:\\*?\"<>|+";
    public static Random rng = new Random();

    public static String getRndStr(String charSet, int wordLength) {

        StringBuilder sb = new StringBuilder(wordLength);
        for (int i = 0; i < wordLength; i++) {
            int r = rng.nextInt(charSet.length());
            sb.append(charSet.charAt(r));
        }
        return sb.toString();
    }

    public static String getRndStrAnyLength(String charSet, int wordLengthLimit){
        int length;
        do {
            length = rng.nextInt(wordLengthLimit);
        }while(length == 0); // exclude empty string
        return getRndStr(charSet, length);
    }

}
