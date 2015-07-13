package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author <a href="petrovic.aleks@outlook.com">Aleksandar Petrovic</a>
 */

@FacesValidator("integerValidator")
public class IntegerValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object obj) throws ValidatorException {

		if (!((String) obj).equals(""))
			try {
				Integer.parseInt((String) obj);
			} catch (NumberFormatException e) {
				FacesMessage msg = new FacesMessage("Not a number", "Неисправно унет број");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}
	}

}
