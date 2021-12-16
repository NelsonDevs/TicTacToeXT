package com.nelson.tictactoext;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class WinningDialogX5 extends Dialog {

    private final String message;
    private final BoardX5 board;

    public WinningDialogX5(@NonNull Context context, String message, BoardX5 board) {
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
