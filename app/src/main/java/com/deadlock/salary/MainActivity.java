package com.deadlock.salary;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton minusSalary, minusPart, minusNight, minusExtra,
            plusSalary, plusPart, plusNight, plusExtra;

    TextView salary, part, night, extra, food, bounty, category,
            numSalary, numNight, numPart, numExtra, numFood;

    TextView month, finishSumSalary;

    Calendar calendar = Calendar.getInstance();

    SharedPreferences sp;

    int oneSalary, onePart, oneNight, oneExtra, oneFood,
            nSalary, nPart, nNight, nExtra, nFood,
            sumSalary, sumPart, sumNight, sumExtra, sumFood, sumBounty, sumCategory;

    final String MONTH = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
    final String SAVED_SALARY = "saved_salary";
    final String SAVED_PART = "saved_part";
    final String SAVED_NIGHT = "saved_night";
    final String SAVED_EXTRA = "saved_extra";


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        salary = (TextView) findViewById(R.id.salary);
        part = (TextView) findViewById(R.id.part);
        night = (TextView) findViewById(R.id.night);
        extra = (TextView) findViewById(R.id.extra);
        food = (TextView) findViewById(R.id.food);
        bounty = (TextView) findViewById(R.id.bounty);
        category = (TextView) findViewById(R.id.category);

        numSalary = (TextView) findViewById(R.id.numSalary);
        numPart = (TextView) findViewById(R.id.numPart);
        numNight = (TextView) findViewById(R.id.numNight);
        numExtra = (TextView) findViewById(R.id.numExtra);
        numFood = (TextView) findViewById(R.id.numFood);

        month = (TextView) findViewById(R.id.month);
        finishSumSalary = (TextView) findViewById(R.id.finishSumSalary);

        minusSalary = (ImageButton) findViewById(R.id.minusSalary);
        minusPart = (ImageButton) findViewById(R.id.minusPart);
        minusNight = (ImageButton) findViewById(R.id.minusNight);
        minusExtra = (ImageButton) findViewById(R.id.minusExtra);
        plusSalary = (ImageButton) findViewById(R.id.plusSalary);
        plusPart = (ImageButton) findViewById(R.id.plusPart);
        plusNight = (ImageButton) findViewById(R.id.plusNight);
        plusExtra = (ImageButton) findViewById(R.id.plusExtra);

        loadResult();

        oneSalary = 1100;
        onePart = 1200;
        oneNight = 1400;
        oneExtra = 150;
        oneFood = 300;

        sumSalary = nSalary * oneSalary;
        sumPart = nPart * onePart;
        sumNight = nNight * oneNight;
        sumExtra = nExtra * oneExtra;
        sumFood = nFood * oneFood;
        sumBounty = 9500;
        sumCategory = 4000;

        month.setText(MONTH);
        finishSumSalary.setText(finishSalary());

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
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.minusSalary):
                nSalary -= 1;
                sumSalary -= oneSalary;
                sumFood -= oneFood;
                numSalary.setText(Integer.toString(nSalary));
                numFood.setText(Integer.toString((nSalary + nPart)));
                salary.setText(Integer.toString(sumSalary));
                food.setText(Integer.toString(sumFood));
                finishSumSalary.setText(finishSalary());
                break;
            case (R.id.plusSalary):
                nSalary += 1;
                sumSalary += oneSalary;
                sumFood += oneFood;
                numSalary.setText(Integer.toString(nSalary));
                numFood.setText(Integer.toString((nSalary + nPart)));
                salary.setText(Integer.toString(sumSalary));
                food.setText(Integer.toString(sumFood));
                finishSumSalary.setText(finishSalary());
                break;
            case (R.id.minusPart):
                nPart -= 1;
                sumPart -= onePart;
                sumFood -= oneFood;
                numPart.setText(Integer.toString(nPart));
                numFood.setText(Integer.toString((nSalary + nPart)));
                part.setText(Integer.toString(sumPart));
                food.setText(Integer.toString(sumFood));
                finishSumSalary.setText(finishSalary());
                break;
            case (R.id.plusPart):
                nPart += 1;
                sumPart += onePart;
                sumFood += oneFood;
                numPart.setText(Integer.toString(nPart));
                numFood.setText(Integer.toString((nSalary + nPart)));
                part.setText(Integer.toString(sumPart));
                food.setText(Integer.toString(sumFood));
                finishSumSalary.setText(finishSalary());
                break;
            case (R.id.minusNight):
                nNight -= 1;
                sumNight -= oneNight;
                numNight.setText(Integer.toString(nNight));
                night.setText(Integer.toString(sumNight));
                finishSumSalary.setText(finishSalary());
                break;
            case (R.id.plusNight):
                nNight += 1;
                sumNight += oneNight;
                numNight.setText(Integer.toString(nNight));
                night.setText(Integer.toString(sumNight));
                finishSumSalary.setText(finishSalary());
                break;
            case (R.id.minusExtra):
                nExtra -= 1;
                sumExtra -= oneExtra;
                numExtra.setText(Integer.toString(nExtra));
                extra.setText(Integer.toString(sumExtra));
                finishSumSalary.setText(finishSalary());
                break;
            case (R.id.plusExtra):
                nExtra += 1;
                sumExtra += oneExtra;
                numExtra.setText(Integer.toString(nExtra));
                extra.setText(Integer.toString(sumExtra));
                finishSumSalary.setText(finishSalary());
                break;
        }
        saveResult();
    }

    public void saveResult() {
        sp = getSharedPreferences(MONTH, MODE_PRIVATE);
        Editor ed = sp.edit();
        ed.putString(SAVED_SALARY, numSalary.getText().toString());
        ed.putString(SAVED_PART, numPart.getText().toString());
        ed.putString(SAVED_NIGHT, numNight.getText().toString());
        ed.putString(SAVED_EXTRA, numExtra.getText().toString());
        ed.commit();
    }

    public void loadResult() {
        sp = getSharedPreferences(MONTH, MODE_PRIVATE);
        nSalary = Integer.parseInt(sp.getString(SAVED_SALARY, "15"));
        nPart = Integer.parseInt(sp.getString(SAVED_PART, "0"));
        nNight = Integer.parseInt(sp.getString(SAVED_NIGHT, "0"));
        nExtra = Integer.parseInt(sp.getString(SAVED_EXTRA, "0"));
        nFood = nSalary + nPart;
    }

    public String finishSalary() {
        return Integer.toString(sumSalary + sumPart + sumNight + sumExtra +
                sumFood + sumBounty + sumCategory )  + " rub";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveResult();
    }

    /*  public void checkZero(int number, Button button) {
        if (number == 0) {
            button.setVisibility(View.INVISIBLE);
        } else {
            button.setVisibility(View.VISIBLE);
        }
    }*/
}
