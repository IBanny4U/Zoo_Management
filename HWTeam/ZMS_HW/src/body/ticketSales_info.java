package body;

public class ticketSales_info {
private int male;
private int female;
private int adult;
private int child;
private String tour;
private String transport;
private int transport_QTY;
private double totalPrice;

public ticketSales_info(int male, int female, int adult, int child, String tour, String transport, int transport_QTY,
		double totalPrice) {
	super();
	this.male = male;
	this.female = female;
	this.adult = adult;
	this.child = child;
	this.tour = tour;
	this.transport = transport;
	this.transport_QTY = transport_QTY;
	this.totalPrice = totalPrice;
}

public int getMale() {
	return male;
}

public void setMale(int male) {
	this.male = male;
}

public int getFemale() {
	return female;
}

public void setFemale(int female) {
	this.female = female;
}

public int getAdult() {
	return adult;
}

public void setAdult(int adult) {
	this.adult = adult;
}

public int getChild() {
	return child;
}

public void setChild(int child) {
	this.child = child;
}

public String getTour() {
	return tour;
}

public void setTour(String tour) {
	this.tour = tour;
}

public String getTransport() {
	return transport;
}

public void setTransport(String transport) {
	this.transport = transport;
}

public int getTransport_QTY() {
	return transport_QTY;
}

public void setTransport_QTY(int transport_QTY) {
	this.transport_QTY = transport_QTY;
}

public double getTotalPrice() {
	return totalPrice;
}

public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}
}
