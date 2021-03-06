package gavilan.kakuro;

import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Arrays;
import static java.util.Arrays.asList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;

public class TestJava {

@Ignore
@Test
public void testSet() {
  Set<Integer> t = new HashSet<>(Arrays.asList(1, 2, 3, 1, 2, 3));
  assertEquals(3, t.size());
}

@Ignore
@Test
public void testLinked() {
  LinkedList<Integer> list = new LinkedList<>();
  list.push(1);
  list.push(2);
  list.push(3);
  assertEquals(1, (int) list.get(2));
  assertEquals(2, (int) list.get(1));
  assertEquals(3, (int) list.get(0));
}

@Ignore
@Test
public void testFonts() {
  Arrays.stream(java.awt.GraphicsEnvironment
    .getLocalGraphicsEnvironment()
    .getAvailableFontFamilyNames())
    .forEach(System.out::println);
  System.out.println("---");
  System.out.println(RenderingHints.KEY_TEXT_ANTIALIASING);
  System.out.println(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
  System.out.println(RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
  System.out.println(RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT);
  System.out.println(RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
  System.out.println("---");
  Toolkit tk = Toolkit.getDefaultToolkit();
  Map desktopHints = (Map) tk.getDesktopProperty("awt.font.desktophints");
  if (null != desktopHints) {
    desktopHints.keySet().forEach(k -> {
      System.out.println(k + " = " + desktopHints.get(k));
    });
  }
}

public int varStrs(String... data) {
  return data.length;
}

public <T> List<T> list(T... args) {
  return asList(args);
}

@Ignore
@Test
public void testVarargs() {
  assertEquals(4, varStrs("a", "a", "a", "a"));
  assertEquals(4, varStrs(list("a", "a", "a", "a").toArray(new String[]{})));
}

@Ignore
@Test(expected = IllegalStateException.class)
public void testStream() {
  IntStream range = IntStream.range(0, 10);
  OptionalInt findFirst = range.findFirst();
  range.forEach(System.out::println);
}

}
