package com.example.ghostbusters.game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ghostbusters.R;

public class MainGameActivity extends AppCompatActivity {
    private static final String SHARED_PREFERENCES = "scores";
    private static final int ONE_SEC = 1000;
    private static final int COUNTDOWN_SECS = 10;

    private static String currentPlayer;
    private static SharedPreferences sharedPref;
    private static SharedPreferences.Editor editor;
    private static int widthGame;
    private static int heightGame;

    private TextView playerScore;
    private TextView bestPlayerScore;
    private TextView topScoreText;
    private TextView topScorePlayerText;
    private TextView countdownText;
    private Button startGameButton;
    private Button goBackButton;
    private ConstraintLayout gameConstraint;
    private ImageView ghostImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        currentPlayer = getIntent().getExtras().getString("player");

        addFunctionality();
    }

    private void addFunctionality() {
        TextView playerPlayingText = findViewById(R.id.playerPlayingText);
        playerPlayingText.setText(currentPlayer);

        playerScore = findViewById(R.id.playerScore);
        bestPlayerScore = findViewById(R.id.bestPlayerScore);
        topScoreText = findViewById(R.id.topScoreText);
        topScorePlayerText = findViewById(R.id.topScorePlayerText);
        countdownText = findViewById(R.id.countdownText);

        startGameButton = findViewById(R.id.startGameButton);
        startGameButton.setOnClickListener(v -> startGame());

        goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(v -> goBack());

        gameConstraint = findViewById(R.id.gameConstraint);

        sharedPref = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        bestPlayerScore.setText(Integer.toString(sharedPref.getInt(currentPlayer, 0)));
        topScoreText.setText(Integer.toString(sharedPref.getInt("maxScore", 0)));
        topScorePlayerText.setText(sharedPref.getString("topPlayer", "Nadie"));
    }

    private void startGame() {
        widthGame = gameConstraint.getWidth();
        heightGame = gameConstraint.getHeight();

        ghostImg = findViewById(R.id.ghostImg);
        ghostImg.setOnClickListener(v -> increasePoints(ghostImg));
        ghostImg.setVisibility(View.VISIBLE);
        moveGhost(ghostImg);

        countdownText.setText(Integer.toString(COUNTDOWN_SECS));
        CountDownTimer countDown = new CountDownTimer((COUNTDOWN_SECS * ONE_SEC), ONE_SEC) {

            public void onTick(long millisUntilFinished) {
                countdownText.setText(Integer.toString(Integer.parseInt(countdownText.getText().toString()) - 1));
            }
            public void onFinish() {
                endGame(ghostImg);
            }
        };

        countDown.start();

        startGameButton.setEnabled(!startGameButton.isEnabled());
    }

    private void moveGhost(ImageView ghostImg) {
        ghostImg.setX(widthGame - ghostImg.getWidth() - (int) (Math.random() * (widthGame - ghostImg.getWidth())));
        ghostImg.setY(heightGame - ghostImg.getHeight() - (int) (Math.random() * (heightGame - ghostImg.getHeight())));
    }

    private void increasePoints(ImageView ghostImg) {
        playerScore.setText(Integer.toString(Integer.parseInt(playerScore.getText().toString()) + 1));
        moveGhost(ghostImg);
    }

    private void endGame(ImageView ghostImg) {
        Toast.makeText(this, "Game over", Toast.LENGTH_SHORT).show();

        int finalScore = Integer.parseInt(playerScore.getText().toString());

        if (sharedPref.getInt(currentPlayer, 0) == 0 || sharedPref.getInt(currentPlayer, 0) < finalScore) {
            editor.putInt(currentPlayer, finalScore);
        }

        if (sharedPref.getInt("maxScore", 0) < finalScore) {
            editor.putString("topPlayer", currentPlayer);
            editor.putInt("maxScore", finalScore);
        }


        editor.commit();

        bestPlayerScore.setText(Integer.toString(sharedPref.getInt(currentPlayer, 0)));
        topScoreText.setText(Integer.toString(sharedPref.getInt("maxScore", 0)));
        topScorePlayerText.setText(sharedPref.getString("topPlayer", "Nadie"));


        playerScore.setText("0");
        countdownText.setText("");
        startGameButton.setText("Play again");

        ghostImg.setVisibility(View.INVISIBLE);
        startGameButton.setEnabled(!startGameButton.isEnabled());
    }

    private void goBack() {
        finish();
    }
}