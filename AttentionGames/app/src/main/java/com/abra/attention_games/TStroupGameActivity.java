package com.abra.attention_games;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
/**
 * Непосредственно класс, который отвечает
 * за саму игру.
 * Он отображает слова при помощи отдельного потока*/
public class TStroupGameActivity extends AppCompatActivity {
    //Массив используемых цветов
   private final String[] words = {"красный","оранжевый","желтый","зеленый","синий","коричневый","фиолетовый","розовый","салатовый","серый","черный","бирюзовый"};
    private Handler handler;
    private Runnable thread;
    TextView text;
    private Random rand = new Random();
    private int id;
    private double speed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_stroup_game);
        text = findViewById(R.id.tvWords);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        speed = setSpeed(id);
        handler = new Handler();
        /*
        * Здесь создается новый поток, отвечающий за отображение
        * и смену слов. Реализовон он при помощи лямбда выражения */
        thread = ()->{
            // здесь при помощи рандома выбирается слово, обозначающее цвет из массива
            int randWordId = rand.nextInt(12);
            String word = words[randWordId];
            text.setText(word);
            Colors color = Colors.RED;
            /*
            * здесь при помощи рандома выбирается сам цвет при помощи
            * конструкции множественного выбора.
            * Так же здесь выполняется проверка на равенство слова и цвета(они не должны совпадать)*/
            while (true){
                int randColor = rand.nextInt(12);
                switch (randColor){
                    case 0:{color = Colors.RED; text.setTextColor(0xFFFF0000);break;}
                    case 1:{color = Colors.ORANGE;text.setTextColor(0xFFFF6F00);break;}
                    case 2:{color = Colors.YELLOW;text.setTextColor(0xFFFFFF00);break;}
                    case 3:{color = Colors.GREEN;text.setTextColor(0xFF61970D);break;}
                    case 4:{color = Colors.BLUE;text.setTextColor(0xFF0027FF);break;}
                    case 5:{color = Colors.BROWN;text.setTextColor(0xFF714900);break;}
                    case 6:{color = Colors.VIOLET;text.setTextColor(0xFFAA00FF);break;}
                    case 7:{color = Colors.PINK;text.setTextColor(0xFFFF4081);break;}
                    case 8:{color = Colors.SALAD;text.setTextColor(0xFFB2FF59);break;}
                    case 11:{color = Colors.BIRUZ;text.setTextColor(0xFF64FFDA);break;}
                    case 9:{color = Colors.GRAY;text.setTextColor(0xFF807C75);break;}
                    case 10:{color = Colors.BLACK;text.setTextColor(Color.BLACK);break;}
                }
                if(color.id == randWordId){
                    continue;
                }
                break;
            }
            /*После завершения потока он вызывается циклически через
            * время, которое выбиралось в предыдущей активности*/
            handler.postDelayed(thread, (long)(speed*1000));
        };
        //Строка отвечает за вызов этого потока из главного
        handler.postDelayed(thread, 1000);
        }
/**
 * В этом методе устанавливается частота смены слов
 * в зависимости от id кнопки из предыдущей активности*/
    private double setSpeed(int id){
        switch (id){
            case 0:{return 2;}
            case 1:{return 1.5;}
            case 2:{return 1;}
            case 3:{return 0.75;}
            case 4:{return 0.5;}
            case 5:{return 0.4;}
            case 6:{return 0.35;}
            case 7:{return 0.275;}
            default:{return 2;}
        }
    }
/** Метод обрабатывает нажатие кнопки для возврата в MainActivity*/
    public void backToMainMenu(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}