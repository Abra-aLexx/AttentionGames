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
public class FindTheThingActivity extends AppCompatActivity {
    RadioGroup group;
    RadioButton r0,r1,r2;
    RadioButton[] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_thing);
        group = findViewById(R.id.radioGroup4);
        r0 = findViewById(R.id.radioButton0000);
        r1 = findViewById(R.id.radioButton0001);
        r2 = findViewById(R.id.radioButton0002);
        /*Второй массив был создан для инициализации массива на уровне класса
         * чтобы потом его можно было использовать для пребора кнопок*/
        RadioButton[] buttons1 = {r0,r1,r2};
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
        Intent intent = null;
        // В зависимости от id кнопки запускаем соответствующую активность
        switch (modifiedId){
            case 0:{ intent = new Intent(this,FindTheThingEasyGameActivity.class); break;}
            case 1:{ intent = new Intent(this,FindTheThingNormalActivity.class); break;}
            case 2:{ intent = new Intent(this,FindTheThingHardActivity.class); break;}
        }
        startActivity(intent);
        finish();
    }
}