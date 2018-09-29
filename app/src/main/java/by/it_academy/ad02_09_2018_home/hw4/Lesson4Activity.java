package by.it_academy.ad02_09_2018_home.hw4;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import by.it_academy.ad02_09_2018_home.R;
import by.it_academy.ad02_09_2018_home.hw3.Lesson3Activity;

public class Lesson4Activity extends Activity {

    private int[] values = {3, 2, 1, 4};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4);
        ImageView owl = (ImageView) findViewById(R.id.owl);
        owl.setBackgroundResource(R.drawable.animation_owl);
        final AnimationDrawable anim = (AnimationDrawable) owl.getBackground();

        final Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                anim.start();
            }
        });

        final Button stop = findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                anim.stop();
            }
        });

        final Button calculate = findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.editTextNumbers);
                if (TextUtils.isEmpty(editText.getText())) {
                    return;
                } else {
                    try {
                        String string = editText.getText().toString();
                        String strArr[] = string.split(" ");
                        if (strArr.length > 5) {
                            Toast toast = Toast.makeText(Lesson4Activity.this, R.string.diagram_array_error, Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return;
                        }
                        int values[] = new int[strArr.length];
                        for (int i = 0; i < strArr.length; i++) {
                            values[i] = Integer.parseInt(strArr[i]);
                        }
                        CustomView customView = new CustomView(Lesson4Activity.this);
                        customView.setValues(values);
                        RelativeLayout layout = (RelativeLayout) findViewById(R.id.layoutDiagram);
                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
                        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                        customView.setLayoutParams(params);
                        layout.addView(customView);
                    } catch (Exception exception) {
                        Toast toast = Toast.makeText(Lesson4Activity.this, R.string.input_error, Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                        return;
                    }
                }
            }
        });


    }
}




