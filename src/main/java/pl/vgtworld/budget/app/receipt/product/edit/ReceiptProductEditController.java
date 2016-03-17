package pl.vgtworld.budget.app.receipt.product.edit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.vgtworld.budget.app.receipt.product.ReceiptProductControllerService;
import pl.vgtworld.budget.app.receipt.product.edit.dto.ReceiptProductForm;
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

	@EJB
	private ReceiptProductControllerService receiptProductControllerService;

	private Integer receiptId;

	private Integer productId;

	private Integer receiptProductId;

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

	public Integer getReceiptProductId() {
		return receiptProductId;
	}

	public void setReceiptProductId(Integer receiptProductId) {
		this.receiptProductId = receiptProductId;
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
		receiptProductControllerService.updateReceiptTotalAmount(receiptId);
		LOGGER.info("New product added to receipt. receiptId:{}, productId:{}, productName:{}", receiptId, productId, product.getName());
		return "receipt-product-list?receiptId=" + receiptId + "&amp;faces-redirect=true";
	}

	public String initData() {
		LOGGER.debug("Init data");
		if (receiptProductId != null) {
			return initDataForEditingExistingProduct();
		}
		if (receiptId != null && productId != null) {
			return initDataForAddingNewProduct();
		}
		LOGGER.debug("Missing parameters. Redirecting.");
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

	private String initDataForEditingExistingProduct() {
		LOGGER.trace("Init data for editing existing product");
		//TODO
		return null;
	}

	private String initDataForAddingNewProduct() {
		LOGGER.trace("Init data for adding new product");
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

}
