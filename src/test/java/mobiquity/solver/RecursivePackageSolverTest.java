package mobiquity.solver;

import com.mobiquity.model.Item;
import com.mobiquity.model.Package;
import com.mobiquity.model.PackageResult;
import com.mobiquity.solver.RecursivePackageSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author Mohannad Elmagharby
 * on 6/16/2021
 */
public class RecursivePackageSolverTest {

    RecursivePackageSolver recursivePackageSolver;

    @BeforeEach
    void before() {
        this.recursivePackageSolver = new RecursivePackageSolver();
    }


    @Test
    @DisplayName("If tow items have the same cost and you have to chose only one(chose the smaller one)")
    void whenGivenTowItemsWithTheSameCostOnlyChoseTheSmaller() {
        Item item1 = new Item(1, 4, 3);
        Item item2 = new Item(2, 2, 3);
        Package pack = new Package(5, Arrays.asList(item1, item2));

        PackageResult solve = recursivePackageSolver.solve(pack);

        Assertions.assertEquals(3, solve.getTotalCost());
        //check if index = the item with the smaller weight
        Assertions.assertEquals(2, solve.getItems().get(0).getIndex());

    }

    @Test
    @DisplayName("When given pack weight bigger than or equal the the sum of all items weight chose all items")
    void whenGivenItemsChoseAllOfThem() {
        Item item1 = new Item(1, 4, 3);
        Item item2 = new Item(2, 2, 5);
        Item item3 = new Item(3, 6, 2);
        Item item4 = new Item(4, 3, 8);

        Package pack = new Package(15, Arrays.asList(item1, item2, item3, item4));
        PackageResult packageResult = recursivePackageSolver.solve(pack);

        Assertions.assertEquals(18, packageResult.getTotalCost());
        //check if index = the item with the smaller weight
        Assertions.assertEquals(4, packageResult.getItems().size());
        Assertions.assertEquals(0, packageResult.getFreeWeight());


        //test with pack weight > all items weight
        Package pack2 = new Package(20, Arrays.asList(item1, item2, item3, item4));
        PackageResult packageResultBigger = recursivePackageSolver.solve(pack2);

        Assertions.assertEquals(18, packageResultBigger.getTotalCost());
        //check if index = the item with the smaller weight
        Assertions.assertEquals(4, packageResultBigger.getItems().size());
        Assertions.assertEquals(5, packageResultBigger.getFreeWeight());

    }


    @Test
    @DisplayName("When given pack weight smaller than all items weight chose nothing")
    void whenGivenPackWeightSmallerThanItemsWeightChoseNothing() {
        Item item1 = new Item(1, 4, 3);
        Item item2 = new Item(2, 3, 5);
        Item item3 = new Item(3, 6, 2);
        Item item4 = new Item(4, 7, 8);

        Package pack = new Package(2, Arrays.asList(item1, item2, item3, item4));

        PackageResult packageResult = recursivePackageSolver.solve(pack);

        Assertions.assertEquals(0, packageResult.getTotalCost());
        //check if index = the item with the smaller weight
        Assertions.assertEquals(0, packageResult.getItems().size());
        Assertions.assertEquals(2, packageResult.getFreeWeight());

    }


    @Test
    @DisplayName("When given pack weight smaller than all items weight chose nothing")
    void whenGivenItemsMaximizeCost() {
        Item item1 = new Item(1, 4, 4);
        Item item2 = new Item(2, 2, 5);
        Item item3 = new Item(3, 6, 2);
        Item item4 = new Item(4, 7, 9);

        //ans should be item(1,2)
        Package pack = new Package(8, Arrays.asList(item1, item2, item3, item4));

        PackageResult packageResult = recursivePackageSolver.solve(pack);

        Assertions.assertEquals(9, packageResult.getTotalCost());
        //check if index = the item with the smaller weight
        Assertions.assertEquals(2, packageResult.getFreeWeight());

        //assert items indices ans= (item[1] , item[2]) -> 1+2 = 3
        Assertions.assertEquals(2, packageResult.getItems().size());
        int indicesSum = 3;
        for (Item item : packageResult.getItems()) {
            indicesSum -= item.getIndex();
        }
        Assertions.assertEquals(0, indicesSum);
    }

    @Test
    @DisplayName("When given only one item chose it")
    void whenGivenOneItemMaximizeCost() {
        Item item1 = new Item(1, 4, 4);

        //ans should be item(1,2)
        Package pack = new Package(6, Arrays.asList(item1));
        PackageResult packageResult = recursivePackageSolver.solve(pack);

        Assertions.assertEquals(4, packageResult.getTotalCost());
        //check if index = the item with the smaller weight
        Assertions.assertEquals(2, packageResult.getFreeWeight());
        Assertions.assertEquals(1, packageResult.getItems().size());
        Assertions.assertEquals(1, packageResult.getItems().get(0).getIndex());
    }

//    @Test
//    @DisplayName("When given Maximum count of allowed items, maximize cost")
//    void whenGivenMaxItemCountMaximizeCost() {
//        //ans should be item(1,2)
//        Package pack = new Package(99.5, Arrays.asList(
//                new Item(1, 3, 4.5),
//                new Item(2, 5.5, 12.4),
//                new Item(3, 4, 100),
//                new Item(4, 80.8, 4),
//                new Item(5, 7, 4.6),
//                new Item(6, 20.3, 20),
//                new Item(7, 11, 24),
//                new Item(8, 4.8, 4),
//                new Item(9, 12, 4),
//                new Item(10, 6.5, 55),
//                new Item(11, 7.6, 4),
//                new Item(12, 100, 98.7),
//                new Item(13, 4.1, 4),
//                new Item(14, 2, 30),
//                new Item(15, 5, 4),
//                new Item(16, 6.7, 4)
//        ));
//        PackageResult packageResult = recursivePackageSolver.solve(pack);
//
//        Assertions.assertEquals(274.5, packageResult.getTotalCost());
//        //check if index = the item with the smaller weight
//        Assertions.assertEquals(0, packageResult.getFreeWeight());
//        Assertions.assertEquals(1, packageResult.getItems().size());
//
//    }


}
