package pl.vgtworld.budget.app.receiptproductedit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receiptproductedit.dto.ReceiptProductForm;
import pl.vgtworld.budget.services.dto.products.ProductDto;
import pl.vgtworld.budget.services.dto.receipts.ReceiptDto;
import pl.vgtworld.budget.services.dto.receipts.ReceiptProductDto;
import pl.vgtworld.budget.services.storage.ProductService;
import pl.vgtworld.budget.services.storage.ReceiptProductService;
import pl.vgtworld.budget.services.storage.ReceiptService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ReceiptProductEditController implements Serializable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptProductEditController.class);

	@EJB
	private ReceiptService receiptService;

	@EJB
	private ProductService productService;

	@EJB
	private ReceiptProductService receiptProductService;

	private Integer receiptId;

	private Integer productId;

	private ProductDto product;

	private ReceiptProductForm form = new ReceiptProductForm();

	public Integer getReceiptId() {
		return receiptId;
	}

	public void setReceiptId(Integer receiptId) {
		this.receiptId = receiptId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public ProductDto getProduct() {
		return product;
	}

	public ReceiptProductForm getForm() {
		return form;
	}

	public void setForm(ReceiptProductForm form) {
		this.form = form;
	}

	public String submitForm() {
		LOGGER.debug("Submitted receipt product form: {}", form);
		receiptProductService.addNewProduct(asReceiptProductDto(receiptId, productId, form));
		//TODO Update total amount in receipt.
		return "receipt-product-list?receiptId=" + receiptId + "&amp;faces-redirect=true";
	}

	public String initData() {
		if (receiptId != null) {
			ReceiptDto receipt = receiptService.findById(receiptId);
			if (receipt != null) {
				product = productService.findById(productId);
				if (product != null) {
					return null;
				}
				LOGGER.debug("Product with provided id does not exist. ID:{}", productId);
				return "receipt-list?faces-redirect=true";
			}
			LOGGER.debug("Receipt with provided id does not exist. ID:{}", receiptId);
			return "receipt-list?faces-redirect=true";
		}
		LOGGER.warn("Receipt id not available. Unable to fill form.");
		return "receipt-list?faces-redirect=true";
	}

	private ReceiptProductDto asReceiptProductDto(Integer receiptId, Integer productId, ReceiptProductForm form) {
		ReceiptProductDto result = new ReceiptProductDto();
		result.setReceiptId(receiptId);
		result.setProductId(productId);
		result.setAmount(form.getAmount());
		result.setPricePerUnit(form.getUnitPrice());
		result.setDescription(form.getDescription());
		return result;
	}

}
