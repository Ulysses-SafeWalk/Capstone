package com.codeup.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {
    @GetMapping("/add/{x}/and/{y}")
    public @ResponseBody String addition(
        @PathVariable int x,
        @PathVariable int y
    ) {
        return String.format("%d + %d = %d", x, y, x + y);
    }

    @GetMapping("/subtract/{y}/from/{x}")
    public @ResponseBody String subtraction(
        @PathVariable int x,
        @PathVariable int y
    ) {
        return String.format("%d - %d = %d", x, y, x - y);
    }

    @GetMapping("/multiply/{x}/and/{y}")
    public @ResponseBody String multiplication(
        @PathVariable int x,
        @PathVariable int y
    ) {
        return String.format("%d x %d = %d", x, y, x * y);
    }

    @GetMapping("/divide/{x}/by/{y}")
    public @ResponseBody String division(
        @PathVariable int x,
        @PathVariable int y
    ) {
        return String.format("%d / %d = %d", x, y, x / y);
    }
}
