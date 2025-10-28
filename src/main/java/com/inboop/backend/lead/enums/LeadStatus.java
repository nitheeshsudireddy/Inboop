package com.inboop.backend.lead.enums;

public enum LeadStatus {
    NEW,              // Just received
    CONTACTED,        // Initial response sent
    QUALIFIED,        // Verified as potential customer
    NEGOTIATING,      // In discussion
    CONVERTED,        // Became a customer/order
    LOST,             // Did not convert
    SPAM              // Marked as spam
}
