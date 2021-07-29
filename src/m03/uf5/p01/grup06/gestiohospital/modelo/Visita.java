/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m03.uf5.p01.grup06.gestiohospital.modelo;

import java.time.LocalDateTime;

/**
 *
 * @author Aleix
 */
public class Visita {

    private final LocalDateTime data;
    private final Malaltia malaltia;
    private final Metge metge;
    private final String dni;
    private final String SegSocial;

    public Visita(LocalDateTime data, Malaltia malaltia, Metge metge, String dni, String SegSocial) {

        this.data = data;
        this.malaltia = malaltia;
        this.metge = metge;
        this.dni=dni;
        this.SegSocial=SegSocial;
    }

    
    
    public LocalDateTime getData() {
        return data;
    }

    public Malaltia getMalaltia() {
        return malaltia;
    }

    public Metge getMetge() {
        return metge;
    }

    public String getDni() {
        return dni;
    }

    public String getSegSocial() {
        return SegSocial;
    }
}
