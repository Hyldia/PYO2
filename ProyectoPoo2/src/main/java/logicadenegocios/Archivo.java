/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

/**
 * Clase que representa la abstracción de un Archivo
 * 
 * @author Hyldia T., Berenice A. & Deywenie S.
 */
public class Archivo {
  private String nombre;
  private String ruta;
  private String tipo;
  private String atributos;
  private LocalDate fechaCreacion;
  
  /**
   * Método constructor de la clase Archivo
   * 
   * @param pNombre
   * @param pRuta
   * @param pTipo 
   */
  public Archivo(String pNombre, String pRuta, String pTipo) {
    nombre = pNombre;
    ruta = pRuta;
    tipo = pTipo;
    fechaCreacion = LocalDate.now();
  }
  
  /**
   * Método constructor de la clase Archivo
   */
  public Archivo () {
    
  }
  
  public void copiarArchivo(String pRutaDestino) throws IOException {
    File archivo = new File(ruta);
    File destino = new File(pRutaDestino + File.separator + nombre);
    Files.copy(archivo.toPath(), destino.toPath());
  }
  
  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

  public void abrirArchivo(String ruta) throws IOException {
    if (Desktop.isDesktopSupported()) {
      Desktop desktop = Desktop.getDesktop();
      File archivo = new File(ruta);
      
      if (archivo.exists()) {
        try {
          desktop.open(archivo);
        }catch (IOException e){
          throw new IOException("No se puede abrir el archivo por un error en "
             + "el sistema.");
        }
      }else {
        throw new IOException("El archivo no existe en la ruta dada.");
      }
    }else {
      throw new IOException("El sistema no soporta abrir el archivo "
         + "seleccionado");
    }
  }
  
  @Override
  public String toString() {
    String info = "Nombre del archivo: " + nombre + "\n";
    info += "Tipo: " + tipo + "\n";
    info += "Ubicación: " + ruta + "\n";
    info += "Creación: " + fechaCreacion + "\n";
    return info;
  }
}
