package androidsamples.java.dicegames;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class TwoOrMoreActivity extends AppCompatActivity {

  static final String MAIN_BALANCE_RETURN = "MAIN_BALANCE_RETURN";
  TwoOrMoreViewModel TwoOrMoreVM;
  Button go, back, info;
  TextView die_one, die_two, die_three, die_four, txt_Coins;

  RadioGroup radioGroup;
  RadioButton radioButton;
  GameResult game_result_toast;
  EditText wager_txt;
  @SuppressLint("NonConstantResourceId")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    TwoOrMoreVM = new ViewModelProvider(this).get(TwoOrMoreViewModel.class);

    int balance = getIntent().getIntExtra(WalletActivity.MAIN_BALANCE, 0);
    TwoOrMoreVM.setBalance(balance);

    TwoOrMoreVM.setDie();
    txt_Coins = findViewById(R.id.txt_coins);
    go = findViewById(R.id.btn_go);
    back = findViewById(R.id.btn_back);
    info = findViewById(R.id.btn_info);
    die_one = findViewById(R.id.die1);
    die_two = findViewById(R.id.die2);
    die_three = findViewById(R.id.die3);
    die_four = findViewById(R.id.die4);
    wager_txt = findViewById(R.id.Wager);
    radioGroup = findViewById(R.id.radioGroup);


    go.setOnClickListener(v -> {
      int selected = radioGroup.getCheckedRadioButtonId();
      radioButton = findViewById(selected);

      switch (selected) {
        case R.id.alike_2:
          TwoOrMoreVM.setGameType(GameType.TWO_ALIKE);
          break;
        case R.id.alike_3:
          TwoOrMoreVM.setGameType(GameType.THREE_ALIKE);
          break;
        case R.id.alike_4:
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
        die_one.setText(String.valueOf(TwoOrMoreVM.dies.get(0).value()));
        die_two.setText(String.valueOf(TwoOrMoreVM.dies.get(1).value()));
        die_three.setText(String.valueOf(TwoOrMoreVM.dies.get(2).value()));
        die_four.setText(String.valueOf(TwoOrMoreVM.dies.get(3).value()));
        txt_Coins.setText(String.valueOf(TwoOrMoreVM.balance()));

      }
    });

    // Update UI
    die_one.setText(String.valueOf(TwoOrMoreVM.dies.get(0).value()));
    die_two.setText(String.valueOf(TwoOrMoreVM.dies.get(1).value()));
    die_three.setText(String.valueOf(TwoOrMoreVM.dies.get(2).value()));
    die_four.setText(String.valueOf(TwoOrMoreVM.dies.get(3).value()));
    txt_Coins.setText(String.valueOf(TwoOrMoreVM.balance()));
  }

  public void returnToWallet(View view) {
    Intent change = new Intent(this, WalletActivity.class);
    int TwoOrMoreBalance = TwoOrMoreVM.balance();
    change.putExtra(MAIN_BALANCE_RETURN, TwoOrMoreBalance);
    setResult(RESULT_OK, change);
    finish();
  }

  @Override
  public void onBackPressed() {
    Intent change = new Intent(this, WalletActivity.class);
    int TwoOrMoreBalance = TwoOrMoreVM.balance();
    change.putExtra(MAIN_BALANCE_RETURN, TwoOrMoreBalance);
    setResult(RESULT_CANCELED, change);
    finish();
    super.onBackPressed();
  }

  public void getInfo(View view) {
    Intent info = new Intent(this, InformationOfDiceGamesActivity.class);
    startActivity(info);
  }

}