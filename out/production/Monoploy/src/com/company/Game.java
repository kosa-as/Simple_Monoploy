package com.company;

import java.util.ArrayList;

public class Game {

    private ArrayList<Person> people;
    private ArrayList<Integer> board;

    public Game(int num)
    {
        people = new ArrayList<Person>();

        board = new ArrayList<Integer>();

        for(int i=0; i<num ; ++i)//添加玩家人数
        {
            Person person = new Person();
            people.add(person);
        }
        //构建棋盘
        board.add(0);board.add(3);board.add(-2);board.add(3);board.add(0);
        board.add(-3);board.add(0);board.add(0);board.add(0);board.add(5);
        board.add(0);board.add(0);board.add(-5);board.add(0);board.add(0);
        board.add(0);board.add(-3);board.add(0);board.add(3);board.add(0);
        board.add(0);board.add(0);board.add(0);board.add(0);board.add(0);
        board.add(-4);board.add(-4);board.add(0);board.add(5);board.add(0);
        board.add(3);board.add(0);board.add(0);board.add(0);board.add(0);
    }

    public ArrayList<Integer> getBoard() {
        return board;
    }

    public boolean isWinned (Integer num) {
        if(people.get(num).position == 34)
        {
            return true;
        }
        return false;
    }

    public int judgeBoard(Integer pos)
    {
        Integer ans = 0;
        if(pos>=34){
            ans = 34;
        }else if(pos<=0){
            ans = 0;
        }else{
            while(board.get(pos)!=0) {
                pos += board.get(pos);
                if (pos >= 34) {
                    pos = 34;
                } else if (pos <= 0) {
                    pos = 0;
                }
            }
            ans = pos;
        }
        return ans;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setBoard(ArrayList<Integer> board) {
        this.board = board;
    }

    public Integer playRound(Integer playerNum) {

        Integer newPosition = people.get(playerNum).nextPosition();

        Integer ans = newPosition - people.get(playerNum).position;

        Integer nextPosition = judgeBoard(newPosition);

        people.get(playerNum).setPosition(nextPosition);

        return ans;
    }

}
