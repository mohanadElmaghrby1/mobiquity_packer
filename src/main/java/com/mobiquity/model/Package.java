package com.mobiquity.model;

import java.util.List;

/**
 * @author Mohannad Elmagharby
 * on 6/15/2021
 */
public class Package {
    private final double totalWeight;
    private final List<Item> availableItems;

    public Package(double totalWeight, List<Item> availableItems) {
        this.totalWeight = totalWeight;
        this.availableItems = availableItems;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public List<Item> getAvailableItems() {
        return availableItems;
    }
}
