package pl.vgtworld.budget.app.storelist;

import pl.vgtworld.budget.services.dto.stores.StoreItem;
import pl.vgtworld.budget.services.storage.StoreService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean
public class StoreListController {

	@EJB
	private StoreService storeService;

	private List<StoreItem> storeList;

	public List<StoreItem> getStoreList() {
		return storeList;
	}

	public void setStoreList(List<StoreItem> storeList) {
		this.storeList = storeList;
	}

	@PostConstruct
	public void loadStores() {
		storeList = storeService.listAvailableStores();
	}
}
