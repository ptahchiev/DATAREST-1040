package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

/**
 * @author Petar Tahchiev
 * @since 1.5
 */
@Repository
@RepositoryRestResource
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
