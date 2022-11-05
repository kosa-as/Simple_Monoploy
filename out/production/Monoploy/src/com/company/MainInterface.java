package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MainInterface extends JFrame {

    Font font_default=new Font("微软雅黑", Font.PLAIN,35);
    Font font_game=new Font("微软雅黑",Font.PLAIN,25);

    URL icon_url;
    URL bg_url;
    URL square_url;
    URL day_url;
    URL cloudy_url;
    URL rain_url;
    URL night_url;

    ImageIcon icon_icon;
    ImageIcon bg_icon;
    ImageIcon square_icon;
    ImageIcon day_icon;
    ImageIcon night_icon;
    ImageIcon cloudy_icon;
    ImageIcon rain_icon;

    Integer num;
    Game game;
    Dimension window;

    private GamePanel gamePanel;
    private MainPanel mainPanel;

    public MainInterface(){

        window=Toolkit.getDefaultToolkit().getScreenSize();

        icon_url=  MainInterface.class.getResource("/com/resource/icon.png");
        bg_url=    MainInterface.class.getResource("/com/resource/bg.png");
        square_url=MainInterface.class.getResource("/com/resource/square.png");
        
        day_url=   MainInterface.class.getResource("/com/resource/clear_day.png");
        night_url= MainInterface.class.getResource("/com/resource/clear_night.png");
        cloudy_url=MainInterface.class.getResource("/com/resource/cloudy.png");
        rain_url=  MainInterface.class.getResource("/com/resource/heavy_rain.png");

        bg_icon=new ImageIcon(bg_url);
        icon_icon=new ImageIcon(icon_url);
        square_icon= new ImageIcon(square_url);
        day_icon = new ImageIcon(day_url);
        rain_icon = new ImageIcon(rain_url);
        cloudy_icon = new ImageIcon(cloudy_url);
        night_icon = new ImageIcon(night_url);

        this.setLayout(null);
        mainPanel=new MainPanel();
        this.add(mainPanel);
        this.setTitle("MONOPLOY");
        this.setIconImage(icon_icon.getImage());
        this.setSize(1400,900);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocation((window.width-1400)/2,(window.height-900)/2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void startGame(){

        gamePanel=new GamePanel();
        mainPanel.setVisible(false);
        this.add(gamePanel);

    }

    public void endGame() {
        this.remove(gamePanel);
        mainPanel.setVisible(true);
    }

    class MainPanel extends JPanel implements ActionListener{

        JLabel background;
        JButton exit_button;
        JButton start_button;

        public MainPanel() {
            background=new JLabel(bg_icon);
            background.setBounds(0,0,1400,850);

            start_button=new JButton("开始游戏");
            exit_button=new JButton("退出游戏");

            this.setLayout(null);

            start_button.setFont(font_default);
            start_button.setBounds(325,550,300,100);
            start_button.addActionListener(this);
            start_button.setActionCommand("startGame");

            exit_button.setFont(font_default);
            exit_button.setBounds(775,550,300,100);
            exit_button.addActionListener(this);
            exit_button.setActionCommand("exitGame");

            this.add(start_button);
            this.add(exit_button);

            this.setSize(1400,900);
        }

        public void actionPerformed(ActionEvent e) {
            ButtonSound.playButton();
            if(e.getActionCommand().equals("startGame")) {
                MainInterface.this.startGame();
            }else if(e.getActionCommand().equals("exitGame")) {
                System.exit(0);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage((Image) bg_icon.getImage(),0,0,this.getWidth(),this.getHeight(),this);
        }
    }
    class GamePanel extends JPanel implements ActionListener{
        JLabel background;
        JButton dice_button;

        ArrayList<Integer> squarePositionX;
        ArrayList<Integer> squarePositionY;

        JLabel playerOne = new JLabel();
        JLabel playerTwo= new JLabel();
        JLabel playerThree= new JLabel();
        JLabel playerFour= new JLabel();

        ArrayList<JLabel> playerPoint = new ArrayList<>();
        ArrayList<JLabel> players = new ArrayList<>();

        public GamePanel() {

            this.setSize(1400, 900);
            this.setLayout(null);
            this.setBackground(new Color(155, 232, 184));
            new SetPlayersNumber(MainInterface.this, "选择人数", true).show();

            Integer boxLength = 128;
            Integer boxWidth = 128;
            Integer beginX = 80;
            Integer beginY = 50;

            for (int i = 0; i < num; ++i)
            {
                JLabel jLabel1 = new JLabel();
                JLabel jLabel2 = new JLabel();
                playerPoint.add(jLabel1);
                players.add(jLabel2);

                playerPoint.get(i).setFont(font_default);
                playerPoint.get(i).setForeground(Color.RED);
                playerPoint.get(i).setSize(100,100);
                playerPoint.get(i).setLocation(875+i*100,200);

                if(i == 0){
                    players.get(i).setText("你");
                }else{
                    players.get(i).setText("玩家"+String.valueOf(i));
                }
                players.get(i).setForeground(Color.BLACK);
                players.get(i).setSize(100,30);
                players.get(i).setLocation(875+i*100,300);

                this.add(playerPoint.get(i));
                this.add(players.get(i));

            }

            squarePositionX =  new ArrayList<>();
            squarePositionY = new ArrayList<>();

            for(int i=0;i<10;++i)
            {
                JLabel box= new JLabel();

                box.setIcon(square_icon);

                box.setSize(boxLength,boxWidth);

                squarePositionX.add(beginX + i*boxLength);
                squarePositionY.add(beginY);

                box.setLocation(beginX+i*boxLength,beginY);

                this.add(box);

            }

            for(int i =0; i<3; i++)
            {
                JLabel box= new JLabel();

                box.setIcon(square_icon);

                box.setSize(boxLength,boxWidth);

                squarePositionX.add(beginX+9*boxLength);
                squarePositionY.add(beginY+boxWidth+i*boxWidth);

                box.setLocation(beginX+9*boxLength,beginY+boxWidth+i*boxWidth);

                this.add(box);

            }

            for(int i=8;i>=5;--i)
            {
                JLabel box= new JLabel();

                box.setIcon(square_icon);
                box.setSize(boxLength,boxWidth);

                squarePositionX.add(beginX+i*boxLength);
                squarePositionY.add(beginY+3*boxWidth);

                box.setLocation(beginX+i*boxLength,beginY+3*boxWidth);
                this.add(box);
            }

            for(int i=5;i>=0;i--)
            {
                JLabel box= new JLabel();

                box.setIcon(square_icon);
                box.setSize(boxLength,boxWidth);

                squarePositionX.add(beginX+i*boxLength);
                squarePositionY.add(beginY+2*boxWidth);

                box.setLocation(beginX+i*boxLength,beginY+2*boxWidth);

                this.add(box);
            }

            for(int i = 2; i < 4; i++)
            {
                JLabel box= new JLabel();

                box.setIcon(square_icon);

                box.setSize(boxLength,boxWidth);

                squarePositionX.add(beginX);
                squarePositionY.add(beginY+boxWidth+i*boxWidth);

                box.setLocation(beginX,beginY+boxWidth+i*boxWidth);

                this.add(box);
            }

            for(int i=0;i<10;++i)
            {
                JLabel box= new JLabel();
                box.setIcon(square_icon);

                box.setSize(boxLength,boxWidth);

                squarePositionX.add(beginX+i*boxLength);
                squarePositionY.add(beginY+5*boxWidth);

                box.setLocation(beginX+i*boxLength,beginY+5*boxWidth);

                this.add(box);
            }

            for( int i =0 ; i < num ; ++i)//初始化棋子
            {
                switch (i){
                    case 0: {
                        day_icon.setImage(day_icon.getImage().getScaledInstance(24,24,Image.SCALE_DEFAULT));
                        playerOne.setIcon(day_icon);
                        playerOne.setSize(24,24);
                        playerOne.setLocation(90+i*24,66);
                        this.add(playerOne);
                    }break;
                    case 1: {
                        night_icon.setImage(night_icon.getImage().getScaledInstance(24,24,Image.SCALE_DEFAULT));
                        playerTwo.setIcon(night_icon);
                        playerTwo.setSize(24,24);
                        playerTwo.setLocation(90+i*24,66);
                        this.add(playerTwo);
                    }break;
                    case 2: {
                        cloudy_icon.setImage(cloudy_icon.getImage().getScaledInstance(24,24,Image.SCALE_DEFAULT));
                        playerThree.setIcon(cloudy_icon);
                        playerThree.setSize(24,24);
                        playerThree.setLocation(90+i*24,66);
                        this.add(playerThree);
                    }break;
                    default:{
                        rain_icon.setImage(rain_icon.getImage().getScaledInstance(24,24,Image.SCALE_DEFAULT));
                        playerFour.setIcon(rain_icon);
                        playerFour.setSize(24,24);
                        playerFour.setLocation(90+i*24,66);
                        this.add(playerFour);
                    }
                }

            }

            JLabel playerOneIcon = new JLabel();
            JLabel playerOneText = new JLabel();

            day_icon.setImage(day_icon.getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT));

            playerOneIcon.setIcon(day_icon);
            playerOneText.setText("-->您");

            playerOneText.setFont(font_game);

            playerOneIcon.setSize(32,32);
            playerOneText.setSize(100,32);

            playerOneIcon.setLocation(280,475);
            playerOneText.setLocation(312,475);

            this.add(playerOneIcon);
            this.add(playerOneText);

            JLabel playerTwoIcon = new JLabel();
            JLabel playerTwoText = new JLabel();

            night_icon.setImage(night_icon.getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT));

            playerTwoIcon.setIcon(night_icon);
            playerTwoText.setText("-->电脑1");

            playerTwoText.setFont(font_game);

            playerTwoIcon.setSize(32,32);
            playerTwoText.setSize(200,32);

            playerTwoIcon.setLocation(280,525);
            playerTwoText.setLocation(312,525);

            this.add(playerTwoIcon);
            this.add(playerTwoText);

            JLabel playerThreeIcon = new JLabel();
            JLabel playerThreeText = new JLabel();

            cloudy_icon.setImage(cloudy_icon.getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT));

            playerThreeIcon.setIcon(cloudy_icon);
            playerThreeText.setText("-->电脑2");

            playerThreeText.setFont(font_game);

            playerThreeIcon.setSize(32,32);
            playerThreeText.setSize(200,32);

            playerThreeIcon.setLocation(280,570);
            playerThreeText.setLocation(312,570);

            this.add(playerThreeIcon);
            this.add(playerThreeText);

            JLabel playerFourIcon = new JLabel();
            JLabel playerFourText = new JLabel();

            rain_icon.setImage(rain_icon.getImage().getScaledInstance(32,32,Image.SCALE_DEFAULT));

            playerFourIcon.setIcon(rain_icon);
            playerFourText.setText("-->电脑3");

            playerFourText.setFont(font_game);

            playerFourIcon.setSize(32,32);
            playerFourText.setSize(200,32);

            playerFourIcon.setLocation(280,610);
            playerFourText.setLocation(312,610);

            this.add(playerFourIcon);
            this.add(playerFourText);

            game = new Game(num);

            for(int i=0; i<35; i++){
                if( game.getBoard().get(i) == 0)
                {
                    JLabel text = new JLabel();
                    text.setFont(font_game);
                    text.setForeground(Color.BLUE);
                    if(i == 0){
                        text.setText("起点");
                    } else if(i == 34){
                        text.setText("终点");
                    } else {
                        text.setText("休息一下");
                    }
                    text.setSize(boxWidth,boxLength);
                    text.setLocation(squarePositionX.get(i)+20,squarePositionY.get(i));
                    this.add(text);

                }else if(game.getBoard().get(i) < 0){

                    JLabel text = new JLabel();
                    text.setFont(font_game);
                    text.setForeground(Color.RED);
                    text.setText("后退"+String.valueOf(game.getBoard().get(i)*(-1))+"步");
                    text.setSize(boxWidth,boxLength);
                    text.setLocation(squarePositionX.get(i)+20,squarePositionY.get(i));
                    this.add(text);

                }else{
                    JLabel text = new JLabel();
                    text.setFont(font_game);
                    text.setForeground(Color.ORANGE);
                    text.setText("前进"+String.valueOf(game.getBoard().get(i))+"步");
                    text.setSize(boxWidth,boxLength);
                    text.setLocation(squarePositionX.get(i)+20,squarePositionY.get(i));
                    this.add(text);
                }
            }

        }
        public void showPawn(Integer playerNum, boolean stop){

            Integer point = game.playRound(playerNum);

            playerPoint.get(playerNum).setText(String.valueOf(point));

            Integer newPosition = game.getPeople().get(playerNum).position;

            if(playerNum == 0 ){
                playerOne.setLocation(squarePositionX.get(newPosition)+10,squarePositionY.get(newPosition)+10);
            }else if(playerNum == 1){
                playerTwo.setLocation(squarePositionX.get(newPosition)+30,squarePositionY.get(newPosition)+10);
            }else if(playerNum == 2){
                playerThree.setLocation(squarePositionX.get(newPosition)+50,squarePositionY.get(newPosition)+10);
            }else{
                playerFour.setLocation(squarePositionX.get(newPosition)+70,squarePositionY.get(newPosition)+10);
            }
            if(stop)
            {
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            ButtonSound.playButton();
            if(e.getActionCommand().equals("dice")){

                showPawn(0,true);

                if(game.isWinned(0)){
                    new ResultDialog(MainInterface.this,"游戏结束",true,0).show();
                }

                new Systurn().start();

            }else{

            }
        }
        class SetPlayersNumber extends JDialog implements ActionListener {
            JLabel tip;
            JTextField input;
            JButton confirm;

            public SetPlayersNumber(Frame owner, String title, boolean modal){
                super(owner, title, modal);
                tip=new JLabel("请输入游玩的人数");
                input=new JTextField(String.valueOf(2));
                confirm=new JButton("确认");

                tip.setFont(font_default);
                tip.setBounds(50,50,400,60);
                input.setFont(font_default);
                input.setBounds(50,130,400,60);
                confirm.setFont(font_default);
                confirm.setBounds(300,200,150,60);
                confirm.addActionListener(this);


                this.setLayout(null);
                this.add(tip);
                this.add(input);
                this.add(confirm);

                this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                this.setSize(500,350);
                this.getContentPane().setBackground(Color.WHITE);
                this.setLocation((window.width-500)/2,(window.height-350)/2);

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonSound.playButton();
                try {
                    num = Integer.parseInt(input.getText());
                    if(num>=2&&num<=4) {
                        game = new Game(num);
                        new initGame().start();
                        this.dispose();
                    }else if(num>4){
                        tip.setText("人数太多了！！");
                    }else{
                        tip.setText("人数太少了！！");
                    }
                }catch (Exception e1) {
                    tip.setText("请输入数字：");
                }
            }
        }

        class ResultDialog extends JDialog implements ActionListener{

            JLabel resultTitle = new JLabel();
            JButton confirm = new JButton();

            public ResultDialog(Frame owner, String title, boolean modal, Integer result){
                super(owner,title,modal);

                if(result ==0 ){
                    resultTitle.setText("你获得了胜利！");
                    SoundTools.playWin();
                }else{
                    resultTitle.setText("电脑玩家"+String.valueOf(result)+"获胜");
                    SoundTools.playLose();
                }

                resultTitle.setFont(font_default);

                resultTitle.setBounds(50,60,400,60);

                confirm.setText("确认");
                confirm.setFont(font_default);
                confirm.setBounds(300,200,150,60);
                confirm.addActionListener(this);

                this.setLayout(null);
                this.add(resultTitle);
                this.add(confirm);

                this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                this.setSize(500,350);
                this.getContentPane().setBackground(Color.WHITE);
                this.setLocation((window.width-500)/2,(window.height-350)/2-50);
            }
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonSound.playButton();
                endGame();
                this.dispose();
            }
        }

        class initGame extends Thread {
            @Override
            public void run()
            {
                super.run();
                SoundTools.playBgm();
                background=new JLabel(bg_icon);
                background.setBounds(0,0,1400,850);

                dice_button = new JButton("投掷");
                dice_button.setFont(font_default);
                dice_button.setBounds(975,350,120,60);
                dice_button.addActionListener(GamePanel.this);
                dice_button.setActionCommand("dice");

                GamePanel.this.add(dice_button);

                GamePanel.this.repaint();

            }
        }
        class Systurn extends Thread{
            @Override
            public void run()
            {
                super.run();

                gamePanel.dice_button.setEnabled(false);

                for(int i=1 ;i < num; ++i)
                {
                    showPawn(i,false);

                    if(game.isWinned(i)){
                        new ResultDialog(MainInterface.this,"游戏结束",true,i).show();
                    }

                }

                gamePanel.dice_button.setEnabled(true);

            }
        }
    }


}
