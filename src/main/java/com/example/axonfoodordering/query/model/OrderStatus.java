package com.example.axonfoodordering.query.model;

public enum OrderStatus {
    PLACED, CONFIRMED, SHIPPED;

    public boolean isPlaced() {
        return PLACED.equals(this);
    }

    public boolean isConfirmed() {
        return CONFIRMED.equals(this);
    }

    public boolean isUnconfirmed() {
        return !CONFIRMED.equals(this);
    }

    public boolean isShipped() {
        return SHIPPED.equals(this);
    }
}
