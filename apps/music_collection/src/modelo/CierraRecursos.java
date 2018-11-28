package modelo;



import java.sql.Connection;
import java.sql.SQLException;


/** Cierra los recursos de BBDD y su acceso antes de finalizar la aplicación
 * @author Alfonso
 *
 */

public class CierraRecursos {

	/**Cierra todos recursos antes de finalizar la aplicación
	 * 
	 */
	public static void cierraTodo()	{
		
		Connection con = Conexion.dameConexion();
		if (con!=null)
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("SE HA PRODUCIDO UN ERROR AL CERRAR LA CONEXION DE BBDD");
				e.printStackTrace();
			}
		finally	{	System.exit(0);     }
		
	}
	
	
	
}
