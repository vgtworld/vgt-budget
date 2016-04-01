package pl.vgtworld.budget.app.receipt.list;

import pl.vgtworld.budget.app.receipt.ReceiptRepository;
import pl.vgtworld.budget.app.receipt.ReceiptWithStoreDto;
import pl.vgtworld.budget.services.ReceiptStorageService;
import pl.vgtworld.budget.services.StoreStorageService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ReceiptListController {

	@EJB
	private ReceiptStorageService receiptStorageService;

	@EJB
	private StoreStorageService storeStorageService;

	@EJB
	private ReceiptRepository receiptRepository;

	private List<ReceiptWithStoreDto> receiptList;

	private Integer resultsPerPage;

	private Integer pageNumber;

	public List<ReceiptWithStoreDto> getReceiptList() {
		return receiptList;
	}

	public Integer getResultsPerPage() {
		return resultsPerPage;
	}

	public void setResultsPerPage(Integer resultsPerPage) {
		this.resultsPerPage = resultsPerPage;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String init() {
		if (!validateParameters()) {
			return "index?faces-redirect=true";
		}
		loadReceiptList();
		return null;
	}

	public void moveReceiptToTrash(int receiptId) {
		receiptStorageService.moveToTrash(receiptId);
		loadReceiptList();
	}

	private boolean validateParameters() {
		if (pageNumber != null && pageNumber < 1) {
			return false;
		}
		if (resultsPerPage != null && resultsPerPage < 1) {
			return false;
		}
		return true;
	}

	private void loadReceiptList() {
		Integer offset = resultsPerPage != null && pageNumber != null ? resultsPerPage * (pageNumber - 1) : null;
		receiptList = receiptRepository.listNewestReceipts(offset, resultsPerPage);
	}

}
