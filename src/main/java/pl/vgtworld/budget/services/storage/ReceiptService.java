package pl.vgtworld.budget.services.storage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
import pl.vgtworld.budget.storage.receipts.Receipt;
import pl.vgtworld.budget.storage.receipts.ReceiptDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

	public List<ReceiptDto> listNewestReceipts() {
		List<Receipt> entities = receiptDao.listNewest();
		return entities.stream().map(ReceiptService::asReceiptDto).collect(Collectors.toList());
	}

	public List<ReceiptDto> listDeletedReceipts() {
		List<Receipt> entities = receiptDao.listDeleted();
		return entities.stream().map(ReceiptService::asReceiptDto).collect(Collectors.toList());
	}

	public void moveToTrash(int receiptId) {
		receiptDao.moveToTrash(receiptId);
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
