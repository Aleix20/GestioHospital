package m03.uf5.p01.grup06.gestiohospital.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class Historial {
    
    private final int codi;
    private static int ultCodigo = 1;
    private final ArrayList<Visita> visitas = new ArrayList<>();
    private final Pacient pacient;
    
    public Historial(Pacient pacient) {
        this.codi = ultCodigo++;
        this.pacient = pacient;
    }
    
    @Override
    public String toString() {
        String s = "<html><h2>HISTORIAL [Codigo " + this.codi + "]</h2><br>----------------------------------------------";
        
        Iterator<Visita> lit = visitas.iterator();
        
        while (lit.hasNext()) {
            Visita v = lit.next();
            s += "<br> Visita " + v.getData().toString();
            s += "<br> Medico: " + v.getMetge().getNom() + " " + v.getMetge().getCognom1();
            s += "<br> Enfermedad: " + v.getMalaltia().getNom();
            s += "<br>----------------------------------------------";
        }
        return s;
    }
    
    public void addVisita (Visita v) {
        visitas.add(v);
    }
    
    public int getCodi () {
        return this.codi;
    }
}
