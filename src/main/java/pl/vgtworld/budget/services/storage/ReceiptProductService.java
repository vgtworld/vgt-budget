package pl.vgtworld.budget.services.storage;

import pl.vgtworld.budget.services.dto.receipts.ReceiptProductDto;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProduct;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProductDao;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProductPK;

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
		entity.setId(new ReceiptProductPK(product.getReceiptId(), product.getProductId()));
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
		dto.setReceiptId(entity.getId().getReceiptId());
		dto.setProductId(entity.getId().getProductId());
		dto.setAmount(entity.getAmount());
		dto.setPricePerUnit(entity.getPricePerUnit());
		dto.setDescription(entity.getDescription());
		return dto;
	}

}
