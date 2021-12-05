package com.example.eksamen3semester.controller;

import com.example.eksamen3semester.model.BridgeStatus;
import com.example.eksamen3semester.repository.BridgeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("/bridge")
public class BridgeController implements PropertyChangeListener {

    private String status;
    private BridgeRepository bridgeRepository;


    public BridgeController(BridgeRepository bridgeRepository) {
        this.bridgeRepository = bridgeRepository;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.setStatus((String) evt.getNewValue());
    }

    @GetMapping()
    public ResponseEntity<List<BridgeStatus>> status() {
        List<BridgeStatus> status = bridgeRepository.findAll();

        return ResponseEntity.ok(status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BridgeStatus> changeStatus(@RequestBody BridgeStatus bridgeStatus, @PathVariable ("id") Long id) {
        if (bridgeRepository.findById(id).isPresent()) {
            bridgeStatus.setBridge_id(id);
            return ResponseEntity.ok(bridgeRepository.save(bridgeStatus));
        } else {
            return ResponseEntity.notFound().build();
        }
    }













    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
