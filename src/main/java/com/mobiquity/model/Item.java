package com.mobiquity.model;

/**
 * @author Mohannad Elmagharby
 * on 6/15/2021
 */
public class Item {
    private final int index;
    private final double weight;
    private final double cost;

    public Item(int index, double weight, double cost) {
        this.index = index;
        this.weight = weight;
        this.cost = cost;
    }

    public int getIndex() {
        return index;
    }

    public double getWeight() {
        return weight;
    }

    public double getCost() {
        return cost;
    }
}
