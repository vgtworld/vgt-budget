package pl.vgtworld.budget.app.storecreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.storecreate.dto.NewStoreForm;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class StoreCreateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreCreateController.class);

	private NewStoreForm store = new NewStoreForm();

	public NewStoreForm getStore() {
		return store;
	}

	public void setStore(NewStoreForm store) {
		this.store = store;
	}

	public void submitForm() {
		LOGGER.debug("Submitted new store form: {}", store);
	}

}
