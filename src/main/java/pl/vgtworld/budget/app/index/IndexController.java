package pl.vgtworld.budget.app.index;

import pl.vgtworld.budget.services.storage.ReceiptService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class IndexController {

	@EJB
	private ReceiptService receiptService;

	public BigDecimal getSpendingThisMonth() {
		return receiptService.getTotalAmountSumForCurrentMonth();
	}

}
