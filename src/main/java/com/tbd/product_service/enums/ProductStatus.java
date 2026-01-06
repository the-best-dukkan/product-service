package com.tbd.product_service.enums;

public enum ProductStatus {
    DRAFT,          // Created but not visible anywhere
    ACTIVE,         // Live and sellable
    INACTIVE,       // Temporarily disabled (business decision)
    DISCONTINUED    // Permanently retired, kept for history
}

