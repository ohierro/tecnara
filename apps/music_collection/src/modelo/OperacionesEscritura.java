package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

/**Reside en el paquete MODELO y compila todas las operaciones de escritura de información que el aplicativo realiza contra la BBDD
 * @author Alfonso Aledo
 *
 */
public class OperacionesEscritura {

//	Definimos aquí, de forma centralizada, las sentencias SQL que se van a ejecutar en los métodos
//	que se van definiendo más abajo.

	final static String SQLasignaGente = "UPDATE GENTE SET IDARTISTAGENTE=? WHERE IDGENTE=?";	
	
	final static String SQLeliminaRelacion = "DELETE FROM RELACIONARTISTAS WHERE IDARTISTA1=? AND IDARTISTA2=?";
	
	final static String SQLcreaRelacion = "INSERT INTO RELACIONARTISTAS (IDARTISTA1, IDARTISTA2) VALUES(?,?)";
	
	final static String SQLcreaArtista = "INSERT INTO ARTISTAS (NOMBREARTISTA, AÑOARTISTA) VALUES(?,?)";
	
	final static String SQLasignaEstilo = "INSERT INTO ARTISTASESTILOS (IDART, IDEST) VALUES(?,?)";	
	
	final static String SQLeliminaArtista = "DELETE FROM ARTISTAS WHERE IDARTISTA=?";	
	
	final static String SQLcreaPersona = "INSERT INTO GENTE (NOMBREGENTE, AÑOGENTE) VALUES(?,?)";
	

	
	
/**Asigna una persona a un artista
 * @param gente
 * @param artista
 */
public static void asignaGente(long gente, long artista)	{
		
		Connection con = Conexion.dameConexion();
		PreparedStatement st=null;
		try {
				st = con.prepareStatement(SQLasignaGente);
				st.setLong(1, artista);	
				st.setLong(2, gente);			
				st.executeUpdate();
		}
			
		 catch (SQLException e1) {
				System.out.println("SE HA PRODUCIDO UN ERROR AL PREPARAR SENTENCIAS SQL");
				e1.printStackTrace();

		}
		
		finally {
				try {	st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
		}
}		
		

/**Elimina la relación entre dos artistas ( en ambos sentidos a->b y b->a ) 
 * @param ArtistaA
 * @param ArtistaB
 */
public static void eliminaRelacion(long artA, long artB)	{
	
	Connection con = Conexion.dameConexion();
	PreparedStatement st=null;
	try {
			st = con.prepareStatement(SQLeliminaRelacion);
			st.setLong(1, artA);	
			st.setLong(2, artB);			
			st.executeUpdate();
			st.setLong(1, artB);	
			st.setLong(2, artA);			
			st.executeUpdate();			
	}
		
	catch (SQLException e1) {
			System.out.println("SE HA PRODUCIDO UN ERROR AL PREPARAR SENTENCIAS SQL");
			e1.printStackTrace();

	}
	finally {
		try {	st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
}



/**Crea relación entre dos artistas ( en ambos sentidos a->b y b->a ) 
 * @param artA
 * @param artB
 */
public static void creaRelacion(long artA, long artB)	{
	
	Connection con = Conexion.dameConexion();
	PreparedStatement st=null;
	try {
			st = con.prepareStatement(SQLcreaRelacion);
			st.setLong(1, artA);	
			st.setLong(2, artB);			
			st.executeUpdate();
			st.setLong(1, artB);	
			st.setLong(2, artA);			
			st.executeUpdate();			
			
	}
	 catch (SQLException e1) {
			System.out.println("SE HA PRODUCIDO UN ERROR AL PREPARAR SENTENCIAS SQL");
			e1.printStackTrace();

	}
	 finally {
		try {	st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
}
}



/**Da de alta un nuevo artista
 * @param nombre
 * @param año
 * @param estilo
 * @return Identificador
 */
public static long creaArtista(String nombre, int año, String estilo)	{
	
	Connection con = Conexion.dameConexion();
	PreparedStatement st=null;
	ResultSet rs=null;
	try {
		
//			1 - Creamos registro en maestro de artistas :
				
			st = con.prepareStatement(SQLcreaArtista, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, nombre);	
			st.setInt(2, año);	
			st.executeUpdate();

//			2 - Recuperamos el índice creado de forma automática por el autoincremento de la BBDD :
			
			long codigoArtista=0;
			rs = st.getGeneratedKeys();
			if (rs!=null)	if (rs.next()) codigoArtista = rs.getInt(1);

//			3 - Creamos la relacion del artista creado con el estilo introducido :
			
			st = con.prepareStatement(SQLasignaEstilo);
			st.setLong(1, codigoArtista);
			st.setLong(2, OperacionesLectura.dameCodigoEstilo(estilo));
			st.executeUpdate();			

//			4 - Devolvemos el índice del artista recién creado :			
			

			return codigoArtista;			
			
			
	}
		
	 catch (SQLException e1) {
			System.out.println("SE HA PRODUCIDO UN ERROR EN SENTENCIAS SQL");
			e1.printStackTrace();
			return 0;

	}
	
	finally {
		try {	st.close();rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
}
}




/**Da de baja un artista a aprtir de su identificador
 * @param artista
 * @throws SQLException  para controlar error SQL general
 * @throws com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException para controlar error SQL de violación de integridad referencial de la BBDD
 */
public static void eliminaArtista(long artista) throws SQLException, com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException	{
	
	Connection con = Conexion.dameConexion();
	PreparedStatement st=null;

			st = con.prepareStatement(SQLeliminaArtista);
			st.setLong(1, artista);	
			st.executeUpdate();

			st.close();
}	
	

/**Da de alta una persona en el fichero maestro de gente
 * @param nombre
 * @param año
 */
public static void creaPersona(String nombre, int año)	{
	
	Connection con = Conexion.dameConexion();
	PreparedStatement st=null;
	try {
				
			st = con.prepareStatement(SQLcreaPersona);
			st.setString(1, nombre);	
			st.setInt(2, año);	
			st.executeUpdate();
	
	}
		
	 catch (SQLException e) {
			System.out.println("SE HA PRODUCIDO UN ERROR EN SENTENCIAS SQL");
			e.printStackTrace();

	}
	 finally {
		try {	st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
}
}

}
