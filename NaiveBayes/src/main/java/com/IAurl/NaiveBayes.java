package com.IAurl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NaiveBayes {

    public Map<String, Integer> FrecuenciasR = new HashMap<String, Integer>();
    public int TotalR = 0;
    public Map<String, Integer> FrecuenciasN = new HashMap<String, Integer>();
    public int TotalN = 0;
    public List<String> allFeatures = new ArrayList<String>();
    public Map<String, TablaProbabilidad> Probabilidades = new HashMap<String, TablaProbabilidad>();
    public int featuresRecomendadas = 0;
    public int featuresNoRecomendadas = 0;
    public double totalRec; //Ham
    public double totalNoRec; //Spam

    public void entrenarBayes(){
        totalRec = Ham(); //Ham
        totalNoRec = Spam(); //Spam
        for (int i =0; i<allFeatures.size(); i++) {
            TablaProbabilidad tabla = new TablaProbabilidad();
            String feature = allFeatures.get(i);
            double fr;
            double fn;
            if (FrecuenciasR.containsKey(feature)) {
                fr = FrecuenciasR.get(feature);
            }else{
                fr = 0;
            }
            if (FrecuenciasN.containsKey(feature)) {
                fn = FrecuenciasN.get(feature);
            } else {
                fn = 0;
            }
            double recomendada = fr / TotalR; //P(m|Ham)
            double noRecomendada = fn / TotalN; //P(m|Spam)
            tabla.construirTabla(recomendada, noRecomendada);
            Probabilidades.put(feature, tabla);
        }
    }

    /*public void calcularRecomendaciones(List<Feature> peliculas){
        List<Recomendacion> prob = new ArrayList<Recomendacion>();
        for (Feature pelicula : peliculas) {
            Recomendacion rec = new Recomendacion();
            rec.recomendacion(pelicula.Nombre, calcularBayes(pelicula));
            prob.add(rec);
        }
        Collections.sort(prob);
        int i = 0;
        while(i < 15){
            Recomendaciones.add(prob.get(i).Titulo);
        }
    }*/

    public double Ham(){
        double resultado = 1;
        resultado = featuresRecomendadas + featuresNoRecomendadas;
        //resultado = resultado 
        resultado = featuresRecomendadas/resultado;
        return resultado;
    }

    public double Spam(){
        double resultado = 1;
        resultado = featuresRecomendadas + featuresNoRecomendadas;
        //resultado = resultado 
        resultado = featuresNoRecomendadas/resultado;
        return resultado;
    }

    public double probabilidadFeature(String feature){
        double resultado = (Probabilidades.get(feature).positivo * totalRec)+(Probabilidades.get(feature).negativo * totalNoRec);
        resultado = (Probabilidades.get(feature).positivo * totalRec) / resultado;
        //resultado = (Probabilidades.get(feature).positivo * totalRec)/((Probabilidades.get(feature).positivo * totalRec)+(Probabilidades.get(feature).negativo * totalNoRec));
        return resultado;
    }
    
    public void insertarBayes(Feature pelicula, boolean opinion){
        if (opinion) {
            featuresRecomendadas++;
            agregarFeaturesR(pelicula);
        } else {
            featuresNoRecomendadas++;
            agregarFeaturesN(pelicula);
        }
    }

    public double calcularBayes(Feature pelicula){
        List<String> features = new ArrayList<String>();
        features.add(pelicula.Actor1);
        features.add(pelicula.Actor2);
        features.add(pelicula.Actor3);
        features.add(pelicula.Anio);
        features.add(pelicula.Color);
        features.add(pelicula.Director);
        features.add(pelicula.Idioma);
        for (String genero : pelicula.Generos) {
            features.add(genero);
        }
        for (String plot : pelicula.Plot) {
            features.add(plot);
        }
        double resultado = 1;
        for (String feature : features) {
            resultado = probabilidadFeature(feature);
        }
        return resultado;
    }

    public void agregarFeaturesN(Feature feature){
        int cont = 0;
        while(cont < feature.Generos.size()){
            if(FrecuenciasN.containsKey(feature.Generos.get(cont))){
                FrecuenciasN.put(feature.Generos.get(cont), FrecuenciasN.get(feature.Generos.get(cont)) + 1);
                cont++;
                TotalN++;
            }else{
                FrecuenciasN.put(feature.Generos.get(cont), 1);
                if ( !allFeatures.contains(feature.Generos.get(cont)) ) {
                    allFeatures.add(feature.Generos.get(cont));
                }
                cont++;
                TotalN++;
            }
           
        }
        cont = 0;
        while (cont < feature.Plot.size()) {
            if (FrecuenciasN.containsKey(feature.Plot.get(cont))) {
                FrecuenciasN.put(feature.Plot.get(cont), FrecuenciasN.get(feature.Plot.get(cont)) + 1);
                cont++;
                TotalN++;
            }else{
                FrecuenciasN.put(feature.Plot.get(cont), 1);
                if ( !allFeatures.contains(feature.Plot.get(cont)) ) {
                    allFeatures.add(feature.Plot.get(cont));
                }
                cont++;
                TotalN++;
            }
        }
        if (FrecuenciasN.containsKey(feature.Director)) {
            FrecuenciasN.put(feature.Director, FrecuenciasN.get(feature.Director) + 1);
            TotalN++;
        }else{
            FrecuenciasN.put(feature.Director, 1);
            if ( !allFeatures.contains(feature.Director) ) {
                allFeatures.add(feature.Director);
            }
            TotalN++;
        }
        if (FrecuenciasN.containsKey(feature.Actor1)) {
            FrecuenciasN.put(feature.Actor1, FrecuenciasN.get(feature.Actor1) + 1);
            TotalN++;
        }else{
            FrecuenciasN.put(feature.Actor1, 1);
            if ( !allFeatures.contains(feature.Actor1) ) {
                allFeatures.add(feature.Actor1);
            }
            TotalN++;
        }
        if (FrecuenciasN.containsKey(feature.Actor2)) {
            FrecuenciasN.put(feature.Actor2, FrecuenciasN.get(feature.Actor2) + 1);
            TotalN++;
        }else{
            FrecuenciasN.put(feature.Actor2, 1);
            if ( !allFeatures.contains(feature.Actor2) ) {
                allFeatures.add(feature.Actor2);
            }
            TotalN++;
        }
        if (FrecuenciasN.containsKey(feature.Actor3)) {
            FrecuenciasN.put(feature.Actor3, FrecuenciasN.get(feature.Actor3) + 1);
            TotalN++;
        }else{
            FrecuenciasN.put(feature.Actor3, 1);
            if ( !allFeatures.contains(feature.Actor3) ) {
                allFeatures.add(feature.Actor3);
            }
            TotalN++;
        }
        if (FrecuenciasN.containsKey(feature.Color)) {
            FrecuenciasN.put(feature.Color, FrecuenciasN.get(feature.Color) + 1);
            TotalN++;
        } else {
            FrecuenciasN.put(feature.Color, 1);
            if ( !allFeatures.contains(feature.Color) ) {
                allFeatures.add(feature.Color);
            }
            TotalN++;
        }
        if (FrecuenciasN.containsKey(feature.Anio)) {
            FrecuenciasN.put(feature.Anio, FrecuenciasN.get(feature.Anio) + 1);
            TotalN++;
        } else {
            FrecuenciasN.put(feature.Anio, 1);
            if ( !allFeatures.contains(feature.Anio) ) {
                allFeatures.add(feature.Anio);
            }
            TotalN++;
        }
        if (FrecuenciasN.containsKey(feature.Idioma)) {
            FrecuenciasN.put(feature.Idioma, FrecuenciasN.get(feature.Idioma) + 1);
            TotalN++;
        } else {
            FrecuenciasN.put(feature.Idioma, 1);
            if ( !allFeatures.contains(feature.Idioma) ) {
                allFeatures.add(feature.Idioma);
            }
            TotalN++;
        }
    }

    public void agregarFeaturesR(Feature feature){
        int cont = 0;
        while(cont < feature.Generos.size()){
            if(FrecuenciasR.containsKey(feature.Generos.get(cont))){
                FrecuenciasR.put(feature.Generos.get(cont), FrecuenciasR.get(feature.Generos.get(cont)) + 1);
                cont++;
                TotalR++;
            }else{
                FrecuenciasR.put(feature.Generos.get(cont), 1);
                allFeatures.add(feature.Generos.get(cont));
                cont++;
                TotalR++;
            }
           
        }
        cont = 0;
        while (cont < feature.Plot.size()) {
            if (FrecuenciasR.containsKey(feature.Plot.get(cont))) {
                FrecuenciasR.put(feature.Plot.get(cont), FrecuenciasR.get(feature.Plot.get(cont)) + 1);
                cont++;
                TotalR++;
            }else{
                FrecuenciasR.put(feature.Plot.get(cont), 1);
                allFeatures.add(feature.Plot.get(cont));
                cont++;
                TotalR++;
            }
        }
        if (FrecuenciasR.containsKey(feature.Director)) {
            FrecuenciasR.put(feature.Director, FrecuenciasR.get(feature.Director) + 1);
            TotalR++;
        }else{
            FrecuenciasR.put(feature.Director, 1);
            allFeatures.add(feature.Director);
            TotalR++;
        }
        if (FrecuenciasR.containsKey(feature.Actor1)) {
            FrecuenciasR.put(feature.Actor1, FrecuenciasR.get(feature.Actor1) + 1);
            TotalR++;
        }else{
            FrecuenciasR.put(feature.Actor1, 1);
            allFeatures.add(feature.Actor1);
            TotalR++;
        }
        if (FrecuenciasR.containsKey(feature.Actor2)) {
            FrecuenciasR.put(feature.Actor2, FrecuenciasR.get(feature.Actor2) + 1);
            TotalR++;
        }else{
            FrecuenciasR.put(feature.Actor2, 1);
            allFeatures.add(feature.Actor2);
            TotalR++;
        }
        if (FrecuenciasR.containsKey(feature.Actor3)) {
            FrecuenciasR.put(feature.Actor3, FrecuenciasR.get(feature.Actor3) + 1);
            TotalR++;
        }else{
            FrecuenciasR.put(feature.Actor3, 1);
            allFeatures.add(feature.Actor3);
            TotalR++;
        }
        if (FrecuenciasR.containsKey(feature.Color)) {
            FrecuenciasR.put(feature.Color, FrecuenciasR.get(feature.Color) + 1);
            TotalR++;
        } else {
            FrecuenciasR.put(feature.Color, 1);
            allFeatures.add(feature.Color);
            TotalR++;
        }
        if (FrecuenciasR.containsKey(feature.Anio)) {
            FrecuenciasR.put(feature.Anio, FrecuenciasR.get(feature.Anio) + 1);
            TotalR++;
        } else {
            FrecuenciasR.put(feature.Anio, 1);
            allFeatures.add(feature.Anio);
            TotalR++;
        }
        if (FrecuenciasR.containsKey(feature.Idioma)) {
            FrecuenciasR.put(feature.Idioma, FrecuenciasR.get(feature.Idioma) + 1);
            TotalR++;
        } else {
            FrecuenciasR.put(feature.Idioma, 1);
            allFeatures.add(feature.Idioma);
            TotalR++;
        }
    }

}