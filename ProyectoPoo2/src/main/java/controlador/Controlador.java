/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import logicadenegocios.UnidadLogica;
import logicadenegocios.Archivo;
import logicadenegocios.Directorio;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Clase que representa la abstracción de un Controlador
 *
 * @author Hyldia T., Berenice A. & Deywenie S.
 */
public class Controlador {

  private UnidadLogica unidadLogica;
  private Archivo archivo;
  private Directorio directorio;

  public Controlador() {
    unidadLogica = new UnidadLogica();
    archivo = new Archivo();
    directorio = new Directorio();

  }
  
  // Archivos y directorios
  
  /**
   * Método encargado de conseguir los archivos de la unidad lógica C
   *
   * @param pDirectorio
   * @return Un arreglo con los archivos
   * @throws Exception
   */
  public File[] conseguirListaArchivos(File pDirectorio) throws Exception {
    if (pDirectorio == null) {
      throw new IllegalArgumentException("El directorio no puede ser nulo.");
    }
    if (!pDirectorio.exists() || !pDirectorio.isDirectory()) {
      throw new IllegalArgumentException("El archivo proporcionado no es un "
         + "directorio válido.");
    }
    File[] archivos = pDirectorio.listFiles();
    if (archivos == null || archivos.length == 0) {
      throw new IOException("El directorio está vacío o no se pudo acceder.");
    }
    return archivos;
  }
  
    /**
     *
     * @param rutaArchivo
     */
    public void abrirArchivo(String rutaArchivo) {
    try {
      archivo.abrirArchivo(rutaArchivo);
    }catch (IOException e){
      JOptionPane.showMessageDialog(null, "Error al abrir el archivo: " + e.getMessage(), 
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
    
  public String consultarInfoArchivo(String rutaArchivo) throws IOException {
    if (rutaArchivo == null || rutaArchivo.isEmpty()) {
        throw new IllegalArgumentException("La ruta del archivo no es válida.");
    }

    File archivo = new File(rutaArchivo);

    if (!archivo.exists() || archivo.isDirectory()) {
        throw new IllegalArgumentException("El archivo no es válido.");
    }

    // Obtener nombre y extensión del archivo
    String nombreArchivo = archivo.getName();
    String extension = nombreArchivo.contains(".")
        ? nombreArchivo.substring(nombreArchivo.lastIndexOf('.') + 1)
        : "Sin extensión";

    // Construir la información del archivo
    String info = "Nombre del archivo: " + nombreArchivo + "\n";
    info += "Extensión: " + extension + "\n";
    info += "Tamaño del archivo: " + (archivo.length() / 1024) + " KB\n";
    info += "Ubicación del archivo: " + archivo.getAbsolutePath() + "\n";

    // Obtener la fecha de creación del archivo
    BasicFileAttributes atributos = Files.readAttributes(archivo.toPath(), BasicFileAttributes.class);
    String fechaCreacion = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(atributos.creationTime().toMillis());
    info += "Fecha de creación: " + fechaCreacion + "\n";

    // Atributos del archivo (permisos)
    info += "Atributos: ";
    info += (archivo.canRead() ? "Lectura permitida, " : "Lectura no permitida, ");
    info += (archivo.canWrite() ? "Escritura permitida, " : "Escritura no permitida, ");
    info += (archivo.canExecute() ? "Ejecución permitida" : "Ejecución no permitida");

    return info;
}

  
  public String consultarinfoDirectorio(String rutaDirectorio) throws IOException {
    if (rutaDirectorio == null || rutaDirectorio.isEmpty()) {
        throw new IllegalArgumentException("La ruta del directorio proporcionada es inválida.");
    }

    File directorio = new File(rutaDirectorio);

    if (!directorio.exists()) {
        throw new IllegalArgumentException("El directorio no existe.");
    }
    if (!directorio.isDirectory()) {
        throw new IllegalArgumentException("El archivo proporcionado no es un directorio.");
    }

    String info = "";
    info += "Ruta del directorio: " + directorio.getAbsolutePath() + "\n";

    long tamañoTotal = calcularTamañoDirectorio(directorio);
    info += "Tamaño total del directorio: " + (tamañoTotal / (1024 * 1024)) + " MB\n";

    File[] archivos = directorio.listFiles();
    int cantidadArchivos = 0;
    int cantidadDirectorios = 0;

    if (archivos != null) {
        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                cantidadDirectorios++;
            } else {
                cantidadArchivos++;
            }
        }
    }

    info += "Cantidad de archivos: " + cantidadArchivos + "\n";
    info += "Cantidad de subdirectorios: " + cantidadDirectorios + "\n";

    info += "Atributos: ";
    info += (directorio.canRead() ? "Lectura permitida, " : "Lectura no permitida, ");
    info += (directorio.canWrite() ? "Escritura permitida, " : "Escritura no permitida, ");
    info += (directorio.canExecute() ? "Ejecución permitida" : "Ejecución no permitida");

    return info;
}

private long calcularTamañoDirectorio(File directorio) {
    long tamañoTotal = 0;
    File[] archivos = directorio.listFiles();

    if (archivos != null) {
        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                tamañoTotal += calcularTamañoDirectorio(archivo);
            } else {
                tamañoTotal += archivo.length();
            }
        }
    }

    return tamañoTotal;
}
  
  
  // Unidad Lógica
  
  /**
   * Método encarga de listar las unidades lógicas
   * 
   * @return un arreglo con las unidades lógicas
   */
  public File[] listarUnidadesLogicas() {
    File[] unidades = File.listRoots();
    return unidades;
  }

  public String getFechaCreacionArchivo(File pArchivo) {
    if (pArchivo == null || !pArchivo.exists()) {
      return "Archivo no válido o inexistente";
    }
    try {
      BasicFileAttributes atributos = Files.readAttributes(pArchivo.toPath(),
         BasicFileAttributes.class);
      return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(
         atributos.creationTime().toMillis());
    } catch (IOException e) {
      return "No disponible";
    }
  }
  
  public double getTamañoArchivo(File pArchivo) {
    return 0.0;
  }

  public List<String> getContenido(String pRuta) {
    if (pRuta == null || pRuta.isEmpty()) {
      throw new IllegalArgumentException("La ruta proporcionada no es válida");
    }
    return unidadLogica.getContenido(pRuta);
  }
  
  public String infoUnidadLogica() {
    return unidadLogica.toString();
  }
}
