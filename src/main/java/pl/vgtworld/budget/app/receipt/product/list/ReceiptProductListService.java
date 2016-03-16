package pl.vgtworld.budget.app.receipt.product.list;

import pl.vgtworld.budget.app.receipt.product.list.dto.AddedProductDto;
import pl.vgtworld.budget.services.storage.ProductService;
import pl.vgtworld.budget.services.storage.ReceiptProductService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ReceiptProductListService {

	@EJB
	private ProductService productService;

	@EJB
	private ReceiptProductService receiptProductService;

	public List<AddedProductDto> findProductsForReceipt(int receiptId) {
		List<pl.vgtworld.budget.services.dto.receipts.ReceiptProductDto> productList = receiptProductService.findProductsForReceipt(receiptId);
		return productList.stream().map(this::asReceiptProductDto).collect(Collectors.toList());
	}

	private AddedProductDto asReceiptProductDto(pl.vgtworld.budget.services.dto.receipts.ReceiptProductDto input) {
		AddedProductDto output = new AddedProductDto();
		output.setProductName(productService.findById(input.getProductId()).getName());
		output.setAmount(input.getAmount());
		output.setPricePerUnit(input.getPricePerUnit());
		return output;
	}

}
