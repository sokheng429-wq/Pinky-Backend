package com.pinky.backend.service;

import com.pinky.backend.dto.NotesDto;
import com.pinky.backend.dto.ProductDto;
import com.pinky.backend.dto.ReviewDto;
import com.pinky.backend.entity.Gender;
import com.pinky.backend.entity.Notes;
import com.pinky.backend.entity.Product;
import com.pinky.backend.entity.Review;
import com.pinky.backend.exception.ResourceNotFoundException;
import com.pinky.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts(String gender) {
        List<Product> products;
        if (gender != null && !gender.isBlank()) {
            products = productRepository.findByGenderWithReviews(Gender.fromValue(gender));
        } else {
            products = productRepository.findAllWithReviews();
        }
        return products.stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ProductDto getProductById(Long id) {
        return toDto(findProductOrThrow(id));
    }

    @Transactional
    public ProductDto createProduct(ProductDto dto) {
        Product product = new Product();
        applyDto(product, dto);
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductDto dto) {
        Product product = findProductOrThrow(id);
        applyDto(product, dto);
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = findProductOrThrow(id);
        productRepository.delete(product);
    }

    @Transactional
    public ProductDto addReview(Long productId, int rating) {
        Product product = findProductOrThrow(productId);
        Review review = new Review(rating, product);
        product.getReviews().add(review);
        recalculateRating(product);
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Transactional
    public ProductDto deleteReview(Long productId, Long reviewId) {
        Product product = findProductOrThrow(productId);
        boolean removed = product.getReviews().removeIf(r -> r.getId().equals(reviewId));
        if (!removed) {
            throw new ResourceNotFoundException("Review " + reviewId + " not found on product " + productId);
        }
        recalculateRating(product);
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    // ---- helpers ----

    private Product findProductOrThrow(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    private void recalculateRating(Product product) {
        List<Review> reviews = product.getReviews();
        product.setReviewsCount(reviews.size());
        if (reviews.isEmpty()) {
            product.setRating(0);
        } else {
            double avg = reviews.stream().mapToInt(Review::getRating).average().orElse(0);
            product.setRating(Math.round(avg * 10.0) / 10.0);
        }
    }

    private void applyDto(Product product, ProductDto dto) {
        product.setName(dto.getName());
        product.setBrand(dto.getBrand());
        product.setGender(Gender.fromValue(dto.getGender()));
        product.setPrice(dto.getPrice());
        product.setOldPrice(dto.getOldPrice());
        product.setBadge(dto.getBadge());
        product.setStock(dto.getStock());
        product.setImage(dto.getImage());
        product.setSmell(dto.getSmell());
        product.setDescription(dto.getDescription());
        product.setSize(dto.getSize());

        NotesDto notesDto = dto.getNotes();
        if (notesDto != null) {
            product.setNotes(new Notes(notesDto.getTop(), notesDto.getHeart(), notesDto.getBase()));
        } else {
            product.setNotes(new Notes(null, null, null));
        }
    }

    private ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setBrand(product.getBrand());
        dto.setGender(product.getGender() != null ? product.getGender().getValue() : null);
        dto.setPrice(product.getPrice());
        dto.setOldPrice(product.getOldPrice());
        dto.setRating(product.getRating());
        dto.setReviewsCount(product.getReviewsCount());
        dto.setReviews(product.getReviews().stream()
                .map(r -> new ReviewDto(r.getId(), r.getRating(), r.getDate()))
                .toList());
        dto.setBadge(product.getBadge());
        dto.setStock(product.getStock());
        dto.setImage(product.getImage());
        dto.setSmell(product.getSmell());
        dto.setDescription(product.getDescription());

        Notes notes = product.getNotes();
        dto.setNotes(notes != null
                ? new NotesDto(notes.getTop(), notes.getHeart(), notes.getBase())
                : new NotesDto(null, null, null));

        dto.setSize(product.getSize());
        return dto;
    }
}
