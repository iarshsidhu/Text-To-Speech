package com.texttospeech;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.texttospeech.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        binding.speakBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speak();
            }
        });
    }

    private void speak() {
        String text = binding.textEditText.getText().toString();
        float pitch = (float) binding.pitchSeekBar.getProgress() / 50;
        if (pitch < 0.1) pitch = 0.1f;
        float speed = (float) binding.speedSeekBar.getProgress() / 50;
        if (speed < 0.1) speed = 0.1f;

        textToSpeech.setPitch(pitch);
        textToSpeech.setSpeechRate(speed);
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}