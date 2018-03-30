package proj.jaeshim.project_for_android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private TextView dice1 = null;
    private TextView dice2 = null;
    private TextView dice3 = null;
    private int value = 0;
    private int rotateValue = 500;
    private int visibleCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
        dice1=(TextView)findViewById(R.id.dice1);
        dice2=(TextView)findViewById(R.id.dice2);
        dice3=(TextView)findViewById(R.id.dice3);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                switch (visibleCount){
                    case 1:
                        dice2.setVisibility(View.VISIBLE);
                        visibleCount++;
                        break;
                    case 2:
                        dice3.setVisibility(View.VISIBLE);
                        visibleCount++;
                        break;
                    case 3:
                        dice2.setVisibility(View.INVISIBLE);
                        dice3.setVisibility(View.INVISIBLE);
                        visibleCount=1;
                        break;

                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHandler.sendEmptyMessage(0);
        List<ViewPropertyAnimator> animatorList = new ArrayList<ViewPropertyAnimator>();

        rotateValue += 500;

        animatorList.add(dice1.animate());
        animatorList.add(dice2.animate());
        animatorList.add(dice3.animate());

        for(ViewPropertyAnimator animator : animatorList){
            Random rand = new Random();

            animator.rotation(rotateValue);
        }

        if(rotateValue < 0){
            rotateValue = 0;
        }

        value=0;
        return super.onTouchEvent(event);
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Random rand = new Random();
            int randVal1 = rand.nextInt(6)+1;
            int randVal2 = rand.nextInt(6)+1;
            int randVal3 = rand.nextInt(6)+1;

            dice1.setText(randVal1+"");
            dice2.setText(randVal2+"");
            dice3.setText(randVal3+"");

            value++;
            if(value <= 50){
                mHandler.sendEmptyMessage(0);
            }

        }
    };
}


