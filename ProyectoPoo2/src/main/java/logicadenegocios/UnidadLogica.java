/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package logicadenegocios;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa la abstracción de una Unidad Lógica
 * 
 * @author Hyldia T., Berenice A. & Deywenie S.
 */
public class UnidadLogica {
  private File unidad;
  
  /**
   * Contructor Sis e ingresa una ruta incia en la unidad de la ruta especificada
   * Si no incia en la unidad C: de forma predeterminada
   * 
   */
  public UnidadLogica(){
    unidad = new File("c:\\");
  }
  
  /**
   * Convierte de bytes a gigabytes
   * @param bytes
   * @return El equivalente en gigabytes
   */
  public static double bytesAGigabytes(long bytes){
    return bytes /(1024 * 1024 * 1024);
  }
  
  /**
   * Formatea los bytes en grupos de tres con espacios como separadores.
   * @param bytes
   * @return Los bytes formateados con separadores de espacios
   */
  public static String formaBytes(long bytes) {
    DecimalFormat formato = new DecimalFormat("###,###,###"); // Formato con separadores de miles
    return formato.format(bytes); // Devuelve el valor formateado
  }
  
  /**
   * 
   * @return 
   */
  public String getSistemaArchivos() {
    try {
      FileStore store = Files.getFileStore(Paths.get(unidad.getPath()));
      return store.type();
    }catch (Exception e) {
      return "Desconocido";
    }
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
  
  
  public String getFechaCreacion(File contenido) {
    long fecha = contenido.lastModified();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    return sdf.format(fecha);
  }
  
  
  /**
   * Obtiene el contenido del direcctorio especificado si no
   * De la unidad C:
   * @param pRuta
   * @return La lista de nombres de los archivos y directorios
   */
  public List<String> getContenido(String pRuta) {
    List<String> contenidos = new ArrayList<>();
    File unidadDir = new File("C:\\");
    //Verificar que la unidad existe
    if (unidadDir.exists() && unidadDir.isDirectory()) {
      File[] listaArchivos = unidadDir.listFiles();
      //Si se peudo acceder agrega la infromaciónd e los archvios y directorios
      if (listaArchivos != null) {
        for (File contenido : listaArchivos) {
          //Comprobar si el directorio o archivo
          String tipo = contenido.isDirectory()? "[Directorio]" : "[Archivo]";
          //fecha de creación
          String fechaCreacion = getFechaCreacion(contenido);
          //tamaño
          String tamano = contenido.isDirectory()? "N/A" : bytesAGigabytes(
             contenido.length()) + "GB";
          contenidos.add(tipo + " " + contenido.getName() + " - Tamaño: " + 
             tamano + " - Fecha de creación: " + fechaCreacion);
        }
      }else{
        contenidos.add("No se peude acceder al contenido");
      }
    }else{
      contenidos.add("La unidad C:\\ no exixste");
    }
    return contenidos;
  }
  
  /**
   * Muestra una cadena de carateres con la información de la unidad
   * @return Información de la unidad logica
   */
  @Override
  public String toString() {
    String info = "Nombre de la unidad: " + getNombre() + "\n";
    info += "Sistema de archvios: " + getSistemaArchivos() + "\n";
    info += "Capacidad total:" + "     " + formaBytes(getEspacioTotal()) + " bytes" + "           " + bytesAGigabytes(getEspacioTotal())+" GB"+"\n";
    info += "Espacio libre:" + "          " + formaBytes(getEspacioLibre()) + " bytes" + "           " + bytesAGigabytes(getEspacioLibre())+" GB"+"\n";
    info += "Espacio Usado:" + "      " + formaBytes(getEspacioUsado()) + " bytes" + "           " + bytesAGigabytes(getEspacioUsado())+" GB"+"\n";
    return info;
  }
}

  
