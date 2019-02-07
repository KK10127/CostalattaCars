package edu.miracostacollege.cs134.costalattacars.model;


/**
 * Data model for a car loan, including information about the car price, down payment,
 * amount borrowed, interest amount and total cost of the loan.
 *
 * @author Michael Paulding
 */
public class CarLoan {
    // Constants
    public static final double OCEANSIDE_TAX_RATE = 0.0775;
    public static final double LOAN_RATE_3_YEARS = 0.0535;
    public static final double LOAN_RATE_4_YEARS = 0.0471;
    public static final double LOAN_RATE_5_YEARS = 0.0477;

    // Instance variables
    private double mPrice;
    private double mDownPayment;
    private int mLoanTerm;

    /**
     * Sets the price of the car.
     * @param price The price of the car.
     */
    public void setPrice(double price) {
        mPrice = price;
    }

    /**
     * Gets the price of the car.
     * @return The price of the car.
     */
    public double getPrice() {
        return mPrice;
    }

    /**
     * Sets the down payment amount.
     * @param down The down payment amount.
     */
    public void setDownPayment(double down) {
        mDownPayment = down;
    }

    /**
     * Gets the down payment amount.
     * @return The down payment amount.
     */
    public double getDownPayment() {
        return mDownPayment;
    }

    /**
     * Sets the loan term to either 3, 4, or 5 years (loans available on cars).
     * @param term
     */
    public void setLoanTerm(int term) {
        mLoanTerm = term;
    }

    /**
     * Gets the loan term.
     * @return The loan term.
     */
    public int getLoanTerm() {
        return mLoanTerm;
    }

    /**
     * Calculates the tax amount by multiplying the price by the current tax rate
     * in Oceanside, CA (7.75%)
     * @return The tax amount of the car.
     */
    public double taxAmount() {
        return mPrice * OCEANSIDE_TAX_RATE;
    }

    /**
     * Calculates the total cost of the car by adding the purchase price plus the tax amount.
     * @return The total cost of the car.
     */
    public double totalCost() {
        return mPrice + taxAmount();
    }

    /**
     * Calculates the amount borrowed on the car by subtracting the down payment from the
     * total cost of the car.
     * @return The amount borrowed.
     */
    public double borrowedAmount() {
        return totalCost() - mDownPayment;
    }

    /**
     * Calculates the amount paid in interest by multiplying the borrowed amount times the interest
     * rate.
     * @return The amount paid in interest.
     */
    public double interestAmount() {
        double interestRate;
        switch (mLoanTerm) {
            case 3:
                interestRate = LOAN_RATE_3_YEARS;
                break;
            case 4:
                interestRate = LOAN_RATE_4_YEARS;
                break;
            default:
                interestRate = LOAN_RATE_5_YEARS;
        }
        return borrowedAmount() * interestRate;
    }

    /**
     * Calculates the monthly payment by adding the amount borrowed to the interest amount,
     * then dividing by the loan term * 12 (e.g. 5 years * 12 mo/year = 60 months).
     * @return The monthly payment amount for the car loan.
     */
    public double monthlyPayment() {
        return (borrowedAmount() + interestAmount()) / (mLoanTerm * 12.0);
    }

}
