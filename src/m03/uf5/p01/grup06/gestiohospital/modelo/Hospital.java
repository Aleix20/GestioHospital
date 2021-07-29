package m03.uf5.p01.grup06.gestiohospital.modelo;

public class Hospital {

    private final Hospital hospital;
    private final String nomHospital;
    private final Adreca adreca;

    public Hospital(String nom, Adreca adreca) {
        this.nomHospital = nom;
        this.adreca = adreca;
        this.hospital = this;
    }

    public Hospital(String nom, String ciutat, int codiPostal, String carrer, int numero, String planta, String porta) {
        this.nomHospital = nom;
        this.adreca = new Adreca(ciutat, codiPostal, carrer, numero, planta, porta);
        this.hospital = this;
    }

    public Hospital getInstance() {
        return this.hospital;
    }
    
    public String getName() {
        return this.nomHospital;
    }

    @Override
    public String toString() {
        return "Hospital " + this.nomHospital + "\n" + this.adreca;
    }
}
