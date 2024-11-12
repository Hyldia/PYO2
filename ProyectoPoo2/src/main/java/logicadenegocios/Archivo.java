/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicadenegocios;

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
  
  public Archivo(String pNombre, String pRuta, String pTipo) {
    nombre = pNombre;
    ruta = pRuta;
    tipo = pTipo;
    fechaCreacion = LocalDate.now();
  }
  
  public String eliminar() {
    return "hello";
  }
  
  public void copiarArchivo(String pRutaDestino) {
    
  }
  
  public LocalDate getFechaCreacion() {
    return fechaCreacion;
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
