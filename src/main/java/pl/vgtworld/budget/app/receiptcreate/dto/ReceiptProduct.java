package pl.vgtworld.budget.app.receiptcreate.dto;

public class ReceiptProduct {

	private Integer id;

	private String name;

	private Double pricePerUnit;

	private Double amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTotalPrice() {
		if (amount != null && pricePerUnit != null) {
			return amount * pricePerUnit;
		}
		return 0.0;
	}

	@Override
	public String toString() {
		return "ReceiptProduct{" +
			  "id=" + id +
			  ", name='" + name + '\'' +
			  ", pricePerUnit=" + pricePerUnit +
			  ", amount=" + amount +
			  '}';
	}

}
