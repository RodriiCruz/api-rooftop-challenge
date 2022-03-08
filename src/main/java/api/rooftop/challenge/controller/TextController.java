package api.rooftop.challenge.controller;

import api.rooftop.challenge.dto.RequestDTO;
import api.rooftop.challenge.dto.ResponseDTO;
import api.rooftop.challenge.dto.TextDTO;
import api.rooftop.challenge.service.TextServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rodrigo Cruz <rodriikc@gmail.com>
 */
@RestController
@RequestMapping("/text")
public class TextController {

    @Autowired
    private TextServiceImpl service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody RequestDTO request) throws Exception {
        ResponseDTO response = service.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        TextDTO response = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
