package pl.vgtworld.budget.app.product.monthlycost;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
public class MonthlySpendingController {

	private Calendar calendar = Calendar.getInstance();

	@EJB
	private ProductWithSpendingRepository productWithSpendingRepository;

	public Date getTimestamp() {
		return calendar.getTime();
	}

	public List<ProductWithSpendingDto> listProductsWithMostSpending() {
		int month = calendar.get(Calendar.MONTH);
		int year = calendar.get(Calendar.YEAR);
		return productWithSpendingRepository.listProductsWithBiggestSpending(year, month, null);
	}

}
