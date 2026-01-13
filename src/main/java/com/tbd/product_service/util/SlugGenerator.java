package com.tbd.product_service.util;

public final class SlugGenerator {

    private SlugGenerator() {
    }

    private static final int MAX_LENGTH = 120;

    public static String generate(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input cannot be null or blank");
        }

        String slug = input;

        // 1. Normalize unicode (é → e, ü → u)
        slug = java.text.Normalizer.normalize(slug, java.text.Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{M}", "");

        // 2. Lowercase
        slug = slug.toLowerCase();

        // 3. Replace common symbols
        slug = slug.replace("&", "and");

        // 4. Remove invalid characters
        slug = slug.replaceAll("[^a-z0-9\\s-]", "");

        // 5. Replace spaces with hyphens
        slug = slug.replaceAll("\\s+", "-");

        // 6. Collapse multiple hyphens
        slug = slug.replaceAll("-{2,}", "-");

        // 7. Trim hyphens
        slug = slug.replaceAll("^-|-$", "");

        // 8. Enforce length
        if (slug.length() > MAX_LENGTH) {
            slug = slug.substring(0, MAX_LENGTH);
            slug = slug.replaceAll("-+$", "");
        }

        return slug;
    }
}
