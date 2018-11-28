package vista;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import org.omg.Messaging.SyncScopeHelper;
import controlador.*;
import java.awt.*;

/**
 * Define la ventana de artistas, encargada de la gestión completa de los artistas :
 *
 */
public class Ventana2 extends JFrame {

	private JPanel contentPane, panelContenedor;
	private JTable tablaArtistas, tablaArtistasRelacionados;
	private DefaultTableModel dtm1;

	DefaultTableModel dtm22, dtm231, dtm232;
	private CardLayout layout;
	private JLabel lblNoPuedeEliminarse;
	private JButton btnConfirmar25;
	private JTextField textNombre;	
	
//	Referencias estáticas :
	static long idArtistaSeleccionado;
	static String nombreArtistaSeleccionado;
	static int añoArtistaSeleccionado;

	
	/**
	 * Constructor con los datos de artistas a cargar.
	 */
	public Ventana2(ResultSet rs) {
		
		setTitle("MUSIC COLLECTION");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		Centramos la ventana según la resolución de la pantalla en que se visualice :
		CentrarVentanas.centraVentana(this, 1366, 768);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//************************************************************************
//		21 - Panel superior de la ventana, ofreciendo todas las ocpiones : 	
//************************************************************************
		
		JPanel panel21 = new JPanel();
		panel21.setBounds(0, 0, 1372, 368);
		contentPane.add(panel21);
		panel21.setLayout(null);
		
		JLabel lblArtistas = new JLabel("ARTISTAS ESCOGIDOS");
		lblArtistas.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblArtistas.setBounds(500, 33, 280, 40);
		panel21.add(lblArtistas);

//		Modelo de tabla por defecto :
		
		dtm1 = new DefaultTableModel();
		dtm1.addColumn("Codigo");
		dtm1.addColumn("Nombre");		
		dtm1.addColumn("Año");		
		
		tablaArtistas = new JTable(dtm1);	

//		Para establecer el ancho de cada columna :
		
		TableColumnModel modeloColumna = tablaArtistas.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(50);
		modeloColumna.getColumn(1).setPreferredWidth(100);
		modeloColumna.getColumn(2).setPreferredWidth(50);	
		
		tablaArtistas.setFont(new Font("Dialog", Font.PLAIN, 14));

//		Ahora establecemos la alineación para que salgan centradas las columnas de importe ( 1 y 3 ) :
		
	    DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();  
	    dtcr.setHorizontalAlignment(SwingConstants.CENTER);		
		modeloColumna.getColumn(0).setCellRenderer(dtcr);	
		modeloColumna.getColumn(2).setCellRenderer(dtcr);	
		
//		Definimos el scroll y lo añadimos en el area central :
		
		JScrollPane scroll = new JScrollPane(tablaArtistas);
		
//		Para desactivar edición PERO OFRECER SELECCION de las líneas mostradas :
		
		tablaArtistas.setDefaultEditor(Object.class, null);		

		scroll.setBounds(257, 108, 617, 247);
		scroll.setViewportView(tablaArtistas);		
		panel21.add(scroll);

//		
//		AÑADIMOS AQUI EL CODIGO PARA CARGAR LA TABLA DESDE EL RESULTSET QUE HEMOS RECIBIDO POR PARÁMETRO EN EL CONSTRUCTOR :
//		

		
		if (rs!=null)
			try {
				dtm1.setRowCount(0);				
				while(rs.next())	dtm1.addRow(new Object[]{rs.getLong("idArtista"), rs.getString("nombreArtista"), rs.getInt("añoArtista")});
				
			} catch (SQLException e) {
				System.out.println("SE HA PRODUCIDO UN ERROR AL LEER LOS ARTISTAS FILTRADOS");
				e.printStackTrace();
			}	
		
		
		JButton btnAsignarGente = new JButton("Asignar Gente");
		btnAsignarGente.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAsignarGente.setBounds(972, 178, 182, 40);
		btnAsignarGente.setToolTipText( "Asigna al artista seleccionado una persona concreta");
		panel21.add(btnAsignarGente);
		
		btnAsignarGente.addActionListener(e -> {

//			Si hay artista seleccionado recuperamos y guardamos sus datos en las variables estáticas, recuperamos la gente disponible y visualizamos todo en panel inferior :
			
			if(tablaArtistas.getSelectedRow() != -1)		{
				
				guardaArtistaSeleccionado();	
				
				dtm22.setRowCount(0);
				
				ResultSet rs22 = GestionVentana2.recuperaPersonasDisponibles();				
		
				if (rs22!=null)		{
					
					try {	while(rs22.next())		dtm22.addRow(new Object[]{rs22.getString("nombreGente"), rs22.getInt("añoGente"), rs22.getLong("idGente")});

						} catch (SQLException e1) {
								System.out.println("SE HA PRODUCIDO UN ERROR AL LEER LAS PERSONAS DISPONIBLES");
								e1.printStackTrace();
						}
	
				layout.show(panelContenedor, "panel22");
				panelContenedor.setVisible(true);
	
				}	
			}
			
		});
		
	
//		OPCION  MANTENER ARTISTAS RELACIONADOS :
		
		
		JButton btnMantenerRelacionados = new JButton("Mantener Relacionados");
		btnMantenerRelacionados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnMantenerRelacionados.setBounds(972, 110, 182, 40);
		btnMantenerRelacionados.setToolTipText( "Mantiene la relación del artista seleccionado con otros artistas" );
		panel21.add(btnMantenerRelacionados);
	
		btnMantenerRelacionados.addActionListener(e -> {

			
//			Si hay artista seleccionado recuperamos y guardamos sus datos en las variables estáticas, y cargamos las dos tablas de artistas relacionados y no. Luego visualizamos todo en panel inferior :
			
			if(tablaArtistas.getSelectedRow() != -1)		{
		
				guardaArtistaSeleccionado();	
		
//			Tabla 1 :				
				
				ResultSet rs23 = GestionVentana2.recuperarArtistasRelacionados(idArtistaSeleccionado);	
				dtm231.setRowCount(0);
				if (rs23!=null)		{
					try {	while(rs23.next())		dtm231.addRow(new Object[]{rs23.getString("nombreArtista"), rs23.getInt("añoArtista"), rs23.getLong("idArtista")});
						} catch (SQLException e1) {
								System.out.println("SE HA PRODUCIDO UN ERROR AL LEER LOS ARTISTAS RELACIONADOS");
								e1.printStackTrace();
						}

//			Tabla 2 :
					
				rs23 = GestionVentana2.recuperarArtistasNoRelacionados(idArtistaSeleccionado);	
				dtm232.setRowCount(0);
				if (rs23!=null)		{
					try {	while(rs23.next())		dtm232.addRow(new Object[]{rs23.getString("nombreArtista"), rs23.getInt("añoArtista"), rs23.getLong("idArtista")});
					} catch (SQLException e1) {
						System.out.println("SE HA PRODUCIDO UN ERROR AL LEER LOS ARTISTAS RELACIONADOS");
						e1.printStackTrace();
					}					
					
	
				layout.show(panelContenedor, "panel23");
				panelContenedor.setVisible(true);
				
				}	
			}
			}
			
		});		
		

//		OPCION  DAR DE ALTA NUEVO ARTISTA ( Muestra panel inferior solicitando los datos ) :
		
		JButton btnCrearArtista = new JButton("Crear Artista");
		btnCrearArtista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCrearArtista.setBounds(972, 315, 182, 40);
		btnCrearArtista.setToolTipText( "Crea un nuevo artista");
		panel21.add(btnCrearArtista);
		
		btnCrearArtista.addActionListener(e -> {
			layout.show(panelContenedor, "panel24");
			panelContenedor.setVisible(true);
	
		});
		
//		OPCION  DAR DE BAJA ARTISTA  :		
		
		
		JButton btnEliminarArtista = new JButton("Eliminar Artista");
		btnEliminarArtista.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnEliminarArtista.setBounds(972, 248, 182, 40);
		btnEliminarArtista.setToolTipText( "Eimina el artista seleccionado");
		panel21.add(btnEliminarArtista);
		
		btnEliminarArtista.addActionListener(e -> {
			
			if(tablaArtistas.getSelectedRow() != -1)		{
			guardaArtistaSeleccionado();
			lblNoPuedeEliminarse.setVisible(false);
			btnConfirmar25.setVisible(true);
			layout.show(panelContenedor, "panel25");
			panelContenedor.setVisible(true);
			}
		});
		

//		Solicitamos elegir una de las cuatro opciones que se ofrecen :
		
		JLabel lblSeleccioneArtistaY = new JLabel("Seleccione artista y elija opción :");
		lblSeleccioneArtistaY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSeleccioneArtistaY.setBounds(949, 56, 248, 30);
		
		panel21.add(lblSeleccioneArtistaY);	
		
		
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSalir.setBounds(568, 629, 149, 36);
		contentPane.add(btnSalir);	
		btnSalir.addActionListener(e  ->  {	
			
//			Si tenemos activado el panel inferior es porque queremos permanecer en pantalla y volver al panel superior :
			
			if (panelContenedor.isVisible()) panelContenedor.setVisible(false);	
		
//			En otro caso queremos volver a la pantalla inicial :
			
			else	{
				this.dispose();
				Ventana1 miVentana1=new Ventana1();
				miVentana1.setLocationRelativeTo(null);
				miVentana1.setVisible(true);
				miVentana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
			
		
		
//		Definimos panel CardLayout para manejar la visualización alternativa de los distintos paneles inferiores :
		
		panelContenedor = new JPanel();
		panelContenedor.setBounds(0, 378, 1352, 253);
		contentPane.add(panelContenedor);
		layout = new CardLayout();
		panelContenedor.setLayout(layout);

//***************************************************************************	
//		22 - Definimos el primer panel inferior para asignación de personas :		
		
//***************************************************************************

		JPanel panel22 = new JPanel();
		panelContenedor.add(panel22, "panel22");
		panel22.setLayout(null);
		

		dtm22 = new DefaultTableModel();
		dtm22.addColumn("Nombre");		
		dtm22.addColumn("Año");	
		dtm22.addColumn("Id");		
		
		JTable tablaPersonas = new JTable(dtm22);	

//		Para establecer el ancho de cada columna :
		
		TableColumnModel modeloColumna22 = tablaPersonas.getColumnModel();
		modeloColumna22.getColumn(0).setPreferredWidth(100);
		modeloColumna22.getColumn(1).setPreferredWidth(50);
		
//		Ahora establecemos la alineación para que salgan centradas las columnas  :
		
		modeloColumna22.getColumn(1).setCellRenderer(dtcr);	
		
//		Ocultamos la tercera columna (id de persona ) a la vista pero mantenemos su valor en la tabla para posterior recuperación  :		
		
		tablaPersonas.removeColumn(modeloColumna22.getColumn(2));
		
//		Definimos el scroll y lo añadimos en el area central :
		
		JScrollPane scroll22 = new JScrollPane(tablaPersonas);
		
//		Para desactivar edición PERO OFRECER SELECCION de las líneas mostradas :
		
		tablaPersonas.setDefaultEditor(Object.class, null);		

		scroll22.setBounds(255, 46, 339, 148);
		scroll22.setViewportView(tablaPersonas);		
		panel22.add(scroll22);
		
		JLabel lblPersonasDisponibles = new JLabel("PERSONAS DISPONIBLES");
		lblPersonasDisponibles.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPersonasDisponibles.setBounds(348, 11, 175, 22);
		panel22.add(lblPersonasDisponibles);
		
		JButton btnAsignar22 = new JButton("Asignar");
		btnAsignar22.setBounds(674, 98, 80, 80);
		panel22.add(btnAsignar22);
		
//		Programamos botón para obtener la persona seleccionada de la tabla de gente, llamamos al método actualizador y eliminamos la persona de la lista de personas disponibles :
		
		btnAsignar22.addActionListener(e -> {
		
			int fila = tablaPersonas.getSelectedRow();
			if (fila!= -1)	{
				long idPersonaSeleccionada = (long) tablaPersonas.getModel().getValueAt(fila, 2);
				GestionVentana2.asignarAtistaAPersona(idPersonaSeleccionada, idArtistaSeleccionado);
				dtm22.removeRow(fila);
				layout.show(panelContenedor, "panel22");
			}			
			
		});		
		
		
		
//********************************************************************************************	
//		23 - Definimos el segundo panel inferior para mantener las relaciones entre artistas :		
//********************************************************************************************
		
		
		JPanel panel23 = new JPanel();
		panelContenedor.add(panel23, "panel23");
		panel23.setLayout(null);

		
//********************************************************************************************	
//		23.1 - Tabla de Artistas Relacionados :		
//********************************************************************************************		
			
		dtm231 = new DefaultTableModel();
		dtm231.addColumn("Artista");		
		dtm231.addColumn("Año");			
		dtm231.addColumn("Id");
		
		JTable tablaArtistasRelacionados = new JTable(dtm231);	

//		Para establecer el ancho de cada columna :
		
		TableColumnModel modeloColumna231 = tablaArtistasRelacionados.getColumnModel();
		modeloColumna231.getColumn(0).setPreferredWidth(100);
		modeloColumna231.getColumn(1).setPreferredWidth(50);
		
//		Ahora establecemos la alineación para que salgan centradas las columnas  :
		
		modeloColumna231.getColumn(1).setCellRenderer(dtcr);	

//		Ocultamos la tercera columna (id de persona ) a la vista pero mantenemos su valor en la tabla para posterior recuperación  :		
		
		tablaArtistasRelacionados.removeColumn(modeloColumna231.getColumn(2));		
		
//		Definimos el scroll y lo añadimos en el area central :
		
		JScrollPane scroll231 = new JScrollPane(tablaArtistasRelacionados);		
		
		tablaArtistasRelacionados.setDefaultEditor(Object.class, null);		

		scroll231.setBounds(147, 43, 339, 148);
		scroll231.setViewportView(tablaArtistasRelacionados);		
		panel23.add(scroll231);		
		
		JLabel lblArtistasRelacionados = new JLabel("Artistas Relacionados");
		lblArtistasRelacionados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArtistasRelacionados.setBounds(256, 11, 129, 23);
		panel23.add(lblArtistasRelacionados);
		
		JButton btnEliminar23 = new JButton("Eliminar");
		btnEliminar23.setBounds(534, 65, 80, 79);
		panel23.add(btnEliminar23);	
		btnEliminar23.addActionListener(e ->	{

//			Programamos botón para obtener el artista relacionado seleccionado. Llamamos al método correspondiente para eliminar al relación de ambos artistas y movemos de tabla el artista relacionado  :
			
			int fila = tablaArtistasRelacionados.getSelectedRow();
			if (fila!= -1)	{
				
				long idArtistaRelacionadoSeleccionado = (long) tablaArtistasRelacionados.getModel().getValueAt(fila, 2);
				GestionVentana2.eliminarRelacionArtistas(idArtistaSeleccionado, idArtistaRelacionadoSeleccionado);
				String nombreArtistaRelacionadoSeleccionado = (String) tablaArtistasRelacionados.getModel().getValueAt(fila, 0);
				int añoArtistaRelacionadoSeleccionado = (int) tablaArtistasRelacionados.getModel().getValueAt(fila, 1);			
				dtm232.addRow(new Object[]{nombreArtistaRelacionadoSeleccionado, añoArtistaRelacionadoSeleccionado, idArtistaRelacionadoSeleccionado });
				dtm231.removeRow(fila);
				
				layout.show(panelContenedor, "panel23");
	
			}
		});

		
		
//********************************************************************************************	
//		23.2 - Tabla de Artistas NO Relacionados :		
//********************************************************************************************			
	
	
		
		dtm232 = new DefaultTableModel();
		dtm232.addColumn("Artista");		
		dtm232.addColumn("Año");			
		dtm232.addColumn("Id");
		
		JTable tablaArtistasNoRelacionados = new JTable(dtm232);	

//		Para establecer el ancho de cada columna :
		
		TableColumnModel modeloColumna232 = tablaArtistasNoRelacionados.getColumnModel();
		modeloColumna232.getColumn(0).setPreferredWidth(100);
		modeloColumna232.getColumn(1).setPreferredWidth(50);
		
//		Ahora establecemos la alineación para que salgan centradas las columnas  :
		
		modeloColumna232.getColumn(1).setCellRenderer(dtcr);	
		
//		Ocultamos la tercera columna (id de persona ) a la vista pero mantenemos su valor en la tabla para posterior recuperación  :		
		
		tablaArtistasNoRelacionados.removeColumn(modeloColumna232.getColumn(2));	
		
//		Definimos el scroll y lo añadimos en el area central :
		
		JScrollPane scroll232 = new JScrollPane(tablaArtistasNoRelacionados);		
		
		tablaArtistasNoRelacionados.setDefaultEditor(Object.class, null);		

		scroll232.setBounds(700, 43, 339, 148);		
		scroll232.setViewportView(tablaArtistasNoRelacionados);		
		panel23.add(scroll232);		
		
		JLabel lblArtistasNoRelacionados = new JLabel("Artistas No Relacionados");
		lblArtistasNoRelacionados.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblArtistasNoRelacionados.setBounds(800, 11, 163, 23);
		panel23.add(lblArtistasNoRelacionados);
		
		JButton btnAñadir23 = new JButton("Añadir");
		btnAñadir23.setBounds(1080, 65, 80, 79);
		panel23.add(btnAñadir23);	
		
//		Programamos botón para obtener el artista no-relacionado seleccionado. Llamamos al método correspondiente para añadir la relación entre ambos artistas,
//		y movemos de tabla el artista recién asignado : 
		
		btnAñadir23.addActionListener(e ->	{
		int fila = tablaArtistasNoRelacionados.getSelectedRow();
		if (fila!= -1)	{
			
			long idArtistaNoRelacionadoSeleccionado = (long) tablaArtistasNoRelacionados.getModel().getValueAt(fila, 2);
			GestionVentana2.crearRelacionArtistas(idArtistaSeleccionado, idArtistaNoRelacionadoSeleccionado);
			
			String nombreArtistaNoRelacionadoSeleccionado = (String) tablaArtistasNoRelacionados.getModel().getValueAt(fila, 0);
			int añoArtistaNoRelacionadoSeleccionado = (int) tablaArtistasNoRelacionados.getModel().getValueAt(fila, 1);			
			dtm231.addRow(new Object[]{nombreArtistaNoRelacionadoSeleccionado, añoArtistaNoRelacionadoSeleccionado, idArtistaNoRelacionadoSeleccionado });
			dtm232.removeRow(fila);
			
			layout.show(panelContenedor, "panel23");
	
			}		
		
		});
	
//********************************************************************************************	
//		24 - Definimos el tercer panel inferior para dar altas de artistas :		
//********************************************************************************************		
		
		JPanel panel24 = new JPanel();
		panelContenedor.add(panel24, "panel24");
		panel24.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(360, 53, 76, 28);
		panel24.add(lblNombre);
		
		JLabel lblAño = new JLabel("Año :");
		lblAño.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAño.setBounds(360, 92, 76, 28);
		panel24.add(lblAño);
		
		textNombre = new JTextField();
		textNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textNombre.setBounds(457, 57, 323, 21);
		panel24.add(textNombre);
		textNombre.setColumns(10);
		
		JButton btnAceptar24 = new JButton("Aceptar");
		btnAceptar24.setBounds(846, 58, 85, 58);
		panel24.add(btnAceptar24);
		
		JLabel lblEstilo = new JLabel("Estilo :");
		lblEstilo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEstilo.setBounds(360, 131, 76, 28);
		panel24.add(lblEstilo);
		
		JComboBox comboEstilos = new JComboBox();
		comboEstilos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboEstilos.setBounds(457, 135, 245, 21);
		panel24.add(comboEstilos);
	
//		Cargamos combo de estilos con los datos recuperados de la BBDD :
		ArrayList<String> estilos = GestionVentana1.recuperaListaEstilos();
		estilos.forEach(s -> comboEstilos.addItem(s));	
		
		JComboBox comboAño = new JComboBox();
		comboAño.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboAño.setBounds(456, 96, 76, 21);
		panel24.add(comboAño);
//		Cargamos el combo con todos los años desde 1950 al año actual :
		for(int i=1950; i<=LocalDate.now().getYear(); i++)		comboAño.addItem(i);
		
		JLabel lblMensaje = new JLabel("Introduzca Nombre de Artista");
		lblMensaje.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMensaje.setBounds(457, 191, 323, 28);
		panel24.add(lblMensaje);
		
		JLabel lblIntroduzcaLosDatos = new JLabel("Introduzca los datos del nuevo artista :");
		lblIntroduzcaLosDatos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblIntroduzcaLosDatos.setBounds(360, 22, 420, 21);
		panel24.add(lblIntroduzcaLosDatos);

		btnAceptar24.addActionListener(a -> {

//			1.- Validación de nombre introducido :
				
			lblMensaje.setVisible(textNombre.getText().equals(""));			

//			2.- Llamamos al correspondiente método del controlador para crear el artista y relacionarlo con su estilo  :	
			
			if (!textNombre.getText().equals(""))			{
			int año = (int) comboAño.getSelectedItem();
			String estilo = (String) comboEstilos.getSelectedItem();
			long indice = GestionVentana2.darAltaArtista(textNombre.getText(), año, estilo);
			dtm1.addRow(new Object[]{indice, textNombre.getText(), año});
			textNombre.setText("");
			comboAño.setSelectedIndex(0);
			comboEstilos.setSelectedIndex(0);
			layout.show(panelContenedor, "panel24");
			
			}
		});	
		
		
//********************************************************************************************	
//		25 - Definimos el cuarto panel inferior, para eliminación de artistas :		
//********************************************************************************************			
		
		
		
		JPanel panel25 = new JPanel();
		panelContenedor.add(panel25, "panel25");
		panel25.setLayout(null);
		

		
		lblNoPuedeEliminarse = new JLabel("");
		lblNoPuedeEliminarse.setForeground(Color.RED);
		lblNoPuedeEliminarse.setBackground(Color.LIGHT_GRAY);
		lblNoPuedeEliminarse.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoPuedeEliminarse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNoPuedeEliminarse.setBounds(273, 91, 758, 47);
		panel25.add(lblNoPuedeEliminarse);
		
		JLabel lblDeseaEliminar = new JLabel("¿ Desea eliminar el artista seleccionado ?");
		lblDeseaEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeseaEliminar.setBounds(415, 50, 423, 30);
		panel25.add(lblDeseaEliminar);
		lblMensaje.setVisible(false);
		
		JButton btnCancelar25 = new JButton("Cancelar");
		btnCancelar25.setBounds(665, 160, 102, 23);
		panel25.add(btnCancelar25);	
		btnCancelar25.addActionListener(a -> {
			panelContenedor.setVisible(false);
		});			
		
		
		btnConfirmar25 = new JButton("Confirmar");
		btnConfirmar25.setBounds(478, 160, 130, 23);
		panel25.add(btnConfirmar25);	
		
		JSeparator separator = new JSeparator();
		separator.setBounds(245, 38, 829, 2);
		panel25.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(245, 213, 829, 2);
		panel25.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(245, 39, 2, 176);
		panel25.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(1072, 39, 2, 176);
		panel25.add(separator_3);
		
//		Comprobamos si hay algún registro en las tablas dependientes. 
//		La excepción de violación de integridad nos permitirá comprobar si el artista tiene asociados otro artista, alguna gente o un estilo musical.
//		La excepción SQL genérica nos permitirá capturar un error SQL de segundo nivel.
		
		btnConfirmar25.addActionListener(a -> {
			int fila = tablaArtistas.getSelectedRow();
			
			if (fila!= -1)	{		
				long idArtista = (long) tablaArtistas.getModel().getValueAt(fila, 0);	
				
				try	{
					
				GestionVentana2.darBajaArtista(idArtista);
				dtm1.removeRow(fila);	
				panelContenedor.setVisible(false);	
				layout.show(panelContenedor, "panel22");
				
				} catch (com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e1)	{
					
				btnConfirmar25.setVisible(false);
				lblNoPuedeEliminarse.setText("No puede eliminarse artista.  Asegúrese de borrar primero sus registros asociados (otros artistas, gente y estilos musicales)");				
				lblNoPuedeEliminarse.setVisible(true);
				layout.show(panelContenedor, "panel25");
				
				}  catch (SQLException e2)	{
					btnConfirmar25.setVisible(false);
					lblNoPuedeEliminarse.setText("Se ha producido un error al acceder a la Base de Datos");
					lblNoPuedeEliminarse.setVisible(true);					
					lblNoPuedeEliminarse.setVisible(false);
					layout.show(panelContenedor, "panel25");
					
				}

			}
			
		});
	
				
		panelContenedor.setVisible(false);


	}
	
	/**Guarda en variables estáticas la información del artista (id, nombre y año) que el usuario ha seleccionado.
	 * 
	 */
	void guardaArtistaSeleccionado()	{
		
		int fila = tablaArtistas.getSelectedRow();
		idArtistaSeleccionado = (long) tablaArtistas.getValueAt(fila, 0);
		String nombreArtistaSeleccionado = (String) tablaArtistas.getValueAt(fila, 1);
		añoArtistaSeleccionado = (int) tablaArtistas.getValueAt(fila, 2);

	}
}
	

