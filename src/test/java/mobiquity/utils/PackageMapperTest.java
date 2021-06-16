package mobiquity.utils;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Package;
import com.mobiquity.utils.PackageMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * @author Mohannad Elmagharby
 * on 6/16/2021
 */
public class PackageMapperTest {

    @Test
    void whenGivenValidPackageLineShouldSucceed() throws APIException {
        String validLinePackage = "8 : (1,15.3,€34)";
        Package pack = PackageMapper.lineToPackage(validLinePackage);
        Assertions.assertEquals(8 , pack.getTotalWeight());
        Assertions.assertEquals(1, pack.getAvailableItems().size());

    }

    @Test
    void whenGivenValidPackageLineWithMultiItemShouldSucceed() throws APIException {
        String validLinePackage = "100 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) " +
                "(6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78) (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) " +
                "(4,26.24,€55) (5,63.69,€52) (6,76.25,€75)";
        Package pack = PackageMapper.lineToPackage(validLinePackage);
        Assertions.assertEquals(100 , pack.getTotalWeight());
        Assertions.assertEquals(15, pack.getAvailableItems().size());

    }


    @ParameterizedTest
    @ValueSource(strings = {"8 ; (1,15.3,€34)" , ": (1,15.3,€34)" , "4 : " , "8:1:15.3,€34" , "", "1"} )
    void whenGivenInvalidPackagesShouldFail(String invalidPackage) {
        Assertions.assertThrows(APIException.class, () -> {
            Package aPackage = PackageMapper.lineToPackage(invalidPackage);
            System.out.println(aPackage.getAvailableItems());
        });
    }
}
