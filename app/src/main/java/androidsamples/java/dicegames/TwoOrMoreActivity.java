package androidsamples.java.dicegames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TwoOrMoreActivity extends AppCompatActivity {

  private TwoOrMoreViewModel viewModel;
  private TextView txtCoins;
  private Button btnTwoAlike, btnThreeAlike, btnFourAlike, btnGo, btnBack, btnInfo;
  private EditText Wager;
  private Button die1, die2, die3, die4;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_two_or_more);

    viewModel = new TwoOrMoreViewModel();

    // Initialize UI components
    txtCoins = findViewById(R.id.textViewCoins);
    btnTwoAlike = findViewById(R.id.radioButton2Alike);
    btnThreeAlike = findViewById(R.id.radioButton3Alike);
    btnFourAlike = findViewById(R.id.radioButton4Alike);
    Wager = findViewById(R.id.editTextWager);
    btnGo = findViewById(R.id.buttonGo);
    btnBack = findViewById(R.id.buttonBack);
    btnInfo = findViewById(R.id.buttonInfo);
    die1 = findViewById(R.id.buttonDie1);
    die2 = findViewById(R.id.buttonDie2);
    die3 = findViewById(R.id.buttonDie3);
    die4 = findViewById(R.id.buttonDie4);

    // Find the "back" button by its ID
    Button backButton = findViewById(R.id.buttonBack);

    // Set an OnClickListener for the button
    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // Create an Intent to navigate back to the WalletActivity
        Intent intent = new Intent(TwoOrMoreActivity.this, WalletActivity.class);
        startActivity(intent);
      }
    });

    Button infoButton = findViewById(R.id.buttonInfo);

    infoButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent intent = new Intent(TwoOrMoreActivity.this, InformationOfDiceGamesActivity.class);
        startActivity(intent);
      }
    });

//    // Set click listeners for the game type buttons
//    btnTwoAlike.setOnClickListener(v -> {
//      viewModel.setGameType(GameType.TWO_ALIKE);
//      updateUI();
//    });
//
//    btnThreeAlike.setOnClickListener(v -> {
//      viewModel.setGameType(GameType.THREE_ALIKE);
//      updateUI();
//    });
//
//    btnFourAlike.setOnClickListener(v -> {
//      viewModel.setGameType(GameType.FOUR_ALIKE);
//      updateUI();
//    });

    // Set click listener for the "Go" button
//    btnGo.setOnClickListener(v -> {
//      String wagerStr = Wager.getText().toString();
//      if (!wagerStr.isEmpty()) {
//        int wager = Integer.parseInt(wagerStr);
//        viewModel.setWager(wager);
//        if (viewModel.isValidWager()) {
//          // Roll the dice and update UI with the result
//          GameResult result = viewModel.play();
//          updateDiceUI(viewModel.diceValues());
//          updateUI();
//          // Show a toast message with the game result
//          showGameResultToast(result);
//        } else {
//          Toast.makeText(this, "Invalid wager amount.", Toast.LENGTH_SHORT).show();
//        }
//      } else {
//        Toast.makeText(this, "Please enter a wager amount.", Toast.LENGTH_SHORT).show();
//      }
//    });
//
//    // Set click listeners for the "Back" and "Info" buttons
//    btnBack.setOnClickListener(v -> onBackPressed());
//    btnInfo.setOnClickListener(v -> {
//      // Implement the logic to show game rules or information activity here
//      // You can launch a new activity or display a dialog with game rules
//    });
//
//    // Initialize the UI
//    updateUI();
//  }
//
//  private void updateUI() {
//    txtCoins.setText(getString(R.string.coins_format, viewModel.balance()));
//    Wager.setText(String.valueOf(viewModel.wager()));
//  }
//
//  private void showGameResultToast(GameResult result) {
//    String message;
//    switch (result) {
//      case WIN:
//        message = "Congratulations! You won.";
//        break;
//      case LOSE:
//        message = "Sorry, you lost.";
//        break;
//      default:
//        message = "Game result: " + result.toString();
//        break;
//    }
//    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }
}
