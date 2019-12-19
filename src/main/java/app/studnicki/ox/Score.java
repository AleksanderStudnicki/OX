package app.studnicki.ox;

public class Score {
    public int value;

    public void addWin() {
        if (value + 3 > 6) {
            throw new VerifyError();
        }
        value += 3;
    }

    public void addDraw() {
        if (value + 1 > 6) {
            throw new VerifyError();
        }
        value += 1;
    }
}
