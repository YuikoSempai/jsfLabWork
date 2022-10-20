package validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("xValidator")
public class xValidator implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        try{
            Double newValue = (Double) value;
            if(value.equals("") || (value.toString()).length()>10 || newValue<=-4 || newValue>=4){
                throw new Exception();
            }
        }catch (Exception e){
            FacesMessage msg = new FacesMessage("Input validation failed");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
