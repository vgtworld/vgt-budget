package pl.vgtworld.budget.services.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
import pl.vgtworld.budget.storage.receipts.Receipt;
import pl.vgtworld.budget.storage.receipts.ReceiptDao;
import pl.vgtworld.budget.storage.receipts.ReceiptWithStoreDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Stateless
public class ReceiptService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptService.class);

	@EJB
	private ReceiptDao receiptDao;

	public ReceiptDto findById(int id) {
		LOGGER.debug("Find receipt by id: {}", id);
		return asReceiptDto(receiptDao.findById(id));
	}

	public int createNewReceipt(ReceiptDto receipt) {
		LOGGER.debug("Create new receipt: {}", receipt);
		Receipt entity = new Receipt();
		entity.setStoreId(receipt.getStoreId());
		entity.setPurchaseDate(receipt.getPurchaseDate());
		entity.setTotalAmount(BigDecimal.ZERO);
		entity.setDeleted(false);
		entity.setCreatedAt(new Date());
		return receiptDao.create(entity);
	}

	public void updateReceipt(ReceiptDto receipt) {
		Receipt entity = receiptDao.findById(receipt.getId());
		entity.setStoreId(receipt.getStoreId());
		entity.setPurchaseDate(receipt.getPurchaseDate());
	}

	public BigDecimal updateReceiptTotalAmount(int receiptId, BigDecimal total) {
		Receipt entity = receiptDao.findById(receiptId);
		entity.setTotalAmount(total);
		return total;
	}

	public List<ReceiptWithStoreDto> listNewestReceipts() {
		return receiptDao.listNewest();
	}

	public List<ReceiptWithStoreDto> listDeletedReceipts() {
		return receiptDao.listDeleted();
	}

	public void moveToTrash(int receiptId) {
		Receipt entity = receiptDao.findById(receiptId);
		entity.setDeleted(true);
	}

	public void restoreFromTrash(int receiptId) {
		Receipt entity = receiptDao.findById(receiptId);
		entity.setDeleted(false);
	}

	public void emptyTrash() {
		receiptDao.removeMarkedAsDeleted();
	}

	private static ReceiptDto asReceiptDto(Receipt receipt) {
		if (receipt == null) {
			return null;
		}
		ReceiptDto result = new ReceiptDto();
		result.setId(receipt.getId());
		result.setStoreId(receipt.getStoreId());
		result.setPurchaseDate(receipt.getPurchaseDate());
		result.setTotalAmount(receipt.getTotalAmount());
		result.setCreatedAt(receipt.getCreatedAt());
		return result;
	}

}
