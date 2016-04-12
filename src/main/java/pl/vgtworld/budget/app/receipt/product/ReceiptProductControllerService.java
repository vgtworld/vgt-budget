package pl.vgtworld.budget.app.receipt.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receipt.product.list.dto.AddedProductDto;
import pl.vgtworld.budget.services.ProductStorageService;
import pl.vgtworld.budget.services.ReceiptProductStorageService;
import pl.vgtworld.budget.services.ReceiptStorageService;
import pl.vgtworld.budget.services.dto.receipts.ReceiptProductDto;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ReceiptProductControllerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptProductControllerService.class);

	@EJB
	private ProductStorageService productStorageService;

	@EJB
	private ReceiptProductStorageService receiptProductStorageService;

	@EJB
	private ReceiptStorageService receiptStorageService;

	public List<AddedProductDto> findProductsForReceipt(int receiptId) {
		LOGGER.debug("Find products for receipt. receiptId:{}", receiptId);
		List<ReceiptProductDto> productList = receiptProductStorageService.findProductsForReceipt(receiptId);
		return productList.stream().map(this::asReceiptProductDto).collect(Collectors.toList());
	}

	public BigDecimal updateReceiptTotalAmount(int receiptId) {
		LOGGER.debug("Update receipt total amount. receiptId: {}", receiptId);
		List<AddedProductDto> productList = findProductsForReceipt(receiptId);
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (AddedProductDto product : productList) {
			totalAmount = totalAmount.add(product.getTotalPrice());
		}
		return receiptStorageService.updateReceiptTotalAmount(receiptId, totalAmount);
	}

	private AddedProductDto asReceiptProductDto(pl.vgtworld.budget.services.dto.receipts.ReceiptProductDto input) {
		AddedProductDto output = new AddedProductDto();
		output.setId(input.getId());
		output.setProductId(input.getProductId());
		output.setProductName(productStorageService.findById(input.getProductId()).getName());
		output.setAmount(input.getAmount());
		output.setPricePerUnit(input.getPricePerUnit());
		return output;
	}

}
