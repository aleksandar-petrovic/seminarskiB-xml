package connection;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Database;

public class DBConnection {

	public static String URI = "xmldb:exist://nastava.is.pmf.uns.ac.rs:8080/exist/xmlrpc";

	static {
		String driver = "org.exist.xmldb.DatabaseImpl";

		try {

			Class cl = Class.forName(driver);
			Database database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
