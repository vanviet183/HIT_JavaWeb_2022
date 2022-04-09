package com.vitvn183.trainb5.base;

import com.vitvn183.trainb5.entity.Provinces;

public class UrlConstant {
    public static final class Provinces {
        public static final String PREFIX = "/provinces";
        public static final String GET_PROVINCES = PREFIX;
        public static final String PROVINCE_WITH_CODE = PREFIX + "/{code}";
        public static final String DISTRICT_WITH_CODE_PROVINCE = PREFIX + "/{code}/districts";
        public static final String COLLECTION = PREFIX + "/province-collection";
    }

    public static final class Districts {
        public static final String PREFIX = "/districts";
        public static final String GET_DISTRICTS = PREFIX;
        public static final String DISTRICT_WITH_CODE = PREFIX + "/{code}";
        public static final String PROVINCE_WITH_CODE_DISTRICT = PREFIX + "/{code}/provinces";
        public static final String COLLECTION = PREFIX + "/district-collection";

    }
}
