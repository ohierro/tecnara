# Ejercicio de MVC

## Introducción
Vamos a crear un sistema que nos permita ver la separación de conceptos del MVC, junto con algunas buenas prácticas de programación


## Diseño
Nuestro sistema va a llevar el control de stock de una tienda. Para ello vamos a diseñar una aplicación de consola para gestionarlo

El sistema se compondrá de:
src/
  com/hiberus/training
	model/
	view/
	controller/

En la parte del model encontraremos

```java
class Product {
	int id;
	String name;
	String description;
}

class Order {
	int id;
	int productId;
	int quantity;
}
```

```java
public interface Store {
	List<Product> getProductList();
	Product getProduct(int productId);
	int getProductQuantity(Product product);
	void addProductUnits(Product product, int quantity) throws IOException;
	void removeProductUnits(Product product, int quantity) throws NoMoreUnitsException;
}

public interface OrderManager {
	void createOrder(Product product, int quantity); 
	List<Order> listOrders();
	List<Order> listOrders(Product product);
}
```

En la parte de los controladores, encontraremos


```java
public class StoreController {
	List<Product> index();

	int detail();		

	
}


public class OrderController {
	List<Order> index();
}
```

Finalmente, para la parte de la vista, tendremos

```java

public class ConsoleUI {
	OrderController orderController;
	StoreController storeController;

	public void listOrders() {
		List<Order> orders = orderController.index();

		for (Order order : orders) {
			System.out.println(order.toString())
		}
	}

	public void static main(String[] args) {		
		ConsoleUI ui = new ConsoleUI();

		if ("order".equals(args[0])) {
			ui.list()
		}			

		if ("detail".equals(args[0])) {

		}

	}
}
```


## Testing
