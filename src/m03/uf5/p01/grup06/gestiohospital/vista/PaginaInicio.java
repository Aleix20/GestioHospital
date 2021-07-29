package m03.uf5.p01.grup06.gestiohospital.vista;

import java.awt.*;
import javax.swing.*;
import m03.uf5.p01.grup06.gestiohospital.controlador.ControladorBusqueda;
import m03.uf5.p01.grup06.gestiohospital.modelo.*;

public class PaginaInicio extends JFrame {

    private JPanel pPrincipal, pNorte, pCentro, pSur, pBusqueda;
    private JComboBox cbTipoDato, cbTipoId;
    private JLabel lblTitulo, lblFiltros;
    private javax.swing.JTable tblDatos;
    private JTextField tfBuscar;
    private JCheckBox chkFiltrar;
    private JButton btnBuscar, btnNuevo;
    private final String[] objetos = {"Enfermedades", "Visitas", "Medicos", "Pacientes"};
    private final String[] idsEnfermedad = {"Codigo"};
    private final String[] idsHistorial = {"Codigo", "DNI"};
    private final String[] idsMedico = {"NºSegSocial", "DNI"};
    private final String[] idsPaciente = {"Codigo", "DNI", "NºSegSocial"};
    private final Hospital hospital;

    public PaginaInicio(Hospital hospital) {
        this.hospital = hospital;
        this.setTitle("Gestor hospital");
        ImageIcon i = new ImageIcon("drawables/ic_hospitalLauncher.png");
        this.setIconImage(i.getImage());
        CreaGUI();
        crearEventos();
    }

    private void CreaGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1200, 600);
        this.setMinimumSize(new Dimension(1200, 600));

        pPrincipal = new JPanel();
        pPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pNorte = new JPanel();
        pCentro = new JPanel();
        pSur = new JPanel();
        pBusqueda = new JPanel(new GridLayout(2,1,10,10));
        pBusqueda.setBorder(BorderFactory.createTitledBorder("Opciones de Búsqueda: "));
        chkFiltrar = new JCheckBox("Filtrar datos");
        pPrincipal.setLayout(new BorderLayout());
        pNorte.setLayout(new GridLayout(0, 1));
        lblTitulo = new JLabel("<html><h1>Pantalla de Visualización<h1>");
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        cbTipoDato = new JComboBox(objetos);
        cbTipoDato.setPreferredSize(new Dimension(300, 30));
        cbTipoId = new JComboBox(idsEnfermedad);
        cbTipoId.setPreferredSize(new Dimension(100, 30));
        tfBuscar = new JTextField();
        tfBuscar.setPreferredSize(new Dimension(300, 30));
        
        JPanel pTabla = new JPanel(new BorderLayout());
        
        tblDatos = new JTable();
        tblDatos.getTableHeader().setReorderingAllowed(false);
        tblDatos.setFillsViewportHeight(true);
        tblDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        tblDatos.setPreferredScrollableViewportSize(tblDatos.getPreferredSize());
        tblDatos.setFillsViewportHeight(true);
        tblDatos.setRowHeight(24);
        
        pTabla.add(tblDatos, BorderLayout.CENTER);
        pTabla.add(tblDatos.getTableHeader(), BorderLayout.NORTH);         
 
        pCentro.setBorder(BorderFactory.createTitledBorder("Información Buscada: "));
        
        btnBuscar = new JButton("Filtrar");
        btnBuscar.setEnabled(false);
        btnNuevo = new JButton("Nuevo");
        
        JPanel pBarraBusqueda = new JPanel(new FlowLayout());
        JPanel pFiltros = new JPanel(new FlowLayout());
        pBarraBusqueda.add(new JLabel("Buscar en: "));
        pBarraBusqueda.add(cbTipoDato);
        pBarraBusqueda.add(chkFiltrar);
        lblFiltros = new JLabel("Filtrar por: ");
        lblFiltros.setEnabled(false);
        pFiltros.add(lblFiltros);
        tfBuscar.setEnabled(false);
        cbTipoId.setEnabled(false);
        pFiltros.add(tfBuscar);
        pFiltros.add(cbTipoId);
        pBusqueda.add(pBarraBusqueda);
        pBusqueda.add(pFiltros);
        
        pFiltros.add(tfBuscar);
        pFiltros.add(cbTipoId);
        pBusqueda.add(pFiltros);
        pNorte.add(lblTitulo);
        pNorte.add(pBusqueda);
        pCentro.add(pTabla);
        pSur.add(btnBuscar);
        pSur.add(btnNuevo);

        pPrincipal.add(pNorte, BorderLayout.NORTH);
        pPrincipal.add(pCentro, BorderLayout.CENTER);
        pPrincipal.add(pSur, BorderLayout.SOUTH);

        this.add(pPrincipal);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void crearEventos() {
        cbTipoDato.addActionListener(new ControladorBusqueda(this, hospital));
    }

    public JComboBox getCbTipoDato() {
        return cbTipoDato;
    }

    public JComboBox getCbTipoId() {
        return cbTipoId;
    }

    public JTextField getTfBuscar() {
        return tfBuscar;
    }

    public JTable getTblDatpos() {
        return tblDatos;
    }

    public String[] getObjetos() {
        return objetos;
    }

    public String[] getIdsEnfermedad() {
        return idsEnfermedad;
    }

    public String[] getIdsHistorial() {
        return idsHistorial;
    }

    public String[] getIdsMedico() {
        return idsMedico;
    }

    public String[] getIdsPaciente() {
        return idsPaciente;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public JCheckBox getChkFiltrar() {
        return chkFiltrar;
    }

    public JLabel getLblFiltros() {
        return lblFiltros;
    }
}
