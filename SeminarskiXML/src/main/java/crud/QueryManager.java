package crud;

import java.util.ArrayList;
import java.util.List;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import org.xmldb.api.modules.XQueryService;

import connection.DBConnection;

public class QueryManager {

	private String collection;

	public QueryManager(String collection) {
		this.collection = collection;
	}

	public List<XMLResource> performXpath(String query) {
		try {
			Collection col = DatabaseManager.getCollection(DBConnection.URI
					+ collection);
			XPathQueryService service = (XPathQueryService) col.getService(
					"XPathQueryService", "1.0");
			service.setProperty("indent", "yes");
			ResourceSet result = service.query(query);
			ResourceIterator iter = result.getIterator();
			List<XMLResource> resources = new ArrayList<XMLResource>();
			while (iter.hasMoreResources()) {
				resources.add((XMLResource) iter.nextResource());
			}
			col.close();
			return resources;
		} catch (Exception e) {
			System.out.println("Greska QueryManager");
			e.printStackTrace();
			return null;
		}
	}

}
