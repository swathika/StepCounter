package com.example.swathika.StepCounter;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class Timecounter extends Fragment {




        private static String TAG = "TimerDemo";

        private TextView mTextView = null;
        private Button mButton_start = null;
        private Button mButton_pause = null;

        private Timer mTimer = null;
        private TimerTask mTimerTask = null;

        private Handler mHandler = null;

        private static int second = 0;
        private static int minute=0;
        private static int hour=0;

        private boolean isPause = false;
        private boolean isStop = true;

    private static int period = 1000;  //1s

        private static final int UPDATE_TEXTVIEW = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_timecounter, container, false);
        mTextView = (TextView) v.findViewById(R.id.timeCounter1);
        mButton_start = (Button) v.findViewById(R.id.button_start);
        mButton_pause = (Button) v.findViewById(R.id.button_pause);





            mButton_start.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    if (isStop) {
                        Log.i(TAG, "Start");
                    } else {
                        Log.i(TAG, "Stop");
                    }

                    isStop = !isStop;

                    if (!isStop) {
                        startTimer();
                    } else {
                        stopTimer();
                    }

                    if (isStop) {
                        mButton_start.setText(R.string.start);
                    } else {
                        mButton_start.setText(R.string.stop);
                    }
                }
            });

            mButton_pause.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    if (isPause) {
                        Log.i(TAG, "Resume");
                    } else {
                        Log.i(TAG, "Pause");
                    }

                    isPause = !isPause;

                    if (isPause) {
                        mButton_pause.setText(R.string.resume);
                    } else {
                        mButton_pause.setText(R.string.pause);
                    }
                }
            });


           mHandler= new Handler() {

                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case UPDATE_TEXTVIEW:
                            updateTextView();
                            break;
                        default:
                            break;
                    }
                }
            };
        return v;
    }


        private void updateTextView() {
            mTextView.setText((String.valueOf(hour)+":"+String.valueOf(minute)+":"+String.valueOf(second)));
        }

        private void startTimer() {
            if (mTimer == null) {
                mTimer = new Timer();
            }

            if (mTimerTask == null) {
                mTimerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Log.i(TAG, "count: " + String.valueOf(hour)+":"+String.valueOf(minute)+":"+String.valueOf(second));
                        sendMessage(UPDATE_TEXTVIEW);

                        do {
                            try {
                                Log.i(TAG, "sleep(1000)...");
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                        } while (isPause);

                        second++;
                        if(minute>0)
                        {
                            hour=hour+(minute/60);
                        }
                        if(second>0)
                        {
                            minute=second+(second/60);
                        }
                         second=second%60;

                    }
                };
            }

            int delay = 1000;
            if (mTimer != null && mTimerTask != null)
                mTimer.schedule(mTimerTask, delay, period);

        }

        private void stopTimer() {

            if (mTimer != null) {
                mTimer.cancel();
                mTimer = null;
            }

            if (mTimerTask != null) {
                mTimerTask.cancel();
                mTimerTask = null;
            }

            second = 0;

        }

        public void sendMessage(int id) {
            if (mHandler != null) {
                Message message = Message.obtain(mHandler, id);
                mHandler.sendMessage(message);
            }
        }
    }



