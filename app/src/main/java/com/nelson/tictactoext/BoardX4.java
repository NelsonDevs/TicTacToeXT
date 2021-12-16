package com.nelson.tictactoext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class BoardX4 extends AppCompatActivity {

    private TextView playerOneName;
    private TextView playerTwoName;
    private final Button[][] buttons = new Button[3][3];
    private final ArrayList<Button> catButtons = new ArrayList<Button>();

    private String playerOne;
    private String playerTwo;
    private boolean xTurn = true;
    private boolean isWinner = false;
    private boolean CPU = false;
    private String winner = "";
    private int filledSpaces = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_x4);
        Intent intent = getIntent();

        playerOne = intent.getStringExtra("playerOne");
        playerTwo = intent.getStringExtra("playerTwo");

        if (playerTwo.equals("CPU")) {
            CPU = true;
        }


        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);

        playerOneName.setText(playerOne);
        playerTwoName.setText(playerTwo);


        Button btnOne = findViewById(R.id.button_1);
        Button btnTwo = findViewById(R.id.button_2);
        Button btnThree = findViewById(R.id.button_3);
        Button btnFour = findViewById(R.id.button_4);
        Button btnFive = findViewById(R.id.button_5);
        Button btnSix = findViewById(R.id.button_6);
        Button btnSeven = findViewById(R.id.button_7);
        Button btnEight = findViewById(R.id.button_8);
        Button btnNine = findViewById(R.id.button_9);
        Button btnten = findViewById(R.id.button_10);
        Button btnEleven = findViewById(R.id.button_11);
        Button btnTwelve = findViewById(R.id.button_12);
        Button btnThirteen = findViewById(R.id.button_13);
        Button btnFourteen = findViewById(R.id.button_14);
        Button btnFifteen = findViewById(R.id.button_15);
        Button btnSixteen = findViewById(R.id.button_16);

        buttons[0][0] = btnOne;
        buttons[0][1] = btnTwo;
        buttons[0][2] = btnThree;
        buttons[0][3] = btnFour;
        buttons[1][0] = btnFive;
        buttons[1][1] = btnSix;
        buttons[1][2] = btnSeven;
        buttons[1][3] = btnEight;
        buttons[2][0] = btnNine;
        buttons[2][1] = btnten;
        buttons[2][2] = btnEleven;
        buttons[2][3] = btnTwelve;
        buttons[3][0] = btnThirteen;
        buttons[3][1] = btnFourteen;
        buttons[3][2] = btnFifteen;
        buttons[3][3] = btnSixteen;

    }

    public void clickButton(View v) {
        Button btn = findViewById(v.getId());

        if (btn.getText() != "X" && btn.getText() != "O") {
            if (xTurn == true) {
                btn.setText("X");
                for (int i = 0; i < buttons.length; i++) {
                    for (int j = 0; j < buttons[i].length; j++) {
                        if (btn == buttons[i][j]) {
                            buttons[i][j].setText("X");
                            buttons[i][j].setAllCaps(true);
                        }
                    }
                }
                winner = checkWinner(buttons);
                if (isWinner) {
                    announceWinner(winner);
                    for (int i = 0; i < buttons.length; i++) {
                        for (int j = 0; j < buttons[i].length; j++) {
                            if (btn == buttons[i][j]) {
                                buttons[i][j].setClickable(false);
                            }
                        }
                    }
                } else if (!isWinner) {
                    checkCAT();
                }

                xTurn = false;
                if(CPU){
                    cpuPlay();
                }

            } else if (!xTurn) {
                btn.setText("O");
                for (int i = 0; i < buttons.length; i++) {
                    for (int j = 0; j < buttons[i].length; j++) {
                        if (btn == buttons[i][j]) {
                            buttons[i][j].setText("O");
                            buttons[i][j].setAllCaps(true);
                        }
                    }
                }
                winner = checkWinner(buttons);
                if (isWinner) {
                    announceWinner(winner);
                    //Toast.makeText(this,"The "+ winner +"s Win!", Toast.LENGTH_LONG).show();
                    for (int i = 0; i < buttons.length; i++) {
                        for (int j = 0; j < buttons[i].length; j++) {
                            buttons[i][j].setClickable(false);
                        }
                    }
                } else if (!isWinner) {
                    checkCAT();
                }
                xTurn = true;
            }
        }
        if (btn.getText() == "X" || btn.getText() == "O") {
            return;
        }


    }
    public void cpuPlay() {
        boolean cpuPlayed = false;
        while (!cpuPlayed  && !isWinner) {
            Random randIndex = new Random();
            int x = randIndex.nextInt(3);
            int y = randIndex.nextInt(3);

            if (buttons[x][y].getText().toString().isEmpty()) {
                buttons[x][y].setText("O");
                cpuPlayed= true;
            }
        }
        winner = checkWinner(buttons);
        if (isWinner) {
            announceWinner(winner);
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    buttons[i][j].setClickable(false);
                }
            }
        } else if (!isWinner) {
            checkCAT();
        }

        xTurn = true;
    }
    public void rematch() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setClickable(true);
                buttons[i][j].setText("");

            }
        }
        filledSpaces = 0;
        winner = "";
        isWinner = false;
        catButtons.clear();

    }

    public void checkCAT() {
        if(!isWinner){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (!buttons[i][j].getText().toString().isEmpty()) {
                        if (!catButtons.contains(buttons[i][j])) {
                            catButtons.add(buttons[i][j]);
                            filledSpaces++;
                            Log.d("FilledSpaces", String.valueOf(filledSpaces));
                            if (filledSpaces == 9) {
                                announceWinner("CAT! It's a Tie!");
                            }
                        }

                    }
                }
            }
        }


    }

    public void announceWinner(String w) {
        WinningDialogX4 winDialog = new WinningDialogX4(this, w + " wins!", this);
        winDialog.setCancelable(true);
        winDialog.show();
    }

    public boolean equals(Button a, Button b, Button c, Button d) {
        String a_ = a.getText().toString();
        String b_ = b.getText().toString();
        String c_ = c.getText().toString();
        String d_ = d.getText().toString();

        return a_ == b_ && b_ == c_ && d_ == a_ && !a_.isEmpty();
    }

    public String checkWinner(Button[][] btnArray) {


        for (int i = 0; i < 4; i++) {
            if (equals(btnArray[i][0], btnArray[i][1], btnArray[i][2],btnArray[i][3])) {
                isWinner = true;
                return btnArray[i][0].getText().toString();
            }
        }

        for (int i = 0; i < 4; i++) {
            if (equals(btnArray[0][i], btnArray[1][i], btnArray[2][i], btnArray[3][i])) {
                isWinner = true;
                return btnArray[0][i].getText().toString();
            }
        }

        if (equals(btnArray[0][0], btnArray[1][1], btnArray[2][2],btnArray[3][3])) {
            isWinner = true;
            return btnArray[0][0].getText().toString();
        }

        if (equals(btnArray[3][0], btnArray[2][1], btnArray[1][2],btnArray[0][3])) {
            isWinner = true;
            return btnArray[3][0].getText().toString();
        }


        return "";
    }

}