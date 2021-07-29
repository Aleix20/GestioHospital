package m03.uf5.p01.grup06.gestiohospital.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ResultSetModelTableData extends AbstractTableModel {

    private String tipoTabla;
    private final ArrayList<String> nomsColumnes;
    private final ArrayList<ArrayList> dadesFiles;

    public ResultSetModelTableData(ResultSet rs, String tipo) throws SQLException {
        super();
        nomsColumnes = new ArrayList<>();
        dadesFiles = new ArrayList<>();
        this.tipoTabla = tipo;

        ResultSetMetaData rsmd = rs.getMetaData();
        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            nomsColumnes.add(rsmd.getColumnLabel(i));
        }

        while (rs.next()) {
            ArrayList<String> datosFila = new ArrayList<>();

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                datosFila.add(rs.getString(i));
            }

            dadesFiles.add(datosFila);
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if(column == 0 || tipoTabla.equals("Visita")) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        ArrayList aux = dadesFiles.get(row);
        aux.set(col,(String)value);
        fireTableCellUpdated(row, col);
    }

    @Override
    public String getColumnName(int i) {
        return formatTitle(nomsColumnes.get(i));
    }

    @Override
    public int getRowCount() {
        return dadesFiles.size();
    }

    @Override
    public int getColumnCount() {
        return nomsColumnes.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return dadesFiles.get(rowIndex).get(columnIndex);
    }
    
    private String formatTitle(String title) {
        String formatedTitle = String.valueOf(title.charAt(0)).toUpperCase();
        
        for(int i = 1; i < title.length(); i++) {
            if (Character.isUpperCase(title.charAt(i))) {
                formatedTitle += " " + title.charAt(i);
            } else {
                formatedTitle += title.charAt(i);
            }
        }
        return formatedTitle;
    }   
}
