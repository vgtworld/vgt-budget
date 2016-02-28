package pl.vgtworld.budget.app.productedit;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.util.ArrayList;
import java.util.List;

@FacesConverter("budget.productEdit.tagsConverter")
public class TagsConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		List<String> tags = new ArrayList<>();
		if (value == null || value.trim().length() == 0) {
			return tags;
		}
		String[] values = value.split(",");
		for (String tag : values) {
			tags.add(tag.trim());
		}
		return tags;
	}

	@Override
	@SuppressWarnings("unchecked")
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		if (!(value instanceof List)) {
			FacesMessage message = new FacesMessage("Unexpected object type");
			throw new ConverterException(message);
		}
		List<String> tags = (List<String>) value;
		StringBuilder builder = new StringBuilder();
		for (String tag : tags) {
			builder.append(", ").append(tag);
		}
		String output = builder.toString();
		return output.length() > 0 ? output.substring(1) : "";
	}

}
