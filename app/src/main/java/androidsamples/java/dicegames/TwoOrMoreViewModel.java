package androidsamples.java.dicegames;

import java.util.List;
import java.util.ArrayList;

import androidx.lifecycle.ViewModel;

/**
 * A {@link ViewModel} for the gambling game that allows the user to choose a game type, set a wager, and then play.
 */
public class TwoOrMoreViewModel extends ViewModel {

  private int mBalance;
  private GameType mGameType;
  private int mWager;
  private List<Die> mDice;

  /**
   * No argument constructor.
   */
  public TwoOrMoreViewModel() {
    // TODO implement method
    mBalance = 0;
    mGameType = null;
    mWager = 0;
    mDice = new ArrayList<>();
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public int balance() {
    // TODO implement method
    return 0;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param balance the given amount
   */
  public void setBalance(int balance) {
    // TODO implement method
    mBalance = balance;
  }

  /**
   * Reports the current game type as one of the values of the {@code enum} {@link GameType}.
   *
   * @return the current game type
   */
  public GameType gameType() {
    // TODO implement method
    // return null;
    return mGameType;
  }

  /**
   * Sets the current game type to the given value.
   *
   * @param gameType the game type to be set
   */
  public void setGameType(GameType gameType) {
    // TODO implement method
    mGameType = gameType;
  }

  /**
   * Reports the wager amount.
   *
   * @return the wager amount
   */
  public int wager() {
    // TODO implement method
    // return 0;
    return mWager;
  }

  /**
   * Sets the wager to the given amount.
   *
   * @param wager the amount to be set
   */
  public void setWager(int wager) {
    // TODO implement method
    mWager = wager;
  }

  /**
   * Reports whether the wager amount is valid for the given game type and current balance.
   * For {@link GameType#TWO_ALIKE}, the balance must be at least twice as much, for {@link GameType#THREE_ALIKE}, at least thrice as much, and for {@link GameType#FOUR_ALIKE}, at least four times as much.
   * The wager must also be more than 0.
   *
   * @return {@code true} iff the wager set is valid
   */
  public boolean isValidWager(){
    if (mWager <= 0) return false;
    int requiredBalance = mWager * (mGameType.ordinal() + 2);
    return mBalance >= requiredBalance;
  }

  /**
   * Returns the current values of all the dice.
   *
   * @return the values of dice
   */
  public List<Integer> diceValues() {
    // TODO implement method
    List<Integer> values = new ArrayList<>();
    for (Die die : mDice) {
      values.add(die.value());
    }
    return values;
  }

  /**
   * Adds the given {@link Die} to the game.
   *
   * @param d the Die to be added
   */
  public void addDie(Die d) {
    // TODO implement method
    mDice.add(d);
  }

  /**
   * Simulates playing the game based on the type and the wager and reports the result as one of the values of the {@code enum} {@link GameResult}.
   *
   * @return result of the current game
   * @throws IllegalStateException if the wager or the game type was not set to a proper value.
   */
  public GameResult play() throws IllegalStateException {
    // TODO implement method
    if (mGameType == null || mWager <= 0) {
      throw new IllegalStateException("Wager or game type not set properly.");
    }

    // Roll all the dice
    for (Die die : mDice) {
      die.roll();
    }

    // Check if the dice values match the game type
    int matchCount = 0;
    for (Integer value : diceValues()) {
      if (value == mDice.get(0).value()) {
        matchCount++;
      }
    }

    // Determine the result and update balance
    int multiplier = mGameType.ordinal() + 2;  // Multiplier for winnings
    if (matchCount == mGameType.ordinal() + 2) {
      mBalance += mWager * multiplier;
      return GameResult.WIN;
    } else {
      mBalance -= mWager * multiplier;
      return GameResult.LOSS;
    }

  }
}
