package com.nelson.tictactoext;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class WinningDialog extends Dialog {

    private final String message;
    private final Board board;

    public WinningDialog(@NonNull Context context,String message, Board board) {
        super(context);
        this.message = message;
        this.board = board;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_dialog_layout);

        final TextView winningMessage = findViewById(R.id.winningMessageText);
        final Button playAgain = findViewById(R.id.playAgainButton);

        winningMessage.setText(message);
        playAgain.setOnClickListener(view -> {
            board.rematch();
            dismiss();
        });

    }
}
