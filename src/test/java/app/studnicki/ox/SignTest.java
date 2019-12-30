package app.studnicki.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class SignTest {
  public void toStringShouldReturnOForNaught() {
    //given
    Sign sign;

    //when
    sign = Sign.NAUGHT;

    //then
    assertEquals(sign.toString(), "O");
  }

  public void toStringShouldReturnXForCross() {
    //given
    Sign sign;

    //when
    sign = Sign.CROSS;

    //then
    assertEquals(sign.toString(), "X");
  }
}
