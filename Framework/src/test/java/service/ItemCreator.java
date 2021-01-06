package service;

import model.Item;

import static util.Resolver.resolveTemplate;

public class ItemCreator {
    public static final String ITEM_URI_TEMPLATE = "test.data.%s.uri";
    public static final String ITEM_NAME_TEMPLATE = "test.data.%s.name";
    public static final String ITEM_SIZE_TEMPLATE = "test.data.%s.size";
    public static final String ITEM_COST_TEMPLATE = "test.data.%s.cost";
    public static final String ITEM_AMOUNT_TEMPLATE = "test.data.%s.amount";

    public static Item withCredentialsFromProperty(String orderNumber){
        orderNumber = orderNumber.toLowerCase();

        String itemName = resolveTemplate(ITEM_NAME_TEMPLATE, orderNumber);
        String itemSize = resolveTemplate(ITEM_SIZE_TEMPLATE, orderNumber);
        String itemCost = resolveTemplate(ITEM_COST_TEMPLATE, orderNumber);
        String itemAmount = resolveTemplate(ITEM_AMOUNT_TEMPLATE, orderNumber);

        return Item.of(TestDataReader.getTestData(itemName),
                TestDataReader.getTestData(itemSize),
                Float.parseFloat(TestDataReader.getTestData(itemCost)),
                Integer.parseInt(TestDataReader.getTestData(itemAmount)));
    }

    public static String getName(String orderNumber){
        orderNumber = orderNumber.toLowerCase();

        String itemUri = resolveTemplate(ITEM_NAME_TEMPLATE, orderNumber);

        return  TestDataReader.getTestData(itemUri);
    }

    public static String getUri(String orderNumber){
        orderNumber = orderNumber.toLowerCase();

        String itemUri = resolveTemplate(ITEM_URI_TEMPLATE, orderNumber);

        return  TestDataReader.getTestData(itemUri);
    }
}
