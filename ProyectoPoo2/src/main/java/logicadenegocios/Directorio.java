/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


/**
 * Clase que representa la abstracción de un Directorio
 * 
 * @author Hyldia T., Berenice A. & Deywenie S.
 */
public class Directorio {
  
  private List<Archivo> archivos;
  private int tamaño;
   private LocalDate fechaCreacion;

   /**
    * Método constructor de la clase Directorio
    */
  public Directorio() {
    archivos = new ArrayList<>();
  }
  
  public void listarContenido() {
    
  }
  
  public void crearDirectorio(String pNombre) {
    
  }
  
  public void eliminarDirectorio() {
    
  }
  
  public String consultarPropiedades() {
    return "hello";
  }
  
  public void copiarDirectorio(String pRutaDestino) {
    
  }
  
  public LocalDate getFechaCreacion() {
    return fechaCreacion;
  }

}
