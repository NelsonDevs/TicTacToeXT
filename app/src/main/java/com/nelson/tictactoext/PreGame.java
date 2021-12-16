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
    private Switch switchX4;
    private Switch switchX5;
    private Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);

        playerOne = findViewById(R.id.playerOneName);
        playerTwo = findViewById(R.id.playerTwoName);
        startButton = findViewById(R.id.startButton);

        _switch = findViewById(R.id.switch1);
        switchX4 = findViewById(R.id.boardx4);
        switchX5 = findViewById(R.id.boardx5);

        _switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(playerTwo.getHint().toString().equals("")){
                    playerTwo.setHint("CPU PLAYING!");
                    playerTwo.setText("CPU");
                } else {
                    playerTwo.setHint("Enter your name.");
                }

            }
        });
        switchX5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switchX4.isChecked()){
                    switchX4.setChecked(false);
                }
            }
        });

        switchX4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switchX5.isChecked()){
                    switchX5.setChecked(false);
                }
            }
        });

    }

    public void startGame(View view){

        String p1 = playerOne.getText().toString();
        String p2;
        if(_switch.isChecked()){
            p2 = "CPU";
            playerTwo.setHint("CPU PLAYING!");
            playerTwo.setText("CPU");
        }
        else {
            p2 = playerTwo.getText().toString();
        }

        if (playerOne.getText().toString().equals("") || playerTwo.getText().toString().equals("")) {
            Toast.makeText(this,"Please pick a name for both boxes", Toast.LENGTH_SHORT).show();
        }

        if (switchX4.isChecked()){
            Intent intent = new Intent(this, BoardX4.class);
            intent.putExtra("playerOne", p1);
            intent.putExtra("playerTwo", p2);
            startActivity(intent);
        }
        if(switchX5.isChecked()){
            Intent intent = new Intent(this, BoardX5.class);
            intent.putExtra("playerOne", p1);
            intent.putExtra("playerTwo", p2);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, Board.class);
            intent.putExtra("playerOne", p1);
            intent.putExtra("playerTwo", p2);
            startActivity(intent);
        }
    }
}