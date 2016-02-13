package pl.vgtworld.budget.services.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.dto.receipts.NewReceipt;
import pl.vgtworld.budget.services.dto.receipts.ReceiptItem;
import pl.vgtworld.budget.storage.receipts.Receipt;
import pl.vgtworld.budget.storage.receipts.ReceiptDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class ReceiptService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptService.class);

	@EJB
	private ReceiptDao receiptDao;

	public ReceiptItem findById(int id) {
		LOGGER.debug("Find receipt by id: {}", id);
		return asReceiptItem(receiptDao.findById(id));
	}

	public int createNewReceipt(NewReceipt receipt) {
		LOGGER.debug("Create new receipt: {}", receipt);
		Receipt entity = new Receipt();
		entity.setStoreId(receipt.getStoreId());
		entity.setPurchaseDate(receipt.getPurchaseDate());
		entity.setTotalAmount(receipt.getTotalAmount());
		entity.setCreatedAt(new Date());
		return receiptDao.create(entity);
	}

	private static ReceiptItem asReceiptItem(Receipt receipt) {
		if (receipt == null) {
			return null;
		}
		ReceiptItem result = new ReceiptItem();
		result.setId(receipt.getId());
		result.setStoreId(receipt.getStoreId());
		result.setPurchaseDate(receipt.getPurchaseDate());
		result.setTotalAmount(receipt.getTotalAmount());
		result.setCreatedAt(receipt.getCreatedAt());
		return result;
	}

}
