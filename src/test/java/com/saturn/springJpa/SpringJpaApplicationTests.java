package com.saturn.springJpa;

import com.saturn.springJpa.entities.Product;
import com.saturn.springJpa.repositories.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringJpaApplicationTests {

	@Autowired
	ProductRepo productRepo;

	@Test
	void contextLoads() {
	}


//	@Test
//	void testRepository(){
//		Product p = Product.builder().brand("versace").item("dylan-blue").price(BigDecimal.valueOf(1200.0)).quantity(2).build();
//		Product saved = productRepo.save(p);
//		System.out.println(saved.getCreatedAt());
//
//	}

	@Test
	void getProducts(){
		Product p = productRepo.findById(5L).orElseThrow(()-> new RuntimeException("Product not found"));
		System.out.println(p);

	}

	@Test
	void getProductsPriceGreaterThan(){
		List<Product> products = productRepo.findAllByPriceGreaterThan(BigDecimal.valueOf(20000));
		System.out.println(products);
	}

	@Test
	void getProductDistinctByBrand(){
		List<Product> products = productRepo.findDistinctByBrand("versace");
		System.out.println(products);
	}

	@Test
	void getByPriceAndQuantity(){
		List<Product> products=productRepo.findByPriceGreaterThanAndQuantityLessThan(BigDecimal.valueOf(12000.0), 20);
		System.out.println(products);
	}

	@Test
	void getProductByBrand(){
		Optional<Product> product = productRepo.findByBrand("Prada");
		System.out.println(product);
	}

}
