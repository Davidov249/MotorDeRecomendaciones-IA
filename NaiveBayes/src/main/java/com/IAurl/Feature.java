package com.IAurl;

import java.util.ArrayList;
import java.util.List;

public class Feature {
    public String Nombre;
    public List<String> Generos = new ArrayList<>();
    public String Director;
    public String Actor1;
    public String Actor2;
    public String Actor3;
    public String Color;
    public List<String> Plot = new ArrayList<>();
    public String Idioma;
    public String Anio;

    public List<String[]> feature = new ArrayList<>();

    public void construirFeature(String[] arregloStrings){
        int cont = 1;
        Nombre = arregloStrings[0].replace("\"", "");
        while (cont <= arregloStrings.length) {
            switch (cont) {
                case 1:
                    setGeneros( arregloStrings[cont].replace("\"", "").split("\\|") );
                    cont++;
                    break;
                case 2:
                    Director = arregloStrings[cont].replace("\"", "");
                    cont++;
                    break;
                case 3:
                    Actor1 = arregloStrings[cont].replace("\"", "");
                    cont++;
                    break;
                case 4:
                    Actor2 = arregloStrings[cont].replace("\"", "");
                    cont++;
                    break;
                case 5:
                    Actor3 = arregloStrings[cont].replace("\"", "");
                    cont++;
                    break;
                case 6:
                    Color = arregloStrings[cont].replace("\"", "");
                    cont++;
                    break;
                case 7:
                    setPlot(arregloStrings[cont].replace("\"", "").split("\\|"));
                    cont++;
                    break;
                case 8:
                    Idioma = arregloStrings[cont].replace("\"", "");
                    cont++;
                    break;
                case 9:
                    Anio = arregloStrings[cont].replace("\"", "");
                    cont++;
                    break;
                default:
                    cont++;
                    break;
            }
        }
    }

    public void setGeneros(String genero){
        Generos.add(genero);
    }

    public void setGeneros(String[] generos){
        for (String genero : generos) {
            Generos.add(genero);
        }
    }

    public void setPlot(String[] trama){
        for (String plots : trama) {
            Plot.add(plots);
        }
    }
}