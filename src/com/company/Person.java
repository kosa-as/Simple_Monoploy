package com.company;

public class Person {
    int position;
    Dice dice;

    public Person(){
        this.position=0;
        dice = new Dice();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Dice getDice() {
        return dice;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }
    public int nextPosition() {

        return position + dice.getOne();
    }
}
