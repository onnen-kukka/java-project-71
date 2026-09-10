package hexlet.code;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DifferTest {
  @Test
  public void testDiffer() throws Exception {
    String filepath1 = "src/test/resources/file1.json";
    String filepath2 = "src/test/resources/file2.json";
    var expected =
        "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}\n";
    var actual = Differ.generate(filepath1, filepath2);
    assertEquals(expected, actual);
  }

  @Test
  public void testSameValues() throws Exception {
    String filepath1 = "src/test/resources/same1.json";
    String filepath2 = "src/test/resources/same2.json";
    var expected = "{\n" + "    host: hexlet.io\n" + "}\n";
    var actual = Differ.generate(filepath1, filepath2);
    assertEquals(expected, actual);
  }

  @Test
  public void testDifferentValues() throws Exception {
    String filepath1 = "src/test/resources/diff1.json";
    String filepath2 = "src/test/resources/diff2.json";
    var expected = "{\n" + "  - timeout: 50\n" + "  + timeout: 20\n" + "}\n";
    var actual = Differ.generate(filepath1, filepath2);
    assertEquals(expected, actual);
  }
}
