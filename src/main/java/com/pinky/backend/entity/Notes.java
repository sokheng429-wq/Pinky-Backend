package com.pinky.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fragrance notes, embedded directly into the Product table
 * (matches the frontend's { top, heart, base } shape).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Notes {

    @Column(name = "note_top")
    private String top;

    @Column(name = "note_heart")
    private String heart;

    @Column(name = "note_base")
    private String base;
}
