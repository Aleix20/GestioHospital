package m03.uf5.p01.grup06.gestiohospital.controlador;

import m03.uf5.p01.grup06.gestiohospital.modelo.*;
import m03.uf5.p01.grup06.gestiohospital.vista.*;

public class GestioHospital {

    private static Hospital h;

    public static void main(String[] args) {
        iniciaHospital();

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaginaInicio(h).setVisible(true);
            }
        });
    }

    private static void iniciaHospital() {
        try {
            System.out.println("INICIANDO HOSPITAL...");
            h = new Hospital("Mutua Terrassa", new Adreca("Terrassa", 8221, "Plaça Doctor Robert", 5, "S/N", "S/N"));
            System.out.println("Hospital iniciado con exito.\n\n" + h + "\n");

        } catch (Exception e) {
            System.out.println("ERROR DE INICIALIZACION : " + e.getMessage());
        }
    }
}
