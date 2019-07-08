package com.example.mayank.recordlist;

public class ItemModel {
    String title;
    String shortDescription;
    String collectedValue;
    String totalValue;
    String startDate;
    String endDate;
    String mainImageURL;


    public ItemModel(String title, String shortDescription, String collectedValue, String totalValue, String startDate, String endDate, String mainImageURL) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.collectedValue = collectedValue;
        this.totalValue = totalValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mainImageURL = mainImageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getCollectedValue() {
        return collectedValue;
    }

    public void setCollectedValue(String collectedValue) {
        this.collectedValue = collectedValue;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMainImageURL() {
        return mainImageURL;
    }

    public void setMainImageURL(String mainImageURL) {
        this.mainImageURL = mainImageURL;
    }
}
