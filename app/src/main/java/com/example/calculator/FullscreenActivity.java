package com.example.calculator;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Math.abs;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;

    private Button mNum0,mNum1,mNum2,mNum3
            ,mNum4,mNum5,mNum6
            ,mNum7,mNum8,mNum9;

    private Button mAdd,mSub,mMul,mDiv,mExec;

    private Button mCancel,mNeg,mComma;

    private TextView mCmdText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        mNum0=(Button) findViewById(R.id.button2);
        mNum1=(Button) findViewById(R.id.button4);
        mNum2=(Button) findViewById(R.id.button5);
        mNum3=(Button) findViewById(R.id.button6);

        mNum4=(Button) findViewById(R.id.button7);
        mNum5=(Button) findViewById(R.id.button8);
        mNum6=(Button) findViewById(R.id.button9);

        mNum7=(Button) findViewById(R.id.button10);
        mNum8=(Button) findViewById(R.id.button11);
        mNum9=(Button) findViewById(R.id.button12);

        mAdd=(Button) findViewById(R.id.buttonadd);
        mSub=(Button) findViewById(R.id.buttonsub);
        mMul=(Button) findViewById(R.id.buttonmul);
        mDiv=(Button) findViewById(R.id.buttondiv);
        mExec=(Button) findViewById(R.id.buttoneql);

        mCancel=(Button) findViewById(R.id.buttoncanc);
        mNeg=(Button) findViewById(R.id.button3);
        mComma=(Button) findViewById(R.id.button);

        mCmdText=(TextView) findViewById(R.id.textView);
        mCmdText.setText("0");

        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }
}
