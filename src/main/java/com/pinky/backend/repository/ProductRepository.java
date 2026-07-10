package com.pinky.backend.repository;

import com.pinky.backend.entity.Gender;
import com.pinky.backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Plain findAll()/findByGender() trigger one extra SELECT per product
    // to lazy-load its reviews (N+1) -- with Neon being a remote database,
    // each of those round trips adds real, noticeable latency. Fetching
    // reviews in the same query keeps the whole product list to a single
    // round trip.
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.reviews")
    List<Product> findAllWithReviews();

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.reviews WHERE p.gender = :gender")
    List<Product> findByGenderWithReviews(@Param("gender") Gender gender);

    @Query("SELECT p FROM Product p LEFT JOIN FETCH p.reviews WHERE p.id = :id")
    Optional<Product> findByIdWithReviews(@Param("id") Long id);
}
