package thrust.input; 


//import java.awt.event.KeyEvent;
/**
 * Processes and delegates each keyboard input received.
 * @author Joe Kiniry (kiniry@acm.org)
 * @version 2 April 2008
 * holly ursula simon - worked on input physics and audio equally
 */
public class InputHandler {
  /** An unknown character code. */
  private static final char UNKNOWN_CHAR = '\0';
  /** Fill in this comment. */
  public static final char DISPLAY_HIGH_SCORES = 'h';
  /** Fill in this comment. */
  public static final char TOGGLE_MUSIC_OR_EFFECTS = 'm';
  /** Fill in this comment. */
  public static final char START_GAME = '\u00A0';
  /** Fill in this comment. */
  public static final char STOP_GAME = '\u001B';
  /** Fill in this comment. */
  public static final char FIRE_GUN = '\n';
  /** Fill in this comment. */
  public static final char TURN_LEFT = 'a';
  /** Fill in this comment. */
  public static final char TURN_RIGHT = 's';
  /** Fill in this comment. */
  public static final char USE_ENGINE = '\u000F';
  /** Fill in this comment. */
  public static final char USE_SHIELD = '\u00A0';

  /**
   * @return What are the legal keyboard inputs?
   */
  public final /*@ pure @*/ char[] legalInputs() {
    char[] array = new char[2 + 2 + 2 + 2 + 1];
    array[0] = '\0';
    array[1] = 'h';
    array[2] = 'm';
    array[2 + 1 ] = '\u00A0';
    array[2 + 2] = '\u001B';
    array[2 + 2 + 1] = '\n';
    array[2 + 2 + 2] = 'a';
    array[2 + 2 + 2 + 1] = 's';
    array[2 + 2 + 2 + 2] = '\u000F';
    //assert false; //@ assert false;
    return array;
  }

  /**
   * @return Is this character a legal keyboard input?
   * @param the_character the character to check.
   */
  /*@ ensures \result <==> (the_character == DISPLAY_HIGH_SCORES) |
    @                      (the_character == TOGGLE_MUSIC_OR_EFFECTS) |
    @                      (the_character == START_GAME) |
    @                      (the_character == STOP_GAME) |
    @                      (the_character == FIRE_GUN) |
    @                      (the_character == TURN_LEFT) |
    @                      (the_character == TURN_RIGHT) |
    @                      (the_character == USE_ENGINE) |
    @                      (the_character == USE_SHIELD);
    @*/
  /**
   * @return Is this character a legal keyboard input?
   * @stuff
   */
  public final /*@ pure @*/ boolean legalInput(char theCharacter) {
    if ((theCharacter == 'h') ||
        (theCharacter == 'm') || (theCharacter == '\u00A0')
        || (theCharacter == '\u001B') || (theCharacter == '\n') || (theCharacter == 'a')
        || (theCharacter == 's') || (theCharacter == '\u000F')){
      return true;
     }
    //assert false; //@ assert false;
    return false;
  }

  /**
   * Process this keyboard input character.
   * @param the_keyboard_input the input character to process.
   */
  //@ requires legal_input(the_keyboard_input);
  public void process(char the_keyboard_input) {
    //assert false; //@ assert false;
  }
}