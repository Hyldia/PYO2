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
  private LocalDate fechaCreacion;
  
  /**
   * Método constructor de la clase Archivo
   */
  public Archivo () {
    
  }
  
  /**
   * Método encargado de copiar un archivo a otro
   * 
   * @param pRutaDestino
   * @throws IOException 
   */
  public void copiarArchivo(String pRutaDestino) throws IOException {
    File archivo = new File(ruta);
    File destino = new File(pRutaDestino + File.separator + nombre);
    Files.copy(archivo.toPath(), destino.toPath());
  }

  /**
   * Método encargado de abrir el archivo seleccionado por el usuario
   * 
   * @param ruta
   * @throws IOException 
   */
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
  
  /**
   * Método encargado de devolver la un objeto de tipo archivo en cadena de texto
   * 
   * @return Una cadena de texto
   */
  @Override
  public String toString() {
    String info = "Nombre del archivo: " + nombre + "\n";
    info += "Tipo: " + tipo + "\n";
    info += "Ubicación: " + ruta + "\n";
    info += "Creación: " + fechaCreacion + "\n";
    return info;
  }
}
