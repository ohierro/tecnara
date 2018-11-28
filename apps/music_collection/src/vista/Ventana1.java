package vista;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import controlador.*;
import modelo.*;

/**
 * Define la primera ventana, encargada de la selección inicial de la aplicación.
 *
 */
public class Ventana1 extends JFrame {

//	Definimos aquí las referencias que se usarán a lo largo de toda la clase :
	
	private JPanel panel12, panel13;
	private JComboBox comboBoxEstilo1;
	private JTextField textFieldNombre2;
	

	/** 
	 * Constructor de la ventana inicial (selección)
	 */
	public Ventana1() {
	
		setTitle("MUSIC COLLECTION");
		setResizable(false);
		
//		Centramos la ventana según la resolución de la pantalla en que se visualice :
			
		CentrarVentanas.centraVentana(this, 720, 500);
		
	
//**************************************************************************************************	
//		11 - Primer panel. Ofrece únicamente la posibilidad de seleccionar los artistas o la gente :		
//**************************************************************************************************			
		JPanel panel11 = new JPanel();
		panel11.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel11);
		panel11.setLayout(null);
		
		JLabel lblMusicCollection = new JLabel("MUSIC COLLECTION");
		lblMusicCollection.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusicCollection.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMusicCollection.setBounds(268, 49, 191, 23);
		panel11.add(lblMusicCollection);
		
		JButton btnArtistas1 = new JButton("ARTISTAS");
		btnArtistas1.setBounds(175, 113, 100, 33);
		btnArtistas1.setToolTipText( "Mantiene los artistas y gestiona sus relaciones" );
		panel11.add(btnArtistas1);		
		
//		Si pulsamos el botón de artistas, se visualiza un nuevo panel para ofrecer diferentes métodos de búsqueda del artista :
		
		btnArtistas1.addActionListener(e -> {
			
//			Cargamos combo de estilos con los datos recuperados de la BBDD :
			
			ArrayList<String> estilos = GestionVentana1.recuperaListaEstilos();
			estilos.forEach(s -> comboBoxEstilo1.addItem(s));	
			
			panel12.revalidate();
			panel12.repaint();
			panel12.setVisible(true);
			panel13.setVisible(false);			
		});			

	
		JButton btnGente1 = new JButton("GENTE");
		btnGente1.setBounds(450, 113, 98, 33);
		btnGente1.setToolTipText( "Da de alta una persona para el fichero maestro de Gente" );
		panel11.add(btnGente1);

		btnGente1.addActionListener(e -> {
			textFieldNombre2.setText("");
			panel13.setVisible(true);
			panel12.setVisible(false);
			panel11.validate();
			panel11.repaint();			
			
		});
		

		
//**************************************************************************
//		12 - Panel para ofrecer diferentes métodos de búsqueda del artista :			
//***************************************************************************		
	
		
		panel12 = new JPanel();
		panel12.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel12.setBounds(93, 185, 233, 164);
		panel11.add(panel12);
		panel12.setLayout(null);
		
//		No es mostrado inicialmente este panel :
		panel12.setVisible(false);

//		Definimos los botones de radio :		
		
		ButtonGroup btnGroupRadio1 = new ButtonGroup();		

		JRadioButton rdbtnTodos1 = new JRadioButton("Todos");
		rdbtnTodos1.setBounds(6, 36, 109, 23);
		panel12.add(rdbtnTodos1);
		btnGroupRadio1.add(rdbtnTodos1);
		
		JRadioButton rdbtnPorEstilo1 = new JRadioButton("Por Estilo :");
		rdbtnPorEstilo1.setBounds(6, 59, 109, 23);
		panel12.add(rdbtnPorEstilo1);
		btnGroupRadio1.add(rdbtnPorEstilo1);
		
		JRadioButton rdbtnPorNombre1 = new JRadioButton("Por Nombre :");
		rdbtnPorNombre1.setBounds(6, 85, 109, 23);
		panel12.add(rdbtnPorNombre1);
		btnGroupRadio1.add(rdbtnPorNombre1);		

//		Definimos el resto de componentes del segundo panel :

		comboBoxEstilo1 = new JComboBox();
		comboBoxEstilo1.setBounds(121, 59, 100, 23);
		comboBoxEstilo1.setAlignmentY(RIGHT_ALIGNMENT);
		panel12.add(comboBoxEstilo1);
		
		JTextField textFieldNombre1 = new JTextField();
		textFieldNombre1.setBounds(123, 87, 98, 23);
		panel12.add(textFieldNombre1);
		textFieldNombre1.setColumns(10);

//		Definimos el comportamiento de los botones de radio :		
		
		rdbtnTodos1.addActionListener(e -> {
			comboBoxEstilo1.setEnabled(false);
			textFieldNombre1.setText("");
			textFieldNombre1.setEnabled(false);
		});		
		
		rdbtnPorEstilo1.addActionListener(e -> {
			comboBoxEstilo1.setEnabled(true);
			textFieldNombre1.setText("");
			textFieldNombre1.setEnabled(false);
		});			
		
		rdbtnPorNombre1.addActionListener(e -> {
			comboBoxEstilo1.setEnabled(false);
			textFieldNombre1.setText("");
			textFieldNombre1.setEnabled(true);
			textFieldNombre1.requestFocus();
	});		

//		Establecemos la visualización inicial de los campos de selección ( en este caso selección por estilo musical ) :
		
		rdbtnPorEstilo1.setSelected(true);		
		comboBoxEstilo1.setEnabled(true);
		textFieldNombre1.setText("");
		textFieldNombre1.setEnabled(false);			
		

//		Definimos y establecemos el comportamiento del botón de aceptar las selecciones de artistas.
//		Según la selección realizada llamamos al método responsable de acceder a la BBDD y crear una nueva
//		ventana para gestionar todas las opciones de artistas. Lo hacemos normalizando dos parámetros para su gestión unificada :

		
		JLabel lblSeleccioneCriteriosDe = new JLabel("Seleccione criterios de búsqueda :");
		lblSeleccioneCriteriosDe.setBounds(10, 11, 197, 14);
		panel12.add(lblSeleccioneCriteriosDe);

		JButton btnAceptar1 = new JButton("Aceptar");
		btnAceptar1.setBounds(76, 130, 89, 23);
		panel12.add(btnAceptar1);		
		
		btnAceptar1.addActionListener(e -> {
			
			int tipoSeleccion = 0;
			String datosSeleccion = "";
			
			if (rdbtnTodos1.isSelected())	tipoSeleccion=1;
			else if (rdbtnPorEstilo1.isSelected())	{
				tipoSeleccion=2;
				datosSeleccion = (String) comboBoxEstilo1.getSelectedItem();
				}
			else	{
				tipoSeleccion=3;
				datosSeleccion = textFieldNombre1.getText().toLowerCase();
			}
			GestionVentana2.cargaVentana2(tipoSeleccion, datosSeleccion, this);
		});
		

//		Ya sólo resta definir el botón de salida :
		
		JButton btnSalir1 = new JButton("Salir");
//		btnSalir1.setBounds(310, 434, 89, 23);
		btnSalir1.setBounds(310, 420, 89, 35);
		panel11.add(btnSalir1);	

		
//************************************************************
//		13 - Definimos nuevo panel para dar de alta personas :		
//************************************************************		
		
		panel13 = new JPanel();
		panel13.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel13.setBounds(366, 185, 311, 138);
		panel11.add(panel13);
		panel13.setLayout(null);
		
		JLabel lblCreacinPersonaIntroduzca = new JLabel("Introduzca datos para alta de persona :");
		lblCreacinPersonaIntroduzca.setBounds(21, 11, 245, 14);
		panel13.add(lblCreacinPersonaIntroduzca);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(21, 46, 66, 14);
		panel13.add(lblNombre);
		
		JLabel lblAo = new JLabel("Año :");
		lblAo.setBounds(21, 71, 53, 14);
		panel13.add(lblAo);
		
		textFieldNombre2 = new JTextField();
		textFieldNombre2.setBounds(97, 43, 155, 20);
		panel13.add(textFieldNombre2);
		textFieldNombre2.setColumns(10);
		
		JComboBox comboBoxAño = new JComboBox();
		comboBoxAño.setBounds(97, 70, 53, 17);
		panel13.add(comboBoxAño);
		
//		Cargamos el combo con todos los años desde 1950 al año actual :
		
		for(int i=1950; i<=LocalDate.now().getYear(); i++)		comboBoxAño.addItem(i);
		
		JButton btnAceptar2 = new JButton("Aceptar");
		btnAceptar2.setBounds(45, 104, 89, 23);
		panel13.add(btnAceptar2);

//		Llamamos al correspondiente método para dar altas en el fichero maestro de gente :
		
		btnAceptar2.addActionListener(e ->     {
			
			if (!textFieldNombre2.getText().equals("")) {
				GestionVentana1.darAltaPersona(textFieldNombre2.getText(), (int)comboBoxAño.getSelectedItem());
				textFieldNombre2.setText("");
			}
		});		
		
		
		JButton btnCancelar2 = new JButton("Cancelar");
		btnCancelar2.setBounds(177, 104, 89, 23);
		panel13.add(btnCancelar2);
		btnCancelar2.addActionListener(e -> {
		panel13.setVisible(false);
		panel11.repaint();
		});

//		No es mostrado inicialmente este panel :

		panel13.setVisible(false);		

		
//		El botón SALIR cierra todos los recursos y termina la aplicación :
		
		btnSalir1.addActionListener(e -> 	CierraRecursos.cierraTodo()	);
		
	}
}
