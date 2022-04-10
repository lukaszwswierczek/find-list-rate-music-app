package pl.coderslab;

import org.springframework.stereotype.Controller;

import javax.validation.Validator;

@Controller
public class ValidationController {

    private final Validator validation;

    public ValidationController(Validator validation) {
        this.validation = validation;
    }
}
