package pl.vgtworld.budget.app.product.monthlycost;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Calendar;
import java.util.List;

@Named
@RequestScoped
public class MonthlySpendingController {

	@EJB
	private ProductWithSpendingRepository productWithSpendingRepository;

	public List<ProductWithSpendingDto> listProductsWithMostSpending() {
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		return productWithSpendingRepository.listProductsWithBiggestSpending(year, month, null);
	}

}
