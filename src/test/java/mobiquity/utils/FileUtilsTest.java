package mobiquity.utils;

import com.mobiquity.utils.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.NoSuchFileException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Mohannad Elmagharby
 * on 6/16/2021
 */
public class FileUtilsTest {

    @Test
    void whenGivenValidFilePathShouldReturnStreamOfLines() throws NoSuchFileException {
        Stream<String> streamlines = FileUtils.getLines("src/test/resources/example_input");
        List<String> lines = streamlines.collect(Collectors.toList());
        Assertions.assertEquals(4, lines.size());

    }


    @Test
    void whenGivenInValidFilePathShouldFail()  {
        Assertions.assertThrows(NoSuchFileException.class, () ->  FileUtils.getLines("src/resources/invalid_path"));

    }
}
