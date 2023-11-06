package main.java.com.example.companycalculations.companycalculations.controller;

import com.iftm.consumer.models.dto.MasterMessage;
import com.iftm.consumer.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CompanyCalculationController {

    @Autowired
    private ConsumerService service;

    @GetMapping("/pull")
    public ResponseEntity<MasterMessage> getMasterMessage() {
        return service.getMasterMessage();
    }

    @GetMapping("/funfando")
    public ResponseEntity<String> getFunfando() {
        return ResponseEntity.ok().body("Tá funfando tranquilamente!");
    }
}
