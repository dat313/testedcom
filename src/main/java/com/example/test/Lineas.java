package com.example.test;

public class Lineas {
    private Island isla1;
    private Island isla2;

    public double getLongitud() {
        return longitud;
    }

    private double longitud;

    public Lineas(Island isla1, Island isla2) {
        this.isla1 = isla1;
        this.isla2 = isla2;
        setLongitud();
    }

    private void setLongitud() {
        longitud = Math.sqrt(Math.pow(isla2.getPointX() - isla1.getPointX(), 2) + Math.pow(isla2.getPointy() - isla1.getPointy(), 2));
        System.out.println("Longitus linea " + String.valueOf(longitud));
    }

    public Island getIsla1() {
        return isla1;
    }

    public Island getIsla2() {
        return isla2;
    }
}
