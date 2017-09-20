package com.epam.test_generator.controllers;

import com.epam.test_generator.dto.StepSuggestionDTO;
import com.epam.test_generator.services.StepSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StepSuggestionController {

    @Autowired
    StepSuggestionService stepSuggestionService;

    @RequestMapping(value = "/step_suggestion", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<StepSuggestionDTO>> getAutoCompleteList() {
        return new ResponseEntity<>(stepSuggestionService.getAutoCompleteList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/step_suggestion", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> addAutoComplete(@RequestBody StepSuggestionDTO stepSuggestionDTO) {
        if (contentIsValid(stepSuggestionDTO)) {
            stepSuggestionService.addAutoComplete(stepSuggestionDTO);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @RequestMapping(value = "/step_suggestion/{stepSuggestionId}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Void> removeAutoComplete(@PathVariable("stepSuggestionId") long stepSuggestionId) {
        stepSuggestionService.removeAutoComplete(stepSuggestionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private boolean contentIsValid(StepSuggestionDTO stepSuggestionDTO) {
        return stepSuggestionDTO.getContent() != null && stepSuggestionDTO.getContent().length() > 2;
    }
}
