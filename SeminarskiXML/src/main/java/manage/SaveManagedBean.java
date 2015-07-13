package manage;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import model.MyModel;

import org.primefaces.context.RequestContext;

import crud.DatabaseAccess;

/**
 * @author <a href="petrovic.aleks@outlook.com">Aleksandar Petrovic</a>
 */

@ManagedBean
@RequestScoped
public class SaveManagedBean {

	MyModel mymodel;

	@PostConstruct
	private void init() {
		mymodel = new MyModel();
	}

	public void submit() {
		DatabaseAccess.saveDocument(mymodel);
	}

	public MyModel getModel() {
		return mymodel;
	}

	public void setModel(MyModel mymodel) {
		this.mymodel = mymodel;
	}

}
