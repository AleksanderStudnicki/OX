package app.studnicki.ox;

import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.*;

@Test
public class TransitionRuleTest {
  public void shouldReturnOptionalOfNullOnNextRightForDiagonalUpOnFieldTwoInDimensionThree() {
    //given
    int id = 2;
    int dimension = 3;

    //when
    Optional<Integer> nextId = TransitionRule.DIAGONAL_UP.nextRight(id, dimension);

    //then
    assertFalse(nextId.isPresent());
  }

  public void shouldReturnOptionalOfFourOnNextLefttForDiagonalUpOnFieldTwoInDimensionThree() {
    //given
    int id = 2;
    int dimension = 3;

    //when
    Optional<Integer> nextId = TransitionRule.DIAGONAL_UP.nextLeft(id, dimension);

    //then
    assertEquals(nextId, Optional.of(4));
  }
}
