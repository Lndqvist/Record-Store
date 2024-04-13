package se.iths.java23.petterlundqvist.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import se.iths.java23.petterlundqvist.webshop.service.RecordService;
import se.iths.java23.petterlundqvist.webshop.model.Record;

@Controller
public class RecordController {

    @Autowired
    RecordService recordService;

    @GetMapping("/home")
    public String viewHome(Model m) {
        m.addAttribute("category", recordService.getCategories());
        m.addAttribute("records", recordService.getAllRecords());
        return "home-page";
    }

    @GetMapping("/category/{name}")
    public String viewRecordsInCategory(Model m, @PathVariable("name") String name) {
        m.addAttribute("records", recordService.getRecordsByCategory(name));
        m.addAttribute("category", name);
        return "category-page";
    }
    @GetMapping("/records/{id}")
    public String viewRecord(Model m, @PathVariable("id") int id) {
        m.addAttribute("record", recordService.getRecordById(id));
        m.addAttribute("confirmation", "");
        return "record-page";
    }
    @PostMapping("/search")
    public String viewSearch(Model m, @RequestParam("search") String search) {
        m.addAttribute("records", recordService.getRecordsContaining(search));
        return "search-page";
    }
    @PostMapping("/records/{id}")
    public String addToCart(@PathVariable("id") int id, @RequestParam("amount") int amount,
                              Model m){
        recordService.addToCart(id, amount);
        m.addAttribute("record", recordService.getRecordById(id));
        m.addAttribute("confirmation", "The record has been added to the cart");

        return "record-page";
    }

    @GetMapping("/add-record")
    public String newRecord(Model m) {
        m.addAttribute("categories", recordService.getCategories());
        m.addAttribute("message", "");
        return "add-record-page";
    }
    @PostMapping("/add-record")
    public String addRecord(Model m,
                            @RequestParam("name") String name,
                            @RequestParam("artist") String artist,
                            @RequestParam("price") int price,
                            @RequestParam("category") String category) {
        if(recordService.addRecord(name, artist, price, category)) {
            m.addAttribute("message", "The record has been added");
        } else {
            m.addAttribute("message", "The record already exists");
        }
        m.addAttribute("categories", recordService.getCategories());
        return "add-record-page";
    }
}