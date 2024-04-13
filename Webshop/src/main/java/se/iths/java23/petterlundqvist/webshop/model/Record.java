package se.iths.java23.petterlundqvist.webshop.model;

import jakarta.persistence.*;

@Entity
public class Record {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_record")
    private int idRecord;
    private String name;
    private String artist;
    private int price;
    @ManyToOne
    @JoinColumn(name = "category", referencedColumnName = "id_category")
    private Category category;

    public Record(String name, String artist, int price, Category category) {
        this.name = name;
        this.artist = artist;
        this.price = price;
        this.category = category;
    }

    public Record() {
    }

    public int getIdRecord() {
        return idRecord;
    }

    public void setIdItem(int idRecord) {
        this.idRecord = idRecord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
