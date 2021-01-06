package service;

import static util.Resolver.resolveTemplate;

public class PromoCodeCreator {
    public static final String PROMO_CODE = "test.data.promo.code";
    public static final String DISCOUNT = "test.data.promo.discount";

    public static String withCredentialsFromProperty(){
        return TestDataReader.getTestData(PROMO_CODE);
    }

    public static int getDiscount(){
        return Integer.parseInt(TestDataReader.getTestData(DISCOUNT));
    }

}
