package thrust.audio;

public class Music {
  //@ public model boolean is_playing;

  /**
   * @return Is music playing?
   */
  //@ ensures \result == is_playing;
  public /*@ pure @*/ boolean playing() {
    assert false; //@ assert false;
    return false;
  }

  /**
   * Start playing the music.
   */
  //@ ensures is_playing;
  public void start() {
    assert false; //@ assert false;
  }

  /**
   * Stop playing the music.
   */
  //@ ensures !is_playing;
  public void stop() {
    assert false; //@ assert false;
  }
}
