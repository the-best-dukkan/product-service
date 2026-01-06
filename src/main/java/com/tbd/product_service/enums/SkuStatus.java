package com.tbd.product_service.enums;

public enum SkuStatus {

    ACTIVE,          // Can be sold (inventory permitting)
    OUT_OF_STOCK,    // No available inventory
    INACTIVE,        // Disabled manually
    DISCONTINUED     // Variant permanently removed
}
