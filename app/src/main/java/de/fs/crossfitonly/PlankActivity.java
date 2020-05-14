package de.fs.crossfitonly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class PlankActivity extends AppCompatActivity {

    private EditText EditTextInput;
    private TextView TextViewCountDown;
    private Button ButtonSet;
    private Button ButtonStartPause;
    private Button ButtonReset;
    private CountDownTimer CountDownTimer;
    private boolean TimerRunning;
    private long StartTimeInMillis;
    private long TimeLeftInMillis;
    private long EndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plank);
        EditTextInput = findViewById(R.id.edit_text_input);
        TextViewCountDown = findViewById(R.id.text_view_countdown);
        ButtonSet = findViewById(R.id.button_set);
        ButtonStartPause = findViewById(R.id.button_start_pause);
        ButtonReset = findViewById(R.id.button_reset);

        ButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = EditTextInput.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(PlankActivity.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(PlankActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
                setTime(millisInput);
                EditTextInput.setText("");
            }
        });

        ButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        ButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

    }
    private void setTime(long milliseconds) {
        StartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }
    private void startTimer() {
        EndTime = System.currentTimeMillis() + TimeLeftInMillis;
        CountDownTimer = new CountDownTimer(TimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                TimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                TimerRunning = false;
                updateWatchInterface();
            }
        }.start();
        TimerRunning = true;
        updateWatchInterface();
    }
    private void pauseTimer() {
        CountDownTimer.cancel();
        TimerRunning = false;
        updateWatchInterface();
    }
    private void resetTimer() {
        TimeLeftInMillis = StartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }
    private void updateCountDownText() {
        int hours = (int) (TimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((TimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (TimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        TextViewCountDown.setText(timeLeftFormatted);
    }
    private void updateWatchInterface() {
        if (TimerRunning) {
            EditTextInput.setVisibility(View.INVISIBLE);
            ButtonSet.setVisibility(View.INVISIBLE);
            ButtonReset.setVisibility(View.INVISIBLE);
            ButtonStartPause.setText("Pause");
        } else {
            EditTextInput.setVisibility(View.VISIBLE);
            ButtonSet.setVisibility(View.VISIBLE);
            ButtonStartPause.setText("Start");
            if (TimeLeftInMillis < 1000) {
                ButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                ButtonStartPause.setVisibility(View.VISIBLE);
            }
            if (TimeLeftInMillis < StartTimeInMillis) {
                ButtonReset.setVisibility(View.VISIBLE);
            } else {
                ButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
