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
 *
 * @author Usuario
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
  public File[] conseguirListaArchivos(File pDirectorio) throws Exception {
    File[] archivos = pDirectorio.listFiles();
    if (archivos != null) {
      return archivos;
    } else {
      throw new Exception("El directorio se encuentra vacío");
    }
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

  public File[] listarUnidadesLogicas() {
    File[] unidades = File.listRoots();
    return unidades;
  }
}
