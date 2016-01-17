package pl.vgtworld.budget.app.storecreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.storecreate.dto.NewStoreForm;
import pl.vgtworld.budget.app.storecreate.validator.NewStoreValidationResult;
import pl.vgtworld.budget.app.storecreate.validator.NewStoreValidator;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
public class StoreCreateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreCreateController.class);

	@EJB
	private StoreService storeService;

	private NewStoreForm store = new NewStoreForm();

	private List<String> submitErrors = new ArrayList<>();

	public NewStoreForm getStore() {
		return store;
	}

	public void setStore(NewStoreForm store) {
		this.store = store;
	}

	public List<String> getSubmitErrors() {
		return submitErrors;
	}

	public void setSubmitErrors(List<String> submitErrors) {
		this.submitErrors = submitErrors;
	}

	public String submitForm() {
		LOGGER.debug("Submitted new store form: {}", store);
		NewStoreValidator validator = new NewStoreValidator();
		NewStoreValidationResult result = validator.validate(store);
		if (result.isValid()) {
			storeService.createNewStore(result.getStore());
			return "store-create-success.xhtml";
		} else {
			submitErrors = result.getErrors();
			return "store-create.xhtml";
		}
	}

}
