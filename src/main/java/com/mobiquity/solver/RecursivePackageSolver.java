package com.mobiquity.solver;

import com.mobiquity.model.Item;
import com.mobiquity.model.Package;
import com.mobiquity.model.PackageResult;

import java.util.*;

/**
 * @author Mohannad Elmagharby
 * on 6/15/2021
 */
public class RecursivePackageSolver implements PackageSolver {

    public PackageResult solve(Package pack) {
        return maximizeCost(pack.getAvailableItems(), 0, pack.getTotalWeight(), 0, new LinkedList<>());
    }


    /**
     * Maximize items cost that does not exceed given weight
     * time complexity O (2^n) , n = items length
     * since items length <= 15 it will not cause a problem
     * later we can add a memoization data structure to be faster
     *
     * @param items
     * @param currIndex
     * @param weight
     * @param cost
     * @param selectedItems linkedList add and remove item =  O(1)
     * @return @{@link PackageResult} contains selected items and max cost
     */
    private PackageResult maximizeCost(List<Item> items, int currIndex, double weight, double cost,
                                       LinkedList<Item> selectedItems) {
        if (currIndex >= items.size() || weight <= 0) {
            return new PackageResult(cost, weight, new LinkedList<>(selectedItems));
        }

        //leave item
        PackageResult packageResultAns = maximizeCost(items, currIndex + 1, weight, cost, selectedItems);

        //pick item if it's weight <= lifted weight
        if (items.get(currIndex).getWeight() <= weight) {
            //add item to selected list
            selectedItems.add(items.get(currIndex));
            PackageResult picked = maximizeCost(items, currIndex + 1, weight - items.get(currIndex).getWeight(),
                    cost + items.get(currIndex).getCost(), selectedItems);
            //if costs != chose the bigger one, else if cost equals chose the one with more free weight
            if (picked.getTotalCost() > packageResultAns.getTotalCost() ||
                    ( picked.getTotalCost() == packageResultAns.getTotalCost()
                            && picked.getFreeWeight() > packageResultAns.getFreeWeight() ) )
                packageResultAns = picked;


            //remove item from selected list after finishing recursive calls
            selectedItems.removeLast();
        }

        return packageResultAns;
    }


}

