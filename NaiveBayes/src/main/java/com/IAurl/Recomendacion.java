package com.IAurl;

public class Recomendacion implements Comparable<Recomendacion> {

    public String Titulo;
    public double Probabilidad;

    public void recomendacion(String titulo, double prob){
        Titulo = titulo;
        Probabilidad = prob;
    }

    /**
     * @return the probabilidad
     */
    public double getProbabilidad() {
        return Probabilidad;
    }
    /**
     * @return the titulo
     */
    public String getTitulo() {
        return Titulo;
    }
    
    @Override
    public int compareTo(Recomendacion o) {
        return new Double(Probabilidad).compareTo( o.Probabilidad);
    }
}