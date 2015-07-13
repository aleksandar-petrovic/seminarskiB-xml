package manage;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import model.MyModel;

import org.primefaces.context.RequestContext;

import crud.DatabaseAccess;

/**
 * @author <a href="petrovic.aleks@outlook.com">Aleksandar Petrovic</a>
 */

@ManagedBean
@SessionScoped
public class SearchManagedBean {

	String broj;
	Date datumPrijema;
	String nadlezniOrgan;
	String granicniPrelaz;
	String vrstaPrijave;

	List<MyModel> documents;
	MyModel chosen;

	// action listener za parametar pri pozivu metode showDocument
	public void showDocumentListener(ActionEvent event) {
		String documentId = (String) event.getComponent().getAttributes().get("documentId");
		chosen = documents.get(documents.indexOf(new MyModel(documentId)));
	}

	public String showDocument() {
		return "/page/show/showDoc.xhtml?faces-redirect=true";
	}

	// action listener za parametar pri pozivu metode removeDocument
	public void removeDocumentListener(ActionEvent event) {
		String documentId = (String) event.getComponent().getAttributes().get("documentId");
		DatabaseAccess.removeDocument(documentId);
		documents.remove(new MyModel(documentId));
	}

	public String removeDocument() {
		return search();
	}

	public void changeDocument() {
		DatabaseAccess.changeDocument(chosen);
	}

	public String search() {
		documents = DatabaseAccess.getAllDocumentsByCriteria(broj, datumPrijema, nadlezniOrgan, granicniPrelaz, vrstaPrijave);

		return "searchDoc.xhtml?faces-redirect=true";
	}

	public List<MyModel> getDocuments() {
		return documents;
	}

	public void setDocuments(List<MyModel> documents) {
		this.documents = documents;
	}

	public MyModel getChosen() {
		return chosen;
	}

	public void setChosen(MyModel choosen) {
		this.chosen = choosen;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public Date getDatumPrijema() {
		return datumPrijema;
	}

	public void setDatumPrijema(Date datumPrijema) {
		this.datumPrijema = datumPrijema;
	}

	public String getNadlezniOrgan() {
		return nadlezniOrgan;
	}

	public void setNadlezniOrgan(String nadlezniOrgan) {
		this.nadlezniOrgan = nadlezniOrgan;
	}

	public String getGranicniPrelaz() {
		return granicniPrelaz;
	}

	public void setGranicniPrelaz(String granicniPrelaz) {
		this.granicniPrelaz = granicniPrelaz;
	}

	public String getVrstaPrijave() {
		return vrstaPrijave;
	}

	public void setVrstaPrijave(String vrstaPrijave) {
		this.vrstaPrijave = vrstaPrijave;
	}
}
