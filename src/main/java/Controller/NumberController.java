package Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.NumberResponse;
import Service.NumberService;

@RestController
@RequestMapping("/numbers")
public class NumberController{

    private NumberService numberService;

    
    @Autowired
    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }



    @GetMapping("/{numberId}")
    public NumberResponse AverageCalculator(@PathVariable String numberId){
        return numberService.fetchNumber(numberId);
    }
}