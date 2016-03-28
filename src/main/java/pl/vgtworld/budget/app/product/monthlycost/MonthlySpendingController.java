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

	private Calendar calendar;

	private Integer year;

	private Integer month;

	private List<ProductWithSpendingDto> productsWithSpending;

	@EJB
	private ProductWithSpendingRepository productWithSpendingRepository;

	public Date getTimestamp() {
		return calendar.getTime();
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public List<ProductWithSpendingDto> getProductsWithSpending() {
		return productsWithSpending;
	}

	public String initData() {
		calendar = Calendar.getInstance();
		if (month == null && year == null) {
			month = calendar.get(Calendar.MONTH);
			year = calendar.get(Calendar.YEAR);
		}
		if (month == null || year == null || month < 0 || month > 11 || year < 1) {
			return "index?faces-redirect==true";
		}
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		productsWithSpending = productWithSpendingRepository.listProductsWithBiggestSpending(year, month, null);
		return null;
	}

}
