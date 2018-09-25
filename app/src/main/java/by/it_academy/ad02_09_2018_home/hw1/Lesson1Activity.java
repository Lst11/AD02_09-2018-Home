package by.it_academy.ad02_09_2018_home.hw1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import by.it_academy.ad02_09_2018_home.R;

public class Lesson1Activity extends Activity implements View.OnClickListener {
    private Button button;
    private TextView string1;
    private TextView string2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson1);

        button = (Button) findViewById(R.id.button);
        string1 = (TextView) findViewById(R.id.textView1);
        string2 = (TextView) findViewById(R.id.textView2);

        button.setOnClickListener(this);

        string1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = (String) string1.getText();
                string1.setText(string2.getText());
                string2.setText(temp);
            }
        });
    }

    public void clickTextView2(View view) {
        String temp = (String) string1.getText();
        string1.setText(string2.getText());
        string2.setText(temp);
    }

    @Override
    public void onClick(View v) {
        String temp = (String) string1.getText();
        string1.setText(string2.getText());
        string2.setText(temp);
        button.setBackgroundColor(getRandomColor());
    }

    private int getRandomColor() {
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
