package com.inboop.backend.shared.constant;

public class AppConstants {

    // Pagination
    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "createdAt";
    public static final String DEFAULT_SORT_DIRECTION = "desc";

    // Instagram API
    public static final String INSTAGRAM_GRAPH_API_URL = "https://graph.instagram.com";
    public static final String INSTAGRAM_API_VERSION = "v21.0";

    // Webhook
    public static final String WEBHOOK_VERIFY_TOKEN = "inboop_webhook_verify_token";

    // Date Format
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private AppConstants() {
        // Private constructor to prevent instantiation
    }
}
