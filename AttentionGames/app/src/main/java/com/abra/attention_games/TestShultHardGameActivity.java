package com.abra.attention_games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.Random;
/**Класс, отвечающий за саму игру Тест Шульте на Сложной сложности.
 * Класс работает в два потока, второстепенный поток
 * отвечает за смену цифр на кнопках
 * */
public class TestShultHardGameActivity extends AppCompatActivity {
    Handler handler;
    ConstraintLayout layout;
    Runnable thread;
    Button[] buttons = new Button[25];
    Random rand = new Random();
    boolean lastButtonPressed = false;
    // список хранит значения нажатых кнопок
    LinkedList<Integer> pressedButtons = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_shult_hard_game);
        layout = findViewById(R.id.layoutHard);
        buttons[0] = findViewById(R.id.button29);
        buttons[1] = findViewById(R.id.button30);
        buttons[2] = findViewById(R.id.button31);
        buttons[3] = findViewById(R.id.button32);
        buttons[4] = findViewById(R.id.button33);
        buttons[5] = findViewById(R.id.button34);
        buttons[6] = findViewById(R.id.button35);
        buttons[7] = findViewById(R.id.button36);
        buttons[8] = findViewById(R.id.button37);
        buttons[9] = findViewById(R.id.button38);
        buttons[10] = findViewById(R.id.button39);
        buttons[11] = findViewById(R.id.button40);
        buttons[12] = findViewById(R.id.button41);
        buttons[13] = findViewById(R.id.button42);
        buttons[14] = findViewById(R.id.button43);
        buttons[15] = findViewById(R.id.button44);
        buttons[16] = findViewById(R.id.button45);
        buttons[17] = findViewById(R.id.button46);
        buttons[18] = findViewById(R.id.button47);
        buttons[19] = findViewById(R.id.button48);
        buttons[20] = findViewById(R.id.button49);
        buttons[21] = findViewById(R.id.button50);
        buttons[22] = findViewById(R.id.button51);
        buttons[23] = findViewById(R.id.button52);
        buttons[24] = findViewById(R.id.button53);
        /* добавляем один элемент, чтобы не было пустого списка и
         * не получить исключение*/
        pressedButtons.add(0);
        handler = new Handler();
        /*Второй поток, в котором происходит смена цвета фона после
         * неправильного ответа, а так же если нажата последняя кнопка, то
         * все кнопки отображаются заново и им присваиваюся рандомные числа.*/
        thread = () -> {layout.setBackgroundResource(R.drawable.gradient);
            if (lastButtonPressed) {
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setVisibility(View.VISIBLE);
                }
                setButtonValues();
                lastButtonPressed = false;
            }
        };
        setButtonValues();
    }
    /**
     * Метод отвечает за присвоение чисел кнопкам в рандомном порядке
     * */
    private void setButtonValues(){
        // массив, в который помещаются уже занятые числа
        int[] existNumbers = new int[25];
        // переменная, которая хранит рандомное значение
        int n;
        /*
         * Цикл, в котором осуществляется присвоение рандомных чисел к кнопкам,
         * а так же осуществляется проверка на совпадение с уже занятыми
         * числами.*/
        for (int i = 0; i < buttons.length; i++) {
            // присвоение числа самой первой кнопке
            if(i<1){
                n = rand.nextInt(25)+1;
                buttons[i].setText(""+n);
                existNumbers[i] = n;
                continue;
            }
            // присвоение чисел остальным кнопкам с проверкой на совпадение
            while (true){
                int count = 0;
                n = rand.nextInt(25)+1;
                for (int j = 0; j < existNumbers.length; j++) {
                    if (n == existNumbers[j]){
                        ++count;
                        break;
                    }
                }
                // если есть хоть одно совпадение, то происходит возврат к началу цикла.
                if(count>0){
                    continue;
                }else{
                    existNumbers[i] = n;
                    buttons[i].setText(""+n);
                    break;
                }
            }
        }
    }
    /**
     * Метод обрабатывает нажатия кнопок.
     * */
    public void buttonsClickHard(View view) {
        Button button = (Button)view;
        // получаем цифру кнопки
        String text = button.getText().toString();
        int n = Integer.parseInt(text);
        /* Сравниваем полученное число с предыдущенажатой кнопкой,
         * при помощи спеиальных чисел, содержащихся в списке pressedButtons.
         * Первая кнопка является исключением, поэтому в список было добавлено число 0
         * При неправильном ответе экран загорается красным цветом*/
        if(n-1 == pressedButtons.getLast()){
            // добавляем число в специальный список
            pressedButtons.add(n);
            // делаем кнопку невидимой
            button.setVisibility(View.INVISIBLE);
            /* проверяем является ли кнопка последней, если да,
             * то меняем флаг на true и очищаем список для новой игры*/
            if(n==25){
                lastButtonPressed = true;
                pressedButtons.clear();
                pressedButtons.add(0);
            }
            //запускаем поток через 100 милисекунд
            handler.postDelayed(thread,100);
        }else{
            // меняет цвет на красный
            layout.setBackgroundColor(Color.RED);
            //запускаем поток через 100 милисекунд
            handler.postDelayed(thread,100);
        }
    }
}