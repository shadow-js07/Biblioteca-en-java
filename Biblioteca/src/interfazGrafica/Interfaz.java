package interfazGrafica;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.EventQueue;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import modelo.libro;
import controlador.Biblioteca;

public class Interfaz extends JFrame implements ActionListener{
    private JTextField txtTitulo, txtAutor, txtDescripcion, txtBuscar;
    private JComboBox<String> cmbGenero;
    private JButton btnAgregar, btnEliminar, btnBuscar, btnEditar;
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;
    JMenuItem menuItemGuardar, menuItemCargar;
    private Biblioteca biblioteca;
    private ArrayList<libro> librosGuardados = new ArrayList<>();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interfaz window = new Interfaz();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Interfaz() {
        super("Gestión de Biblioteca");

        biblioteca = new Biblioteca();
        tablaLibros = new JTable();
        modeloTabla = new DefaultTableModel(new Object[]{"Título", "Autor", "Género", "Descripción"}, 0);
        tablaLibros.setModel(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaLibros);
        scrollPane.setBounds(24, 119, 561, 234);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.add(scrollPane);

        getContentPane().add(panelPrincipal);
        
        JLabel label = new JLabel("Título:");
        label.setBounds(10, 28, 56, 19);
        panelPrincipal.add(label);
        
        txtTitulo = new JTextField(20);
        txtTitulo.setBounds(51, 28, 166, 19);
        panelPrincipal.add(txtTitulo);
        
        JLabel label_1 = new JLabel("Autor:");
        label_1.setBounds(227, 29, 42, 16);
        panelPrincipal.add(label_1);
        
        txtAutor = new JTextField(20);
        txtAutor.setBounds(267, 28, 166, 19);
        panelPrincipal.add(txtAutor);
        
        JLabel label_2 = new JLabel("Género:");
        label_2.setBounds(435, 29, 56, 16);
        panelPrincipal.add(label_2);
        
        cmbGenero = new JComboBox<>(new String[]{"Ficción", "No ficción", "Poesia", "Teatro", "Literatura infantil", 
        		"Historieta o cómic"});
        cmbGenero.setBounds(488, 28, 108, 19);
        panelPrincipal.add(cmbGenero);
        
        JLabel label_3 = new JLabel("Descripción:");
        label_3.setBounds(10, 57, 83, 19);
        panelPrincipal.add(label_3);
        txtDescripcion = new JTextField(20);
        txtDescripcion.setBounds(91, 57, 166, 19);
        panelPrincipal.add(txtDescripcion);
        
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(101, 86, 82, 23);
        btnAgregar.addActionListener(this);
        panelPrincipal.add(btnAgregar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(241, 87, 82, 22);
        btnEliminar.addActionListener(this);
        panelPrincipal.add(btnEliminar);
        
        JLabel label_4 = new JLabel("Buscar:");
        label_4.setBounds(267, 57, 56, 19);
        panelPrincipal.add(label_4);
        
        txtBuscar = new JTextField(20);
        txtBuscar.setBounds(316, 57, 166, 19);
        panelPrincipal.add(txtBuscar);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(488, 55, 75, 23);
        btnBuscar.addActionListener(this);
        panelPrincipal.add(btnBuscar);
        
        btnEditar = new JButton("Editar\r\n");
        btnEditar.setBounds(370, 86, 82, 23);
        panelPrincipal.add(btnEditar);
        btnEditar.addActionListener(this);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuArchivo = new JMenu("Archivo");
        menuBar.add(menuArchivo);

        menuItemGuardar = new JMenuItem("Guardar");
        menuArchivo.add(menuItemGuardar);
        menuItemGuardar.addActionListener(this);
        
        JMenu menuGuardarComo = new JMenu("Guardar como");
        menuArchivo.add(menuGuardarComo);
        
        JMenuItem itemGuardarComoPDF = new JMenuItem("PDF");
        menuGuardarComo.add(itemGuardarComoPDF);
        
        JMenuItem itemGuardarComoDOCX = new JMenuItem("DOCX");
        menuGuardarComo.add(itemGuardarComoDOCX);
        
        JMenuItem itemGuardarComoXLSX = new JMenuItem("XLSX");
        menuGuardarComo.add(itemGuardarComoXLSX);
        
        JMenuItem itemGuardarComoOtro = new JMenuItem("Otro tipo de archivo");
        menuGuardarComo.add(itemGuardarComoOtro);

        menuItemCargar = new JMenuItem("Cargar");
        menuArchivo.add(menuItemCargar);
        menuItemCargar.addActionListener(this);

        JMenu menuAyuda = new JMenu("Ayuda");
        menuBar.add(menuAyuda);
        
        JMenuItem itemAyuda = new JMenuItem("Ayuda");
        menuAyuda.add(itemAyuda);
        
        JMenu menuSoporte = new JMenu("Soporte técnico");
        menuAyuda.add(menuSoporte);
        
        JMenuItem itemSoporteReportar = new JMenuItem("Reportar problema");
        menuSoporte.add(itemSoporteReportar);
        
        JMenuItem itemSoporteSugerencias = new JMenuItem("Sugerencias");
        menuSoporte.add(itemSoporteSugerencias);
        
        JMenuItem itemSoporteComunicarse = new JMenuItem("Comunicarse con soporte");
        menuSoporte.add(itemSoporteComunicarse);

        JMenuItem menuItemAcercaDe = new JMenuItem("Acerca de");
        menuAyuda.add(menuItemAcercaDe);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(620, 440);
        setVisible(true);
    }

    private void agregarLibro() {
        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = cmbGenero.getSelectedItem().toString();
        String descripcion = txtDescripcion.getText();

        libro libro = new Libro(titulo, autor, genero, descripcion);
        biblioteca.agregarLibro(libro);
        actualizarTabla();
    }

    private void eliminarLibro() {
        int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada != -1) {
            String titulo = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
            biblioteca.eliminarLibro(titulo);
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarLibro () {
    	int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada != -1) {
            
            String tituloAnterior = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
            String autorAnterior = modeloTabla.getValueAt(filaSeleccionada, 1).toString();
            String generoAnterior = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
            String descripcionAnterior = modeloTabla.getValueAt(filaSeleccionada, 3).toString();

            String nuevoTitulo = JOptionPane.showInputDialog(this, "Nuevo Título:", tituloAnterior);
            String nuevoAutor = JOptionPane.showInputDialog(this, "Nuevo Autor:", autorAnterior);
            String nuevoGenero = (String) JOptionPane.showInputDialog(this, "Nuevo Género:", "Editar Género", JOptionPane.QUESTION_MESSAGE, null, 
            		new String[]{"Ficción", "No ficción", "Poesia", "Teatro", "Literatura infantil", 
                    		"Historieta o cómic"}, generoAnterior);
            String nuevaDescripcion = JOptionPane.showInputDialog(this, "Nueva Descripción:", descripcionAnterior);

            modeloTabla.setValueAt(nuevoTitulo, filaSeleccionada, 0);
            modeloTabla.setValueAt(nuevoAutor, filaSeleccionada, 1);
            modeloTabla.setValueAt(nuevoGenero, filaSeleccionada, 2);
            modeloTabla.setValueAt(nuevaDescripcion, filaSeleccionada, 3);

            libro libroEditado = biblioteca.getLibros().get(filaSeleccionada);
            libroEditado.setTitulo(nuevoTitulo);
            libroEditado.setAutor(nuevoAutor);
            libroEditado.setGenero(nuevoGenero);
            libroEditado.setDescripcion(nuevaDescripcion);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un libro para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarLibros() {
        librosGuardados.clear();
        
        for (libro libro : biblioteca.getLibros()) {
            librosGuardados.add(new Libro(libro.getTitulo(), libro.getAutor(), libro.getGenero(), libro.getDescripcion()));
        }
        JOptionPane.showMessageDialog(this, "Libros guardados correctamente.", "Guardar libros", JOptionPane.INFORMATION_MESSAGE);
    }

    private void cargarLibros() {
        biblioteca.getLibros().clear();
        
        for (libro libro : librosGuardados) {
            biblioteca.agregarLibro(new Libro(libro.getTitulo(), libro.getAutor(), libro.getGenero(), libro.getDescripcion()));
        }
        JOptionPane.showMessageDialog(this, "Libros cargados correctamente.", "Cargar libros", JOptionPane.INFORMATION_MESSAGE);
        actualizarTabla();
    }

    private void buscarLibro() {
    	
        String busqueda = txtBuscar.getText();
        ArrayList<libro> resultados = biblioteca.buscarLibro(busqueda);
        mostrarResultados(resultados);
    }

    private void mostrarResultados(ArrayList<libro> resultados) {
    	
        modeloTabla.setRowCount(0);
        for (libro libro : resultados) {
            modeloTabla.addRow(new Object[]{libro.getTitulo(), libro.getAutor(), libro.getGenero(), libro.getDescripcion()});
        }
    }

    private void actualizarTabla() {
    	
        modeloTabla.setRowCount(0);
        for (libro libro : biblioteca.getLibros()) {
            modeloTabla.addRow(new Object[]{libro.getTitulo(), libro.getAutor(), libro.getGenero(), libro.getDescripcion()});
        }
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnBuscar) {
			buscarLibro();
		}
		else if (e.getSource() == btnEliminar) {
			eliminarLibro();
		}
		else if(e.getSource() == btnAgregar) {
			agregarLibro();
		}
		else if (e.getSource() == btnEditar) {
			editarLibro();
		}
		else if (e.getSource() == menuItemGuardar) {
            guardarLibros(); 
            
        } else if (e.getSource() == menuItemCargar) {
            cargarLibros(); 

        }
	}
}
