package m03.uf5.p01.grup06.gestiohospital.modelo;

public class Pacient extends Persona {

    private final Historial historial;

    public Pacient(String nom, String cognom1, String cognom2, String numSegSocial, 
            String nif, String telefon, Adreca adreca) {
        super(nom, cognom1, cognom2, numSegSocial, nif, telefon, adreca);
        historial = new Historial(this);
    }

    @Override
    public String toString() {
        return "[" + getNif() + "]: " + getNom() + " " + getCognom1();
    }
    
    public Historial getHistorial() {
        return this.historial;
    }
}
