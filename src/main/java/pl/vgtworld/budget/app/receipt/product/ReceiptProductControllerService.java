package pl.vgtworld.budget.app.receipt.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receipt.product.list.dto.AddedProductDto;
import pl.vgtworld.budget.storage.products.ProductService;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProductService;
import pl.vgtworld.budget.storage.receipts.ReceiptService;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProductDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ReceiptProductControllerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptProductControllerService.class);

	@EJB
	private ProductService productService;

	@EJB
	private ReceiptProductService receiptProductService;

	@EJB
	private ReceiptService receiptService;

	public List<AddedProductDto> findProductsForReceipt(int receiptId) {
		LOGGER.debug("Find products for receipt. receiptId:{}", receiptId);
		List<ReceiptProductDto> productList = receiptProductService.findProductsForReceipt(receiptId);
		return productList.stream().map(this::asReceiptProductDto).collect(Collectors.toList());
	}

	public BigDecimal updateReceiptTotalAmount(int receiptId) {
		LOGGER.debug("Update receipt total amount. receiptId: {}", receiptId);
		List<AddedProductDto> productList = findProductsForReceipt(receiptId);
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (AddedProductDto product : productList) {
			totalAmount = totalAmount.add(product.getTotalPrice());
		}
		return receiptService.updateReceiptTotalAmount(receiptId, totalAmount);
	}

	private AddedProductDto asReceiptProductDto(ReceiptProductDto input) {
		AddedProductDto output = new AddedProductDto();
		output.setId(input.getId());
		output.setProductId(input.getProductId());
		output.setProductName(productService.findById(input.getProductId()).getName());
		output.setAmount(input.getAmount());
		output.setPricePerUnit(input.getPricePerUnit());
		return output;
	}

}
