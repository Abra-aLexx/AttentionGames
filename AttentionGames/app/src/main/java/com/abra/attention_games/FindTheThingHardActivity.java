package com.abra.attention_games;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

/**Класс, отвечающий за саму игру Найди предмет на Cложной сложности.
 * Класс работает в два потока, второстепенный поток
 * отвечает за смену картинок на кнопках*/
public class FindTheThingHardActivity extends AppCompatActivity {
    // массив хранит все кнопки
    ImageButton[] buttons = new ImageButton[25];
    Handler handler;
    ConstraintLayout layout;
    Runnable thread;
    TextView question;
    Random rand = new Random();
    boolean rightButtonPressed;
    int randomAnswer;
    ImageButton pressedButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_the_thing_hard);
        layout = findViewById(R.id.layoutFTHard);
        buttons[0] = findViewById(R.id.imgButton001);
        buttons[1] = findViewById(R.id.imgButton002);
        buttons[2] = findViewById(R.id.imgButton003);
        buttons[3] = findViewById(R.id.imgButton004);
        buttons[4] = findViewById(R.id.imgButton005);
        buttons[5] = findViewById(R.id.imgButton006);
        buttons[6] = findViewById(R.id.imgButton007);
        buttons[7] = findViewById(R.id.imgButton008);
        buttons[8] = findViewById(R.id.imgButton009);
        buttons[9] = findViewById(R.id.imgButton0010);
        buttons[10] = findViewById(R.id.imgButton0011);
        buttons[11] = findViewById(R.id.imgButton0012);
        buttons[12] = findViewById(R.id.imgButton0013);
        buttons[13] = findViewById(R.id.imgButton0014);
        buttons[14] = findViewById(R.id.imgButton0015);
        buttons[15] = findViewById(R.id.imgButton0016);
        buttons[16] = findViewById(R.id.imgButton0017);
        buttons[17] = findViewById(R.id.imgButton0018);
        buttons[18] = findViewById(R.id.imgButton0019);
        buttons[19] = findViewById(R.id.imgButton0020);
        buttons[20] = findViewById(R.id.imgButton0021);
        buttons[21] = findViewById(R.id.imgButton0022);
        buttons[22] = findViewById(R.id.imgButton0023);
        buttons[23] = findViewById(R.id.imgButton0024);
        buttons[24] = findViewById(R.id.imgButton0025);
        question = findViewById(R.id.question002);
        setButtonValues();
        handler = new Handler();
        /*Второй поток, в котором происходит смена цвета фона после
         * неправильного ответа, а так же если нажата правильная кнопка, то
         * меняются картинки на всех кнопках.*/
        thread = () -> {layout.setBackgroundResource(R.drawable.gradient);
            if (rightButtonPressed) {
                pressedButton.setVisibility(View.VISIBLE);
                // сбрасываем значение нажатой кнопки
                pressedButton=null;
                // устанавливаем картинки новые картинки заново
                setButtonValues();
                rightButtonPressed = false;
            }
        };
    }
    /**
     * Метод устанавливает рандомные картинки на кнопки
     * без совпадений и вызывает метод для генерации рандомного ответа.
     * */
    private void setButtonValues(){
        // массив, в который помещаются id уже занятых картинок
        int[] existImages = new int[25];
        // переменная, которая хранит рандомное значение
        int n;
        /*
         * Цикл, в котором осуществляется присвоение рандомных картинок к кнопкам,
         * а так же осуществляется проверка на совпадение с уже занятыми
         * картинками.*/
        for (int i = 0; i < buttons.length; i++) {
            // присвоение картинки самой первой кнопке
            if(i<1){
                n = rand.nextInt(25)+1;
                // устанавливается картинка на кнопке
                buttons[i].setImageResource(getImageId(n));
                /* прикрепляем id картинки, для проверки в будующем.
                 *  Не нашел метода, который возвращает id картинки*/
                buttons[i].setTag(getImageId(n));
                // добавляем кнопку в массив, во избежание повторов кнопок.
                existImages[i] = getImageId(n);
                continue;
            }
            // присвоение картинок остальным кнопкам с проверкой на совпадение
            while (true){
                int count = 0;
                n = rand.nextInt(25)+1;
                for (int j = 0; j < existImages.length; j++) {
                    if (getImageId(n) == existImages[j]){
                        ++count;
                        break;
                    }
                }
                // если есть хоть одно совпадение, то происходит возврат к началу цикла.
                if(count>0){
                    continue;
                }else{
                    existImages[i] = getImageId(n);
                    buttons[i].setTag(getImageId(n));
                    buttons[i].setImageResource(existImages[i]);
                    break;
                }
            }
        }
        //вызываем метод для генирации случайного ответа
        generateRandomAnswer(existImages);
    }
    /**
     * Метод устанавливает случайную картинку из уже занятых
     * в переменную, которая хранит правильный ответ и отображает
     * эту картинку в вопросе.*/
    private void generateRandomAnswer(int ... existImages){
        randomAnswer = existImages[rand.nextInt(25)];
        question.setBackgroundResource(randomAnswer);

    }
    /**
     * Метод возвращает id картинок, опираясь на сгенерированное число,
     * так как картинки условно пронумерованы от 1 до 25*/
    private int getImageId(int number){
        switch (number){
            case 1:return R.drawable.bomb;
            case 2:return R.drawable.brush;
            case 3:return R.drawable.camera;
            case 4:return R.drawable.cepi;
            case 5:return R.drawable.chainik;
            case 6:return R.drawable.chair;
            case 7:return R.drawable.cherep;
            case 8:return R.drawable.heart;
            case 9:return R.drawable.home;
            case 10:return R.drawable.krone;
            case 11:return R.drawable.letter;
            case 12:return R.drawable.lock;
            case 13:return R.drawable.magnit;
            case 14:return R.drawable.naushniki;
            case 15:return R.drawable.nout;
            case 16:return R.drawable.pen;
            case 17:return R.drawable.phone;
            case 18:return R.drawable.player;
            case 19:return R.drawable.rukzak;
            case 20:return R.drawable.settings;
            case 21:return R.drawable.shprits;
            case 22:return R.drawable.smile;
            case 23:return R.drawable.tv;
            case 24:return R.drawable.waste;
            case 25:return R.drawable.watch;
            default: return -1;
        }
    }
    /**
     * Метод обрабатывает нажатия кнопок.
     * */
    public void onClickImageButtons(View view) {
        // получаем нажатую кнопку для дальнейшей работы с ней.
        ImageButton button = (ImageButton)view;
        // получаем id картинки на кнопке
        int n = (Integer) button.getTag();
        // сравниваем id картинки с правильным ответом
        if(n == randomAnswer){
            /* передаём ссылку на текущую кнопку, чтобы потом
             * вернуть ей видимость*/
            pressedButton = button;
            // меняем флаг на true(т.е. кнопка нажата)
            rightButtonPressed = true;
            // меняем цвет заднего фона на зелёный
            layout.setBackgroundColor(Color.GREEN);
            // делаем кнопку невидимой
            button.setVisibility(View.INVISIBLE);
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