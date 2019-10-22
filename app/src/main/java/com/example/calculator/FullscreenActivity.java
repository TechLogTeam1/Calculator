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

    private Button mAdd,mSub,mMul,mDiv,mExec,mRoot;
    private Button mAdd,mSub,mMul,mDiv,mExec,mPow;

    private Button mCancel,mNeg,mComma;

    private TextView mCmdText;
    private String CmdLine,CmdLineTmp;
    private int CmdNum;
    private int CalcType,CalcType2;
    private boolean CalcExec,ExecRunned,ExecRunned2;
    private double Exp1,Exp2,ExpTotal;
    private boolean DigitPress,CalcPress,CalcPress2;
    private boolean FirstCalc,NewCalc,CancelPress;
    private int CalcPressTimes=0;
    private boolean NumEdited=false,NegPress=false,NegPress2=false;
    private boolean CommaPressed=false,CommaExists=false;
    private boolean minus,SqrtRun;


    private int i;

    private final View.OnClickListener calcRun = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            CmdNum = -1; //Null
            //CalcType=0;
            DigitPress = false;
            CalcPress = false;
            CancelPress = false;
            SqrtRun=false;

            //CalcType2=CalcType;
            switch (view.getId()) {
                case R.id.button2:
                    CmdNum = 0;
                    break;

                case R.id.button4:
                    CmdNum = 1;
                    break;
                case R.id.button5:
                    CmdNum = 2;
                    break;
                case R.id.button6:
                    CmdNum = 3;
                    break;

                case R.id.button7:
                    CmdNum = 4;
                    break;
                case R.id.button8:
                    CmdNum = 5;
                    break;
                case R.id.button9:
                    CmdNum = 6;
                    break;

                case R.id.button10:
                    CmdNum = 7;
                    break;
                case R.id.button11:
                    CmdNum = 8;
                    break;
                case R.id.button12:
                    CmdNum = 9;
                    break;

                case R.id.button:
                    CommaPressed=true;
                    DigitPress=true;
                    break;

                case R.id.buttoneql:
                    CalcExec = true;
                    break; //Exec

                case R.id.buttonadd:
                    CalcType2 = CalcType;
                    CalcType = 1;
                    CalcPress = true;
                    FirstCalc = false;
                    break; //Add
                case R.id.buttonsub:
                    CalcType2 = CalcType;
                    CalcType = 2;
                    CalcPress = true;
                    FirstCalc = false;
                    break; //Sub
                case R.id.buttonmul:
                    CalcType2 = CalcType;
                    CalcType = 3;
                    CalcPress = true;
                    FirstCalc = false;
                    break; //Multi
                case R.id.buttondiv:
                    CalcType2 = CalcType;
                    CalcType = 4;
                    CalcPress = true;
                    FirstCalc = false;
                    break; //Div
                case R.id.buttoncanc:
                    CancelPress = true;
                    break;
                case R.id.button3:
                    NegPress = true;
                    break;
                case R.id.buttonroot:
                   /*
                    CalcType2 = CalcType;
                    CalcType = 6;
                    CalcPress = true;
                    FirstCalc = false;
                    */
                    SqrtRun=true;
                    break; //Square Root

                case R.id.buttonPow:
                    CalcType2 = CalcType;
                    CalcType = 5;
                    CalcPress = true;
                    FirstCalc = false;
                    break; //Pow

            }

            if (CancelPress) {
                ExpTotal = 0;
                Exp1 = 0;
                Exp2 = 0;
                CmdLine = "0";
                NewCalc = false;
                CancelPress = false;
                CalcPress = false;
                CalcPress2 = false;
                NumEdited = false;
                DigitPress = false;
                NewCalc = false;
                ExecRunned = false;
                ExecRunned2 = false;
                CommaPressed=false;
                CmdNum = -1;
            }

            if ((CmdNum >= 0) && (CmdNum <= 9)) DigitPress = true;

            if (!NegPress)
                if (ExecRunned) {
                    ExecRunned = false;
                    CmdLine = "";
                }
            if (DigitPress) if (CmdLine == "N") CmdLine = "";

            if (!NegPress)
                if ((DigitPress) && (NewCalc)) {
                    CmdLine = "";
                    NewCalc = false;
                }

            if (CalcPress) NewCalc = true;

            if (DigitPress) NumEdited = true;

            if (CommaPressed)
            {
                CommaExists=false;

                if (CmdLine.length()>1)
                    for (i=0;i<=CmdLine.length()-1;i++) if (CmdLine.charAt(i)=='.') CommaExists=true;

                if ((CmdLine=="") || (CmdLine=="N")) CmdLine="0";
                if (!CommaExists) CmdLine+=".";
                CommaPressed=false;
            }
            if (NegPress)
            {

                if ((CmdLine!="0") && (CmdLine!=""))
                    if (CmdLine.charAt(0)!='-') CmdLine="-"+CmdLine;
                    else
                        //if (CmdLine.charAt(0)=='-') CmdLine=CmdLine.substring(1,CmdLine.length());

                        if (CmdLine.charAt(0)=='-')
                        {
                            CmdLine=CmdLine.substring(1,CmdLine.length());


                            if (FirstCalc)
                                Exp1 = -Exp1;


                            if (!FirstCalc)
                                Exp2 = -Exp2;


                        }
                NegPress2 = true;

                NegPress = false;



            }


            if (CalcPress2)
                if (CalcPress)
                    if (NumEdited) {


                        if (CalcType2 == 1) ExpTotal = Exp1 + Exp2;
                        if (CalcType2 == 2) ExpTotal = Exp1 - Exp2;
                        if (CalcType2 == 3) ExpTotal = Exp1 * Exp2;
                        if (CalcType2 == 4) ExpTotal = Exp1 / Exp2;
                        if (CalcType2 == 6) ExpTotal = Math.sqrt(Exp1);

                        //if (CmdLine.charAt(0)=='-') ExpTotal=-ExpTotal;

                        CmdLine = String.valueOf(ExpTotal);
                        mCmdText.setText(CmdLine);

                        Exp1 = ExpTotal; //NEW
                        //CalcPress=false;
                        CalcPress2 = false;
                        NumEdited = false;
                    }

            if (CalcPress) CalcPress2 = true;

            if (CmdNum == 0) {
                if ((CmdLine != "") && (CmdLine != "0")) CmdLine += "0";
                else CmdLine = "0";
            }

            minus=false;

            if (CmdLine!="")
                if (CmdLine.charAt(0)=='-')
                    minus=true;

            if (!minus)
                if ((DigitPress) && (CmdNum != 0)) if (CmdLine == "0") CmdLine = "";

            if (CmdNum == 1) CmdLine += "1";
            if (CmdNum == 2) CmdLine += "2";
            if (CmdNum == 3) CmdLine += "3";
            if (CmdNum == 4) CmdLine += "4";
            if (CmdNum == 5) CmdLine += "5";
            if (CmdNum == 6) CmdLine += "6";
            if (CmdNum == 7) CmdLine += "7";
            if (CmdNum == 8) CmdLine += "8";
            if (CmdNum == 9) CmdLine += "9";

            mCmdText.setText(CmdLine);

            if (DigitPress) {
                if (FirstCalc) Exp1 = Double.valueOf(CmdLine);
                else Exp2 = Double.valueOf(CmdLine);
            }


            if (SqrtRun)
            //if (CmdLine!="")
            {


                //PREV
                // if (FirstCalc) Exp1 = Math.sqrt(Double.valueOf(CmdLine));
                //else Exp2=Math.sqrt(Double.valueOf(CmdLine));

                if (FirstCalc) Exp1 = Math.sqrt(Double.valueOf(Exp1));
                else Exp2=Math.sqrt(Double.valueOf(Exp2));


                //CmdLine = String.valueOf(ExpTotal);
                //Exp1=Math.sqrt(Exp1);
                if (FirstCalc)
                    mCmdText.setText(String.valueOf(Exp1));
                else
                    mCmdText.setText(String.valueOf(Exp2));
                //SqrtRun=false;
                //CalcType=0;
            }

            if (CalcExec)
            {

                if (NegPress2)
                {
                    if (FirstCalc)
                        Exp1 = -Exp1;


                    if (!FirstCalc)
                        Exp2 = -Exp2;
                }

                if (CalcType == 1) ExpTotal = Exp1 + Exp2;
                if (CalcType == 2) ExpTotal = Exp1 - Exp2;
                if (CalcType == 3) ExpTotal = Exp1 * Exp2;
                if (CalcType == 4) ExpTotal = Exp1 / Exp2;
                //if (SqrtRun) ExpTotal = Math.sqrt(Exp1);

                if (NegPress2) {ExpTotal=-ExpTotal;NegPress2=false;}

                if (CalcType == 5) ExpTotal = Math.pow(Exp1,Exp2);
                CmdLine = String.valueOf(ExpTotal);
                mCmdText.setText(CmdLine);
                CalcExec = false;
                ExecRunned = true;
                ExecRunned2 = true;
                FirstCalc = true;
                CalcType2 = CalcType;
                CalcPress2 = false;
                CalcType = 0;
            }

            if (ExecRunned2)
                if (CalcPress) {
                    Exp1 = ExpTotal;
                    if (NegPress2)
                    {
                        //if (CmdLine!="")
                        //if (CmdLine.charAt(0)=='-')
                        if (FirstCalc) Exp1=-Exp1; //CHECK !
                        NegPress2=false;
                    }
                    FirstCalc = false;
                    //mNum0.setText("A");
                    ExecRunned2 = false;
                }



            return;
        }
    };

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
        mRoot=(Button) findViewById(R.id.buttonroot);
        mExec=(Button) findViewById(R.id.buttoneql);
        mPow=(Button) findViewById(R.id.buttonPow);
        mCancel=(Button) findViewById(R.id.buttoncanc);
        mNeg=(Button) findViewById(R.id.button3);
        mComma=(Button) findViewById(R.id.button);

        mCmdText=(TextView) findViewById(R.id.textView);

        mNum0.setOnClickListener(calcRun);
        mNum1.setOnClickListener(calcRun);
        mNum2.setOnClickListener(calcRun);
        mNum3.setOnClickListener(calcRun);
        mNum4.setOnClickListener(calcRun);
        mNum5.setOnClickListener(calcRun);
        mNum6.setOnClickListener(calcRun);
        mNum7.setOnClickListener(calcRun);
        mNum8.setOnClickListener(calcRun);
        mNum9.setOnClickListener(calcRun);

        mAdd.setOnClickListener(calcRun);
        mSub.setOnClickListener(calcRun);
        mMul.setOnClickListener(calcRun);
        mDiv.setOnClickListener(calcRun);
        mRoot.setOnClickListener(calcRun);
        mPow.setOnClickListener(calcRun);

        mExec.setOnClickListener(calcRun);
        mCancel.setOnClickListener(calcRun);
        mNeg.setOnClickListener(calcRun);
        mComma.setOnClickListener(calcRun);


        CmdLine="N"; //Null
        CalcType=0;
        CalcExec=false;
        FirstCalc=true;

        mCmdText.setText("0");

        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }
}
