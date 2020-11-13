package com.abra.attention_games;
/**
 * @see Colors
 *Класс является перечислением и содержит цвета
 * и их ID.
 * В конструкторе инициализируется переменная id*/
public enum Colors {
    RED(0),ORANGE(1),YELLOW(2),GREEN(3),BLUE(4),BROWN(5),VIOLET(6),PINK(7),SALAD(8),BIRUZ(9),GRAY(10),BLACK(11);
    int id;
    Colors(int id){this.id = id;}
}
