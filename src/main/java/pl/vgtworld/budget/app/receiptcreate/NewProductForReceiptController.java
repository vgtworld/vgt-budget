package pl.vgtworld.budget.app.receiptcreate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class NewProductForReceiptController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NewProductForReceiptController.class);

	private String name;

	private String tags;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public void createProduct() {
		LOGGER.debug("Create new product for receipt. name={}, tags={}", name, tags);
		//TODO Create product in database.
		//TODO Add product to receipt.
		name = null;
		tags = null;
	}

}
