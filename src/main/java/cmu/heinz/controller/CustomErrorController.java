package cmu.heinz.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mouwu Lin
 * @AndrewID mouwul
 */
@RestController
public class CustomErrorController implements ErrorController{

    @Override
    public String getErrorPath() {
        return "error";
    }
}
