package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

  private int mBalance;
  private int mIncrement;
  private int mWinValue;
  private Die mDie;

  /**
   * The no argument constructor.
   */
  public WalletViewModel() {
    // TODO implement method
    mBalance = 0;
    mIncrement = 0;
    mWinValue = 0;
    mDie = new Die6();
  }

  /**
   * Reports the current balance.
   *
   * @return the balance
   */
  public int balance() {
    // TODO implement method
    // return 0;
    return mBalance;
  }

  /**
   * Sets the balance to the given amount.
   *
   * @param amount the new balance
   */
  public void setBalance(int amount) {
    // TODO implement method
    mBalance = amount;
  }

  /**
   * Rolls the {@link Die} in the wallet.
   */
  public void rollDie() {
    // TODO implement method
    mDie.roll();
    int rollValue = mDie.value();

    if (rollValue == mWinValue) {
      mBalance += mIncrement * rollValue;
    } else {
      mBalance -= mIncrement;
    }
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   * @return current value of the die
   */
  public int dieValue() {
    // TODO implement method
    // return 0;
    return mDie.value();
  }

  /**
   * Sets the increment value for earning in the wallet.
   *
   * @param increment amount to add to the balance
   */
  public void setIncrement(int increment) {
    // TODO implement method
    mIncrement = increment;
  }

  /**
   * Sets the value which when rolled earns the increment.
   *
   * @param winValue value to be set
   */
  public void setWinValue(int winValue) {
    // TODO implement method
    mWinValue = winValue;
  }

  /**
   * Sets the {@link Die} to be used in this wallet.
   *
   * @param d the Die to use
   */
  public void setDie(Die d) {
    // TODO implement method
    mDie = d;
  }
}
