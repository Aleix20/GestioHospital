package m03.uf5.p01.grup06.gestiohospital.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Persona {
    
    private String nom;
    private String cognom1;
    private String cognom2;
    private String numSegSocial;
    private String nif;
    private String telefon;
    private Adreca adreca;
    
    public Persona (String nom, String cognom1, String cognom2, String numSegSocial, String nif, String telefon, Adreca adreca) {
        String error = "Datos erroeneos:";
        
        if (!nifCorrecto(nif)) {
            error += "\n - NIF incorrecto (" + nif + ")";
        }
        if (!cumplePatron("^[A-zçñàáéèíóòúÇÑÁÀÉÈÍÒÓÚ·üÜïÏ]*", nom, cognom1, cognom2)){
            error += "\n - Nombre o apellido incorrecto ";
        }
        if (!segSocialCorrecto(numSegSocial)) {
            error += "\n - Numero de la seguridad social incorrecto ";
        }
        if (!cumplePatron("^[967][0-9]{8}$", telefon)) {
            error += "\n - Numero de telefono incorrecto ";
        }
        
        if (!error.equals("Datos erroeneos:")){
            throw(new IllegalArgumentException(error));
        } else {
            this.nom = nom;
            this.cognom1 = cognom1;
            this.cognom2 = cognom2;
            this.numSegSocial = numSegSocial;
            this.nif = nif;
            this.telefon = telefon;
            this.adreca = adreca;
        }        
    }
    
    private boolean nifCorrecto (String nif) {
        String TABLA_LETRA ="TRWAGMYFPDXBNJZSQVHLCKE";        
        Pattern reglas = Pattern.compile("^[0-9]{8}[A-Z]$");
        Matcher textAnalitzar;
        
        textAnalitzar = reglas.matcher(nif);
        if(!textAnalitzar.matches()) {
            return false;
        } else {
            return TABLA_LETRA.charAt((Integer.parseInt(nif.substring(0,8)) % 23)) == nif.charAt(8);
        }
    }
    
    private boolean segSocialCorrecto (String numSegSc) {
        
        if (cumplePatron("^[\\d]{12}$", numSegSc)) {
            
            int idProvincia = Integer.parseInt(numSegSc.substring(0,2));
            
            if ((idProvincia > 0 && idProvincia <= 50) || idProvincia == 53 || idProvincia == 66) {
                
                long n = Long.parseLong(numSegSc.substring(0,10));
                int n2 = Integer.parseInt(numSegSc.substring(10,12));
                
                if (n % 97 == n2) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean cumplePatron (String patron, String... datos) {
        Pattern regles = Pattern.compile(patron);
        for (String dato : datos) {
            Matcher textAnalitzar = regles.matcher(dato);
            if (!textAnalitzar.matches())
                return false;
        }
        return true;          
    }
    
    public String getNom() {
        return nom;
    }
    
    public String getCognom1() {
        return cognom1;
    }
    
    public String getCognom2() {
        return cognom2;
    }
    
    public String getNumSegSocial() {
        return numSegSocial;
    }
    
    public String getNif() {
        return nif;
    }
    
    public String getTelefon() {
        return telefon;
    }

    public Adreca getAdreca() {
        return adreca;
    }    
}
