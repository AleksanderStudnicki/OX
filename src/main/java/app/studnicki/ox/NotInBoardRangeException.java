package app.studnicki.ox;

class NotInBoardRangeException extends RuntimeException {
  NotInBoardRangeException(String message) {
    super(message);
  }
}
