package com.mobiquity.utils;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.Package;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohannad Elmagharby
 * on 6/15/2021
 */
public class PackageMapper {

    /**
     * map line to package object contain items and metadata
     * @param line
     * @return
     * @throws APIException if line not valid
     */
    public static Package lineToPackage(String line) throws APIException {
        try {

            String[] weightAndItems = line.split(":");
            int packageWeight = Integer.parseInt(weightAndItems[0].replaceAll(" ", ""));
            if (packageWeight > 100 || packageWeight < 0)
                throw new APIException("Allowed weight between [0,100]");

            List<Item> availableItems = new ArrayList<>();
            String[] inputItems = weightAndItems[1].split(" ");
            //start by index 1 to skip the first white space
            for (int i = 1; i < inputItems.length; i++) {
                availableItems.add(ItemMapper.itemStringToItem(inputItems[i]));
            }

            if (availableItems.size() == 0)
                throw new APIException("No Items In The Package");

            return new Package(packageWeight, availableItems);
        } catch (Exception ex) {
            throw new APIException(ex.getMessage());
        }
    }


    public static String packageToLine(Package pack) {
        return pack.toString();
    }
}
