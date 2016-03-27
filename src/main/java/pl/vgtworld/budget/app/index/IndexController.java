package pl.vgtworld.budget.app.index;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;

@Named
@RequestScoped
public class IndexController {

	@EJB
	private IndexService indexService;

	@EJB
	private ProductWithSpendingRepository productWithSpendingRepository;

	public BigDecimal getSpendingThisMonth() {
		return indexService.getTotalAmountSumForCurrentMonth();
	}

	public BigDecimal getSpendingLastMonth() {
		return indexService.getTotalAmountSumForPreviousMonth();
	}

	public List<ProductWithSpendingDto> listProductsWithMostSpendingThisMonth() {
		return productWithSpendingRepository.listProductsWithBiggestSpendingInCurrentMonth();
	}

}
