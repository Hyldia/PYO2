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

  public List<String> getContenido(String pRuta) {
    if (pRuta == null || pRuta.isEmpty()) {
      throw new IllegalArgumentException("La ruta proporcionada no es válida");
    }
    return unidadLogica.getContenido(pRuta);
  }
}
