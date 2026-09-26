package com.yash.Scope.invoice.enums;

public enum Status {
    DRAFT,
    SENT,
    PAID,
    OVERDUE;

    public boolean canTransitionTo(Status target){

        return switch (this) {
            case DRAFT -> target == SENT;
            case SENT -> target == PAID || target == OVERDUE;
            case OVERDUE -> target == PAID || target == SENT;
            case PAID -> false;
        };
    }
}
