package body;

public class InventoryProduct {
        private int number;
        private String productId;
		private String productName;
		private String category;
		private String manufacturer;
		private String expDate;
		private String wareHouse;
		private int stock;
		private double price;
		private String status;
		
		
	public InventoryProduct(String productId, String productName, String category, String manufacturer,
				String expDate, String wareHouse, int stock, double price) {
			super();
//			this.number = number;
			this.productId = productId;
			this.productName = productName;
			this.category = category;
			this.manufacturer = manufacturer;
			this.expDate = expDate;
			this.wareHouse = wareHouse;
			this.stock = stock;
			this.price = price;
//			this.status = status;
		}


	public int getNumber() {
		return number;
	}


	public void setNumber(int number) {
		this.number = number;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}


	public String getExpDate() {
		return expDate;
	}


	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}


	public String getWareHouse() {
		return wareHouse;
	}


	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public static void main(String[] args) {
		

	}

}
