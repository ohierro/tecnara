package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**Reside en el paquete MODELO y compila todas las operaciones de recuperación de información que el aplicativo realiza contra la BBDD
 * @author Alfonso Aledo
 *
 */
public class OperacionesLectura {

	
//		Definimos aquí, de forma centralizada, las sentencias SQL que se van a ejecutar en los métodos
//		que se van definiendo más abajo.
	
	
		final static String SQLdameListaEstilos = "SELECT NOMBREESTILO FROM ESTILOS DISTINTC";
		
		final static String SQLdameArtistasTodos = "SELECT IDARTISTA, NOMBREARTISTA, AÑOARTISTA FROM ARTISTAS";	
		
		final static String SQLdameArtistasPorEstilo = "SELECT IDARTISTA, NOMBREARTISTA, AÑOARTISTA "
				+ "FROM ARTISTAS, ARTISTASESTILOS, ESTILOS WHERE IDARTISTA=IDART AND IDEST=IDESTILO AND NOMBREESTILO=?";
		
		final static String SQLdameArtistasPorNombre = "SELECT IDARTISTA, NOMBREARTISTA, AÑOARTISTA "
				+ "FROM ARTISTAS WHERE LOWER(NOMBREARTISTA) LIKE ?";		
		
		final static String SQLdamePersonasDisponibles = "SELECT NOMBREGENTE, AÑOGENTE, IDGENTE FROM GENTE WHERE IDARTISTAGENTE IS NULL";
		
		final static String SQLdameArtistasRelacionados = "SELECT NOMBREARTISTA, AÑOARTISTA, IDARTISTA FROM ARTISTAS,RELACIONARTISTAS WHERE IDARTISTA=IDARTISTA2 AND IDARTISTA1=?";
		
		final static String SQLdameArtistasNoRelacionados = "SELECT NOMBREARTISTA, AÑOARTISTA, IDARTISTA FROM ARTISTAS "
				+ "WHERE IDARTISTA NOT IN ( SELECT IDARTISTA2 FROM RELACIONARTISTAS WHERE IDARTISTA1=? ) AND IDARTISTA<>? ";
		
		final static String SQLdameCodigoEstilo = "SELECT IDESTILO FROM ESTILOS WHERE NOMBREESTILO=?";		
		
		
		
	/** Accede a la BBDD para recuperar la colección completa de estilos
	 * @return  ResultSet obtenido con la lista
	 */
	public static ResultSet dameListaEstilos()	{

		Connection con = Conexion.dameConexion();
		
		try {		
		Statement st = con.createStatement();
		return st.executeQuery(SQLdameListaEstilos);
		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR SENTENCIA EN BBDD");
			e.printStackTrace();
			return null;
			}
	}
	
	
	

	/**Recupera de la BBDD los artistas que cumplen los criterios de los parámetros y los devuelve :
	 * @param tipo :  Tipo de Selección
	 * @param dato : Dato seleccionado
	 * @return ResultSet con los datos de los artistas recuperados.
	 */
	public static ResultSet dameArtistasSeleccionados(int tipo, String dato)	{
		
		Connection con = Conexion.dameConexion();
		PreparedStatement st=null;

//		Manipulamos las tres sentencias SQL para poder ejecutarlas en una misma sentencia, y también devolver
//		un ResultSet único con la ejecución de las tres.
//		De esta forma, para el método llamador, la obtención de una lista de artistas resulta transparente
//		al método de selección utilizado :
		
		try {
			
			switch (tipo)	{
			
			case 1 : 	st = con.prepareStatement(SQLdameArtistasTodos);
						break;
			case 2 :	st = con.prepareStatement(SQLdameArtistasPorEstilo);
						st.setString(1, dato);			
						break;
			case 3 :	st = con.prepareStatement(SQLdameArtistasPorNombre);
						st.setString(1, "%" + dato + "%");
			}		

			ResultSet rs = st.executeQuery();
//			if (rs!=null)	while (rs.next()) System.out.println("" + rs.getLong(1) + rs.getString(2) + rs.getInt(3));			
			return rs;
			
		} catch (SQLException e1) {
				System.out.println("SE HA PRODUCIDO UN ERROR AL PREPARAR SENTENCIAS SQL");
				e1.printStackTrace();
				return null;
		}
}		
		

	
	
	
	/** Accede a la BBDD para recuperar la lista de personas que no tienen nigún artista asociado
	 * @return Devuelve el ResultSet obtenido con la lista
	 */
	public static ResultSet damePersonasDisponibles()	{

		Connection con = Conexion.dameConexion();
		
		try {		
		Statement st = con.createStatement();
		return st.executeQuery(SQLdamePersonasDisponibles);
		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR SENTENCIA EN BBDD");
			e.printStackTrace();
			return null;
			}
	}	

	
	/** Accede a la BBDD para recuperar la lista de artistas que stán relacionados con el artista seleccionado :
	 * @return Devuelve el ResultSet obtenido con la lista
	 */
	public static ResultSet dameArtistasRelacionados(long artista)	{

		Connection con = Conexion.dameConexion();
		
		try {		
		PreparedStatement st = con.prepareStatement(SQLdameArtistasRelacionados);
		 st.setLong(1, artista);	
		return st.executeQuery();
		
		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR SENTENCIA EN BBDD");
			e.printStackTrace();
			return null;
			}
	}		
	
	/** Accede a la BBDD para recuperar la lista de artistas que stán relacionados con el artista seleccionado :
	 * @return Devuelve el ResultSet obtenido con la lista
	 */
	public static ResultSet dameArtistasNoRelacionados(long artista)	{

		Connection con = Conexion.dameConexion();
		
		try {		
		PreparedStatement st = con.prepareStatement(SQLdameArtistasNoRelacionados);
		 st.setLong(1, artista);	
		 st.setLong(2, artista);
		return st.executeQuery();
		
		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR SENTENCIA EN BBDD");
			e.printStackTrace();
			return null;
			}
	}	

	
	/** Accede a la BBDD para recuperar el identificador del estilo partiendo desde su nombre
	 * @return Devuelve el código de estilo
	 */
	public static Long dameCodigoEstilo(String nombreEstilo)	{

		Connection con = Conexion.dameConexion();
		Long codigoEstilo=0L;	
		PreparedStatement st=null;
		
		try {		
		st = con.prepareStatement(SQLdameCodigoEstilo);
		st.setString(1, nombreEstilo);	
		ResultSet rs = st.executeQuery();
		if (rs!=null)	if (rs.next()) codigoEstilo = rs.getLong("IDESTILO");
		
		} catch (SQLException e) {
			System.out.println("ERROR AL EJECUTAR SENTENCIA EN BBDD");
			e.printStackTrace();
			}

	 finally {
		try {	st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return codigoEstilo;			
		}
	
}
	
}
