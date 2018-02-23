package ru.semenov.mavenproject.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.semenov.mavenproject.entity.Product;
import ru.semenov.mavenproject.service.Settings;

public class JdbcStorage implements Storage {

	private  final Connection connection;

	public JdbcStorage() {
		final Settings settings = Settings.getInstance();
		try {
		  this.connection = DriverManager.getConnection(settings.value("jdbc.url"), settings.value("jdbc.username"), settings.value("jdbc.password"));
		} catch (SQLException e) {
			throw new IllegalStateException(e);
			
		}
	}

	@Override
	public Collection<Product> values() {
		final List<Product> Products = new ArrayList<>();
		try (final Statement statement = this.connection.createStatement();
		     final ResultSet rs = statement.executeQuery("select * from test.product")) {
			while (rs.next()) {
				Products.add(new Product(rs.getInt("product_id"), rs.getString("productnumber"), rs.getString("name"), rs.getFloat("weight")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Products;
	}

	
	@Override
	public int add(Product Product) {
		try (final PreparedStatement statement = this.connection.prepareStatement("insert into test.product (productnumber, name, weight) values (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
		//	statement.setInt(1, Product.getId());
			statement.setString(1, Product.getProductNumber());
			statement.setString(2, Product.getName());
			statement.setFloat(3, Product.getWeight());
			statement.executeUpdate();
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException("Could not create new Product");
	}


	@Override
	public void edit(Product Product) {

	}

	@Override

	public void delete(int id) {
	}

	@Override
	public Product get(int id) {
		try (final PreparedStatement statement = this.connection.prepareStatement("select * from client where id=(?)")) {
			statement.setInt(1, id);
			try (final ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					return new Product(rs.getInt("product_id"), rs.getString("productnumber"), rs.getString("name"), rs.getFloat("weight"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new IllegalStateException(String.format("Product %s does not exists", id));
	}

	@Override
	public int generateId() {
		return 0;
	}



	@Override

	public void close() {

		try {

			connection.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

}