package m03.uf5.p01.grup06.gestiohospital.vista;

import java.awt.*;
import javax.swing.*;
import m03.uf5.p01.grup06.gestiohospital.DAO.*;

public class PanelNewVisita extends JPanel {
    private final String[] listaEnfermetats, listaMetges, listaPacients;
    
    private JPanel pacient, metge, enfermetat;
    private JComboBox cbPacient, cbMetge, cbEnfermedad;
    
    public PanelNewVisita () {
        listaPacients = objetsToString(PacienteDAO.getAllPacients()); 
        listaMetges = objetsToString(MetgeDAO.getAllMetges());
        listaEnfermetats = objetsToString(MalaltiaDAO.getAllMalalties());   
        creaPanel();            
    } 

    private void creaPanel() {        
        JPanel pContenido = new JPanel(new GridLayout(4, 1, 5, 5));
        pContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
               
        pacient = new JPanel(new FlowLayout(0));
        cbPacient = new JComboBox(listaPacients);
        cbPacient.setPreferredSize(new Dimension(300, 30));
        JLabel lblPacient = new JLabel("Paciente:");
        lblPacient.setPreferredSize(new Dimension(120, 30));
        pacient.add(lblPacient);
        pacient.add(cbPacient);
        
        metge = new JPanel(new FlowLayout(0));
        cbMetge = new JComboBox(listaMetges);
        cbMetge.setPreferredSize(new Dimension(300, 30));
        JLabel lblMetge = new JLabel("Medico:");
        lblMetge.setPreferredSize(new Dimension(120, 30));
        metge.add(lblMetge);
        metge.add(cbMetge);
        
        enfermetat = new JPanel(new FlowLayout(0));
        cbEnfermedad = new JComboBox(listaEnfermetats);
        cbEnfermedad.setPreferredSize(new Dimension(300, 30));
        JLabel lblEnfermetat = new JLabel("Enfermedad:");
        lblEnfermetat.setPreferredSize(new Dimension(120, 30));
        enfermetat.add(lblEnfermetat);
        enfermetat.add(cbEnfermedad);
        
        JLabel lblDesc = new JLabel ("Introduce los datos de la visita.");
        
        pContenido.add(lblDesc);
        pContenido.add(pacient);
        pContenido.add(metge);
        pContenido.add(enfermetat);
        this.add(pContenido);
    }

    public JComboBox getCbPacient() {
        return cbPacient;
    }

    public JComboBox getCbMetge() {
        return cbMetge;
    }

    public JComboBox getCbEnfermedad() {
        return cbEnfermedad;
    }
    
    private String[] objetsToString (Object[] objs) {
        String[] strings = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            strings[i] = objs[i].toString();
        }
        return strings;
    }
}
