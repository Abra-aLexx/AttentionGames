package com.abra.attention_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
/**
 * Класс отвечает за подготовку к самой игре.
 * Здесь выбирается уровень сложности*/
public class TestStroupActivity extends AppCompatActivity {
    RadioGroup group;
    RadioButton r0,r1,r2,r3,r4,r5,r6,r7;
    RadioButton[] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_stroup);
        group = findViewById(R.id.radioGroup);
        r0 = findViewById(R.id.radioButton0);
        r1 = findViewById(R.id.radioButton1);
        r2 = findViewById(R.id.radioButton2);
        r3 = findViewById(R.id.radioButton3);
        r4 = findViewById(R.id.radioButton4);
        r5 = findViewById(R.id.radioButton5);
        r6 = findViewById(R.id.radioButton6);
        r7 = findViewById(R.id.radioButton7);
        /*Второй массив был создан для инициализации массива на уровне класса
        * чтобы потом его можно было использовать для пребора кнопок*/
        RadioButton[] buttons1 = {r0,r1,r2,r3,r4,r5,r6,r7};
        buttons = buttons1;
    }
    /**
     * Этот метод получает id кнопки и передает её в другую активность с игрой*/
    public void startGame(View view) {
      int id = group.getCheckedRadioButtonId();
      int modifiedId = 0;
      /*В этом цикле происходит перебор
      * id всех кнопок и находится верное значение
      * Значение id модифицируется для удобства,
      * так как метод getId() возвращает большое число */
        for (int i = 0; i < buttons.length; i++) {
            if(id == buttons[i].getId()){
                modifiedId = i;
                break;
            }
        }
      Intent intent = new Intent(this,TStroupGameActivity.class);
      intent.putExtra("id",modifiedId);
      startActivity(intent);
      finish();
    }
}