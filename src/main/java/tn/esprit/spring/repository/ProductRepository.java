package tn.esprit.spring.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.spring.entity.Product;
import tn.esprit.spring.entity.ProductCategory;



public interface ProductRepository extends JpaRepository<Product,Long>{
	@Query("Select p from Product p where p.name=?1") 
	List <Product> findByName(String name);
    @Query("Select p from Product p where p.name=:name") 
	List<Product> findProductByName(@Param("name")String name);
	@Query("Select DISTINCT p From Product p "
			+ "JOIN  p.category c"
			+ " where c.id=:id") 
	List <Product> findProductsByCategory(@Param("id") Long id);
	List <Product> findByCategory(ProductCategory category);
	
}
