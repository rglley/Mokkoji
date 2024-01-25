package online.mokkoji.api.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class HealthCheckController {

    @GetMapping("/healthcheck")
    public ResponseEntity<?> ping(){
        JSONObject obj = new JSONObject();
        obj.put("health","healthy");
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }
}
