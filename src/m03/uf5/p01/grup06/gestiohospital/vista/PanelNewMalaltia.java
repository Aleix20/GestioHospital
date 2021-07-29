package m03.uf5.p01.grup06.gestiohospital.vista;

import java.awt.*;
import javax.swing.*;

public class PanelNewMalaltia extends JPanel {
    
    private JPanel pNom, pTractament, pBaixa, pDurada, pCodi;
    private JCheckBox cbBaixa;
    private JTextField tfNom, tfTractament, tfDurada, tfCodi;
    
    public PanelNewMalaltia () {
        generaGUI();
    }

    private void generaGUI() {
        JPanel pContenido = new JPanel(new GridLayout(6,1));
        pContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        pNom = new JPanel(new FlowLayout(0));
        tfNom = new JTextField();
        tfNom.setPreferredSize(new Dimension(200,30));
        JLabel lblNom = new JLabel("Nombre:");
        lblNom.setPreferredSize(new Dimension(100, 30));
        pNom.add(lblNom);
        pNom.add(tfNom);
        
        pCodi = new JPanel(new FlowLayout(0));
        tfCodi = new JTextField();
        tfCodi.setPreferredSize(new Dimension(200,30));
        JLabel lblCodi = new JLabel("Codigo:");
        lblCodi.setPreferredSize(new Dimension(100, 30));
        pCodi.add(lblCodi);
        pCodi.add(tfCodi);
        
        pTractament = new JPanel(new FlowLayout(0));
        tfTractament = new JTextField();
        tfTractament.setPreferredSize(new Dimension(200,30));
        JLabel lblTractament = new JLabel("Tratamiento:");
        lblTractament.setPreferredSize(new Dimension(100, 30));
        pTractament.add(lblTractament);
        pTractament.add(tfTractament);
        
        pDurada = new JPanel(new FlowLayout(0));
        tfDurada = new JTextField();
        tfDurada.setPreferredSize(new Dimension(200,30));
        JLabel lblDurada = new JLabel("Dias de duracion:");
        lblDurada.setPreferredSize(new Dimension(100, 30));
        pDurada.add(lblDurada);
        pDurada.add(tfDurada);
        
        pBaixa = new JPanel(new FlowLayout(1));
        cbBaixa = new JCheckBox("Causa de baja");
        cbBaixa.setPreferredSize(new Dimension(175,30));
        pBaixa.add(cbBaixa);
        
               
        JLabel lblDesc = new JLabel ("Introduce los datos de la enfermedad.");
        pContenido.add(lblDesc);
        pContenido.add(pNom);
        pContenido.add(pCodi);
        pContenido.add(pTractament);
        pContenido.add(pDurada);
        pContenido.add(pBaixa);
        this.add(pContenido);
    }
    
    public JCheckBox getCbBaixa() {
        return cbBaixa;
    }

    public JTextField getTfNom() {
        return tfNom;
    }

    public JTextField getTfTractament() {
        return tfTractament;
    }

    public JTextField getTfDurada() {
        return tfDurada;
    }
    
    public JTextField getTfCodi() {
        return tfCodi;
    }
}
