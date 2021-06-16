package com.mobiquity.utils;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;

/**
 * @author Mohannad Elmagharby
 * on 6/15/2021
 */
public class ItemMapper {

    /**
     * map itemString to {@link Item}
     * @param itemString (1,85.31,€29)
     * @return
     * @throws APIException if item not match (number<,8number,€number)
     */
    public static Item itemStringToItem(String itemString) throws APIException {
        try {
            String[] itemValues = itemString.split(",");
            int index = Integer.parseInt(itemValues[0].replace("(" , ""));
            double weight = Double.parseDouble(itemValues[1]);
            char[] chars = itemValues[2].replace(")", "").toCharArray();
            String co = new String(chars,1,chars.length-1);
            double cost = Integer.parseInt(co);
            if (cost > 100 || weight > 100)
                throw new APIException("Invalid item parameters:"+itemString);
            return new Item(index, weight, cost);
        }catch (Exception ex){
            throw  new APIException(ex.getMessage());
        }
    }
}
