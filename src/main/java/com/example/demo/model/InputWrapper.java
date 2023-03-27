package com.example.demo.model;

import lombok.Value;

import java.util.List;

@Value
public class InputWrapper {

    String sheetId;
    List<List<Object>> values;
    String range;
    String time;

    public InputWrapper(String sheetId,List<List<Object>> values, String range, String time) {
        this.sheetId = sheetId;
        this.values = values;
        this.range = range;
        this.time = time;
    }

    public String getSheetId() {
        return sheetId;
    }
    public List<List<Object>> getValues() {
        return values;
    }
    public String getRange() {
        return range;
    }
    public String getTime() {
        return time;
    }
}
