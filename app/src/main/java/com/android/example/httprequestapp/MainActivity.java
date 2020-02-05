package com.android.example.httprequestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView mainText;
    Button mainButton;
    ImageView imageView;

    Button playButton;
    Button nextButton;
    Button stopButton;

    int n = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = findViewById(R.id.mainText);
        mainButton = findViewById(R.id.mainKnapBtn);
        imageView = findViewById(R.id.pictureimageView);

        playButton = findViewById(R.id.playButton);
        nextButton = findViewById(R.id.nextButton);
        stopButton = findViewById(R.id.stopButton);

        final MediaPlayer[] list = new MediaPlayer[3];
        list[0] = MediaPlayer.create(this, R.raw.nmy);
        list[1] = MediaPlayer.create(this, R.raw.tr);
        list[2] = MediaPlayer.create(this, R.raw.wdylma);

        mainText.setText("Joken kommer her.");

        String pictureUrl = "https://vignette.wikia.nocookie.net/nurdpedia/images/8/87/Chuck-norris.jpg/revision/latest?cb=20180413072506";

        Picasso.get().load(pictureUrl).into(imageView);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.chucknorris.io/jokes/random";

                ChuckNorrisAPICall chuckNorrisAPICall = new ChuckNorrisAPICall();
                chuckNorrisAPICall.handleAPICall(url, mainText, getApplicationContext());
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list[n].start();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list[n].isPlaying()) {
                    list[n].pause();
                    list[n].seekTo(0);
                }
                if (n == 2) {
                    n = 0;
                }
                else {
                    n++;
                }
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list[n].pause();
                list[n].seekTo(0);
            }
        });


    }
}
