package com.company;

public class CompanyAggregation {

    private Company company;
    private Integer timesRaisedAmount = 0;
    private Integer totalRaisedAmount = 0;

    public CompanyAggregation(Company company, Integer timesRaisedAmount, Integer totalRaisedAmount) {
        this.company = company;
        this.timesRaisedAmount = timesRaisedAmount;
        this.totalRaisedAmount = totalRaisedAmount;
    }

    @Override
    public String toString() {
        return "CompanyAggregation{" +
                "company=" + company +
                ", timesRaisedAmount=" + timesRaisedAmount +
                ", totalRaisedAmount=" + totalRaisedAmount +
                '}';
    }

    public CompanyAggregation() {
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Integer getTimesRaisedAmount() {
        return timesRaisedAmount;
    }

    public void setTimesRaisedAmount(Integer timesRaisedAmount) {
        this.timesRaisedAmount = timesRaisedAmount;
    }

    public Integer getTotalRaisedAmount() {
        return totalRaisedAmount;
    }

    public void setTotalRaisedAmount(Integer totalRaisedAmount) {
        this.totalRaisedAmount = totalRaisedAmount;
    }
}
