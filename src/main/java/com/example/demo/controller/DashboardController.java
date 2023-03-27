package com.example.demo.controller;

import com.example.demo.model.InputWrapper;
import com.example.demo.service.GoogleApiService;
import com.example.demo.util.GoogleApiUtil;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1")
public class DashboardController {
    private final GoogleApiService googleApiService;

    public DashboardController(GoogleApiService googleApiService) {
        this.googleApiService = googleApiService;
    }

    @GetMapping("/check")
    public String check() {
        return "API is accessible";
    }
    @GetMapping("/read")
    public List<Object> readDataFromGoogleSheet(@RequestBody InputWrapper input) throws GeneralSecurityException, IOException {
        GoogleApiUtil.spreadsheetId = input.getSheetId();
        return googleApiService.getDataFromSheet(input.getRange());
    }

    @PostMapping("/write")
    public String writeDataToSheet(@RequestBody InputWrapper input) throws IOException, InterruptedException, GeneralSecurityException {
        String timeString = input.getTime();
        if (timeString != null) {
            LocalDateTime time;
            try {
                time = LocalDateTime.parse(timeString);
            } catch (Exception ignored) {
                return "Wrong input format, should match yyyy-MM-dd-HH-mm-ss.zzz";
            }
            TimeUnit.MILLISECONDS.sleep(ChronoUnit.MILLIS.between(LocalDateTime.now(), time));
        }
        return googleApiService.writeDataToSheet(input.getRange(), input.getValues());
    }
}
