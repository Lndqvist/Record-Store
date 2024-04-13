package se.iths.java23.petterlundqvist.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.java23.petterlundqvist.webshop.model.Category;
import se.iths.java23.petterlundqvist.webshop.model.Record;
import se.iths.java23.petterlundqvist.webshop.repository.RecordRepository;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    RecordRepository recordRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CartService cartService;

    public List<Record> getRecordsByCategory(String name) {
        return recordRepository.findByCategory(categoryService.getCategory(name));
    }

    public Record getRecordById(int id) {
        return recordRepository.getReferenceById(id);
    }

    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    public List<Record> getRecordsContaining(String s) {
        return recordRepository.findByNameContainingOrArtistContaining(s, s);
    }

    public List<Category> getCategories(){
       return categoryService.getCategories();
    }

    public void addToCart(int id, int amount) {cartService.addToCart(getRecordById(id), amount);}

    public boolean addRecord(String name, String artist, int price, String categoryName) {
        Category category = categoryService.getCategory(categoryName);
        if(recordRepository.existsByNameAndArtist(name, artist)) {
            return false;
        } else {
            recordRepository.save(new Record(name, artist, price, category));
            return true;
        }
    }
}
