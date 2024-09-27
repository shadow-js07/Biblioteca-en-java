package interfazGrafica;

import modelo.libro;

public class Libro extends libro{

	public Libro(String titulo, String autor, String genero, String descripcion) {
		super(titulo, autor, genero, descripcion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTitulo() {
		// TODO Auto-generated method stub
		return titulo;
	}

	@Override
	public String getAutor() {
		// TODO Auto-generated method stub
		return autor;
	}

	@Override
	public String getGenero() {
		// TODO Auto-generated method stub
		return genero;
	}

	@Override
	public String getDescripcion() {
		// TODO Auto-generated method stub
		return descripcion;
	}

	@Override
	public void setTitulo(String nuevoTitulo) {
		// TODO Auto-generated method stub
		this.titulo = nuevoTitulo;
	}

	@Override
	public void setAutor(String nuevoAutor) {
		// TODO Auto-generated method stub
		this.autor = nuevoAutor;
	}

	@Override
	public void setGenero(String nuevoGenero) {
		// TODO Auto-generated method stub
		this.genero = nuevoGenero;
	}

	@Override
	public void setDescripcion(String nuevaDescripcion) {
		// TODO Auto-generated method stub
		this.descripcion = nuevaDescripcion;
	}

}
