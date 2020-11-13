package com.abra.attention_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
/**
 * Класс отвечает за подготовку к самой игре.
 * Здесь выбирается уровень сложности*/
public class SumOfNumbersActivity extends AppCompatActivity {
    RadioGroup group;
    RadioButton r0,r1,r2,r3,r4;
    RadioButton[] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_of_numbers);
        group = findViewById(R.id.radioGroup1);
        r0 = findViewById(R.id.radioButton00);
        r1 = findViewById(R.id.radioButton01);
        r2 = findViewById(R.id.radioButton02);
        r3 = findViewById(R.id.radioButton03);
        r4 = findViewById(R.id.radioButton04);
        /*Второй массив был создан для инициализации массива на уровне класса
         * чтобы потом его можно было использовать для пребора кнопок*/
        RadioButton[] buttons1 = {r0,r1,r2,r3,r4};
        buttons = buttons1;
    }
    /**
     * Этот метод получает id кнопки и передает её в другую активность с игрой
     * */
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
        // В зависимости от id кнопки запускаем соответствующую активность
        Intent intent = new Intent(this,SumOfNumbersGameActivity.class);
        intent.putExtra("id",modifiedId);
        startActivity(intent);
        finish();
    }
}