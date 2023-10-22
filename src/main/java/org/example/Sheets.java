package org.example;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class Sheets extends  Data{

    public String id;
    public List<JSONArray> data = new ArrayList<JSONArray>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<JSONArray> getData() {
        return data;
    }

    public void setData(List<JSONArray> data) {
        this.data = data;
    }

    public Sheets(){

    }

    public Sheets(List<Sheets> sheets, String submissionUrl, String id, List<JSONArray> data) {
        super(sheets, submissionUrl);
        this.id = id;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", data=" + data +
                "} " ;
    }


}


