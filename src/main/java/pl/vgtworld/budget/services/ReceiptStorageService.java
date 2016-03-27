package pl.vgtworld.budget.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.core.utils.calendar.DateRangeUtil;
import pl.vgtworld.budget.core.utils.calendar.MonthRangeDto;
import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
import pl.vgtworld.budget.storage.receipts.Receipt;
import pl.vgtworld.budget.storage.receipts.ReceiptDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Date;

@Stateless
public class ReceiptStorageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptStorageService.class);

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

	public BigDecimal getTotalAmountSum(int year, int month) {
		MonthRangeDto monthRange = DateRangeUtil.findFirstAndLastDayOfMonth(year, month);
		return receiptDao.getTotalAmountSum(monthRange.getStartDate(), monthRange.getEndDate());
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
