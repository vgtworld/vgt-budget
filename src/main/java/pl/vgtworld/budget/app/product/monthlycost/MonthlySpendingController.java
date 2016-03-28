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

	private int previousPageYear;

	private int previousPageMonth;

	private int nextPageYear;

	private int nextPageMonth;

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

	public int getPreviousPageYear() {
		return previousPageYear;
	}

	public int getPreviousPageMonth() {
		return previousPageMonth;
	}

	public int getNextPageYear() {
		return nextPageYear;
	}

	public int getNextPageMonth() {
		return nextPageMonth;
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
		calculateParametersForPageLinks();
		return null;
	}

	private void calculateParametersForPageLinks() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.add(Calendar.MONTH, -1);
		previousPageYear = cal.get(Calendar.YEAR);
		previousPageMonth = cal.get(Calendar.MONTH);
		cal.add(Calendar.MONTH, 2);
		nextPageYear = cal.get(Calendar.YEAR);
		nextPageMonth = cal.get(Calendar.MONTH);
	}

}
