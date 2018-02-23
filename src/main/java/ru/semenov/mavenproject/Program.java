/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.semenov.mavenproject;

import java.lang.reflect.Field;

/**
 *
 * @author Григорий
 */
public class Program {
	static public void main(String[] arg)
	{
	/*	JdbcStorage storage = new JdbcStorage();
		Collection<Product> list =  storage.values();
		Product pr = new Product(-1, "ВЕЛО-900", "Велосипед-900", 10);
		list.add(pr);
		int id = storage.add(pr);
		pr.setId(id);
		for(Product p: list)
			System.out.println(p.getId() + " " + p.getName());
	}*/
	
		Class<?> xclass;
		try {
			xclass = Class.forName("ru.semenov.mavenproject.entity.Product");
			generateInsertCommand(xclass, "test.product");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//Field field=zclass.getDeclaredField("firstName");
		
	}
	
	static public String generateInsertCommand(Class<?> x, String table)
	{
		String insertCommand = null;
		for(Field field: x.getDeclaredFields())
		{
			System.out.println(field.getName() + " " + field.getType());
			
		}
		return insertCommand;
		
	}
	
}
