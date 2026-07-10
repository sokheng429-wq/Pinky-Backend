package com.pinky.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Wire shape for Product — matches the frontend's Product type exactly
 * (see productService.js / README.md in the React project).
 * Used both as the response body and as the request body for
 * create/update (the "id" field is ignored on create).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "brand is required")
    private String brand;

    @NotNull(message = "gender is required")
    @Pattern(regexp = "male|female", message = "gender must be 'male' or 'female'")
    private String gender;

    @NotNull(message = "price is required")
    @PositiveOrZero(message = "price must be >= 0")
    private Double price;

    private Double oldPrice;

    private double rating;

    private int reviewsCount;

    private List<ReviewDto> reviews = new ArrayList<>();

    private String badge;

    @NotNull(message = "stock is required")
    @PositiveOrZero(message = "stock must be >= 0")
    private Integer stock;

    private String image;

    private String smell;

    private String description;

    @Valid
    private NotesDto notes;

    private String size;
}
