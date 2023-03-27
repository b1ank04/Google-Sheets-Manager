package com.example.demo.service;

import com.example.demo.util.GoogleApiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class GoogleApiService {
    private final GoogleApiUtil googleApiUtil;

    @Autowired
    public GoogleApiService(GoogleApiUtil googleApiUtil) {
        this.googleApiUtil = googleApiUtil;
    }

    public List<Object> getDataFromSheet(String range) throws GeneralSecurityException, IOException {
        return googleApiUtil.getDataFromSheet(range);
    }

    public String writeDataToSheet(String range, List<List<Object>> values) throws IOException, GeneralSecurityException {
        return googleApiUtil.writeDataToSheet(range, values);
    }
}
