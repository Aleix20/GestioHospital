package m03.uf5.p01.grup06.gestiohospital.vista;

import java.awt.*;
import javax.swing.*;
import m03.uf5.p01.grup06.gestiohospital.controlador.*;
import m03.uf5.p01.grup06.gestiohospital.modelo.*;

public class PaginaAnadir extends JFrame {  
    
    private final Hospital hospital;
    private final String[] tipoObjetos = {"Visita", "Paciente", "Medico", "Enfermedad"};
    private JPanel pNorte, pSur, pCentro, pVisita, pMedico, pPaciente, pMalaltia;
    private JComboBox cbTipo;
    private JButton btnAceptar, btnCancelar;
    private ControladorAnadir c;

    public PaginaAnadir(Hospital hospital) {
        ImageIcon i = new ImageIcon("drawables/ic_hospitalLauncher.png");
        this.setIconImage(i.getImage());
        this.hospital = hospital;
        creaGUI(); 
        asignaMetodos();
        
    }

    private void creaGUI() {

        this.setMinimumSize(new Dimension(666, 611));
        this.setTitle("Nueva entrada");
        this.setLocationRelativeTo(null);

        JPanel contenido = new JPanel(new BorderLayout());
        contenido.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        pNorte = new JPanel(new GridLayout(2, 1, 5, 5));
        pCentro = new JPanel(new FlowLayout());
        pSur = new JPanel(new FlowLayout());

        cbTipo = new JComboBox(tipoObjetos);
        JLabel titulo = new JLabel("<html><h2>Nueva entrada</h2>");
        titulo.setIcon(new ImageIcon("drawables/ic_hospitalHistory.png"));
        pNorte.add(titulo);

        JPanel pTipoEntrada = new JPanel(new FlowLayout());
        pTipoEntrada.add(new JLabel("Seleccione el tipo de entrada:"));
        pTipoEntrada.add(cbTipo);
        pNorte.add(pTipoEntrada);

        pCentro = new JPanel(new CardLayout());
        pVisita = new PanelNewVisita();
        pPaciente = new PanelNewPacient();
        pMedico = new PanelNewMetge();
        pMalaltia = new PanelNewMalaltia();

        pCentro.add("Visita", pVisita);
        pCentro.add("Paciente", pPaciente);
        pCentro.add("Medico", pMedico);
        pCentro.add("Enfermedad", pMalaltia);

        pCentro.setBorder(BorderFactory.createTitledBorder("Formulario"));

        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");
        pSur.add(btnAceptar);
        pSur.add(btnCancelar);

        contenido.add(pNorte, BorderLayout.NORTH);
        contenido.add(pCentro, BorderLayout.CENTER);
        contenido.add(pSur, BorderLayout.SOUTH);

        this.add(contenido);
        this.pack();
    }

    private void asignaMetodos() {
        c = new ControladorAnadir(this);
    }

    public JComboBox getCbTipo() {
        return cbTipo;
    }

    public JButton getBtnAceptar() {
        return btnAceptar;
    }

    public JButton getBtnCancelar() {
        return btnCancelar;
    }

    public JPanel getpCentro() {
        return pCentro;
    }

    public JPanel getpVisita() {
        return pVisita;
    }

    public JPanel getpMedico() {
        return pMedico;
    }

    public JPanel getpPaciente() {
        return pPaciente;
    }

    public JPanel getpMalaltia() {
        return pMalaltia;
    }
}
