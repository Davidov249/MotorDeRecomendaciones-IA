package com.IAurl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    /*
        public static Map<String, Feature> Peliculas = new HashMap<String, Feature>();
        public static List<String> MovieTitle = new ArrayList<String>();
        public static List<Feature> paquete = new ArrayList<Feature>();
        public static List<Recomendacion> recommended = new ArrayList<Recomendacion>();
    */
    public static void main( String[] args )
    {
        Map<String, Feature> Peliculas = new HashMap<String, Feature>();
        List<String> MovieTitle = new ArrayList<String>();
        List<Feature> paquete = new ArrayList<Feature>();
        List<Recomendacion> recommended = new ArrayList<Recomendacion>();
        NaiveBayes algoritmo = new NaiveBayes();
        boolean running = true;
        try{
            BufferedReader csvReader = new BufferedReader(new FileReader("NaiveBayes\\src\\main\\resources\\Recomendadas.csv"));
            String row = csvReader.readLine();

            int n = 0;
            while ((row = csvReader.readLine()) != null) {
                n++;
                String[] data = row.split(",");
                Feature feature = new Feature();
                feature.construirFeature(data);
                algoritmo.agregarFeaturesR(feature);
                if( !Peliculas.containsKey(data[0].replace("\"", "")) ){
                    Peliculas.put(data[0].replace("\"", ""), feature);
                    MovieTitle.add(data[0].replace("\"", ""));
                    paquete.add(feature);
                }
            }
            algoritmo.featuresRecomendadas = n;
            n = 0;
            csvReader.close();
            BufferedReader csvReader2 = new BufferedReader(new FileReader("NaiveBayes\\src\\main\\resources\\NoRecomendadas.csv"));
            row = csvReader2.readLine();
            while ((row = csvReader2.readLine()) != null) {
                n++;
                String[] data = row.split(",");
                Feature feature = new Feature();
                feature.construirFeature(data);
                algoritmo.agregarFeaturesN(feature);
                if( !Peliculas.containsKey(data[0].replace("\"", "")) ){
                    Peliculas.put(data[0].replace("\"", ""), feature);
                    MovieTitle.add(data[0].replace("\"", ""));
                    paquete.add(feature);
                }
            }
            algoritmo.featuresNoRecomendadas = n;
            csvReader2.close();
            //System.out.println( Peliculas.size() );
        }catch (Exception Ex){
            System.out.println( "Something went wrong! D:" );
        }
        //System.out.println(algoritmo.allFeatures.size());
        algoritmo.entrenarBayes();
        //System.out.println(algoritmo.calcularBayes(Peliculas.get(MovieTitle.get(2))));
        while (running) {
            algoritmo.entrenarBayes();
            // Calculo de recomendaciones nueva
            recommended.clear();
            for (Feature pelicula : paquete) {
                Double probaDouble = algoritmo.calcularBayes(pelicula);
                if (probaDouble > 0.5) {
                    Recomendacion rec = new Recomendacion();
                rec.recomendacion(pelicula.Nombre, probaDouble);
                recommended.add(rec);
                }
            }
            Collections.sort(recommended);

            String respuesta;
            System.out.println("¡Bienvenido al sistema de recomendaciones!");
            System.out.println("Eliga una opcion:");
            String[] Recomendaciones = new String[15];
            //Recomendaciones = algoritmo.Recomendaciones.toArray(Recomendaciones);
            String salida;
            int x = 0;
            while (x < 15) {
                System.out.println(Integer.toString(x + 1) + " " + recommended.get(x).Titulo);
                Recomendaciones[x] = recommended.get(x).Titulo;
                x++;
            }
            String eleccion;
            eleccion = System.console().readLine();
            switch (eleccion) {
                case "1":
                    System.out.println(Recomendaciones[0]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "2":
                    System.out.println(Recomendaciones[1]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "3":
                    System.out.println(Recomendaciones[2]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "4":
                    System.out.println(Recomendaciones[3]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "5":
                    System.out.println(Recomendaciones[4]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "6":
                    System.out.println(Recomendaciones[5]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "7":
                    System.out.println(Recomendaciones[6]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "8":
                    System.out.println(Recomendaciones[7]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "9":
                    System.out.println(Recomendaciones[8]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "10":
                    System.out.println(Recomendaciones[9]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "11":
                    System.out.println(Recomendaciones[10]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "12":
                    System.out.println(Recomendaciones[11]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "13":
                    System.out.println(Recomendaciones[12]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "14":
                    System.out.println(Recomendaciones[13]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                case "15":
                    System.out.println(Recomendaciones[14]);
                    System.out.println("Le gustó la pelicula? Y: si N: no");
                    respuesta = System.console().readLine();
                    if (respuesta.equalsIgnoreCase("y")) {
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), true);
                    }else{
                        algoritmo.insertarBayes(Peliculas.get(Recomendaciones[0]), false);
                    }
                    break;
                default:
                
                    break;
            }
            System.out.println("Desea salir? Y: si N: no");
            salida = System.console().readLine();
            if (salida.equalsIgnoreCase("y")) {
                running = false;
            }
        }
        //System.out.println( "Hello World!" );
    }
}
