package androidsamples.java.dicegames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class TwoOrMoreActivity extends AppCompatActivity {

  static final String MAIN_BALANCE_RETURN = "MAIN_BALANCE_RETURN";
  TwoOrMoreViewModel tmvm;
  Button go, back, info;
  TextView one_die, two_die, three_die, four_die, txtCoins;

  RadioGroup radioGroup;
  RadioButton radioButton;
  GameResult game_result_toast;
  EditText wager_txt;
  @SuppressLint("NonConstantResourceId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    tmvm = new ViewModelProvider(this).get(TwoOrMoreViewModel.class);

    int balance = getIntent().getIntExtra(WalletActivity.MAIN_BALANCE, 0);
    tmvm.setBalance(balance);

    tmvm.setDie();
    txtCoins = findViewById(R.id.txt_coins);
    go = findViewById(R.id.btn_go);
    back = findViewById(R.id.btn_back);
    info = findViewById(R.id.btn_info);
    one_die = findViewById(R.id.die1);
    two_die = findViewById(R.id.die2);
    three_die = findViewById(R.id.die3);
    four_die = findViewById(R.id.die4);
    wager_txt = findViewById(R.id.Wager);
    radioGroup = findViewById(R.id.radioGroup);


    go.setOnClickListener(v -> {
      int selected = radioGroup.getCheckedRadioButtonId();
      radioButton = findViewById(selected);

      switch (selected) {
        case R.id.alike_2:
          tmvm.setGameType(GameType.TWO_ALIKE);
          break;
        case R.id.alike_3:
          tmvm.setGameType(GameType.THREE_ALIKE);
          break;
        case R.id.alike_4:
          tmvm.setGameType(GameType.FOUR_ALIKE);
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
        tmvm.setWager((Integer.parseInt(wager_txt.getText().toString())));
      } catch (NumberFormatException e){
        Toast.makeText(this, "Wager not a number!", Toast.LENGTH_SHORT).show();
        return;
      }

      if (!tmvm.isValidWager()) {
        Toast.makeText(this, "Wager is invalid", Toast.LENGTH_SHORT).show();
      } else {

        game_result_toast = tmvm.play();

        if (game_result_toast == GameResult.UNDECIDED) {
          throw new IllegalStateException("Game Result can't be decided!");
        }

        if (game_result_toast == GameResult.WIN)
          Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        else
          Toast.makeText(this, "Loss!", Toast.LENGTH_SHORT).show();

        wager_txt.setText("");

        // Update UI
        one_die.setText(String.valueOf(tmvm.dies.get(0).value()));
        two_die.setText(String.valueOf(tmvm.dies.get(1).value()));
        three_die.setText(String.valueOf(tmvm.dies.get(2).value()));
        four_die.setText(String.valueOf(tmvm.dies.get(3).value()));
        txtCoins.setText(String.valueOf(tmvm.balance()));

      }
    });

    // Update UI
    one_die.setText(String.valueOf(tmvm.dies.get(0).value()));
    two_die.setText(String.valueOf(tmvm.dies.get(1).value()));
    three_die.setText(String.valueOf(tmvm.dies.get(2).value()));
    four_die.setText(String.valueOf(tmvm.dies.get(3).value()));
    txtCoins.setText(String.valueOf(tmvm.balance()));
  }

  public void returnToWallet(View view) {
    Intent change = new Intent(this, WalletActivity.class);
    int tmp = tmvm.balance();
    change.putExtra(MAIN_BALANCE_RETURN, tmp);
    setResult(RESULT_OK, change);
    finish();
  }

  @Override
  public void onBackPressed() {
    Intent change = new Intent(this, WalletActivity.class);
    int tmp = tmvm.balance();
    change.putExtra(MAIN_BALANCE_RETURN, tmp);
    setResult(RESULT_CANCELED, change);
    finish();
    super.onBackPressed();
  }

  public void getInfo(View view) {
    Intent info = new Intent(this, InformationOfDiceGamesActivity.class);
    startActivity(info);
  }

}