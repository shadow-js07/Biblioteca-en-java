package controlador;

import modelo.libro;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(libro libro) {
        libros.add(libro);
    }

    public void eliminarLibro(String titulo) {
        libros.removeIf(libro -> libro.getTitulo().equals(titulo));
    }

    public ArrayList<libro> buscarLibro(String busqueda) {
        ArrayList<libro> resultados = new ArrayList<>();
        for (libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(busqueda.toLowerCase()) ||
                libro.getAutor().toLowerCase().contains(busqueda.toLowerCase()) ||
                libro.getGenero().toLowerCase().contains(busqueda.toLowerCase()) ||
                libro.getDescripcion().toLowerCase().contains(busqueda.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    public ArrayList<libro> getLibros() {
        return libros;
    }
}

