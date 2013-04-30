package fr.isen.cir56.group3_genetic.Utils.XMLTools;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Wasp
 */
public final class XMLTools {

	private XMLTools() {
	}

	/**
	 * Serialisation d'un objet dans un fichier
	 *
	 * @param object objet a serialiser
	 * @param filename chemin du fichier
	 */
	public static void encodeToFile(Object object, String fileName) throws FileNotFoundException, IOException {
		// Instanciation de la classe XStream
		XStream xstream = new XStream(new DomDriver());

		FileOutputStream fos = new FileOutputStream(fileName);
		try {
			//Sérialisation
			xstream.toXML(object, fos);
		} finally {
			// On s'assure de fermer le flux quoi qu'il arrive
			fos.close();
		}
	}

	public static Object decodeFromFile(String fileName) throws FileNotFoundException, IOException {
		Object object = null;

		// Instanciation de la classe XStream
		XStream xstream = new XStream(new DomDriver());

		FileInputStream fis = new FileInputStream(fileName);
		String xml = FisToString(fis, "UTF-8");

		// Désérialisation de l'objet
		object = xstream.fromXML(xml);

		return object;
	}

	private static String FisToString(FileInputStream fis, String utF8) throws IOException {
		String returnString = "";

		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader buffreader = new BufferedReader(isr);

		String readString = buffreader.readLine();
		while (readString != null) {
			returnString = returnString + readString;
			readString = buffreader.readLine();
		}

		isr.close();
		return returnString;

	}
}
