package pl.vgtworld.budget.app.index;

import pl.vgtworld.budget.storage.receipts.ReceiptService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Calendar;

@Stateless
public class IndexService {

	@EJB
	private ReceiptService receiptService;

	public BigDecimal getTotalAmountSumForCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return receiptService.getTotalAmountSum(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
	}

	public BigDecimal getTotalAmountSumForPreviousMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return receiptService.getTotalAmountSum(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
	}

}
