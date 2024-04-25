package com.buygo.demo;


import com.buygo.demo.entity.TrainingCenter;
import com.buygo.demo.exceptions.InvalidRequestBodyException;
import com.buygo.demo.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableMongoAuditing
public class Controller {



    @Autowired
    Service service ;


    @PostMapping("/trainingCenter")
    TrainingCenter createTrainingCenter(@RequestBody @Validated TrainingCenter trainingCenter, BindingResult bindingResult) throws InvalidRequestBodyException {
        if (bindingResult.hasErrors()) {

            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                if (!errorMessage.isEmpty()) {
                    errorMessage.append(", "); // Append comma if not the first error message
                }
                errorMessage.append(error.getDefaultMessage());
            });

            throw new InvalidRequestBodyException(errorMessage.toString());
        }
        return service.createTrainingCenter(trainingCenter);
    }


    @GetMapping("/trainingCenter")
    List<TrainingCenter> getAllTrainingCenter()
    {
        return service.getAllTrainingCenter();
    }

}
