package androidsamples.java.dicegames;

import static java.lang.Math.max;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;

/**
 * A {@link ViewModel} for the gambling game that allows the user to choose a game type, set a wager, and then play.
 */
public class TwoOrMoreViewModel extends ViewModel {

  private int coins, wager_amount;
  public List<Die> dies;
  GameType game_type;
  private int[] freq;

  /**
   * No argument constructor.
   */
  public TwoOrMoreViewModel() {
    dies = new ArrayList<>();
    freq = new int[7];
    game_type = GameType.NONE;
    coins = 0;
    wager_amount = 0;
  }

  /**
   * Initializes the new die Array with 4 new dies
   *
   */
  public void setDie() {
    for (int i=0;i<4;i++) {
      addDie(new Die6());
    }
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public int balance() {
    return coins;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param balance the given amount
   */
  public void setBalance(int balance) {
    coins = balance;
  }

  /**
   * Reports the current game type as one of the values of the {@code enum} {@link GameType}.
   *
   * @return the current game type
   */
  public GameType gameType() {
    return game_type;
  }

  /**
   * Sets the current game type to the given value.
   *
   * @param gameType the game type to be set
   */
  public void setGameType(GameType gameType) {
    game_type = gameType;
  }

  /**
   * Reports the wager amount.
   *
   * @return the wager amount
   */
  public int wager() {
    return wager_amount;
  }

  /**
   * Sets the wager to the given amount.
   *
   * @param wager the amount to be set
   */
  public void setWager(int wager) {
    wager_amount = wager;
  }

  /**
   * Reports whether the wager amount is valid for the given game type and current balance.
   * For {@link GameType#TWO_ALIKE}, the balance must be at least twice as much, for {@link GameType#THREE_ALIKE}, at least thrice as much, and for {@link GameType#FOUR_ALIKE}, at least four times as much.
   * The wager must also be more than 0.
   *
   * @return {@code true} iff the wager set is valid
   */
  public boolean isValidWager() {
    if (wager() <= 0) return false;

    switch (gameType()) {
      case TWO_ALIKE: if (2*wager() > balance()) return false; break;
      case THREE_ALIKE: if (3*wager() > balance()) return false; break;
      case FOUR_ALIKE: if (4*wager() > balance()) return false; break;
      default: return false;
    }

    return true;
  }

  /**
   * Returns the current values of all the dice.
   *
   * @return the values of dice
   */
  public List<Integer> diceValues() {
    List<Integer> die_values = new ArrayList<>();
    for (Die die:dies) {
      die_values.add(die.value());
    }
    return die_values;
  }

  /**
   * Adds the given {@link Die} to the game.
   *
   * @param d the Die to be added
   */
  public void addDie(Die d) {
    dies.add(d);
  }

  /**
   * Simulates playing the game based on the type and the wager and reports the result as one of the values of the {@code enum} {@link GameResult}.
   *
   * @return result of the current game
   * @throws IllegalStateException if the wager or the game type was not set to a proper value.
   */
  public GameResult play() throws IllegalStateException {

    if (wager() == 0) {
      throw new IllegalStateException("Wager not set, can't play!");
    }
    if (gameType() == GameType.NONE) {
      throw new IllegalStateException("Game Type not set, can't play!");
    }
    if (gameType() == GameType.TWO_ALIKE && dies.size() < 2) {
      throw new IllegalStateException("Not enough dice, can't play!");
    }
    if (gameType() == GameType.THREE_ALIKE && dies.size() < 3) {
      throw new IllegalStateException("Not enough dice, can't play!");
    }
    if (gameType() == GameType.FOUR_ALIKE && dies.size() < 4) {
      throw new IllegalStateException("Not enough dice, can't play!");
    }
    if (!isValidWager()) {
      throw new IllegalStateException("Invalid wager, can't play!");
    }

    freq = new int[7];

    for (Die i : dies) {
      i.roll();
    }

    int maxOccurrences = 0;
    for (Die d:dies) {
      freq[d.value()]++;
      maxOccurrences = max(maxOccurrences, freq[d.value()]);
    }

    switch (gameType()) {
      case TWO_ALIKE: if (maxOccurrences >= 2) {
        coins += 2 * wager();
        return GameResult.WIN;
      }
      else {
        coins -= 2 * wager();
        return GameResult.LOSS;
      }
      case THREE_ALIKE: if (maxOccurrences >= 3) {
        coins += 3 * wager();
        return GameResult.WIN;
      }
      else {
        coins -= 3 * wager();
        return GameResult.LOSS;
      }
      case FOUR_ALIKE: if (maxOccurrences >= 4) {
        coins += 4 * wager();
        return GameResult.WIN;
      }
      else {
        coins -= 4 * wager();
        return GameResult.LOSS;
      }
      default: return GameResult.UNDECIDED;
    }

  }
}