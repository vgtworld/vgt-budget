package pl.vgtworld.budget.app.index;

import pl.vgtworld.budget.app.product.monthlycost.ProductWithSpendingDto;
import pl.vgtworld.budget.app.product.monthlycost.ProductWithSpendingRepository;
import pl.vgtworld.budget.app.tag.monthlycost.TagWithSpendingDto;
import pl.vgtworld.budget.app.tag.monthlycost.TagWithSpendingRepository;

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

	@EJB
	private TagWithSpendingRepository tagWithSpendingRepository;

	public BigDecimal getSpendingThisMonth() {
		return indexService.getTotalAmountSumForCurrentMonth();
	}

	public BigDecimal getSpendingLastMonth() {
		return indexService.getTotalAmountSumForPreviousMonth();
	}

	public List<ProductWithSpendingDto> listProductsWithMostSpendingThisMonth() {
		return productWithSpendingRepository.listProductsWithBiggestSpendingInCurrentMonth();
	}

	public List<TagWithSpendingDto> listTagsWithMostSpendingThisMonth() {
		return tagWithSpendingRepository.listTagsWithBiggestSpendingInCurrentMonth();
	}

}
