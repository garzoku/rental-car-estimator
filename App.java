import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

class App {
  String pickupDate;
  int daysForRental;
  boolean payToll;
  boolean hasGps;
  boolean hasRoadsideAssist;
  int age;

  public static boolean getInputAsBoolean(String input) {
    return input.equalsIgnoreCase("yes") ? true : false;
  }

  public App(String pickupDate, int daysForRental, boolean payToll, boolean hasGps, boolean hasRoadsideAssist,
      int age) {
    this.pickupDate = pickupDate;
    this.daysForRental = daysForRental;
    this.payToll = payToll;
    this.hasGps = hasGps;
    this.hasRoadsideAssist = hasRoadsideAssist;
    this.age = age;
  }

  String getPickupDate() {
    return this.pickupDate;
  }

  int getdaysForRental() {
    return this.daysForRental;
  }

  boolean getpayToll() {
    return this.payToll;
  }

  boolean getHasGps() {
    return this.hasGps;
  }

  boolean getHasRoadsideAssist() {
    return this.hasRoadsideAssist;
  }

  int getAge() {
    return this.age;
  }

  double getCostOfRental() {
    return this.daysForRental * 29.99;
  }

  double getCostOfOptions(boolean payToll, boolean hasGps, boolean hasRoadsideAssist, int numberOfDays) {
    double option1 = payToll == true ? 3.95 : 0;
    double option2 = hasGps == true ? 2.95 : 0;
    double option3 = hasRoadsideAssist == true ? 3.95 : 0;
    double totalCost = (option1 + option2 + option3) * numberOfDays;
    return totalCost;
  }

  public static void main(String args[]) {
    Scanner userInputs = new Scanner(System.in);
    System.out.println("Enter pickup date (i.e. 10/03/2022): ");
    String pickupDate = userInputs.nextLine();

    System.out.println("Enter the number of days you will need rental:");
    int daysForRental = Integer.parseInt(userInputs.nextLine());

    System.out.println("Would you like to add a toll tag for $3.95 per day? (yes or no):");
    boolean payToll = App.getInputAsBoolean(userInputs.nextLine());

    System.out.println("Would you like to add GPS for $2.95 per day? (yes or no):");
    boolean hasGps = App.getInputAsBoolean(userInputs.nextLine());

    System.out.println("Would you like to add Roadside Assistance for $3.95 per day? (yes or no):");
    boolean hasRoadsideAssist = App.getInputAsBoolean(userInputs.nextLine());

    System.out.println("Enter your age: ");
    int age = Integer.parseInt(userInputs.nextLine());

    userInputs.close();

    App app = new App(pickupDate, daysForRental, payToll, hasGps, hasRoadsideAssist, age);
    DecimalFormat formatter = new DecimalFormat("#.##");
    formatter.setRoundingMode(RoundingMode.CEILING);
    double basicRentalCost = app.getCostOfRental();
    double costOfOptions = app.getCostOfOptions(app.payToll, app.hasGps, app.hasRoadsideAssist, app.daysForRental);
    double underageCharge = basicRentalCost * .30;
    double totalCost = app.age < 25 ? (basicRentalCost + costOfOptions + underageCharge)
        : (basicRentalCost + costOfOptions);

    if (app.age < 25) {
      String prompt = String.format(
          "Basic Car Rental: $%s\nOptions Cost: $%s\nUnderage Surcharge: $%s\nTotal Cost: $%s",
          formatter.format(basicRentalCost), formatter.format(costOfOptions), formatter.format(underageCharge),
          formatter.format(totalCost));
      System.out.println(prompt);
    } else {
      String prompt = String.format(
          "Basic Car Rental: $%s\nOptions Cost: $%s\nUnderage Surcharge: $%s\nTotal Cost: $%s",
          formatter.format(basicRentalCost), formatter.format(costOfOptions), formatter.format(0),
          formatter.format(totalCost));
      System.out.println(prompt);
    }
  }
}
