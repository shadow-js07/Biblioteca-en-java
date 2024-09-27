package modelo;

public abstract class libro {
    protected String titulo;
    protected String autor;
    protected String genero;
    protected String descripcion;

    public libro(String titulo, String autor, String genero, String descripcion) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.descripcion = descripcion;
    }


    public abstract String getTitulo();

    public abstract String getAutor();

    public abstract String getGenero();

    public abstract String getDescripcion();


	public abstract void setTitulo(String nuevoTitulo);


	public abstract void setAutor(String nuevoAutor);


	public abstract void setGenero(String nuevoGenero);


	public abstract void setDescripcion(String nuevaDescripcion);
}
