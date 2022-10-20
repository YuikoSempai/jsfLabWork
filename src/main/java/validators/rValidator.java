package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("rValidator")
public class rValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        try{
            Integer newValue = (Integer) value;
            if(value.equals("") || (value.toString()).length()>10){
                if(newValue!=1 && newValue!=2 && newValue!=3 && newValue!=4 && newValue!=5) {
                    throw new Exception();
                }
            }
        }catch (Exception e){
            FacesMessage msg = new FacesMessage("Input validation failed");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
