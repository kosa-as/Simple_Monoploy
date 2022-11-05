package com.company;

import javax.swing.*;

public class Main {
    static MainInterface mainInterface;

    public static void main(String[] args) {
        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        }catch (Exception e) {
            e.printStackTrace();
        }
        mainInterface=new MainInterface();
    }

}