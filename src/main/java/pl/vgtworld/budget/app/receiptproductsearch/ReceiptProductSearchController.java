package pl.vgtworld.budget.app.receiptproductsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.services.storage.ReceiptService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ReceiptProductSearchController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptProductSearchController.class);

	@EJB
	private ReceiptService receiptService;

	private Integer receiptId;

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public String initData() {
		if (receiptId != null) {
			if (receiptService.findById(receiptId) == null) {
				LOGGER.debug("Receipt with provided id does not exist. ID:{}", receiptId);
				return "receipt-list?faces-redirect=true";
			}
			return null;
		}
		LOGGER.warn("Receipt id not available. Unable to fill form.");
		return "receipt-list?faces-redirect=true";
	}

}
