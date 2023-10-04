package androidsamples.java.dicegames;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class InformationOfDiceGamesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_of_dice_game);

        // Initialize and set a click listener for the "Back to game" button
        Button backButton = findViewById(R.id.buttonBackToGame);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the TwoOrMoreActivity
                Intent intent = new Intent(InformationOfDiceGamesActivity.this, TwoOrMoreActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

