package com.abra.attention_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
/**Класс отвечающий за саму игру Сумма Чисел.
 * Этот класс работает в два потока главный и второстепенный,
 * отвечающий за появление, генерацию и смену чисел.*/
public class SumOfNumbersGameActivity extends AppCompatActivity {
    private Handler handler;
    private Runnable thread;
    EditText editText;
    private Random rand = new Random();
    StringBuilder equalCases = new StringBuilder();
    TextView text, question;
    private int id;
    private int difficulty;
    int[] numbers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sum_of_numbers_game);
        text = findViewById(R.id.tvNumbers);
        question = findViewById(R.id.tvQuestion);
        editText = findViewById(R.id.editTextPhone);
        //Получаем id кнопки из предыдущей активности
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        difficulty = setDifficulty(id);
        handler = new Handler();
        /*
        * Создаётся новый поток, отвечающий за
        * за появление, генерацию и смену чисел.*/
        thread = ()->{
            // устанавливаем кол-во цифр, которые будут отображаться на экране
            int numberOfElements = difficulty*20;
            StringBuilder numbersText = new StringBuilder();
            int sum = 0;
            numbers = new int[3];
            // Генерируем три рандомных числа и находим их сумму
            for (int i = 0; i < 3; i++) {
                numbers[i] = rand.nextInt(9)+1;
                sum+=numbers[i];
            }
            int[] allNumbers = new int[numberOfElements];
            question.setText("Найдите три числа, которые стоят подряд и в сумме дают "+sum);
            /* инициализируем массив длинной в кол-во цифр, установленных сложностью,
            * а так же проверяем на совпадение по сумме чисел и пытаемся уменьшить кол-во
            * совпадений
            * */
            for (int i = 0; i < numberOfElements; i++) {
                if(i<2){
                    allNumbers[i] = rand.nextInt(9)+1;
                }
                if(i>=2){
                    while (true){
                        int checkSum;
                        allNumbers[i] = rand.nextInt(9)+1;
                        checkSum = allNumbers[i]+allNumbers[i-1]+allNumbers[i-2];
                        if(checkSum==sum) continue;
                        break;
                    }
                }
            }
            /*
            * Получаем рандомное число в диапозоне кол-ва чисел,
            * для вставки искомой тройки чисел
            * */
            int n = rand.nextInt(numberOfElements-3);
            // меняем рандомные числа на готовую тройку
            for (int i = 0; i < 3; i++) {
                Array.setInt(allNumbers, n++, numbers[i]);
            }
            /* присоединяем все числа к объукту StringBuilder
            * для дальнейшего вывода на экран*/
            for (int i = 0; i < numberOfElements; i++) {
                numbersText.append(allNumbers[i]);
            }
            StringBuilder equalCases1 = new StringBuilder();
            equalCases = equalCases1;
            /*Проверяем на совпадения суммы рандомных чисел с заготовленной суммой
            * и присоединяем совпадения к объекту StringBuilder*/
            for (int i = 0; i < allNumbers.length; i++) {
                if(i>=2){
                    int sumNumb = allNumbers[i]+allNumbers[i-1]+allNumbers[i-2];
                    if(sumNumb == sum){
                        equalCases.append(""+allNumbers[i-2]+allNumbers[i-1]+allNumbers[i]+" ");
                    }
                }
            }
            text.setText(numbersText);
        };
        // первый запуск потока через 100 милисекунд
        handler.postDelayed(thread,100);
    }
    /**
     * Метод при помощий id кнопки устанавливает выбранную сложность
     * */
    private int setDifficulty(int id){
        switch (id){
            case 0:{return 1;}
            case 1:{return 2;}
            case 2:{return 3;}
            case 3:{return 4;}
            case 4:{return 5;}
            default:{return 1;}
        }
    }
    /**
     * Метод отвечает за возврат в главное меню
     * */
    public void backToMainMenu(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    /**
     * Метод отвечает за проверку ответов на правильность.
     * Ответ пользователя сравнивается с возможными вариантами ответов.
     * */
    public void checkAnswer(View view) {
        if(!editText.getText().toString().equals("")){
            // получаем текст, если он не равен нулю.
            String text = editText.getText().toString();
            int[] userNumbers = new int[3];
            // создаем массив совпадений по сумме.
            String[] equalNumb = equalCases.toString().split(" ");
            // получаем все три числа из текста
            int a = Integer.parseInt(text);
              userNumbers[0] = a/100;
              userNumbers[1]= (a-userNumbers[0]*100)/10;
              userNumbers[2]= (a-userNumbers[0]*100)-(userNumbers[1]*10);
              String eq = ""+userNumbers[0]+userNumbers[1]+userNumbers[2];
              //здесь сравнивается ответ с возможными вариантами
            for (int i = 0; i < equalNumb.length; i++) {
                if(equalNumb[i].equals(eq)){
                    Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
                    // поток запускается повторно
                    handler.post(thread);
                    break;
                }
                if(i==equalNumb.length-1) {
                    Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
                    // поток запускается повторно
                    handler.post(thread);
                    break;
                }
            }
            editText.setText("");
        }else{
            Toast.makeText(this, "Введите три числа!", Toast.LENGTH_SHORT).show();
        }
    }
}