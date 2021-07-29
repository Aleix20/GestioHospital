package m03.uf5.p01.grup06.gestiohospital.vista;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.*;

public class PanelNewPacient extends JPanel {
    private JPanel nom, apellido1, apellido2, numSS, DNI, telf, calle, num, planta, puerta, CP, ciutat;
    private JTextField tfNom, tfApellido1, tfApellido2, tfNumSS, tfDNI, tfTelf, tfCalle, tfNum, tfPlanta, tfPuerta, tfCP, tfCiutat;
    
    public PanelNewPacient () {
        generaGUI();
    } 

    private void generaGUI() {
        JPanel pContenido = new JPanel(new GridLayout(7,2));
        pContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        nom = new JPanel(new FlowLayout(0));
        tfNom = new JTextField();
        tfNom.setPreferredSize(new Dimension(175,30));
        JLabel lblNom = new JLabel("Nombre:");
        lblNom.setPreferredSize(new Dimension(100, 30));
        nom.add(lblNom);
        nom.add(tfNom);
        
        apellido1 = new JPanel(new FlowLayout(0));
        tfApellido1 = new JTextField();
        tfApellido1.setPreferredSize(new Dimension(175,30));
        JLabel lblApellido1 = new JLabel("Primer apellido:");
        lblApellido1.setPreferredSize(new Dimension(100, 30));
        apellido1.add(lblApellido1);
        apellido1.add(tfApellido1);
        
        apellido2 = new JPanel(new FlowLayout(0));
        tfApellido2 = new JTextField();
        tfApellido2.setPreferredSize(new Dimension(175,30));
        JLabel lblApellido2 = new JLabel("Segundo apellido:");
        lblApellido2.setPreferredSize(new Dimension(100, 30));
        apellido2.add(lblApellido2);
        apellido2.add(tfApellido2);
        
        numSS = new JPanel(new FlowLayout(0));
        tfNumSS = new JTextField();
        tfNumSS.setPreferredSize(new Dimension(175,30));
        JLabel lblnumSS = new JLabel("Numero SS:");
        lblnumSS.setPreferredSize(new Dimension(100, 30));
        numSS.add(lblnumSS);
        numSS.add(tfNumSS);
        
        DNI = new JPanel(new FlowLayout(0));
        tfDNI = new JTextField();
        tfDNI.setPreferredSize(new Dimension(175,30));
        JLabel lblDNI = new JLabel("DNI / NIF:");
        lblDNI.setPreferredSize(new Dimension(100, 30));
        DNI.add(lblDNI);
        DNI.add(tfDNI);
        
        telf = new JPanel(new FlowLayout(0));
        tfTelf = new JTextField();
        tfTelf.setPreferredSize(new Dimension(175,30));
        JLabel lblTelf = new JLabel("Telefono:");
        lblTelf.setPreferredSize(new Dimension(100, 30));
        telf.add(lblTelf);
        telf.add(tfTelf);
        
        calle = new JPanel(new FlowLayout(0));
        tfCalle = new JTextField();
        tfCalle.setPreferredSize(new Dimension(175,30));
        JLabel lblCalle = new JLabel("Calle:");
        lblCalle.setPreferredSize(new Dimension(100, 30));
        calle.add(lblCalle);
        calle.add(tfCalle);
        
        num = new JPanel(new FlowLayout(0));
        tfNum = new JTextField();
        tfNum.setPreferredSize(new Dimension(175,30));
        JLabel lblNum = new JLabel("Numero:");
        lblNum.setPreferredSize(new Dimension(100, 30));
        num.add(lblNum);
        num.add(tfNum);
        
        planta = new JPanel(new FlowLayout(0));
        tfPlanta = new JTextField();
        tfPlanta.setPreferredSize(new Dimension(175,30));
        JLabel lblPlanta = new JLabel("Planta:");
        lblPlanta.setPreferredSize(new Dimension(100, 30));
        planta.add(lblPlanta);
        planta.add(tfPlanta);
        
        JPanel pDir = new JPanel(new GridLayout(2, 2));
        
        puerta = new JPanel(new FlowLayout(0));
        tfPuerta = new JTextField();
        tfPuerta.setPreferredSize(new Dimension(175,30));
        JLabel lblPuerta = new JLabel("Puerta:");
        lblPuerta.setPreferredSize(new Dimension(100, 30));
        puerta.add(lblPuerta);
        puerta.add(tfPuerta);
        
        CP = new JPanel(new FlowLayout(0));
        tfCP = new JTextField();
        tfCP.setPreferredSize(new Dimension(175,30));
        JLabel lblCP = new JLabel("CP:");
        lblCP.setPreferredSize(new Dimension(100, 30));
        CP.add(lblCP);
        CP.add(tfCP);
        
        ciutat = new JPanel(new FlowLayout(0));
        tfCiutat = new JTextField();
        tfCiutat.setPreferredSize(new Dimension(175,30));
        JLabel lblCiutat = new JLabel("Ciudad:");
        lblCiutat.setPreferredSize(new Dimension(100, 30));
        ciutat.add(lblCiutat);
        ciutat.add(tfCiutat);
                
        JLabel lblDesc = new JLabel ("Introduce los datos del paciente.");
        
        pContenido.add(lblDesc);
        pContenido.add(new JLabel(""));
        pContenido.add(nom);
        pContenido.add(apellido1);
        pContenido.add(apellido2);
        pContenido.add(numSS);
        pContenido.add(DNI);
        pContenido.add(telf);
        pContenido.add(calle);
        pContenido.add(num);
        pContenido.add(planta);
        pContenido.add(puerta);
        pContenido.add(CP);
        pContenido.add(ciutat);
        
        this.add(pContenido);
    }

    public JTextField getTfNom() {
        return tfNom;
    }

    public JTextField getTfApellido1() {
        return tfApellido1;
    }

    public JTextField getTfApellido2() {
        return tfApellido2;
    }

    public JTextField getTfNumSS() {
        return tfNumSS;
    }

    public JTextField getTfDNI() {
        return tfDNI;
    }

    public JTextField getTfTelf() {
        return tfTelf;
    }

    public JTextField getTfCalle() {
        return tfCalle;
    }

    public JTextField getTfNum() {
        return tfNum;
    }

    public JTextField getTfPlanta() {
        return tfPlanta;
    }

    public JTextField getTfPuerta() {
        return tfPuerta;
    }

    public JTextField getTfCP() {
        return tfCP;
    }

    public JTextField getTfCiutat() {
        return tfCiutat;
    }
}
