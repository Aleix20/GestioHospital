package m03.uf5.p01.grup06.gestiohospital.controlador;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import m03.uf5.p01.grup06.gestiohospital.DAO.MalaltiaDAO;
import m03.uf5.p01.grup06.gestiohospital.DAO.MetgeDAO;
import m03.uf5.p01.grup06.gestiohospital.DAO.PacienteDAO;
import m03.uf5.p01.grup06.gestiohospital.DAO.VisitaDAO;
import m03.uf5.p01.grup06.gestiohospital.modelo.*;
import m03.uf5.p01.grup06.gestiohospital.utils.CellRender;
import m03.uf5.p01.grup06.gestiohospital.utils.ResultSetModelTableData;
import m03.uf5.p01.grup06.gestiohospital.vista.*;

public class ControladorBusqueda implements ActionListener {

    private final PaginaInicio ventana1;
    private final Hospital h1;
    private final JTable tblDades;
    private KeyListener dniListener, numbersListener;
    private String tablaActual;

    public ControladorBusqueda(PaginaInicio ventanaInicio, Hospital h1) {
        this.ventana1 = ventanaInicio;
        this.h1 = h1;
        this.tblDades = ventana1.getTblDatpos();
        asignarComponentes();
    }

    private void asignarComponentes() {
        ventana1.getBtnBuscar().setActionCommand("btnBuscar");
        ventana1.getBtnBuscar().addActionListener(this);

        ventana1.getBtnNuevo().setActionCommand("btnNuevo");
        ventana1.getBtnNuevo().addActionListener(this);

        ventana1.getCbTipoDato().setActionCommand("cbTipoDato");
        ventana1.getCbTipoDato().addActionListener(this);

        ventana1.getCbTipoId().setActionCommand("cbTipoId");
        ventana1.getCbTipoId().addActionListener(this);

        ventana1.getChkFiltrar().setActionCommand("chkFiltrar");
        ventana1.getChkFiltrar().addActionListener(this);

        onlyAllowNumbers(ventana1.getTfBuscar());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("btnBuscar")) {
            buscaContenido();
        }

        if (e.getActionCommand().equals("btnNuevo")) {
            ventanaNuevo();
        }

        if (e.getActionCommand().equals("cbTipoDato")) {
            int indice = ventana1.getCbTipoDato().getSelectedIndex();
            cambiaIds(indice);
        }

        if (e.getActionCommand().equals("cbTipoId")) {
            int index = ventana1.getCbTipoId().getSelectedIndex();
            ventana1.getTfBuscar().setText("");
            ventana1.getTfBuscar().removeKeyListener(numbersListener);
            ventana1.getTfBuscar().removeKeyListener(dniListener);
            if (index == 1) {
                allowDni(ventana1.getTfBuscar());
            } else {
                onlyAllowNumbers(ventana1.getTfBuscar());
            }
        }

        if (e.getActionCommand().equals("chkFiltrar")) {
            boolean enabled = ventana1.getChkFiltrar().isSelected();
            ventana1.getBtnBuscar().setEnabled(enabled);
            ventana1.getCbTipoId().setEnabled(enabled);
            ventana1.getTfBuscar().setEnabled(enabled);
            ventana1.getLblFiltros().setEnabled(enabled);
        }
    }

    public void cambiaIds(int indice) {
        switch (indice) {
            case 0:
                ventana1.getCbTipoId().removeAllItems();
                for (int i = 0; i < ventana1.getIdsEnfermedad().length; i++) {
                    ventana1.getCbTipoId().addItem(ventana1.getIdsEnfermedad()[i]);
                }
                loadAllData("Malaltia");
                tablaActual="Malaltia";
                break;
            case 1:
                ventana1.getCbTipoId().removeAllItems();
                for (int i = 0; i < ventana1.getIdsHistorial().length; i++) {
                    ventana1.getCbTipoId().addItem(ventana1.getIdsHistorial()[i]);
                }
                loadAllData("Visita");
                 tablaActual="Visita";
                break;
            case 2:
                ventana1.getCbTipoId().removeAllItems();
                for (int i = 0; i < ventana1.getIdsMedico().length; i++) {
                    ventana1.getCbTipoId().addItem(ventana1.getIdsMedico()[i]);
                }
                loadAllData("Metge");
                 tablaActual="Metge";
                break;
            case 3:
                ventana1.getCbTipoId().removeAllItems();
                for (int i = 0; i < ventana1.getIdsPaciente().length; i++) {
                    ventana1.getCbTipoId().addItem(ventana1.getIdsPaciente()[i]);
                }
                loadAllData("Pacient");
                 tablaActual="Pacient";
                break;
            default:
                ventana1.getCbTipoId().removeAllItems();
        }
    }

    private void actualitzaTaula(JTable taula, TableModel model) {
        taula.getTableHeader().setDefaultRenderer(new CellRender("Header"));
        taula.setModel(model);
        CellRender renderizador = new CellRender();
        for (int i = 0; i < taula.getColumnCount(); i++) {

            taula.getColumnModel().getColumn(i).setCellRenderer(renderizador);
        }

        tblDades.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                actualitzaTaulaUpdate(e);
            }
        });
     }
     
     
     private void actualitzaTaulaUpdate(TableModelEvent e){
        System.out.println("MODIFICACION "+ tablaActual);
        int fila = e.getFirstRow();
        
        PreparedStatement sentencia = null;
        Connection con = null;
        TableModel dades = tblDades.getModel();
        
        if(tablaActual.equals("Malaltia")){
            MalaltiaDAO.updateMalaltia(new Malaltia(
                    Integer.parseInt(dades.getValueAt(fila, 0).toString()), 
                    dades.getValueAt(fila, 1).toString(),
                    (String.valueOf(dades.getValueAt(fila, 2)).toUpperCase().equals("SI")),
                    dades.getValueAt(fila, 3).toString(),
                    Duration.ofDays(Integer.parseInt(dades.getValueAt(fila, 4).toString()))));
            
        }else if(tablaActual.equals("Metge")){
            
            MetgeDAO.updateMetge(new Metge(dades.getValueAt(fila, 2).toString(), 
                    dades.getValueAt(fila, 3).toString(), dades.getValueAt(fila, 4).toString(),
                    dades.getValueAt(fila, 5).toString(), dades.getValueAt(fila, 1).toString(),
                    dades.getValueAt(fila, 6).toString(), new Adreca(dades.getValueAt(fila, 9).toString(), 
                            Long.parseLong(dades.getValueAt(fila, 10).toString()), 
                            dades.getValueAt(fila, 11).toString(), 
                            Integer.parseInt(dades.getValueAt(fila, 12).toString()),dades.getValueAt(fila, 13).toString()
                            ,dades.getValueAt(fila, 14).toString()), Integer.parseInt(dades.getValueAt(fila, 0).toString()),
                            Integer.parseInt(dades.getValueAt(fila, 7).toString()), dades.getValueAt(fila, 8).toString()));
            
        }else {
            PacienteDAO.modificaPacient(new Pacient(dades.getValueAt(fila, 2).toString(),
            dades.getValueAt(fila, 3).toString(), dades.getValueAt(fila, 4).toString(), 
                    dades.getValueAt(fila, 5).toString(),dades.getValueAt(fila, 0).toString(),
            dades.getValueAt(fila, 6).toString(), new Adreca(dades.getValueAt(fila, 7).toString(), 
                            Long.parseLong(dades.getValueAt(fila, 8).toString()), 
                            dades.getValueAt(fila, 9).toString(), 
                            Integer.parseInt(dades.getValueAt(fila, 10).toString()),dades.getValueAt(fila, 11).toString()
                            ,dades.getValueAt(fila, 12).toString())));
            
        }
    }

    public void buscaContenido() {
        int tipoDato = ventana1.getCbTipoDato().getSelectedIndex();
        int tipoId = ventana1.getCbTipoId().getSelectedIndex();
        String dato = ventana1.getTfBuscar().getText();

        try {
            ResultSet rsDatos = null;
            switch (tipoDato) {
                case 0: // MALALTIA
                    rsDatos = MalaltiaDAO.getMalaltiesByCodiRS(Integer.parseInt(dato));
                    break;
                case 1: // VISITAS
                    switch (tipoId) {
                        case 0:
                            rsDatos = VisitaDAO.getVisitaByCodiHistorialRS(Integer.parseInt(dato));
                            break;
                        case 1:
                            rsDatos = VisitaDAO.getVisitaByDNIRS(dato);
                            break;
                    }
                    break;
                case 2: // Metge
                    switch (tipoId) {
                        case 0:
                            rsDatos = MetgeDAO.getMetgeBySSRS(dato);
                            break;
                        case 1:
                            rsDatos = MetgeDAO.getMetgeByDNIRS(dato);
                            break;
                    }
                    break;
                case 3: // Pacient
                    switch (tipoId) {
                        case 0:
                            rsDatos = PacienteDAO.pacienteByCodiHistorial(Integer.parseInt(dato));
                            break;
                        case 1:
                            rsDatos = PacienteDAO.pacienteByNifRS(dato);
                            break;
                        case 2:
                            rsDatos = PacienteDAO.pacienteByNSS(dato);
                            break;
                    }
                    break;
            }
            if (rsDatos != null) {
                ResultSetModelTableData model = new ResultSetModelTableData(rsDatos, tablaActual);

                actualitzaTaula(tblDades, model);

            }
        } catch (NumberFormatException e) {
            showErrorMessage("Campos vacios", "No deje el formulario en blanco");
        } catch (NullPointerException e) {
            showErrorMessage("Error de Busqueda", "Información no existente.");
        } catch (SQLException e) {
            showErrorMessage("Error de base de datos", e.getMessage());
        }
    }

    public void ventanaNuevo() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaginaAnadir(h1).setVisible(true);
            }
        });
    }

    private void onlyAllowNumbers(JTextField txt) {
        numbersListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        };
        txt.addKeyListener(numbersListener);
    }

    private void allowDni(JTextField txt) {
        dniListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') || ((c >= 'A') && (c <= 'Z')) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();
                }
            }
        };
        txt.addKeyListener(dniListener);
    }

    private void showErrorMessage(String titulo, String msg) {
        JOptionPane.showMessageDialog(ventana1, msg, titulo, JOptionPane.ERROR_MESSAGE);
    }

    private void loadAllData(String tipo) {
        try {
            ResultSet dadesRS = null;
            switch (tipo) {
                case "Malaltia":
                    dadesRS = MalaltiaDAO.getAllMalaltiesRS();
                    break;
                case "Metge":
                    dadesRS = MetgeDAO.getAllMetgesRS();
                    break;
                case "Pacient":
                    dadesRS = PacienteDAO.getAllPacientsRS();
                    break;
                case "Visita":
                    dadesRS = VisitaDAO.getAllVisitesRS();
                    break;
            }
            if (dadesRS != null) {
                ResultSetModelTableData model = new ResultSetModelTableData(dadesRS, tablaActual);

                actualitzaTaula(tblDades, model);

            }
        } catch (Exception ex) {
            showErrorMessage("ERROR", ex.getMessage());
        }
    }
}
