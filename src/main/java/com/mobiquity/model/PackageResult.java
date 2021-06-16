package com.mobiquity.model;

import java.util.Iterator;
import java.util.List;

/**
 * @author Mohannad Elmagharby
 * on 6/15/2021
 */
public class PackageResult {
    private final double totalCost;
    private final double freeWeight;
    private final List<Item> items;

    public PackageResult(double totalCost, double freeWeight, List<Item> items) {
        this.totalCost = totalCost;
        this.freeWeight = freeWeight;
        this.items = items;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public double getFreeWeight() {
        return freeWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder indices = new StringBuilder();
        Iterator<Item> iterator = items.iterator();
        while (iterator.hasNext()) {
            indices.append(iterator.next().getIndex());
            if (iterator.hasNext()) {
                indices.append(",");
            }
        }
        if (items.size() == 0)
            indices.append("-");
        return indices.append("\n").toString();
    }
}
