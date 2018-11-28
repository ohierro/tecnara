package controlador;

import java.sql.*;
import java.util.ArrayList;
import modelo.*;



/**Desde el paquete controlador, define los métodos que gestionan los eventos de la ventana 1
 * @author Alfonso Aledo
 *
 */
public class GestionVentana1 {

	/**
	 * Recupera de la BBDD la relación completa de estilos disponibles 
	 * @return Lista de Strings con todos los estilos
	 */
	public static ArrayList<String> recuperaListaEstilos()	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador y convertimos el ResultSet obtenido a una lista :
		
		ResultSet rs = OperacionesLectura.dameListaEstilos();
		ArrayList<String> listaEstilos = new ArrayList();
		
		if (rs!=null)	{
			
				try {
					while(rs.next())	{
					listaEstilos.add(rs.getString("nombreEstilo"));	
					}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("ERROR AL MANEJAR RESULTSET CON LISTA DE ESTILOS");
				e.printStackTrace();
			}
			
		}
		
		return listaEstilos;
	}
	

	
	/**Arrancado por el evento de ventana, encarga al modelo la ejecución de la sentencia de alta
	 * @param nombre 
	 * @param año
	 */
	public static void darAltaPersona(String nombre, int año)	{ 

//		Invocamos la ejecución de la consulta SQL correspondiente en el paquete Controlador. Creamos la persona en el maestro GENTE :
		
		 OperacionesEscritura.creaPersona( nombre,  año);

	}		
	
}
