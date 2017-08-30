package com.example.akash.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int currentPlayer = 0; // 0 = blue, 1 = purple
    int[] state = {2,2,2,2,2,2,2,2,2};
    int[][] winning = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    boolean gameStatus = true;

    public void drop(View view) {

        ImageView pressedImg = (ImageView) view;
        int x = Integer.parseInt(pressedImg.getTag().toString());

        if (state[x] == 2 && gameStatus) {
            pressedImg.setTranslationY(-1000f);
            if (currentPlayer == 0) {

                pressedImg.setImageResource(R.drawable.blue);
                currentPlayer = 1;
                state[x] = 0;

            }
            else {

                pressedImg.setImageResource(R.drawable.purple);
                currentPlayer = 0;
                state[x] = 1;

            }
            pressedImg.animate().translationYBy(1000f).setDuration(300);






            for (int[] arr : winning) {

                if (state[arr[0]] == state[arr[1]] && state[arr[0]] == state[arr[2]] && state[arr[0]] != 2) {

                    TextView results = (TextView) findViewById(R.id.messegeTextView);

                    String winner = "Blue";
                    if (state[arr[0]] == 1)
                        winner = "Purple";

                    results.setText(winner + " has won...");
                    gameStatus = false;

                }
                else {
                    boolean draw = true;

                    for (int i = 0; i < 9; i++) {

                        if (state[i] == 2)
                            draw = false;

                    }
                    if (draw) {

                        TextView results = (TextView) findViewById(R.id.messegeTextView);
                        results.setText("Game is a draw");

                    }

                }


            }

        }


    }

    public void newGame(View view) {

        for(int i = 0; i < state.length; i++)
            state[i] = 2;

        gameStatus = true;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }

        TextView results = (TextView) findViewById(R.id.messegeTextView);
        results.setText("New Game");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
