package jajodia.com.scarnedice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView myScore;
    TextView compScore;
    TextView message;
    ImageView dicePhoto;
    Button rollBtn;
    Button holdBtn;
    Button resetBtn;

    int userOverall = 0;
    int userTurn;
    int getUserTurn = 0;
    int compOverall = 0;
    int compTurn;
    int getCompTurn = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myScore = (TextView) findViewById(R.id.textYourScore);
        compScore = (TextView) findViewById(R.id.textCompScore);
        message = (TextView) findViewById(R.id.text_win);
        dicePhoto = (ImageView) findViewById(R.id.diceImage);
        rollBtn = (Button) findViewById(R.id.btnRoll);
        holdBtn = (Button) findViewById(R.id.btnHold);
        resetBtn = (Button) findViewById(R.id.btnReset);



        rollBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Random random = new Random();
                int randomIndex = random.nextInt(6) + 1;
                Log.i("randomUser", Integer.toString(randomIndex));
                String drawableName = "dice" + Integer.toString(randomIndex);
                int resourceId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
                dicePhoto.setImageResource(resourceId);

                if(randomIndex == 1) {
                    Toast.makeText(getApplicationContext(), "User gets 1", Toast.LENGTH_SHORT).show();
                    userTurn = 0;
                    getUserTurn = 0;

                    if(compOverall > 100 && userOverall < 100) {
                        message.setText("Computer Wins");
                        myScore.setText("Your score:" + Integer.toString(userOverall));
                        compScore.setText("Computer score:" + Integer.toString(compOverall));
                        userOverall = 0;
                        userTurn = 0;
                        compOverall = 0;
                        compTurn = 0;
                    }
                    else if(compOverall < 100 && userOverall > 100) {
                        message.setText("User Wins");
                        myScore.setText("Your score:" + Integer.toString(userOverall));
                        compScore.setText("Computer score:" + Integer.toString(compOverall));
                        userOverall = 0;
                        userTurn = 0;
                        compOverall = 0;
                        compTurn = 0;
                    }
                    computerTurn();


                }
                else {
                    userTurn = randomIndex;
                    getUserTurn += userTurn;
                }
            }
        });

        holdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userOverall += getUserTurn;

                myScore.setText("Your score:" + Integer.toString(userOverall));
                userTurn = 0;
                getUserTurn = 0;

                if(compOverall > 100 && userOverall < 100) {
                    message.setText("Computer Wins");
                    myScore.setText("Your score:" + Integer.toString(userOverall));
                    compScore.setText("Computer score:" + Integer.toString(compOverall));
                    userOverall = 0;
                    userTurn = 0;
                    compOverall = 0;
                    compTurn = 0;

                }
                else if(compOverall < 100 && userOverall > 100) {
                    message.setText("User Wins");
                    myScore.setText("Your score:" + Integer.toString(userOverall));
                    compScore.setText("Computer score:" + Integer.toString(compOverall));
                    userOverall = 0;
                    userTurn = 0;
                    compOverall = 0;
                    compTurn = 0;
                }

                computerTurn();

            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userOverall = 0;
                userTurn = 0;
                compOverall = 0;
                compTurn = 0;

                myScore.setText("Your score:" + Integer.toString(userOverall));
                compScore.setText("Computer score:" + Integer.toString(compOverall));

            }
        });

    }

    public void computerTurn() {
        holdBtn.setEnabled(false);
        rollBtn.setEnabled(false);

        int randomIndex = 5;
        Random random = new Random();
        int turn = random.nextInt(6) + 1;

        while(turn != 1) {
            Random rando = new Random();
            randomIndex = rando.nextInt(6) + 1;
            Log.i("randomComputer", Integer.toString(randomIndex));
            String drawableName = "dice" + Integer.toString(randomIndex);
            int resourceId = getResources().getIdentifier(drawableName, "drawable", getPackageName());
            dicePhoto.setImageResource(resourceId);

            if(randomIndex == 1) {
                Toast.makeText(getApplicationContext(), "Computer gets 1", Toast.LENGTH_SHORT).show();
                compTurn = 0;
                getCompTurn = 0;
                break;
            }
            else {
                compTurn = randomIndex;
                getCompTurn += compTurn;
            }
            turn--;
        }

        compOverall += getCompTurn;
        compScore.setText("Computer Score:" + Integer.toString(compOverall));
        compTurn = 0;
        getCompTurn = 0;
        holdBtn.setEnabled(true);
        rollBtn.setEnabled(true);
    }
}
