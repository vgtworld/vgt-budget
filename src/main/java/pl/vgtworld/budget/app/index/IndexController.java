package pl.vgtworld.budget.app.index;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;

@Named
@RequestScoped
public class IndexController {

	@EJB
	private IndexService indexService;

	public BigDecimal getSpendingThisMonth() {
		return indexService.getTotalAmountSumForCurrentMonth();
	}

	public BigDecimal getSpendingLastMonth() {
		return indexService.getTotalAmountSumForPreviousMonth();
	}

}
