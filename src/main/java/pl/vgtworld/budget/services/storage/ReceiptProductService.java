package pl.vgtworld.budget.services.storage;

import pl.vgtworld.budget.services.dto.receipts.ReceiptProductDto;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProduct;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProductDao;
import pl.vgtworld.budget.storage.receiptproducts.ReceiptProductPK;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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

}
