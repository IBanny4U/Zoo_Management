package body;
import java.awt.Color;

public class MenuItem {
	private String name;
	private double price;
	private int quantity;
	private boolean isSelected;
	private Color backgroundColor;
	public MenuItem(String name, double price) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.isSelected = isSelected;
	}
	public MenuItem(String name, double price,Color backgroundColor) {
		super();
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.isSelected = isSelected;
		this.backgroundColor = backgroundColor;
	}
	public synchronized String getName() {
		return name;
	}
	public synchronized void setName(String name) {
		this.name = name;
	}
	public synchronized double getPrice() {
		return price;
	}
	public synchronized void setPrice(double price) {
		this.price = price;
	}
	public synchronized int getQuantity() {
		return quantity;
	}
	public synchronized void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public synchronized boolean isSelected() {
		return isSelected;
	}
	public synchronized void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public synchronized Color getBackgroundColor() {
		return backgroundColor;
	}
	public synchronized void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	}


