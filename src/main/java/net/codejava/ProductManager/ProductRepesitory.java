package net.codejava.ProductManager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepesitory extends JpaRepository<Product, Long> {

}
