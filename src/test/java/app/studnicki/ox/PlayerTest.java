package app.studnicki.ox;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PlayerTest {
  public void nameOfThePlayerAfterCreationShouldBeAleksander() {
    //given
    Player player;

    //when
    player = new Player("Aleksander");

    //then
    Assert.assertEquals(player.name, "Aleksander");
  }
}
