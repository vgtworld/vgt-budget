package pl.vgtworld.budget.app.index;

import pl.vgtworld.budget.services.ReceiptStorageService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.Calendar;

@Stateless
public class IndexService {

	@EJB
	private ReceiptStorageService receiptStorageService;

	public BigDecimal getTotalAmountSumForCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return receiptStorageService.getTotalAmountSum(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
	}

	public BigDecimal getTotalAmountSumForPreviousMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return receiptStorageService.getTotalAmountSum(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
	}

}
