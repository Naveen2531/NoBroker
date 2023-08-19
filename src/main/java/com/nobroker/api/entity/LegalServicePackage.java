package com.nobroker.api.entity;

public enum LegalServicePackage {
    DUE_DILIGENCE("Due Diligence Package", 9999),
    REGISTRATION("Registration Package", 14999),
    COMPLETE_BUYER_ASSIST("Complete Buyer Assist Package", 19999);

    private final String packageName;
    private final long packagePrice;

    LegalServicePackage(String packageName, long packagePrice) {
        this.packageName = packageName;
        this.packagePrice = packagePrice;
    }

    public String getPackageName() {
        return packageName;
    }

    public long getPackagePrice() {
        return packagePrice;
    }
}

