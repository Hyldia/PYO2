/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.io.File;
import controlador.Controlador;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author deywe
 */
public class AbrirDirectorio extends javax.swing.JFrame {

  private Controlador controlador;
  private File directorioActual;
  private DefaultTableModel modelo;
  private File[] archivos;

  /**
   * Creates new form AbrirDirectorio
   */
  public AbrirDirectorio() {
    initComponents();
    setResizable(false);
    controlador = new Controlador();

    String[] columnas = {"Tipo", "Nombre", "Tamaño", "Fecha de Creación"};
    modelo = new DefaultTableModel(null, columnas) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false; // Celdas no editables
      }
    };
    tablaDeArchivos.setModel(modelo);
    tablaDeArchivos.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
          int filaSeleccionada = tablaDeArchivos.getSelectedRow();
          if (filaSeleccionada >= 0) {
            String tipo = (String) modelo.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modelo.getValueAt(filaSeleccionada, 1);
            if (tipo.equals("Directorio")) {
              try {
                cambiarDirectorio(new File(directorioActual, nombre));
              } catch (Exception ex) {
                Logger.getLogger(AbrirDirectorio.class.getName()
                ).log(Level.SEVERE, null, ex);
              }
            }
          }
        }
      }
    });

    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
    tablaDeArchivos.setRowSorter(sorter);
    tablaDeArchivos.getTableHeader().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        int column = tablaDeArchivos.columnAtPoint(e.getPoint());
        if (e.getClickCount() == 2) {
          if (column == 3) {
            try {
              ordenarPorFechaDeCreacion();
            } catch (Exception ex) {
              Logger.getLogger(AbrirDirectorio.class.getName()
              ).log(Level.SEVERE, null, ex);
            }
          }
        }
      }
    });
    comboBox.removeAllItems();
    listarUnidadesLogicas();
    comboBox.setSelectedIndex(0);
  }

  public final void ordenarPorFechaDeCreacion() throws Exception {
    System.out.println("Entre");
    File[] archivosDirectorio = controlador.conseguirListaArchivos(directorioActual);

    // Ordenar usando Arrays.sort basado en el año de la fecha de creación
    Arrays.sort(archivosDirectorio, (a, b) -> {
      try {
        String fechaA = controlador.getFechaCreacionArchivo(a);
        String fechaB = controlador.getFechaCreacionArchivo(b);

        if (fechaA == null || fechaB == null || fechaA.isEmpty() || fechaB.isEmpty()) {
          return 0; // Si alguna fecha es inválida, no se altera el orden
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        int añoA = LocalDateTime.parse(fechaA, formatter).getYear();
        int añoB = LocalDateTime.parse(fechaB, formatter).getYear();

        return Integer.compare(añoA, añoB);
      } catch (Exception e) {
        e.printStackTrace();
        return 0; // En caso de error, no se altera el orden
      }
    });

    // Reinicializar la tabla
    modelo.setRowCount(0);
    for (File arch : archivosDirectorio) {
      String tipo = arch.isDirectory() ? "Directorio" : "Archivo";
      String tamaño = arch.isFile() ? String.format("%.2f MB", arch.length() / (1024.0 * 1024.0)) : "N/A";
      String fechaCreacion = controlador.getFechaCreacionArchivo(arch);
      modelo.addRow(new Object[]{tipo, arch.getName(), tamaño, fechaCreacion});
    }
  }

  public final void listarUnidadesLogicas() {
    archivos = controlador.listarUnidadesLogicas();
    for (File archivo : archivos) {
      comboBox.addItem(archivo.getAbsolutePath());
      comboBox.addActionListener(e -> {
        try {
          cambiarDirectorio(
             new File((String) comboBox.getSelectedItem()));
        } catch (Exception ex) {

        }
      });
    }
  }

  public final void cambiarDirectorio(File pNuevoDirectorio) throws Exception {
    if (pNuevoDirectorio.isDirectory()) {
      directorioActual = pNuevoDirectorio;
      modelo.setRowCount(0);

      File[] archivosDirectorio = controlador.conseguirListaArchivos(
         pNuevoDirectorio);
      for (File arch : archivosDirectorio) {
        String tipo = arch.isDirectory() ? "Directorio" : "Archivo";
        String tamaño = arch.isFile() ? String.format("%.2f MB",
           arch.length() / (1024.0 * 1024.0)) : "N/A";
        String fechaCreacion = controlador.getFechaCreacionArchivo(arch);
        /*ImageIcon icono = arch.isDirectory() ? new ImageIcon(getClass(
        ).getResource("/icons/folder.png")) : new ImageIcon(getClass(
        ).getResource("/icons/file.png"));*/
        modelo.addRow(new Object[]{tipo, arch.getName(), tamaño,
          fechaCreacion});
      }
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tablaDeArchivos = new javax.swing.JTable();
    comboBox = new javax.swing.JComboBox<>();
    jLabel1 = new javax.swing.JLabel();
    botonRetroceder = new javax.swing.JButton();
    crearDirectorio = new javax.swing.JButton();
    consultarInfo = new javax.swing.JButton();
    eliminar = new javax.swing.JButton();
    copiar = new javax.swing.JButton();
    abrirArchivo = new javax.swing.JButton();
    consultarPropiedades = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    salir = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setBackground(new java.awt.Color(204, 204, 255));

    tablaDeArchivos.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Title 1", "Title 2", "Title 3", "Title 4"
      }
    ));
    jScrollPane1.setViewportView(tablaDeArchivos);

    comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    comboBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        comboBoxActionPerformed(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel1.setText("Unidad Lógica");

    botonRetroceder.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    botonRetroceder.setText("Retroceder");
    botonRetroceder.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonRetrocederActionPerformed(evt);
      }
    });

    crearDirectorio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    crearDirectorio.setText("Crear directorio");
    crearDirectorio.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        crearDirectorioActionPerformed(evt);
      }
    });

    consultarInfo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    consultarInfo.setText("Consultar información");
    consultarInfo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        consultarInfoActionPerformed(evt);
      }
    });

    eliminar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    eliminar.setText("Eliminar");
    eliminar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        eliminarActionPerformed(evt);
      }
    });

    copiar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    copiar.setText("Copiar");
    copiar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        copiarActionPerformed(evt);
      }
    });

    abrirArchivo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    abrirArchivo.setText("Abrir");
    abrirArchivo.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        abrirArchivoActionPerformed(evt);
      }
    });

    consultarPropiedades.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    consultarPropiedades.setText("Consultar propiedades");
    consultarPropiedades.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        consultarPropiedadesActionPerformed(evt);
      }
    });

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel2.setText("Archivos y Directorios");

    salir.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    salir.setText("Salir");
    salir.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        salirActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGap(291, 291, 291)
            .addComponent(botonRetroceder)))
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel1)
                  .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(4, 4, 4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                      .addComponent(copiar)
                      .addComponent(abrirArchivo)))))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(consultarPropiedades))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(crearDirectorio))
              .addGroup(jPanel1Layout.createSequentialGroup()
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(consultarInfo)))
            .addContainerGap(18, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(salir)
            .addGap(82, 82, 82))))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(botonRetroceder, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(19, Short.MAX_VALUE))
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(26, 26, 26)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(consultarPropiedades, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(jLabel2)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(abrirArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(copiar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(12, 12, 12)
        .addComponent(crearDirectorio)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(consultarInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(salir, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(29, 29, 29))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void botonRetrocederActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRetrocederActionPerformed
    if (directorioActual != null && directorioActual.getParentFile() != null) {
      try {
        cambiarDirectorio(directorioActual.getParentFile());
      } catch (Exception ex) {
        Logger.getLogger(AbrirDirectorio.class.getName()
        ).log(Level.SEVERE, null, ex);
      }
    }
  }//GEN-LAST:event_botonRetrocederActionPerformed

  private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_comboBoxActionPerformed

  private void crearDirectorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearDirectorioActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_crearDirectorioActionPerformed

  private void consultarInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarInfoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_consultarInfoActionPerformed

  private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_eliminarActionPerformed

  private void copiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copiarActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_copiarActionPerformed

  private void abrirArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirArchivoActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_abrirArchivoActionPerformed

  private void consultarPropiedadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarPropiedadesActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_consultarPropiedadesActionPerformed

  private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
    System.exit(0);
  }//GEN-LAST:event_salirActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(AbrirDirectorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(AbrirDirectorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(AbrirDirectorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(AbrirDirectorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new AbrirDirectorio().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton abrirArchivo;
  private javax.swing.JButton botonRetroceder;
  private javax.swing.JComboBox<String> comboBox;
  private javax.swing.JButton consultarInfo;
  private javax.swing.JButton consultarPropiedades;
  private javax.swing.JButton copiar;
  private javax.swing.JButton crearDirectorio;
  private javax.swing.JButton eliminar;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JButton salir;
  private javax.swing.JTable tablaDeArchivos;
  // End of variables declaration//GEN-END:variables
}