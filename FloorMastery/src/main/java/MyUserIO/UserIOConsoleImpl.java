/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyUserIO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class UserIOConsoleImpl implements UserIO {
final private Scanner console = new Scanner(System.in);

    /**
     *
     * A very simple method that takes in a message to display on the console.
     * and then waits for a integer answer from the user to return.
     *
     * @param msg - String of information to display to the user.
     *
     */
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for an answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as string
     */
    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for a integer answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as integer
     */
    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (asking for the # of cats!)
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                System.out.println("You can't do that Dave...!");
            }
        }
        return num;
    }

    /**
     *
     * A slightly more complex method that takes in a message to display on the
     * console, and then waits for a integer answer within the specified min/max
     * range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an integer value as an answer to the message prompt within the
     * min/max range
     */
    @Override
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for a long answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as long
     */
    @Override
    public long readLong(String msgPrompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                System.out.println("You can't do that Dave...!");
            }
        }
    }

    /**
     * A slightly more complex method that takes in a message to display on the
     * console, and then waits for a long answer within the specified min/max
     * range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an long value as an answer to the message prompt within the
     * min/max range
     */
    @Override
    public long readLong(String msgPrompt, long min, long max) {
        long result;
        do {
            result = readLong(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for a float answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as float
     */
    @Override
    public float readFloat(String msgPrompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                System.out.println("You can't do that Dave...!");
            }
        }
    }

    /**
     *
     * A slightly more complex method that takes in a message to display on the
     * console, and then waits for a float answer within the specified min/max
     * range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an float value as an answer to the message prompt within the
     * min/max range
     */
    @Override
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for a double answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as double
     */
    @Override
    public double readDouble(String msgPrompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                System.out.println("You can't do that Dave...!");
            }
        }
    }

    /**
     *
     * A slightly more complex method that takes in a message to display on the
     * console, and then waits for a double answer within the specified min/max
     * range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an double value as an answer to the message prompt within the
     * min/max range
     */
    @Override
    public double readDouble(String msgPrompt, double min, double max) {
        double result;
        do {
            result = readDouble(msgPrompt);
        } while (result < min || result > max);
        return result;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        Scanner sc = new Scanner(System.in);
        BigDecimal BigDecimalValue = null;
        boolean correctInput = false;

        System.out.println(prompt);
        while (!correctInput) {
            try {
                String userAnswer = sc.nextLine();
                BigDecimalValue = new BigDecimal(userAnswer);
                correctInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Enter a BigDecimal: ");
            }
        }
        return BigDecimalValue;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigDecimal readBigDecimal(String prompt, BigDecimal min) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LocalDate readLocalDate(String prompt) {
        Scanner sc = new Scanner(System.in);
        LocalDate newDate = null;
        boolean correctInput = false;
        LocalDate earliestValidDate = LocalDate.parse("1975-01-01");
        boolean isValidDate = false;
        String readLocalDate;
        while (!isValidDate) {
            try {
                System.out.println("Please enter the date of order using MMddyyyy format."
                        + "\n" + "For example, 11/01/2012 would be 11012012");
                readLocalDate = sc.nextLine();
                newDate = LocalDate.parse(readLocalDate, DateTimeFormatter.ofPattern("MMddyyyy"));
                if (newDate.isBefore(earliestValidDate)) {
                    System.out.println("Invalid date. Date must be after 01/01/1975");
                    continue;
                }
                isValidDate = true;
            } catch (Exception e) {
                System.out.println("Please enter date using MMddyyyy format.");
            }
        }
        return newDate;
    }

    @Override
    public Boolean readBooleanValue(String prompt) {
        Scanner sc = new Scanner(System.in);
        boolean userAnswer = false;
        boolean isValidFormat = false;
        while (!isValidFormat) {
            System.out.println("Please enter Yes or No");
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
                userAnswer = true;
                isValidFormat = true;
            } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
                userAnswer = false;
                isValidFormat = true;
            } else {
                System.out.println("Please enter Yes or No with correct format.");
            }

        }
        return userAnswer;
    }

    @Override
    public String readStringRead(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

//    Scanner sc = new Scanner(System.in);
//
//    @Override
//    public void print(String message) {
//        System.out.println(message);
//    }
//
//    @Override
//    public double readDouble(String prompt) {
//        double num = 0;
//        System.out.println(prompt);
//        try {
//            String bob = sc.nextLine();
//            num = Double.parseDouble(bob);
//        } catch (NumberFormatException ex) {
//            System.out.println("Enter vaild input!!!");
//        }
//
//        return num;
//    }
//
//    @Override
//    public double readDouble(String prompt, double min, double max) {
//        boolean isValid = false;
//        double userDouble = 0;
//        while (!isValid) {
//            userDouble = readDouble(prompt);
//            if (userDouble >= min && userDouble <= max) {
//                isValid = true;
//            } else {
//                print(prompt);
//            }
//        }
//        return userDouble;
//    }
//
//    @Override
//    public float readFloat(String prompt) {
//        System.out.println(prompt);
//        return sc.nextFloat();
//    }
//
//    @Override
//    public float readFloat(String prompt, float min, float max) {
//
//        boolean isValid = false;
//        float userFloat = 0;
//        while (!isValid) {
//            userFloat = readFloat(prompt);
//            if (userFloat >= min && userFloat <= max) {
//                isValid = true;
//            } else {
//                print(prompt);
//            }
//
//        }
//        return userFloat;
//    }
//
//    @Override
//    public int readInt(String prompt) {
//        System.out.println(prompt);
//        return sc.Interger.();
//
//    }
//
//    @Override
//
//    public int readInt(String prompt, int min, int max) {
//
//        boolean isValid = false;
//        int readInt = 0;
//        while (!isValid) {
//            readInt = readInt(prompt);
//            if (readInt >= min && readInt <= max) {
//
//                isValid = true;
//            } else {
//                print(prompt);
//            }
//        }
//        return readInt;
//
//    }
//
//    @Override
//    public long readLong(String prompt) {
//
//        System.out.println(prompt);
//        return sc.nextLong();
//
//    }
//
//    @Override
//    public long readLong(String prompt, long min, long max) {
//        boolean isValid = false;
//        long readLong = 0;
//        while (!isValid) {
//            readLong = readLong(prompt);
//            if (readLong >= min && readLong <= max) {
//
//                isValid = true;
//            } else {
//                print(prompt);
//            }
//
//        }
//        return readLong;
//
//    }
//
//    @Override
//    public String readString(String prompt) {
//        System.out.println(prompt);
//        return sc.nextLine();
//
//    }
//
//    @Override
//    public BigDecimal readBigDecimal(String prompt) {
//        Scanner sc = new Scanner(System.in);
//        BigDecimal BigDecimalValue = null;
//        boolean correctInput = false;
//
//        System.out.println(prompt);
//        while (!correctInput) {
//            try {
//                String userAnswer = sc.nextLine();
//                BigDecimalValue = new BigDecimal(userAnswer);
//                correctInput = true;
//            } catch (NumberFormatException e) {
//                System.out.println("Enter a BigDecimal: ");
//            }
//        }
//        return BigDecimalValue;
//    }
//
//    @Override
//    public BigDecimal readBigDecimal(String prompt, BigDecimal min, BigDecimal max) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public BigDecimal readBigDecimal(String prompt, BigDecimal min) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public LocalDate readLocalDate(String prompt) {
//        Scanner sc = new Scanner(System.in);
//        LocalDate newDate = null;
//        boolean correctInput = false;
//        LocalDate earliestValidDate = LocalDate.parse("1975-01-01");
//        boolean isValidDate = false;
//        String readLocalDate;
//        while (!isValidDate) {
//            try {
//                System.out.println("Please enter the date of order using MMddyyyy format."
//                        + "\n" + "For example, 11/01/2012 would be 11012012");
//                readLocalDate = sc.nextLine();
//                newDate = LocalDate.parse(readLocalDate, DateTimeFormatter.ofPattern("MMddyyyy"));
//                if (newDate.isBefore(earliestValidDate)) {
//                    System.out.println("Invalid date. Date must be after 01/01/1975");
//                    continue;
//                }
//                isValidDate = true;
//            } catch (Exception e) {
//                System.out.println("Please enter date using MMddyyyy format.");
//            }
//        }
//        return newDate;
//    }
//
//    @Override
//    public Boolean readBooleanValue(String prompt) {
//        Scanner sc = new Scanner(System.in);
//        boolean userAnswer = false;
//        boolean isValidFormat = false;
//        while (!isValidFormat) {
//            System.out.println("Please enter Yes or No");
//            String userInput = sc.nextLine();
//            if (userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes")) {
//                userAnswer = true;
//                isValidFormat = true;
//            } else if (userInput.equalsIgnoreCase("n") || userInput.equalsIgnoreCase("no")) {
//                userAnswer = false;
//                isValidFormat = true;
//            } else {
//                System.out.println("Please enter Yes or No with correct format.");
//            }
//
//        }
//        return userAnswer;
//    }
//
//    @Override
//    public String readStringRead(String prompt) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//}
