package m03.uf5.p01.grup06.gestiohospital.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CellRender extends DefaultTableCellRenderer {

    private final String tipo;
    private final Font normal = new Font("Arial", Font.PLAIN, 14);
    private final Font bold = new Font("Arial", Font.BOLD, 14);

    public CellRender() {
        this.tipo = "default";
    }

    public CellRender(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {

        this.setText((String) value);
        this.setForeground(new Color(0, 0, 0));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(normal);

        if (selected) {
            this.setBackground(new Color(244, 244, 209));
            this.setFont(bold);
        } else {
            if (row % 2 == 0) {
                this.setBackground(Color.WHITE);

            } else {
                this.setBackground(new Color(204, 255, 255));
            }
        }

        if (tipo.equals("Header")) {
            this.setText(((String) value).toUpperCase());
            this.setFont(bold);
            this.setBackground(new Color(0, 128, 128));
            this.setForeground(Color.WHITE);
            return this;
        }

        if (tipo.equals("dni")) {
            this.setText(((String) value).toUpperCase());
            this.setForeground(Color.DARK_GRAY);
            return this;
        }

        if (((String) value).toUpperCase().equals("SI")) {
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setText((String) value);
            this.setBackground(new Color(153, 255, 153));
            return this;
        } else if (((String) value).toUpperCase().equals("NO")) {
            this.setHorizontalAlignment(JLabel.CENTER);
            this.setText((String) value);
            this.setBackground(new Color(255,102,102));
            return this;
        }

        return this;
    }
}
