package pl.vgtworld.budget.services.storage;

import pl.vgtworld.budget.services.dto.receipts.ReceiptProductDto;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProduct;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProductDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ReceiptProductService {

	@EJB
	private ReceiptProductDao receiptProductDao;

	public void addNewProduct(ReceiptProductDto product) {
		ReceiptProduct entity = new ReceiptProduct();
		entity.setReceiptId(product.getReceiptId());
		entity.setProductId(product.getProductId());
		entity.setAmount(product.getAmount());
		entity.setPricePerUnit(product.getPricePerUnit());
		entity.setDescription(product.getDescription());
		receiptProductDao.create(entity);
	}

	public List<ReceiptProductDto> findProductsForReceipt(int receiptId) {
		return receiptProductDao.findForReceipt(receiptId).stream().map(ReceiptProductService::asReceiptProductDto).collect(Collectors.toList());
	}

	private static ReceiptProductDto asReceiptProductDto(ReceiptProduct entity) {
		ReceiptProductDto dto = new ReceiptProductDto();
		dto.setId(entity.getId());
		dto.setReceiptId(entity.getReceiptId());
		dto.setProductId(entity.getProductId());
		dto.setAmount(entity.getAmount());
		dto.setPricePerUnit(entity.getPricePerUnit());
		dto.setDescription(entity.getDescription());
		return dto;
	}

}
