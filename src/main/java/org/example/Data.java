package org.example;

import java.util.ArrayList;
import java.util.List;

public class Data {

    public String submissionUrl;
    public List<Sheets> sheets = new ArrayList<Sheets>();


    public Data(List<Sheets> sheets){
        this.sheets = sheets;
    }

    public List<Sheets> getSheets() {
        return sheets;
    }

    public void setSheets(List<Sheets> sheets) {
        this.sheets = sheets;
    }

    public String getSubmissionUrl() {
        return submissionUrl;
    }

    public void setSubmissionUrl(String submissionUrl) {
        this.submissionUrl = submissionUrl;
    }

    public Data(){

    }

    public Data(List<Sheets> sheets, String submissionUrl) {
        this.sheets = sheets;
        this.submissionUrl = submissionUrl;
    }

}
