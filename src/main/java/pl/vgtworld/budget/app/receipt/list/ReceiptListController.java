package pl.vgtworld.budget.app.receipt.list;

import pl.vgtworld.budget.app.receipt.ReceiptRepository;
import pl.vgtworld.budget.app.receipt.ReceiptWithStoreDto;
import pl.vgtworld.budget.storage.receipts.ReceiptService;
import pl.vgtworld.budget.storage.stores.StoreService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ReceiptListController implements Serializable {

	@EJB
	private ReceiptService receiptService;

	@EJB
	private StoreService storeService;

	@EJB
	private ReceiptRepository receiptRepository;

	private List<ReceiptWithStoreDto> receiptList;

	private int resultsPerPage;

	private int pageNumber;

	private long maxPageNumber;

	public List<ReceiptWithStoreDto> getReceiptList() {
		return receiptList;
	}

	public int getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(int resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public long getMaxPageNumber() {
		return maxPageNumber;
	}

	public boolean isPaginationEnabled() {
		return pageNumber > 0 && resultsPerPage > 0 && maxPageNumber > 1;
	}

	public String init() {
		if (!validateParameters()) {
			return "index?faces-redirect=true";
		}
		if (resultsPerPage != 0) {
			if (pageNumber == 0) {
				pageNumber = 1;
			}
			long receiptCount = receiptService.countNotDeleted();
			maxPageNumber = receiptCount / resultsPerPage + (receiptCount % resultsPerPage != 0 ? 1 : 0);
		}
		loadReceiptList();
		return null;
	}

	public void moveReceiptToTrash(int receiptId) {
		receiptService.moveToTrash(receiptId);
		loadReceiptList();
	}

	private boolean validateParameters() {
		if (pageNumber < 0) {
			return false;
		}
		if (resultsPerPage < 0) {
			return false;
		}
		return true;
	}

	private void loadReceiptList() {
		receiptList = receiptRepository.listNewestReceipts(resultsPerPage * (pageNumber - 1), resultsPerPage);
	}

}
