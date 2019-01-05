package com.deadlock.salary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton minusSalary, minusPart, minusNight, minusExtra,
            plusSalary, plusPart, plusNight, plusExtra;

    TextView salary, part, night, extra, food, bounty, category,
            numSalary, numNight, numPart, numExtra, numFood;

    TextView yearTv, monthTv, finishSumSalary, salaryWord;

    View screen;

    int oneSalary, onePart, oneNight, oneExtra, oneFood,
            nSalary, nPart, nNight, nExtra, nFood,
            sumSalary, sumPart, sumNight, sumExtra, sumFood, sumBounty, sumCategory;

    Calendar calendar = Calendar.getInstance();

    SharedPreferences spMonth, spSet;

    GestureDetector gestureDetector;

    String MONTH = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    int YEAR = calendar.get(Calendar.YEAR);

    final String SAVED_SALARY = "saved_salary";
    final String SAVED_PART = "saved_part";
    final String SAVED_NIGHT = "saved_night";
    final String SAVED_EXTRA = "saved_extra";


    @SuppressLint({"SetTextI18n", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addView();

        yearTv.setText(Integer.toString(YEAR));
        monthTv.setText(MONTH);

        loadResult();

        gestureDetector = initGestureDetector();

        screen.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        screen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

    }

    protected void onResume() {
        loadResult();
        super.onResume();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.minusSalary):
                if (nSalary > 0) {
                    nSalary--;
                    sumSalary -= oneSalary;
                    sumFood -= oneFood;
                    numSalary.setText(Integer.toString(nSalary));
                    reSumCount();
                }
                break;

            case (R.id.plusSalary):
                nSalary++;
                sumSalary += oneSalary;
                sumFood += oneFood;
                numSalary.setText(Integer.toString(nSalary));
                reSumCount();
                break;

            case (R.id.minusPart):
                if (nPart > 0) {
                    nPart--;
                    sumPart -= onePart;
                    sumFood -= oneFood;
                    numPart.setText(Integer.toString(nPart));
                    part.setText(Integer.toString(sumPart));
                    reSumCount();
                }
                break;

            case (R.id.plusPart):
                nPart++;
                sumPart += onePart;
                sumFood += oneFood;
                numPart.setText(Integer.toString(nPart));
                part.setText(Integer.toString(sumPart));
                reSumCount();
                break;

            case (R.id.minusNight):
                if (nNight > 0) {
                    nNight--;
                    sumNight -= oneNight;
                    numNight.setText(Integer.toString(nNight));
                    night.setText(Integer.toString(sumNight));
                    reSumCount();
                }
                break;

            case (R.id.plusNight):
                nNight++;
                sumNight += oneNight;
                numNight.setText(Integer.toString(nNight));
                night.setText(Integer.toString(sumNight));
                reSumCount();
                break;

            case (R.id.minusExtra):
                if (nExtra > 0) {
                    nExtra--;
                    sumExtra -= oneExtra;
                    numExtra.setText(Integer.toString(nExtra));
                    extra.setText(Integer.toString(sumExtra));
                    reSumCount();
                }
                break;

            case (R.id.plusExtra):
                nExtra++;
                sumExtra += oneExtra;
                numExtra.setText(Integer.toString(nExtra));
                extra.setText(Integer.toString(sumExtra));
                reSumCount();
                break;
        }
        saveResult();
    }

    public void addView() {
        screen = (View) findViewById(R.id.screen);

        salary = (TextView) findViewById(R.id.salary);
        part = (TextView) findViewById(R.id.part);
        night = (TextView) findViewById(R.id.night);
        extra = (TextView) findViewById(R.id.extra);
        food = (TextView) findViewById(R.id.food);
        bounty = (TextView) findViewById(R.id.bounty);
        category = (TextView) findViewById(R.id.category);

        yearTv = (TextView) findViewById(R.id.yearTv);
        monthTv = (TextView) findViewById(R.id.month);

        numSalary = (TextView) findViewById(R.id.numSalary);
        numPart = (TextView) findViewById(R.id.numPart);
        numNight = (TextView) findViewById(R.id.numNight);
        numExtra = (TextView) findViewById(R.id.numExtra);
        numFood = (TextView) findViewById(R.id.numFood);

        finishSumSalary = (TextView) findViewById(R.id.finishSumSalary);
        salaryWord = (TextView) findViewById(R.id.salaryWord);

        minusSalary = (ImageButton) findViewById(R.id.minusSalary);
        minusPart = (ImageButton) findViewById(R.id.minusPart);
        minusNight = (ImageButton) findViewById(R.id.minusNight);
        minusExtra = (ImageButton) findViewById(R.id.minusExtra);
        plusSalary = (ImageButton) findViewById(R.id.plusSalary);
        plusPart = (ImageButton) findViewById(R.id.plusPart);
        plusNight = (ImageButton) findViewById(R.id.plusNight);
        plusExtra = (ImageButton) findViewById(R.id.plusExtra);
    }

    public void saveResult() {
        Editor ed = spMonth.edit();
        ed.putString(SAVED_SALARY, numSalary.getText().toString());
        ed.putString(SAVED_PART, numPart.getText().toString());
        ed.putString(SAVED_NIGHT, numNight.getText().toString());
        ed.putString(SAVED_EXTRA, numExtra.getText().toString());
        ed.apply();
    }

    @SuppressLint("SetTextI18n")
    public void loadResult() {
        spMonth = getSharedPreferences(MONTH + YEAR, MODE_PRIVATE);
        nSalary = Integer.parseInt(spMonth.getString(SAVED_SALARY, "15"));
        nPart = Integer.parseInt(spMonth.getString(SAVED_PART, "0"));
        nNight = Integer.parseInt(spMonth.getString(SAVED_NIGHT, "0"));
        nExtra = Integer.parseInt(spMonth.getString(SAVED_EXTRA, "0"));
        nFood = nSalary + nPart;

        spSet = PreferenceManager.getDefaultSharedPreferences(this);
        oneSalary = Integer.parseInt(spSet.getString("reqDaySet", "1100"));
        onePart = Integer.parseInt(spSet.getString("addDaySet", "1200"));
        oneNight = Integer.parseInt(spSet.getString("nightWorkSet", "1400"));
        oneExtra = Integer.parseInt(spSet.getString("extHourSet", "150"));
        oneFood = Integer.parseInt(spSet.getString("onFoodSet", "300"));
        sumBounty = Integer.parseInt(spSet.getString("bountySet", "9500"));
        sumCategory = Integer.parseInt(spSet.getString("categorySet", "4000"));

        sumSalary = nSalary * oneSalary;
        sumPart = nPart * onePart;
        sumNight = nNight * oneNight;
        sumExtra = nExtra * oneExtra;
        sumFood = nFood * oneFood;

        numSalary.setText(Integer.toString(nSalary));
        numPart.setText(Integer.toString(nPart));
        numNight.setText(Integer.toString(nNight));
        numExtra.setText(Integer.toString(nExtra));
        numFood.setText(Integer.toString(nFood));
        bounty.setText(Integer.toString(sumBounty));
        category.setText(Integer.toString(sumCategory));

        salary.setText(Integer.toString(sumSalary));
        part.setText(Integer.toString(sumPart));
        night.setText(Integer.toString(sumNight));
        extra.setText(Integer.toString(sumExtra));
        food.setText(Integer.toString(sumFood));

        finishSumSalary.setText(finishSalary() + " rub");
        salaryWord.setText(NumberToString.WordsRus(finishSalary()));
    }

    @SuppressLint("SetTextI18n")
    public void reSumCount() {
        numFood.setText(Integer.toString((nSalary + nPart)));
        salary.setText(Integer.toString(sumSalary));
        food.setText(Integer.toString(sumFood));
        finishSumSalary.setText(finishSalary() + " rub");
        salaryWord.setText(NumberToString.WordsRus(finishSalary()));
    }

    public int finishSalary() {
        return sumSalary + sumPart + sumNight + sumExtra +
                sumFood + sumBounty + sumCategory;
    }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();

            @SuppressLint("SetTextI18n")
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        return false;
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        showToast("Up Swipe");
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        int a = 0;
                        a--;
                        calendar.add(Calendar.MONTH, a);
                        MONTH = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
                        YEAR = calendar.get(Calendar.YEAR);
                        monthTv.setText(MONTH);
                        yearTv.setText(Integer.toString(YEAR));
                        loadResult();
                    } else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        int a = 0;
                        a++;
                        calendar.add(Calendar.MONTH, a);
                        MONTH = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US);
                        YEAR = calendar.get(Calendar.YEAR);
                        monthTv.setText(MONTH);
                        yearTv.setText(Integer.toString(YEAR));
                        loadResult();
                    }
                } catch (Exception e) {
                    showToast("Error: " + e);
                }
                return false;
            }
        });
    }

    private void showToast(String phrase) {
        Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.num2word):
                Intent intent1 = new Intent(this, Num2word.class);
                this.startActivity(intent1);
                break;
            case (R.id.settings):
                Intent intent2 = new Intent(this, PrefActivity.class);
                this.startActivity(intent2);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
