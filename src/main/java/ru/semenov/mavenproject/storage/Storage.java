package ru.semenov.mavenproject.storage;

import java.util.Collection;

import ru.semenov.mavenproject.entity.Product;

public interface Storage {
	public Collection<Product> values();	
	public int add(final Product product);
	public void edit(final Product product);
	public void delete(final int id);
	public Product get(final int id);
	public int generateId();
	public void close();
}
