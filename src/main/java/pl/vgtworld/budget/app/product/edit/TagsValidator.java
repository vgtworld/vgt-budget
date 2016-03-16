package pl.vgtworld.budget.app.product.edit;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.List;

@FacesValidator("budget.productEdit.tagsValidator")
public class TagsValidator implements Validator {

	@Override
	@SuppressWarnings("unchecked")
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}
		if (!(value instanceof List)) {
			FacesMessage message = new FacesMessage("Unexpected object type");
			throw new ValidatorException(message);
		}
		List<String> tags = (List<String>) value;
		if (tags.isEmpty()) {
			FacesMessage message = new FacesMessage("There should be at least one tag added for product");
			throw new ValidatorException(message);
		}
		for (String tag : tags) {
			int tagLength = tag.length();
			if (tagLength < 3) {
				FacesMessage message = new FacesMessage("Tag min length is 3 characters");
				throw new ValidatorException(message);
			}
			if (tagLength > 100) {
				FacesMessage message = new FacesMessage("Tag max length is 100 characters");
				throw new ValidatorException(message);
			}
		}
	}

}
