package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive=true;

    // Player Representation
    // 0-X
    // 1-O
    int activePlayer=0;
    int [] gameState={2, 2, 2, 2, 2, 2, 2, 2, 2};
        // State means
            /*
                0-X
                1-O
                2=Null
             */
    int [][] winPositions={{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
        public void playerTap(View view){
            ImageView img=(ImageView) view;
            int tappedImage=Integer.parseInt(img.getTag().toString());
            if(!gameActive){
                gameReset(view);
            }
            if (gameState[tappedImage]==2 ) {
                gameState[tappedImage] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status=findViewById(R.id.status);
                    status.setText("O's Turn Tap to play");
                } else {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    TextView status=findViewById(R.id.status);
                    status.setText("X's Turn Tap to play");
                }

                img.animate().translationYBy(1000f).setDuration(300);
            }
            // check if anyone has won
            for (int [] winPositions: winPositions ){
                if(gameState[winPositions[0]]==gameState[winPositions[1]] &&
                        gameState[winPositions[1]]==gameState[winPositions[2]] &&
                        gameState[winPositions[2]]!=2){
                    // Someone has won
                    String winnerStr;
                    gameActive=false;
                    if(gameState[winPositions[0]]== 0){
                        winnerStr="X has won";
                    }
                    else{
                        winnerStr="O has won";

                    }
                    // Update the status bar for winner announcement
                    Toast.makeText(this, winnerStr, Toast.LENGTH_SHORT).show();
                    TextView status=findViewById(R.id.status);
                    status.setText("Tap to play");
                }


            }

        }

    public  void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
