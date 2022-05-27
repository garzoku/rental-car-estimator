import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

class App {
  private static String pickupDate;
  private static int daysForRental;
  private static boolean payToll;
  private static boolean hasGps;
  private static boolean hasRoadsideAssist;
  private static int age;

  public static void main(String args[]) {
    getUserInput();

    DecimalFormat formatter = new DecimalFormat("#.##");
    formatter.setRoundingMode(RoundingMode.CEILING);
    double basicRentalCost = getCostOfRental(daysForRental);
    double costOfOptions = getCostOfOptions(payToll, hasGps, hasRoadsideAssist, daysForRental);
    double underageCharge = basicRentalCost * .30;
    double totalCost = age < 25 ? (basicRentalCost + costOfOptions + underageCharge)
        : (basicRentalCost + costOfOptions);

    printMessage(formatter, basicRentalCost, costOfOptions, underageCharge, totalCost);
  }

  private static void printMessage(DecimalFormat formatter, double rentalCost, double optionsCost,
      double underageCharge,
      double totalCost) {
    if (age < 25) {
      String prompt = String.format(
          "Basic Car Rental: $%s\nOptions Cost: $%s\nUnderage Surcharge: $%s\nTotal Cost: $%s",
          formatter.format(rentalCost), formatter.format(optionsCost), formatter.format(underageCharge),
          formatter.format(totalCost));
      System.out.println(prompt);
    } else {
      String prompt = String.format(
          "Basic Car Rental: $%s\nOptions Cost: $%s\nUnderage Surcharge: $%s\nTotal Cost: $%s",
          formatter.format(rentalCost), formatter.format(optionsCost), formatter.format(0),
          formatter.format(totalCost));
      System.out.println(prompt);
    }
  }

  private static boolean getInputAsBoolean(String input) {
    return input.equalsIgnoreCase("yes") ? true : false;
  }

  private static double getCostOfRental(double days) {
    return days * 29.99;
  }

  private static double getCostOfOptions(boolean payToll, boolean hasGps, boolean hasRoadsideAssist, int numberOfDays) {
    double option1 = payToll == true ? 3.95 : 0;
    double option2 = hasGps == true ? 2.95 : 0;
    double option3 = hasRoadsideAssist == true ? 3.95 : 0;
    double totalCost = (option1 + option2 + option3) * numberOfDays;
    return totalCost;
  }

  private static void getUserInput() {
    Scanner userInputs = new Scanner(System.in);
    System.out.println("Enter pickup date (i.e. 10/03/2022): ");
    pickupDate = userInputs.nextLine();

    System.out.println("Enter the number of days you will need rental:");
    daysForRental = Integer.parseInt(userInputs.nextLine());

    System.out.println("Would you like to add a toll tag for $3.95 per day? (yes or no):");
    payToll = getInputAsBoolean(userInputs.nextLine());

    System.out.println("Would you like to add GPS for $2.95 per day? (yes or no):");
    hasGps = getInputAsBoolean(userInputs.nextLine());

    System.out.println("Would you like to add Roadside Assistance for $3.95 per day? (yes or no):");
    hasRoadsideAssist = getInputAsBoolean(userInputs.nextLine());

    System.out.println("Enter your age: ");
    age = Integer.parseInt(userInputs.nextLine());

    userInputs.close();
  }
}
