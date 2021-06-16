package mobiquity.utils;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.utils.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Mohannad Elmagharby
 * on 6/16/2021
 */
public class ItemMapperTest {

    @Test
    void whenGivenAValidItemShouldParseIt() throws APIException {
        String validItem = "(1,85.31,€29)";
        Item item = ItemMapper.itemStringToItem(validItem);

        Assertions.assertEquals(1 , item.getIndex());
        Assertions.assertEquals(85.31 , item.getWeight());
        Assertions.assertEquals(29 , item.getCost());

        String validItem2 = "(2,10,€100)";
        Item item2 = ItemMapper.itemStringToItem(validItem2);

        Assertions.assertEquals(2 , item2.getIndex());
        Assertions.assertEquals(10 , item2.getWeight());
        Assertions.assertEquals(100 , item2.getCost());
    }

    @Test
    void whenGivenItemExceedAllowedWeightShouldFail() {
        String invalidItem = "(2,100.5,€29)";
        APIException apiException = Assertions.assertThrows(APIException.class, () -> ItemMapper.itemStringToItem(invalidItem));
        Assertions.assertEquals(apiException.getMessage() , "Invalid item parameters:"+invalidItem);
    }


    @ParameterizedTest
    @ValueSource(strings = {"2,100.5,€29)" , ",100.5,€29)" , "(2,100.5€29)" , "(2,100.5,€29" ,"15.3,€34", "(2,100)" , "(,100.5,€29)"
    , "(2,100,€101)" , "" , " " , "(,,)" , "()" , "(,,€)"})
    void whenGivenInvalidItemsShouldFail(String invalidItem) {
        Assertions.assertThrows(APIException.class, () -> ItemMapper.itemStringToItem(invalidItem));
    }

}
