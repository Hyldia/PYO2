/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controlador;

import logicadenegocios.UnidadLogica;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Controlador {
  private UnidadLogica unidad;
  
  public Controlador(){
    unidad = new UnidadLogica();
  }
  
  //Gets
  public String getinformacionUnidad() {
    return unidad.toString();
  }
  
  public void mostrarContenido(){
    System.out.println("Contenido de la unidad:");
    List<String> archivos = unidad.getContenido(unidad.getNombre());
    if (archivos.isEmpty()) {
       System.out.println("No hay archivos o directorios en la unidad.");
    }else{
      for (String archivo : archivos) {
        System.out.println(archivo);
      }
    }
  }
  
  
}

