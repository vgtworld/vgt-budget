package pl.vgtworld.budget.app.storeedit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.storeedit.dto.StoreForm;
import pl.vgtworld.budget.services.dto.stores.StoreDto;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class StoreEditController {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreEditController.class);

	@EJB
	private StoreService storeService;

	private StoreForm store = new StoreForm();

	public StoreForm getStore() {
		return store;
	}

	public void setStore(StoreForm store) {
		this.store = store;
	}

	public String submitForm() {
		LOGGER.debug("Submitted new store form: {}", store);
		storeService.createNewStore(asStoreDto(store));
		return "store-list?faces-redirect=true";
	}

	private StoreDto asStoreDto(StoreForm store) {
		if (store == null) {
			return null;
		}
		StoreDto dto = new StoreDto();
		dto.setName(store.getStoreName());
		dto.setCity(store.getStoreCity());
		dto.setAddress(store.getStoreAddress());
		return dto;
	}

}
