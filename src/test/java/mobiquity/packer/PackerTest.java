package mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Package;
import com.mobiquity.packer.Packer;
import com.mobiquity.utils.PackageMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @author Mohannad Elmagharby
 * on 6/16/2021
 */
public class PackerTest {

    @Test
    @DisplayName("When you cant pick any item return -")
    void whenCantPickAnyItemShouldReturnEmpty() throws APIException, IOException {
        String pack = Packer.pack("src/test/resources/empty_package_example_input");
        Assertions.assertEquals("-\n", pack);
    }

    @Test
    void whenGivenFilePathShouldSuccessesAndReturnResult() throws APIException, IOException {
        String pack = Packer.pack("src/test/resources/example_input");
        Assertions.assertEquals(
                "4\n" +
                        "-\n" +
                        "2,7\n" +
                        "8,9\n", pack);
    }


    @Test
    void whenGivenInvalidFilePathShouldFail() {
        Assertions.assertThrows(IOException.class, () -> Packer.pack("src/test/resources/invalid_path"));
    }
}
