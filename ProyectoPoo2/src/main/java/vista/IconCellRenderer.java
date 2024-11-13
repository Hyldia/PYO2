/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author deywe
 */
public class IconCellRenderer extends DefaultTableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(JTable table, Object value,
     boolean isSelected, boolean hasFocus, int row, int column) {
    if (value instanceof ImageIcon) {
      JLabel label = new JLabel((ImageIcon) value);
      label.setHorizontalAlignment(JLabel.CENTER);
      return label;
    }
    return super.getTableCellRendererComponent(table, value, isSelected,
       hasFocus, row, column);
  }
}
