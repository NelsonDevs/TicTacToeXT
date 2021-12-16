package com.nelson.tictactoext;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class PreGame extends AppCompatActivity {
    private EditText playerOne;
    private EditText playerTwo;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch _switch;
    private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        playerOne = findViewById(R.id.playerOneName);
        playerTwo = findViewById(R.id.playerTwoName);
        startButton = findViewById(R.id.startButton);

        _switch = findViewById(R.id.switch1);

        _switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(playerTwo.getHint().toString().equals("")){
                    playerTwo.setHint("CPU PLAYING!");
                } else {
                    playerTwo.setHint("Enter your names.");
                }

            }
        });

        /*if (playerOne.getText().toString().equals("") || playerTwo.getText().toString().equals("")) {
            startButton.setClickable(false);
        }
        if (!playerOne.getText().toString().equals("") || !playerTwo.getText().toString().equals(""))
        {
            startButton.setClickable(true);
        } */
    }

    public void startGame(View view){

        String p1 = playerOne.getText().toString();
        String p2;
        if(_switch.isChecked()){
            p2 = "CPU";
            playerTwo.setHint("CPU PLAYING!");
        }
        else {
            p2 = playerTwo.getText().toString();
        }
        if (playerOne.getText().toString().equals("") || playerTwo.getText().toString().equals("")) {
            Toast.makeText(this,"Please pick a name for both boxes", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Board.class);
            intent.putExtra("playerOne", p1);
            intent.putExtra("playerTwo", p2);
            startActivity(intent);
        }
    }
}