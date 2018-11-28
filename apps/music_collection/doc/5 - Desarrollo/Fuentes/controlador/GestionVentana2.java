package controlador;

import java.sql.*;
import javax.swing.JFrame;
import modelo.*;
import vista.*;


/**Desde el paquete controlador, define los métodos que gestionan los eventos de la ventana 2
 * @author Alfonso Aledo
 *
 */
/**
 * @author Rocio
 *
 */
/**
 * @author Rocio
 *
 */
public class GestionVentana2 {

	
	
	/**Recupera de la BBDD la lista de artistas a visualizar, crea la segunda ventana y los carga en ella.
	 * @param tipo : Tipo de selección realizada
	 * @param datos Valores seleccionados
	 */
	public static void cargaVentana2(int tipo, String dato, Ventana1 ventana1)	{

//		1.- Obtenemos el ResultSet con la lista de artistas deseada :
			
		ResultSet rs = OperacionesLectura.dameArtistasSeleccionados(tipo, dato);
		
//		2.- Cerramos la ventana previa :		
		
		ventana1.dispose();
		
//		3.- Instanciamos la nueva ventana y le pasamos los artistas a cargar para que los inserte en el constructor :		
		
		Ventana2 miVentana2 = new Ventana2(rs);
		miVentana2.setVisible(true);
		miVentana2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	/**Recupera para visualización la lista de personas que no tienen asociadas ningún artista, y son por tanto susceptibles de ser asignadas :
	 * @return Lista de personas libres
	 */
	public static ResultSet recuperaPersonasDisponibles()	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador y devolvemos el ResultSet obtenido :
		
		return OperacionesLectura.damePersonasDisponibles();

	}







	/**Asigna el artista a la persona
	 * @param gente
	 * @param artista
	 */
	public static void asignarAtistaAPersona(long gente, long artista)	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador  :
		
		 OperacionesEscritura.asignaGente(gente, artista);

	}
		

	/**Dado un artista obtiene la lista del resto de artistas que están relacionados con el primero
	 * @param artista
	 * @return Lista de artistas
	 */
	public static ResultSet recuperarArtistasRelacionados(long artista)	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador y devolvemos el ResultSet obtenido :
		
		return OperacionesLectura.dameArtistasRelacionados(artista);

	}
	
	
	/**Dado un artista obtiene la lista del resto de artistas que NO están relacionados con el primero
	 * @param artista
	 * @return Lista de artistas
	 */	
	public static ResultSet recuperarArtistasNoRelacionados(long artista)	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador y devolvemos el ResultSet obtenido :
		
		return OperacionesLectura.dameArtistasNoRelacionados(artista);

	}	

	
	/**Dados dos artistas, elimina la relación entre ellos
	 * @param artista1
	 * @param artista2
	 */
	public static void eliminarRelacionArtistas(long art1, long art2)	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador. Eliminamos los dos sentidos de la relación :
		
		 OperacionesEscritura.eliminaRelacion(art1, art2);

	}	

	
	
	
	/**Dados dos artistas, crea la relación entre ellos
	 * @param art1
	 * @param art2
	 */
	public static void crearRelacionArtistas(long art1, long art2)	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador. Creamos los dos sentidos de la relación :
		
		 OperacionesEscritura.creaRelacion(art1, art2);

	}		

	
	
	/**Crea n artista con los datos obtenidos
	 * @param nombre
	 * @param año
	 * @param estilo
	 * @return identificador
	 */
	public static long darAltaArtista(String nombre, int año, String estilo)	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador. Creamos el artista y la relación artista-estilo :
		
		 return OperacionesEscritura.creaArtista( nombre,  año, estilo);

	}	

	
	
	/**Elimina artista del maestro
	 * @param idArtista
	 * @throws SQLException para controlar error SQL general
	 * @throws com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException para controlar error SQL de violación de integridad referencial de la BBDD
	 */
	public static void darBajaArtista(long idArtista ) throws SQLException, com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador. Borramos el artista  :
		
		 OperacionesEscritura.eliminaArtista( idArtista);

	}		
	
	
}
