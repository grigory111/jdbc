package ru.semenov.mavenproject.entity;

public class Product {
	protected int Id;
	protected String ProductNumber;
	protected String Name;
	protected float Weight;
	public Product(int id, String productNumber, String name, float weight) {
		super();
		Id = id;
		ProductNumber = productNumber;
		Name = name;
		Weight = weight;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getProductNumber() {
		return ProductNumber;
	}
	public void setProductNumber(String productNumber) {
		ProductNumber = productNumber;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public float getWeight() {
		return Weight;
	}
	public void setWeight(float weight) {
		Weight = weight;
	}
	
	
	
}
