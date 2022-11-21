package com.uniajc.wikibird;

public class Constants {
    public static final String DB_NAME = "BIRDS_DB";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "BIRD";
    public static final String C_ID = "ID";
    public static final String C_NAME = "NAME";
    public static  final String C_IMAGE = "IMAGE";
    public static final String C_BIO = "BIO";
    public static final String C_COUNTRY = "COUNTRY";
    public static final String C_FAMILY = "FAMILY";
    // CREAR LA TABLA
    public static  final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +"("
            + C_ID+ " INTEGER PRIMARY KEY,"
            + C_NAME+ " TEXT,"
            + C_IMAGE+ " TEXT,"
            + C_BIO+ " TEXT,"
            + C_COUNTRY + " TEXT,"
            + C_FAMILY+ " TEXT"
            + ")";
}
