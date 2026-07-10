package com.pinky.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Matches what the frontend's apiClient.js reads from a non-2xx response:
 * it looks for either "message" or "error" in the JSON body.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private String message;
}
