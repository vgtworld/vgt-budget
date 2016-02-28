package pl.vgtworld.budget.app.storelist;

import pl.vgtworld.budget.services.dto.stores.StoreDto;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class StoreListController {

	@EJB
	private StoreService storeService;

	private List<StoreDto> storeList;

	public List<StoreDto> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<StoreDto> storeList) {
		this.storeList = storeList;
	}

	@PostConstruct
	public void loadStores() {
		storeList = storeService.listAvailableStores();
	}
}
