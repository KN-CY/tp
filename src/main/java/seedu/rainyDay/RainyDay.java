package seedu.rainyDay;

import seedu.rainyDay.modules.Storage;
import seedu.rainyDay.modules.UI;
import seedu.rainyDay.command.Command;
import seedu.rainyDay.data.FinancialReport;
import seedu.rainyDay.data.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RainyDay {
    public static String filePath = "rainyDay.txt";
    public static FinancialReport financialReport = new FinancialReport(new ArrayList<>());

    private RainyDay(String filePath) {
        try {
            financialReport = new FinancialReport(Storage.loadFromFile(filePath));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No valid save file detected. Starting with empty financial data.");
        }
    }

    private void run() {
        Scanner input = new Scanner(System.in);
        UI.printLogo();
        UI.greetUser(input.nextLine());

        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = UI.getUserInput();
                if (userInput.equalsIgnoreCase(Command.COMMAND_EXIT)) {
                    isExit = true;
                }
                Parser.parseUserInput(userInput);
            } catch (Exception e) {
                UI.wrongInputFormat();
                break;
            }
        }
        UI.sayFarewellToUser();
    }

    public static void main(String[] args) {
        new RainyDay(filePath).run();
    }
}
