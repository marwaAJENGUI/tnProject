package tn.esprit.spring.frontcontroller;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("tn.esprit.spring.frontcontroller.EmailValidator")
public class EmailValidator implements Validator {
	private static final String EMAIL_PATTERN = ".+@.+\\..+";
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(value.toString());
		if(!matcher.matches()) {
			FacesMessage msg = new FacesMessage("     EMail invalid !");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
