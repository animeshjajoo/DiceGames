package androidsamples.java.dicegames;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Locale;

public class WalletActivity extends AppCompatActivity {
  private Button btnDie, button_two_or_more;
  private TextView txtBalance;
  private WalletViewModel vm;
  private static final String TAG = "WalletActivity";
  static final String MAIN_BALANCE = "MAIN_BALANCE";
  private static final int REQUEST_CODE = 2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(TAG, "OnCreate");
    setContentView(R.layout.activity_wallet);

    button_two_or_more = findViewById(R.id.button_two_or_more);

    // Set an OnClickListener for the button

    vm = new ViewModelProvider(this).get(WalletViewModel.class);

    btnDie = findViewById(R.id.btn_die);
    txtBalance = findViewById(R.id.txt_balance);

    btnDie.setOnClickListener(v -> {
      vm.setDie(new Die6());
      vm.rollDie();
      updateUI();
      if (vm.dieValue() == 6) Toast.makeText(this, R.string.congrats, Toast.LENGTH_SHORT).show();
    });

    updateUI();
  }

  public void onClickofTwoorMore(View v) {
    // Create an Intent to navigate to the TwoOrMoreActivity
    Intent intent = new Intent(this, TwoOrMoreActivity.class);
    startActivity(intent);
  }

  void updateUI() {
    btnDie.setText(String.format(Locale.ENGLISH, "%d", vm.dieValue()));
    txtBalance.setText(String.format(Locale.ENGLISH, "%s %d", getString(R.string.coins), vm.balance()));
    Log.d(TAG, "Die Value = " + btnDie.getText());
    Log.d(TAG, "Balance = " + txtBalance.getText());
  }



  public void launchTwoOrMore(View view){
    Intent intent = new Intent(this, TwoOrMoreActivity.class);
    intent.putExtra(MAIN_BALANCE, vm.balance());
    startActivityForResult(intent, REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    Log.d(TAG, "onActivityResult");
    if(requestCode == REQUEST_CODE && (resultCode == RESULT_OK || resultCode == RESULT_CANCELED)){
      if (data != null) {
        int balance = data.getIntExtra(TwoOrMoreActivity.MAIN_BALANCE_RETURN,0);
        Log.d(TAG, "Updated Balance = " + balance);
        vm.setBalance(balance);
        updateUI();
      }
    }
  }
}