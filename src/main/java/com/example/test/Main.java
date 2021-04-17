package com.example.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main  {
    public static String TAG = Main.class.getName();
    private int TestNumberCases;
    private List<TesCase> listCasosDePrueba;
    private List<Archipielago> listDeArchipielagos = new ArrayList<>();
    private String mFileInfo;
    private String mSampleOutputResult;
    
    
    
    public static void main(String[] args) {
    	Main m = new Main();
    	 m.pruebaNumber2();
	}


 
    public void pruebaNumber1() {

        mFileInfo = mSampleOutputResult = "";
        initData("test1.txt"); //Casos de prueba test1.txt, 
        searchArchipielago();
        String results = "Sample Input:\n";
        results += mFileInfo;
        results += "\nSample Output:\n";
        results += mSampleOutputResult + "\n";
        
        System.out.println(results);
    }
    
    
   

    
    
    

    public void pruebaNumber2() {
      
        mFileInfo = mSampleOutputResult = "";
        initData("test2.txt"); //Casos de prueba test2.txt
        searchArchipielago();
        String results = "Sample Input:\n";
        results += mFileInfo;
        results += "\nSample Output:\n";
        results += mSampleOutputResult + "\n";
       System.out.println(results);
    }


    public void searchArchipielago() {

        for (TesCase caso : listCasosDePrueba) {
        	System.out.println( "Obteniendo lineas para caso de Prueba" + caso.toString());
            Island[] arrayDeIslas = new Island[caso.getSetOfIslands().size()];
            //Utils.generarPermutacionNoSustIsland(caso.getSetOfIslands().values().toArray(arrayDeIslas), "", 2);
            Utils.combinations2Island(caso.getSetOfIslands().values().toArray(arrayDeIslas), 2, 0, new Island[2]);
            //Agrupar lineas por Longitud
            HashMap<Double, List<Lineas>> hashMapLineas = groupLinesByLongitud();
            for (Map.Entry<Double, List<Lineas>> entry : hashMapLineas.entrySet()) {
                System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
                Lineas[] arrayDeLineas = new Lineas[entry.getValue().size()];
                Utils.combinations2Lienas(entry.getValue().toArray(arrayDeLineas), 2, 0, new Lineas[2]);
                buscarAcrhipielagos();
                Utils.combinacionDelineasEntreIslas = new ArrayList<>();
            }
            System.out.println( "Numero de Archipielagos en la Prueba -->" + listDeArchipielagos.size());
            mSampleOutputResult += listDeArchipielagos.size() + "\n";
            Utils.lineasEntreIslas = new ArrayList<>();
            listDeArchipielagos = new ArrayList<>();
        }
    }

    public void buscarAcrhipielagos() {
        for (Lineas[] combinacionLineas : Utils.combinacionDelineasEntreIslas) {
            //Si la lineas comparten el mismo punto tenemos un archipielago
            Lineas linea1 = combinacionLineas[0];
            Lineas linea2 = combinacionLineas[1];
            if (compartenMismoPunto(linea1, linea2)) {
                Archipielago archipielago = new Archipielago(linea1, linea2);
                listDeArchipielagos.add(archipielago);
            }
        }
    }

    public boolean compartenMismoPunto(Lineas linea1, Lineas linea2) {
        if (linea1.getIsla1().equals(linea2.getIsla1())) {
            return true;
        }
        if (linea1.getIsla1().equals(linea2.getIsla2())) {
            return true;
        }
        if (linea1.getIsla2().equals(linea2.getIsla1())) {
            return true;
        }
        if (linea1.getIsla2().equals(linea2.getIsla2())) {
            return true;
        }
        return false;
    }


    public HashMap<Double, List<Lineas>> groupLinesByLongitud() {
        HashMap<Double, List<Lineas>> hashMap = new HashMap<Double, List<Lineas>>();
        for (Lineas liena : Utils.lineasEntreIslas) {
            if (!hashMap.containsKey(liena.getLongitud())) {
                List<Lineas> list = new ArrayList<Lineas>();
                list.add(liena);
                hashMap.put(liena.getLongitud(), list);
            } else {
                hashMap.get(liena.getLongitud()).add(liena);
            }
        }
        return hashMap;
    }

    public void initData(String fileName) {
        String fileInfo = mFileInfo = Utils.AssetJSONFile(fileName);
        String[] linesInFile = fileInfo.split("\\n");
        int intNUmbersOfTest = Integer.valueOf(linesInFile[0]);
        listCasosDePrueba = new ArrayList<>(intNUmbersOfTest);
        int numberOfIslands = Integer.valueOf(linesInFile[1]);
        TesCase casoDePrueba = new TesCase(numberOfIslands);
        listCasosDePrueba.add(casoDePrueba);
        for (int lineNumber = 2; lineNumber < linesInFile.length; lineNumber++) {
            String[] pointsofIsland = linesInFile[lineNumber].split(" ");
            if (pointsofIsland.length == 1) {
                numberOfIslands = Integer.valueOf(linesInFile[lineNumber]);
                casoDePrueba = new TesCase(numberOfIslands);
                listCasosDePrueba.add(casoDePrueba);
            } else {
                int pointX = Integer.valueOf(pointsofIsland[0]);
                int pointY = Integer.valueOf(pointsofIsland[1]);
                casoDePrueba.addIslands(new Island(pointX, pointY));
            }
        }
    }

}
