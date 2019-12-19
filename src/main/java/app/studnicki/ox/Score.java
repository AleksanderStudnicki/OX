package app.studnicki.ox;

public class Score {
    public int value;

    static final int WIN = 3;
    static final int DRAW = 1;
    static final int LIMIT = 6;

    public void addWin() {
        if (value + WIN > LIMIT) {
            throw new VerifyError();
        }
        value += WIN;
    }

    public void addDraw() {
        if (value + DRAW > LIMIT) {
            throw new VerifyError();
        }
        value += DRAW;
    }
}
