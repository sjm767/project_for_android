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
    private List<ImageView> diceList = new ArrayList<ImageView>();
    private List<ViewPropertyAnimator> animatorList = new ArrayList<ViewPropertyAnimator>();

    private int value = 0;
    private int rotateValue = 500;
    private int visibleCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout ll = (LinearLayout)findViewById(R.id.ll);
        diceList.add((ImageView)findViewById(R.id.dice1));
        diceList.add((ImageView)findViewById(R.id.dice2));
        diceList.add((ImageView)findViewById(R.id.dice3));
        fab = (FloatingActionButton)findViewById(R.id.fab);

        for(ImageView dice : diceList){
            animatorList.add(dice.animate());
        }

        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                switch (visibleCount){
                    case 1:
                        diceList.get(1).setVisibility(View.VISIBLE);
                        visibleCount++;
                        break;
                    case 2:
                        diceList.get(2).setVisibility(View.VISIBLE);
                        visibleCount++;
                        break;
                    case 3:
                        diceList.get(1).setVisibility(View.INVISIBLE);
                        diceList.get(2).setVisibility(View.INVISIBLE);
                        visibleCount=1;
                        break;

                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHandler.sendEmptyMessage(0);
        rotateValue += 500;

        for(ViewPropertyAnimator animator : animatorList){
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
            for(ImageView dice : diceList){
                int randVal = rand.nextInt(6)+1;

                switch (randVal){
                    case 1:
                        dice.setImageResource(R.drawable.dice1);
                        break;
                    case 2:
                        dice.setImageResource(R.drawable.dice2);
                        break;
                    case 3:
                        dice.setImageResource(R.drawable.dice3);
                        break;
                    case 4:
                        dice.setImageResource(R.drawable.dice4);
                        break;
                    case 5:
                        dice.setImageResource(R.drawable.dice5);
                        break;
                    case 6:
                        dice.setImageResource(R.drawable.dice6);
                        break;
                }
            }

            value++;
            if(value <= 50){
                mHandler.sendEmptyMessage(0);
            }

        }
    };
}


