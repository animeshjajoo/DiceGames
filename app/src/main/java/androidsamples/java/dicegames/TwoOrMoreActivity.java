package androidsamples.java.dicegames;

import static androidsamples.java.dicegames.WalletActivity.MAIN_BALANCE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class TwoOrMoreActivity extends AppCompatActivity {

  static final String MAIN_BALANCE_RETURN = "MAIN_BALANCE_RETURN";
  TwoOrMoreViewModel TwoOrMoreVM;
  Button go, back, info;
  TextView die1, die2, die3, die4, txtCoins;

  RadioGroup radioGroup;
  RadioButton radioButton;
  GameResult game_result_toast;
  EditText wager_txt;
  @SuppressLint("NonConstantResourceId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);
    Log.d("TwoActivity","TwoOrMoreButton");
    TwoOrMoreVM = new ViewModelProvider(this).get(TwoOrMoreViewModel.class);

    int balance = getIntent().getIntExtra(MAIN_BALANCE, 0);
    TwoOrMoreVM.setBalance(balance);

    TwoOrMoreVM.setDie();
    txtCoins = findViewById(R.id.textViewCoins);
    go = findViewById(R.id.buttonGo);
    back = findViewById(R.id.buttonBack);
    info = findViewById(R.id.buttonInfo);
    die1 = findViewById(R.id.buttonDie1);
    die2 = findViewById(R.id.buttonDie2);
    die3 = findViewById(R.id.buttonDie3);
    die4 = findViewById(R.id.buttonDie4);
    wager_txt = findViewById(R.id.Wager);
    radioGroup = findViewById(R.id.radioGroup);

    go.setOnClickListener(v -> {
      int selected = radioGroup.getCheckedRadioButtonId();
      radioButton = findViewById(selected);

      switch (selected) {
        case R.id.radioButton2Alike:
          TwoOrMoreVM.setGameType(GameType.TWO_ALIKE);
          break;
        case R.id.radioButton3Alike:
          TwoOrMoreVM.setGameType(GameType.THREE_ALIKE);
          break;
        case R.id.radioButton4Alike:
          TwoOrMoreVM.setGameType(GameType.FOUR_ALIKE);
          break;
        default:
          Toast.makeText(this, "Select a Game Type", Toast.LENGTH_SHORT).show();
          return;
      }

      if (wager_txt.getText().toString().equals("")) {
        Toast.makeText(this, "Enter a Wager", Toast.LENGTH_SHORT).show();
        return;
      }

      try {
        TwoOrMoreVM.setWager((Integer.parseInt(wager_txt.getText().toString())));
      } catch (NumberFormatException e){
        Toast.makeText(this, "Wager not a number!", Toast.LENGTH_SHORT).show();
        return;
      }

      if (!TwoOrMoreVM.isValidWager()) {
        Toast.makeText(this, "Wager is invalid", Toast.LENGTH_SHORT).show();
      } else {

        game_result_toast = TwoOrMoreVM.play();

        if (game_result_toast == GameResult.UNDECIDED) {
          throw new IllegalStateException("Game Result can't be decided!");
        }

        if (game_result_toast == GameResult.WIN)
          Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        else
          Toast.makeText(this, "Loss!", Toast.LENGTH_SHORT).show();

        wager_txt.setText("");

        // Update UI
        die1.setText(String.valueOf(TwoOrMoreVM.dies.get(0).value()));
        die2.setText(String.valueOf(TwoOrMoreVM.dies.get(1).value()));
        die3.setText(String.valueOf(TwoOrMoreVM.dies.get(2).value()));
        die4.setText(String.valueOf(TwoOrMoreVM.dies.get(3).value()));
        txtCoins.setText(String.valueOf(TwoOrMoreVM.balance()));

      }
    });

    // Update UI
    die1.setText(String.valueOf(TwoOrMoreVM.dies.get(0).value()));
    die2.setText(String.valueOf(TwoOrMoreVM.dies.get(1).value()));
    die3.setText(String.valueOf(TwoOrMoreVM.dies.get(2).value()));
    die4.setText(String.valueOf(TwoOrMoreVM.dies.get(3).value()));
    txtCoins.setText(String.valueOf(TwoOrMoreVM.balance()));
  }

  public void BacktoWalletActivity(View v) {
    // Create an Intent to navigate to the TwoOrMoreActivity
    Intent intent = new Intent(TwoOrMoreActivity.this, WalletActivity.class);
    intent.putExtra(MAIN_BALANCE_RETURN, TwoOrMoreVM.balance());
    startActivity(intent);
  }

  @Override
  public void onBackPressed() {
    Intent change = new Intent(this, WalletActivity.class);
    int temp = TwoOrMoreVM.balance();
    change.putExtra(MAIN_BALANCE_RETURN, temp);
    setResult(RESULT_CANCELED, change);
    finish();
    super.onBackPressed();
  }

  public void getInfo(View view) {
    Intent info = new Intent(this, InformationOfDiceGamesActivity.class);
    startActivity(info);
  }

}