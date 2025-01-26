package com.mypersonalbook.economy.domain;

public class AmountSummary {
    private Float expense;
    private Float revenue;

    public AmountSummary(Float expense, Float revenue) {
        this.expense = expense;
        this.revenue = revenue;
    }

    public AmountSummary() {
        this.expense = 0.0F;
        this.revenue = 0.0F;
    }

    public Float getExpense() {
        return expense;
    }

    public void setExpense(Float expense) {
        this.expense = expense;
    }

    public Float getRevenue() {
        return revenue;
    }

    public void setRevenue(Float revenue) {
        this.revenue = revenue;
    }
}
