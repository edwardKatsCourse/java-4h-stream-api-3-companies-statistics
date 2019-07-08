package com.company;

public class Company {
    private String permalink;
    private String company;
    private Integer numEmployees;
    private String category;
    private String city;
    private String state;
    private String fundedDate;
    private Integer raisedAmount;
    private String raisedCurrency;
    private String round;

    //constructors (default and all arguments)
    //getters/setters
    //toString


    public Company() {
    }

    @Override
    public String toString() {
        return "Company{" +
                "permalink='" + permalink + '\'' +
                ", company='" + company + '\'' +
                ", numEmployees=" + numEmployees +
                ", category='" + category + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", fundedDate='" + fundedDate + '\'' +
                ", raisedAmount=" + raisedAmount +
                ", raisedCurrency='" + raisedCurrency + '\'' +
                ", round='" + round + '\'' +
                '}';
    }

    public Company(String permalink, String company, Integer numEmployees, String category, String city, String state, String fundedDate, Integer raisedAmount, String raisedCurrency, String round) {
        this.permalink = permalink;
        this.company = company;
        this.numEmployees = numEmployees;
        this.category = category;
        this.city = city;
        this.state = state;
        this.fundedDate = fundedDate;
        this.raisedAmount = raisedAmount;
        this.raisedCurrency = raisedCurrency;
        this.round = round;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(Integer numEmployees) {
        this.numEmployees = numEmployees;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFundedDate() {
        return fundedDate;
    }

    public void setFundedDate(String fundedDate) {
        this.fundedDate = fundedDate;
    }

    public Integer getRaisedAmount() {
        return raisedAmount;
    }

    public void setRaisedAmount(Integer raisedAmount) {
        this.raisedAmount = raisedAmount;
    }

    public String getRaisedCurrency() {
        return raisedCurrency;
    }

    public void setRaisedCurrency(String raisedCurrency) {
        this.raisedCurrency = raisedCurrency;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }
}
