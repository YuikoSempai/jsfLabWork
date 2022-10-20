package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("inputValidator")
public class inputValidator implements Validator {
    public inputValidator() {
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {

    }
}
