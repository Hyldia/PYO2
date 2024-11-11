/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package logicadenegocios;

import java.io.File;

/**
 *
 * @author Usuario
 */
public class UnidadLogica {
  private String ruta;
  private File unidad;
  
  /**
   * Contructor Sis e ingresa una ruta incia en la unidad de la ruta especificada
   * Si no incia en la unidad C: de forma predeterminada
   * @param pRuta 
   */
  public UnidadLogica(String pRuta){
    pRuta = ruta;
    if (ruta == null || ruta.isEmpty()) {
      unidad = new File("c:/");
    }else{
      unidad = new File(ruta);
    }
  }
  
  /**
   * Convierte de bytes a gigabytes
   * @param bytes
   * @return El equivalente en gigabytes
   */
  public static double bytesAGigabytes(long bytes){
    return (double) bytes /(1024 * 1024 * 1024);
  }
  
  //Metodos accesores
  public String getNombre(){
    return unidad.getPath();
  }
  
  public long getEspacioTotal(){
    return unidad.getTotalSpace();
  }
  
  public long getEspacioLibre(){
    return unidad.getFreeSpace();
  }
  
  public long getEspacioUsado(){
    return getEspacioTotal() - getEspacioLibre();
  }
  
  /**
   * Muestra una cadena de carateres con la información de la unidad
   * @return Información de la unidad logica
   */
  @Override
  public String toString() {
    String info = "Nombre de la unidad: " + getNombre() + "\n";
    info += "Capacidad total: " + bytesAGigabytes(getEspacioTotal()) + "GB" + "\n";
    info += "Espacio libre: " + bytesAGigabytes(getEspacioLibre()) + "gb" + "\n";
    info += "Espacio Usado: " + bytesAGigabytes(getEspacioUsado()) + "GB" + "\n";
    return info;
  }
}

  
