/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DVDlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class UserIOConsoleImpl implements UserIO {

    Scanner sc = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public double readDouble(String prompt) {
        return sc.nextDouble();
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        boolean isValid = false;
        double userDouble = 0;
        while (!isValid) {
            userDouble = readDouble(prompt);
            if (userDouble >= min && userDouble <= max) {
                isValid = true;
            } else {
                print(prompt);
            }
        }
        return userDouble;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        return sc.nextFloat();
    }

    @Override
    public float readFloat(String prompt, float min, float max) {

        boolean isValid = false;
        float userFloat = 0;
        while (!isValid) {
            userFloat = readFloat(prompt);
            if (userFloat >= min && userFloat <= max) {
                isValid = true;
            } else {
                print(prompt);
            }

        }
        return userFloat;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        return sc.nextInt();

    }

    @Override

    public int readInt(String prompt, int min, int max) {

        boolean isValid = false;
        int readInt = 0;
        while (!isValid) {
            readInt = readInt(prompt);
            if (readInt >= min && readInt <= max) {

                isValid = true;
            } else {
                print(prompt);
            }
        }
        return readInt;

    }

    @Override
    public long readLong(String prompt) {

        System.out.println(prompt);
        return sc.nextLong();

    }

    @Override
    public long readLong(String prompt, long min, long max) {
        boolean isValid = false;
        long readLong = 0;
        while (!isValid) {
            readLong = readLong(prompt);
            if (readLong >= min && readLong <= max) {

                isValid = true;
            } else {
                print(prompt);
            }

        }
        return readLong;

    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();
    }
}
