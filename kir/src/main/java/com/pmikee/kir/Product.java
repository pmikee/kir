package com.pmikee.kir;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	String id;
	String name;
	String description;
	BigDecimal stock;
	BigDecimal unitPrice;

	protected Product() {
	}

	public Product(String name, String description, BigDecimal stock, BigDecimal sellPrice) {
		super();
		this.name = name;
		this.description = description;
		this.stock = stock;
		this.unitPrice = sellPrice;
	}

	@Override
	public String toString() {
		return "(" + id + ")" + name + ", leírás: " + description + ", készleten: " + stock + ", ára: " + unitPrice;
	}

}
