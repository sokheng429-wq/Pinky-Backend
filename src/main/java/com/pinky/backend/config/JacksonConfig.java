package com.pinky.backend.config;

import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Product images are sent as base64 data-URLs inside the JSON body
 * (see ProductDto#image). Jackson's default limit on a single JSON
 * string is 20,000,000 characters (~20MB) — plenty for small images,
 * but a full-resolution phone photo can push a base64 string past that
 * limit, which makes the request fail validation with an obscure
 * "Malformed input" / StreamConstraintsException instead of saving the
 * product. Raise the limit so bigger photos from a phone/PC still work.
 */
@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
        return builder -> builder.postConfigurer((ObjectMapper mapper) ->
                mapper.getFactory().setStreamReadConstraints(
                        StreamReadConstraints.builder()
                                .maxStringLength(60_000_000) // ~60MB, enough for a ~45MB source image
                                .build()
                )
        );
    }
}
