package com.example.test;

 

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {
    public static final String TAG = "JsonUtils";

    public static String AssetJSONFile(String filename) {
        try {
        	 
            InputStream file =  new  Utils().getFileFromResourceAsStream(filename);
            byte[] formArray = new byte[file.available()];
            file.read(formArray);
            file.close();

            return new String(formArray);
        } catch (Exception ex) {
            System.out.println( "AssetJSONFile:: " + ex.getLocalizedMessage());
            return "";
        }
    }
    
    
    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }

    }

    

    static boolean[] control = new boolean[100];
    static List<Lineas> lineasEntreIslas = new ArrayList<>();
    static List<Lineas[]> combinacionDelineasEntreIslas = new ArrayList<>();

    static void combinations2Island(Island[] arr, int len, int startPosition, Island[] result) {
        if (len == 0) {
        	System.out.println("COMBINCAIONES-->"+ Arrays.toString(result));
            lineasEntreIslas.add(new Lineas(result[0], result[1]));
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];
            combinations2Island(arr, len - 1, i + 1, result);
        }
    }

    static void combinations2Lienas(Lineas[] arr, int len, int startPosition, Lineas[] result) {
        if (len == 0) {
        	System.out.println("COMBINCAIONES-->"+ Arrays.toString(result));
            combinacionDelineasEntreIslas.add(result.clone());
            return;
        }
        for (int i = startPosition; i <= arr.length - len; i++) {
            result[result.length - len] = arr[i];
            combinations2Lienas(arr, len - 1, i + 1, result);
        }
    }
}
