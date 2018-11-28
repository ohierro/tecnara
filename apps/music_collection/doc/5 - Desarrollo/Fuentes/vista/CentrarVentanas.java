package vista;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**Utilidad para centrar las diferentes ventanas de una aplicación
 * @author Alfonso Aledo
 *
 */
public class CentrarVentanas {

	
	/**Centramos la ventana llamadora a partir de la resolución específica del equipo que ejecuta la aplicación
	 * @param ventana
	 * @param anchoVentana
	 * @param altoVentana
	 */
	public static void centraVentana(JFrame ventana, int anchoVentana, int altoVentana)	{
		
		Toolkit tool = Toolkit.getDefaultToolkit();	
		Dimension dim = tool.getScreenSize();
		int anchoPantalla = dim.width;
		int altoPantalla = dim.height;
		ventana.setBounds((anchoPantalla-anchoVentana)/2, (altoPantalla-altoVentana)/2, anchoVentana, altoVentana);

	}
	
	
}
