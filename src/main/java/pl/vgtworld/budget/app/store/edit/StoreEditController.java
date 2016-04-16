package pl.vgtworld.budget.app.store.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.store.edit.dto.StoreForm;
import pl.vgtworld.budget.storage.stores.StoreService;
import pl.vgtworld.budget.storage.stores.StoreDto;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class StoreEditController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreEditController.class);

	@EJB
	private StoreService storeService;

	private StoreForm store = new StoreForm();

	private Integer storeId;

	public StoreForm getStore() {
		return store;
	}

	public void setStore(StoreForm store) {
		this.store = store;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String submitForm() {
		LOGGER.debug("Submitted store form: {}", store);
		StoreDto dto = asStoreDto(store);
		if (storeId == null) {
			storeService.createNewStore(dto);
		} else {
			dto.setId(storeId);
			storeService.updateStore(dto);
		}
		return "store-list?faces-redirect=true";
	}

	public void fillFormWithEditedStoreData() {
		if (storeId != null) {
			StoreDto dto = storeService.findById(storeId);
			if (dto != null) {
				store.setStoreName(dto.getName());
				store.setStoreCity(dto.getCity());
				store.setStoreAddress(dto.getAddress());
				return;
			}
			LOGGER.debug("Store with provided id does not exist. ID:{}", storeId);
			storeId = null;
			return;
		}
		LOGGER.warn("Store id not available. Unable to fill form.");
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
