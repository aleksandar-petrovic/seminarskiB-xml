package crud;

import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import connection.DBConnection;

public class ExistManager {

	public static final String COLLECTION = "/db/AleksandarPetrovic";
	public static final String URI = DBConnection.URI + "/db/AleksandarPetrovic";

	public static String saveNode(Node n) {
		try {
			Collection col = DatabaseManager.getCollection(URI, "admin", "xmlbp");
			XMLResource document = (XMLResource) col.createResource(null, "XMLResource");
			document.setContentAsDOM(n);
			System.out.print("storing document " + document.getId() + "...");
			col.storeResource(document);
			System.out.println("ok.");
			col.close();
			return document.getDocumentId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void remove(String documentId) {
		try {
			Collection col = DatabaseManager.getCollection(URI, "admin", "xmlbp");
			Resource res = col.getResource(documentId);
			col.removeResource(res);

			System.out.println("removing document " + res.getId());
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
