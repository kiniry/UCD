package thrust.entities.in_game;

public class GameState extends AbstractGameState {
  
  /**
   * An integer to store the bonus.
   */
  private int my_bonus;
  /**
   * An integer storing the current fuel.
   */
  private int my_current_fuel;
  /**
   * An byte storing lives.
   */
  private byte my_lives; 
  /**
   * An integer storing the maximum fuel.
   */
  private int my_max_fuel;
  /**
   * An integer storing the score.
   */
  private int my_score;
  /**
   * The high scores.
   */
  private HighScore[] my_hiscores;
  /**
   * The maximum fuel a spaceship can have.
   */
  private static final int MAX_FUEL = 2000;
  /**
   * The number of high scores stored.
   */
  private static final int HIGH_SCORE_COUNT = 8;
  public GameState(final int init_fuel, final byte initial_lives) {
    super();
    my_bonus = 0;
    my_current_fuel = init_fuel;
    my_lives = initial_lives;
    my_max_fuel = MAX_FUEL;
    my_score = 0;
    my_hiscores = new HighScore[8];
  }
  
  public void add_high_score(final HighScoreInterface the_new_high_score) {
    for(int i = 0; i >= HIGH_SCORE_COUNT; ++i) {
      if(the_new_high_score.score() > my_hiscores[i].score()) {
        for(int j = HIGH_SCORE_COUNT - 1; j >= i+1; --j) {
          my_hiscores[j] = my_hiscores[j - 1]; 
        }
        my_hiscores[i] = (HighScore) the_new_high_score;
      }
      break;
    }
  }

  public int bonus() {
    return my_bonus;
  }

  public void change_lives(final byte some_new_lives) {
    my_lives += some_new_lives;
  }

  public void change_score(final int some_new_points) {
    my_score += some_new_points;
  }

  public int current_fuel() {
    return my_current_fuel;
  }

  public HighScoreInterface high_score(final int the_index) {
    return new HighScore();
  }

  public HighScoreInterface[] high_scores() {
    return my_hiscores;
  }

  public byte lives() {
    return my_lives;
  }

  public int maximum_fuel() {
    return my_max_fuel;
  }

  public void new_bonus(final int the_new_value) {
    my_bonus = the_new_value;
  }

  public boolean new_high_score(
      final HighScoreInterface the_possible_new_high_score) {
    boolean ret = false;
    for(int i = 0; i < HIGH_SCORE_COUNT; ++i) {
      if(my_hiscores[i].score() < the_possible_new_high_score.score()) {
        ret = true;
      }
    }
    return ret;
  }

  public int score() {
    return my_score;
  }
  
  private class HighScore implements HighScoreInterface {
    /**
     * The initials of the high score holder.
     */
    private char[] my_initials;
    /**
     * The high score value.
     */
    private int my_hiscore;
    
    public HighScore() {
      my_initials = new char[0];
      my_hiscore = 0;
    }
    
    public char[] initials() {
      final char[] ret = new char[my_initials.length];
      System.arraycopy(my_initials, 0, ret, 0, my_initials.length);
      return ret;
    }

    public void new_initials(final char[] the_new_initials) {
      my_initials = new char[the_new_initials.length];
      System.arraycopy(the_new_initials, 0, my_initials, 0, the_new_initials.length);
    }

    public void new_score(final int the_new_score) {
      my_hiscore = the_new_score;
    }

    public int score() {
      return my_hiscore;
    }
  }
}