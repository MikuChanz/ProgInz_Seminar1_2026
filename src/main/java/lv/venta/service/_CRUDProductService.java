package lv.venta.service;

import java.util.ArrayList;
import java.util.Locale.Category;

import lv.venta.model.Product;

//interface JAVA satur tikai abstraktas funkcijas
public interface _CRUDProductService {
	
	//CRUD
	//C- create
	
	public abstract Product createNewProduct(Product newProduct) throws Exception;
	
	//R - retrieve all
	
	public abstract ArrayList<Product> retrieveAllProducts() throws Exception;
	
	//R - retrieve by id
	
	public abstract Product retrieveProductById(int id) throws Exception;
	
	//U - update by id
	
	public abstract Product updateProductById(int id, float price, Category category, String description, int quanitity) throws Exception;
	
	//D - delete by id
	
	public abstract void deleteProductById(int Id) throws Exception;
}
