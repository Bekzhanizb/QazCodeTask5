package bekezhan.io.task5.controller;

import bekezhan.io.task5.entity.ApiResponse;
import bekezhan.io.task5.service.ProbeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    private final ProbeService probeService;

    public TaskController(ProbeService probeService) {
        this.probeService = probeService;
    }

    @GetMapping("/ping")
    public ResponseEntity<ApiResponse> ping() {
        ApiResponse response = ApiResponse.builder()
                .status("OK")
                .message("pong")
                .data(null)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/slow")
    public ResponseEntity<ApiResponse> slow() {
        long startTime = System.currentTimeMillis();
        String data = probeService.slowSelect();
        long endTime = System.currentTimeMillis();

        ApiResponse response = ApiResponse.builder()
                .status("OK")
                .message("Slow DB request processed")
                .processingTime(endTime - startTime)
                .data(data)
                .build();

        return ResponseEntity.ok(response);
    }
}
