//Each day has a specific discount rate associated with it.
//Weekdays have 10% discount, weekends have no discount
package BobsCircus;

public enum DayOfWeek {
    MONDAY(0.10), 
    TUESDAY(0.10),
    WEDNESDAY(0.10),
    THURSDAY(0.10),
    FRIDAY(0.10),
    SATURDAY(0.0),
    SUNDAY(0.0);

    private final double discount; // instance variable

    //Constructor for the DayOfWeek enum
    DayOfWeek(double discount) {
        this.discount = discount;
    }

    //Public method provides access to the discount value associated with a specific enum constant.
    public double getDiscount() {
        return discount;
    }
}

