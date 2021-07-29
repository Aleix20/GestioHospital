package m03.uf5.p01.grup06.gestiohospital.controlador;

import java.awt.CardLayout;
import java.awt.event.*;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import m03.uf5.p01.grup06.gestiohospital.DAO.MalaltiaDAO;
import m03.uf5.p01.grup06.gestiohospital.DAO.MetgeDAO;
import m03.uf5.p01.grup06.gestiohospital.DAO.PacienteDAO;
import m03.uf5.p01.grup06.gestiohospital.DAO.VisitaDAO;
import m03.uf5.p01.grup06.gestiohospital.modelo.*;
import m03.uf5.p01.grup06.gestiohospital.vista.*;

public class ControladorAnadir implements ActionListener {

    private final PaginaAnadir pagina;

    public ControladorAnadir(PaginaAnadir pagina) {
        this.pagina = pagina;
        asignaComponentes();
        System.out.println("[INFO]: Controlador añadir creado.");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getActionCommand().equals("ComboBoxTipo")) {
            String tipoSeleccionado = pagina.getCbTipo().getSelectedItem().toString();
            CardLayout cl = (CardLayout) (pagina.getpCentro().getLayout());
            cl.show(pagina.getpCentro(), tipoSeleccionado);

            System.out.println("[INFO]: Frame cambiado a " + tipoSeleccionado);
        }

        if (ae.getActionCommand().equals("btnCancelar")) {
            System.out.println("[INFO]: Boton cancelar pulsado");
            if (showWaringCloseMessage()) {
                pagina.dispose();
            }
        }

        if (ae.getActionCommand().equals("btnAceptar")) {
            System.out.println("[INFO]: Boton aceptar pulsado");

            int tipoSeleccionado = pagina.getCbTipo().getSelectedIndex();
            boolean creacionCorrecta = false;

            switch (tipoSeleccionado) {
                case 0:
                    creacionCorrecta = createVisita();
                    break;
                case 1:
                    creacionCorrecta = createPacient();
                    break;
                case 2:
                    creacionCorrecta = createMetge();
                    break;
                case 3:
                    creacionCorrecta = createMalaltia();
                    break;
            }
            if (creacionCorrecta) {
                showConfirmationMessage("Añadido correctamente", "La entidad ha sido creada con exito!");
                System.out.println("[INFO]: Entidad creada con exito.");
                pagina.dispose();
            }
        }
    }

    private void asignaComponentes() {

        pagina.getCbTipo().setActionCommand("ComboBoxTipo");
        pagina.getCbTipo().addActionListener(this);

        pagina.getBtnAceptar().setActionCommand("btnAceptar");
        pagina.getBtnAceptar().addActionListener(this);

        pagina.getBtnCancelar().setActionCommand("btnCancelar");
        pagina.getBtnCancelar().addActionListener(this);
        
        onlyAllowNumbers(((PanelNewPacient) pagina.getpPaciente()).getTfCP());
        onlyAllowNumbers(((PanelNewPacient) pagina.getpPaciente()).getTfNum());

        onlyAllowNumbers(((PanelNewMetge) pagina.getpMedico()).getTfCP());
        onlyAllowNumbers(((PanelNewMetge) pagina.getpMedico()).getTfNum());
        onlyAllowNumbers(((PanelNewMetge) pagina.getpMedico()).getTfNumEmpleat());
        onlyAllowNumbers(((PanelNewMetge) pagina.getpMedico()).getTfSalari());

        onlyAllowNumbers(((PanelNewMalaltia) pagina.getpMalaltia()).getTfDurada());
        onlyAllowNumbers(((PanelNewMalaltia) pagina.getpMalaltia()).getTfCodi());
    }

    private boolean createVisita() {
        try {
            PanelNewVisita p = (PanelNewVisita) pagina.getpVisita();
            
            String malOption = p.getCbEnfermedad().getSelectedItem().toString();
            int idMal = Integer.parseInt(String.valueOf(malOption.charAt(1)));
            Malaltia m = MalaltiaDAO.getMalaltiesByCodi(idMal);
            
            String pcntOption = p.getCbPacient().getSelectedItem().toString();
            String dniPcnt = pcntOption.substring(1, 10);
            Pacient pcnt = PacienteDAO.getPacientByNif(dniPcnt);
            
            String mtgOption = p.getCbMetge().getSelectedItem().toString();
            String dniMtg = mtgOption.substring(1, 10);
            Metge mtg = MetgeDAO.getMetgeByDNI(dniMtg);
            
            LocalDateTime t = LocalDateTime.now();
            
            Visita v = new Visita(t, m, mtg, pcnt.getNif(), pcnt.getNumSegSocial());
            System.out.println("[INFO]: Visita creada: " + v);
            
            return VisitaDAO.createVisita(v);
        } catch (NumberFormatException e) {
            showErrorMessage(" Error", "Llena todos los campos.");
            return false;
        } catch (Exception e) {
            showErrorMessage(" Error", e.getMessage());
            return false;
        }
    }

    private boolean createPacient() {
        try {
            PanelNewPacient p = (PanelNewPacient) pagina.getpPaciente();
            String nom = p.getTfNom().getText();
            String cognom1 = p.getTfApellido1().getText();
            String cognom2 = p.getTfApellido2().getText();
            String numSegSocial = p.getTfNumSS().getText();
            String nif = p.getTfDNI().getText().toUpperCase();
            String telefon = p.getTfTelf().getText();

            String ciutat = p.getTfCiutat().getText();
            Long codiPostal = Long.parseLong(p.getTfCP().getText());
            String carrer = p.getTfCalle().getText();
            int numero = Integer.parseInt(p.getTfNum().getText());
            String planta = p.getTfPlanta().getText();
            String porta = p.getTfPuerta().getText();

            Adreca adreca = new Adreca(ciutat, codiPostal, carrer, numero, planta, porta);

            Pacient pcnt = new Pacient(nom, cognom1, cognom2, numSegSocial, nif, telefon, adreca);
            System.out.println("[INFO]: Pacient creat: " + pcnt);

            return PacienteDAO.createPaciente(pcnt);
            
        } catch (NumberFormatException e) {
            showErrorMessage(" Error", "Llena todos los campos.");
            return false;
        } catch (SQLException | IllegalArgumentException e) {
            showErrorMessage(" Error", e.getMessage());
            return false;
        }
    }

    private boolean createMetge() {
        try {
            PanelNewMetge p = (PanelNewMetge) pagina.getpMedico();
            String nom = p.getTfNom().getText();
            String cognom1 = p.getTfApellido1().getText();
            String cognom2 = p.getTfApellido2().getText();
            String numSegSocial = p.getTfNumSS().getText();
            String nif = p.getTfDNI().getText().toUpperCase();
            String telefon = p.getTfTelf().getText();
            int numEmpleat = Integer.parseInt(p.getTfNumEmpleat().getText());
            int salari = Integer.parseInt(p.getTfSalari().getText());
            String compteCorrent = p.getTfCompteCorrent().getText();

            String ciutat = p.getTfCiutat().getText();
            Long codiPostal = Long.parseLong(p.getTfCP().getText());
            String carrer = p.getTfCalle().getText();
            int numero = Integer.parseInt(p.getTfNum().getText());
            String planta = p.getTfPlanta().getText();
            String porta = p.getTfPuerta().getText();

            Adreca adreca = new Adreca(ciutat, codiPostal, carrer, numero, planta, porta);

            Metge mtg = new Metge(nom, cognom1, cognom2, numSegSocial, nif, telefon, adreca, numEmpleat, salari, compteCorrent);
            System.out.println("[INFO]: Metge creat: " + mtg);

            return MetgeDAO.createMetge(mtg);
        } catch (NumberFormatException e) {
            showErrorMessage(" Error", "Llena todos los campos.");
            return false;
        } catch (SQLException | IllegalArgumentException e) {
            showErrorMessage(" Error", e.getMessage());
            return false;
        }
    }

    private boolean createMalaltia() {
        try {
            PanelNewMalaltia p = (PanelNewMalaltia) pagina.getpMalaltia();
            int codi = Integer.parseInt(p.getTfCodi().getText());
            String name = p.getTfNom().getText();
            String tractament = p.getTfTractament().getText();
            Duration duracion = Duration.ofDays(Integer.parseInt(p.getTfDurada().getText()));
            Boolean causaBaixa = p.getCbBaixa().isSelected();

            Malaltia m = new Malaltia(codi, name, causaBaixa, tractament, duracion);
            System.out.println("[INFO]: Malaltia creada: [" + m.getCodi() + "] " + m.getNom());
            return MalaltiaDAO.createMalaltia(m);
        } catch (NumberFormatException e) {
            showErrorMessage(" Error", "Llena todos los campos.");
            return false;
        } catch (SQLException | IllegalArgumentException e) {
            showErrorMessage(" Error", e.getMessage());
            return false;
        }
    }

    KeyAdapter charLisener;
    private void onlyAllowNumbers(JTextField txt) {
        charLisener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        };
        txt.addKeyListener(charLisener);
        if (!(txt.getText().matches("[0-9]"))) {
            txt.setText(txt.getText().replaceAll("[^0-9]", ""));
        }
        txt.setToolTipText("Inserta solamente valores numericos.");
    }

    private boolean showWaringCloseMessage() {
        Object[] options = {"Continuar aqui", "Salir sin guardar"};
        int n = JOptionPane.showOptionDialog(pagina,
                "Si cancela la operacion los datos del formulario se perderan.\nQuieres salir sin guardar?",
                "Salir sin guardar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
        return n == JOptionPane.YES_NO_CANCEL_OPTION;
    }

    private boolean showNullErrorMessage(Object obj, String objName) {
        if (obj == null) {
            String msg = "No se ha encontrado ningun " + objName + " con los datos insertado.";
            JOptionPane.showMessageDialog(pagina, msg, "Instancia no encontrada", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }

    private void showErrorMessage(String titulo, String msg) {
        JOptionPane.showMessageDialog(pagina, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }

    private void showConfirmationMessage(String titulo, String msg) {
        JOptionPane.showMessageDialog(pagina, msg, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
}
