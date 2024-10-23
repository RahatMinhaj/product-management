package com.product.managementsystem.component.route;

public abstract class ApiProvider {

    public static final String SEPARATOR = "/";
    public static final String BASEPATH = SEPARATOR + "api";
    public static final String VERSION = "/v1";

    public static final String OPEN_PARENTHESIS = "{";
    public static final String CLOSE_PARENTHESIS = "}";
    public static final String IDENTIFIER = SEPARATOR + OPEN_PARENTHESIS + "id" + CLOSE_PARENTHESIS;


    public static class Product {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "products";
        public static final String PRODUCT_IDENTIFIER = IDENTIFIER;
        public static final String STOCK_QUANTITY = IDENTIFIER + SEPARATOR + "stock";
    }
}