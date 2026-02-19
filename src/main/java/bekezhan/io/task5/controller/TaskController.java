package bekezhan.io.task5.controller;

import bekezhan.io.task5.entity.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {
    @GetMapping("/ok")
    public ResponseEntity<ApiResponse> ok() {
        ApiResponse response = ApiResponse.builder()
                .status("OK")
                .message("Request processed successfully")
                .data(null)
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/slow")
    public ResponseEntity<ApiResponse> slow() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread.sleep(3000);
        long endTime = System.currentTimeMillis();

        ApiResponse response = ApiResponse.builder()
                .status("OK")
                .message("Slow request processed")
                .processingTime(endTime - startTime)
                .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/error")
    public ResponseEntity<ApiResponse> error() {
        throw new RuntimeException("Intentional error for testing purposes");
    }
}
