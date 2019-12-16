package sk.tsystems.gamestudio.game.minesweeper.consoleui;

/**
 * Wrong format exception.
 */
@SuppressWarnings("serial")
class WrongFormatException extends Exception {
    /**
     * Constructor.
     * @param message message
     */
    public WrongFormatException(String message) {
        super(message);
    }
}