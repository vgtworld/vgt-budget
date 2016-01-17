package pl.vgtworld.budget.services.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.dto.stores.NewStore;
import pl.vgtworld.budget.services.dto.stores.StoreItem;
import pl.vgtworld.budget.storage.stores.Store;
import pl.vgtworld.budget.storage.stores.StoreDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class StoreService {

	private static final Logger LOGGER = LoggerFactory.getLogger(StoreService.class);

	@EJB
	private StoreDao storeDao;

	public void createNewStore(NewStore newStore) {
		LOGGER.debug("Create new store: {}", newStore);
		Store entity = new Store();
		entity.setName(newStore.getName());
		entity.setCity(newStore.getCity());
		entity.setAddress(newStore.getAddress());
		entity.setCreatedAt(new Date());
		entity.setDeleted(false);
		int id = storeDao.create(entity);
		LOGGER.debug("Saved store with id: {}", id);
	}

	public List<StoreItem> listAllStores() {
		LOGGER.debug("List all stores");
		return storeDao.listAll().stream().map(StoreService::asStoreItem).collect(Collectors.toList());
	}

	private static StoreItem asStoreItem(Store store) {
		if (store == null) {
			return null;
		}
		StoreItem result = new StoreItem();
		result.setId(store.getId());
		result.setName(store.getName());
		result.setCity(store.getCity());
		result.setAddress(store.getAddress());
		return result;
	}

}
