package by.it_academy.ad02_09_2018_home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import by.it_academy.ad02_09_2018_home.hw1.Lesson1Activity;
import by.it_academy.ad02_09_2018_home.hw2.Lesson2Activity;
import by.it_academy.ad02_09_2018_home.hw3.Lesson3Activity;
import by.it_academy.ad02_09_2018_home.hw4.Lesson4Activity;
import by.it_academy.ad02_09_2018_home.hw5.Lesson5Activity;
import by.it_academy.ad02_09_2018_home.hw6.Lesson6Activity;
import by.it_academy.ad02_09_2018_home.hw7.Lesson7Activity;

public class MenuActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Lesson1Activity.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Lesson2Activity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Lesson3Activity.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Lesson4Activity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Lesson5Activity.class);
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Lesson6Activity.class);
                startActivity(intent);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, Lesson7Activity.class);
                startActivity(intent);
            }
        });
    }
}
