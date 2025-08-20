package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.SpeedTestService;

@Controller
public class SpeedTestController {

    private final SpeedTestService speedTestService;

    public SpeedTestController(SpeedTestService speedTestService) {
        this.speedTestService = speedTestService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "speedtest";
    }

    // ✅ This will now allow GET and POST requests
    @RequestMapping(value = "/start-test", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String startSpeedTest(@RequestParam(required = false) String testType) {
        // Default to "download" if testType is missing
        if (testType == null || testType.trim().isEmpty()) {
            testType = "download";
        }

        if ("download".equalsIgnoreCase(testType)) {
            return speedTestService.performDownloadTest();
        } else if ("upload".equalsIgnoreCase(testType)) {
            return speedTestService.performUploadTest();
        }
        return "{\"error\": \"Invalid test type\"}";
    }
}
