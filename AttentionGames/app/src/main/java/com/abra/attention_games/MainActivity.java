package com.abra.attention_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/**
 * Стартовый класс, который запускается с самого начала*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
/**
 * Метод обрабатывает нажатие кнопки и переводит на
 * активность с тестом Струпа
 * */
    public void showTestStroup(View view) {
        Intent intent = new Intent(this, TestStroupActivity.class);
        startActivity(intent);
    }
    /**
     * Метод обрабатывает нажатие кнопки и переводит на
     * активность с игрой Сумма Цифр
     * */
    public void showSumOfNumbers(View view) {
        Intent intent = new Intent(this, SumOfNumbersActivity.class);
        startActivity(intent);
    }

    public void showTestShult(View view) {
        Intent intent = new Intent(this, TestShultActivity.class);
        startActivity(intent);
    }
    public void showOnlyOneIsRight(View view) {
        Intent intent = new Intent(this, FindTheThingActivity.class);
        startActivity(intent);
    }
}