package com.example.game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private String playerName;
    private int moves = 0;
    private TextView welcomeTextView;
    private GridView gridView;
    private Button resetButton;
    private List<Card> cards;
    private Card lastSelectedCard = null;
    private boolean isCardFlipped = false;
    private int turnCount = 0; // Add this field to count the turns

    private TextView timerTextView; // Add this field
    private CountDownTimer gameTimer; // Add this field


    private TextView movesTextView; // Add this field


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        playerName = getIntent().getStringExtra("PLAYER_NAME");
        welcomeTextView = findViewById(R.id.gameTitleTextView);
        gridView = findViewById(R.id.gridLayout);
        resetButton = findViewById(R.id.restartButton);
        movesTextView = findViewById(R.id.movesTextView); // Initialize the movesTextView
        timerTextView = findViewById(R.id.timerTextView); // Initialize the timerTextView
        setupGameBoard();

        welcomeTextView.setText("Bienvenido, " + playerName + "!");
        setupGameBoard();

        resetButton.setOnClickListener(v -> resetGame());
    }

    private void setupGameBoard() {
        // Initialize the cards and set up the adapter for GridView
        cards = getShuffledCards();
        CardAdapter adapter = new CardAdapter(this, cards);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Card selectedCard = cards.get(position);
            handleCardSelection(selectedCard);
        });

        // Start the game timer
        gameTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Tiempo: " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Tiempo: 0s");
                endGame();
            }
        }.start();
    }

    private List<Card> getShuffledCards() {
    // Create and shuffle the list of cards
    List<Card> cards = new ArrayList<>();
    cards.add(new Card(R.drawable.poker_6, "6"));
    cards.add(new Card(R.drawable.poker_6, "6")); //1
    cards.add(new Card(R.drawable.poker_7, "7"));
    cards.add(new Card(R.drawable.poker_7, "7")); //2
    cards.add(new Card(R.drawable.poker_8, "8"));
    cards.add(new Card(R.drawable.poker_8, "8")); //3
    cards.add(new Card(R.drawable.poker_9, "9"));
    cards.add(new Card(R.drawable.poker_9, "9")); //4
    cards.add(new Card(R.drawable.poker_10, "10"));
    cards.add(new Card(R.drawable.poker_10, "10")); //5
    cards.add(new Card(R.drawable.poker_joker, "joker"));
    cards.add(new Card(R.drawable.poker_joker, "joker")); //6
    cards.add(new Card(R.drawable.poker_queen, "queen"));
    cards.add(new Card(R.drawable.poker_queen, "queen")); //7
    cards.add(new Card(R.drawable.poker_king, "king"));
    cards.add(new Card(R.drawable.poker_king, "king")); //8

    // Shuffle the list
    Collections.shuffle(cards);
    return cards;
}

    private void handleCardSelection(Card selectedCard) {
    // Logic for handling card selection and checking for pairs
    if (turnCount < 2 && !selectedCard.isFlipped() && !selectedCard.isMatched()) {
        selectedCard.setFlipped(true);
        if (lastSelectedCard != null) {
            if (lastSelectedCard.getName().trim().equals(selectedCard.getName().trim())) {
                // Pair found
                lastSelectedCard.setMatched(true);
                selectedCard.setMatched(true);
                // Keep the matched cards flipped
                lastSelectedCard.setFlipped(true);
                selectedCard.setFlipped(true);
                lastSelectedCard = null;
                turnCount = 0;
            } else {
                // No pair found, flip the cards back after a delay
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lastSelectedCard.setFlipped(false);
                        selectedCard.setFlipped(false);
                        lastSelectedCard = null;
                        turnCount = 0;
                        ((BaseAdapter) gridView.getAdapter()).notifyDataSetChanged();
                    }
                }, 1000); // 1000 milliseconds delay
            }
        } else {
            lastSelectedCard = selectedCard;
            turnCount++;
        }
        moves++;
        movesTextView.setText("Movimientos: " + moves);
    }
    checkGameEnd();
    ((BaseAdapter) gridView.getAdapter()).notifyDataSetChanged();
}

    private void resetGame() {
        // Reset the game board and shuffle the cards
        for (Card card : cards) {
            card.setFlipped(false);
            card.setMatched(false);
        }
        Collections.shuffle(cards);
        ((BaseAdapter) gridView.getAdapter()).notifyDataSetChanged();
        moves = 0;
        movesTextView.setText("Movimientos: " + moves);
    }

    private void endGame() {
    Intent intent = new Intent(this, ResultActivity.class);
    intent.putExtra("PLAYER_NAME", playerName);
    intent.putExtra("MOVES", moves);
    startActivity(intent);
    }

    private void checkGameEnd() {
        // Check if all cards have been matched
        boolean allMatched = true;
        for (Card card : cards) {
            if (!card.isMatched()) {
                allMatched = false;
                break;
            }
        }

        // If all cards have been matched, end the game
        if (allMatched) {
            endGame();
        }
    }

}