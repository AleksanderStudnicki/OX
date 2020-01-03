package app.studnicki.ox;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@Test
public class MainTest {
  public void shouldBeAbleToPlayAndWinAGameWithAleksanderAndCzesioWhenAleksanderWinWith6PointAndCzesioHas3OnDimension3() {
    //given
    String[] args = new String[]{"Aleksander", "O", "Czesio", "3", "3"};
    String input = "0\n1\n2\n3\n4\n5\n6\n\n0\n1\n2\n"
        + "3\n4\n5\n6\n\n0\n1\n2\n3\n4\n5\n6\n\n";
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    System.setOut(new PrintStream(out));
    SoftAssert soft = new SoftAssert();

    Main main = new Main();

    //when
    main.main(args);

    //then
    String[] outArr = out.toString().split("\n");
    int length = outArr.length;

    soft.assertEquals(outArr[length - 4],
        "Player Aleksander. Sign: O. 6 points.  |  Player Czesio. Sign: X. 3 points.");
    soft.assertAll();
  }

  public void shouldBeAbleToPlayAndWinAGameWithAleksanderAndCzesioWhenAleksanderHasCrossAndWinWith6PointAndCzesioHas3OnDimension3() {
    //given
    String[] args = new String[]{"Aleksander", "X", "Czesio", "3", "3"};
    String input = "0\n1\n2\n3\n4\n5\n6\n\n0\n1\n2\n"
        + "3\n4\n5\n6\n\n0\n1\n2\n3\n4\n5\n6\n\n";
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    System.setOut(new PrintStream(out));
    SoftAssert soft = new SoftAssert();

    Main main = new Main();

    //when
    main.main(args);

    //then
    String[] outArr = out.toString().split("\n");
    int length = outArr.length;

    soft.assertEquals(outArr[length - 4],
        "Player Aleksander. Sign: X. 6 points.  |  Player Czesio. Sign: O. 3 points.");
    soft.assertAll();
  }
}
