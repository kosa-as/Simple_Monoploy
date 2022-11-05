package com.company;

import java.util.Random;

public class Dice{
    int point;

    public Dice(){
        this.point=1;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    public int getOne()
    {
        Random random= new Random();
        return random.nextInt(5)+1;
    }
}
