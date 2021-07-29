/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup06.gestiohospital.modelo;

/**
 *
 * @author Joel C
 */
public class Adreca {

    private final String ciutat;
    private final long codiPostal;
    private final String carrer;
    private final int numero;
    private final String planta;
    private final String porta;

    public Adreca(String ciutat, long codiPostal, String carrer, int numero, String planta, String porta) {
        this.ciutat = ciutat;
        this.codiPostal = codiPostal;
        this.carrer = carrer;
        this.numero = numero;
        this.planta = planta;
        this.porta = porta;
    }

    @Override
    public String toString() {
        if (codiPostal > 9999) {
            return "Calle " + carrer + " Nº" + numero + " " + planta + "-" + porta + "\n" + codiPostal + " " + ciutat;
        } else {
            return "Calle " + carrer + " Nº" + numero + " " + planta + "-" + porta + "\n0" + codiPostal + " " + ciutat;
        }
    }
    
    public String  FormCSVAdreca(){
        return  ciutat + "," + codiPostal + "," + carrer + "," + numero + "," + planta + "," + porta;   
    }

    public String getCiutat() {
        return ciutat;
    }

    public long getCodiPostal() {
        return codiPostal;
    }

    public String getCarrer() {
        return carrer;
    }

    public int getNumero() {
        return numero;
    }

    public String getPlanta() {
        return planta;
    }

    public String getPorta() {
        return porta;
    }
}
