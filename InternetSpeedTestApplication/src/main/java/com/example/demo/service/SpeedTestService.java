package com.example.demo.service;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class SpeedTestService {

    private static final int MAX_DOWNLOAD_SPEED_MBPS = 1000;
    private static final int MAX_UPLOAD_SPEED_MBPS = 500;

    public String performDownloadTest() {
        Random random = new Random();
        double speed = random.nextDouble() * MAX_DOWNLOAD_SPEED_MBPS;
        return String.format("%.2f", speed);
    }

    public String performUploadTest() {
        Random random = new Random();
        double speed = random.nextDouble() * MAX_UPLOAD_SPEED_MBPS;
        return String.format("%.2f", speed);
    }
}

