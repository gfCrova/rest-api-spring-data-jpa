package com.demo.obspringrestfuldatajpa.Entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "books",
        indexes = {
                @Index(name = "idx_author", columnList = "author"),
                @Index(name = "idx_title", columnList = "title")
        }
)
@Schema(description = "Book Entity")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the book", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Title of the book", example = "Clean Code")
    private String title;

    @Schema(description = "Author of the book", example = "Robert C. Martin")
    private String author;

    @Schema(description = "Number of pages", example = "460")
    private Integer pages;

    @Schema(description = "Book price", example = "240.50")
    private Double price;

    @Schema(description = "Book release date", example = "2008-07-19")
    private LocalDate releaseDate;

    @Schema(description = "It is available online", example = "true")
    private Boolean online;

    public Book() {}

    public Book(String author, Boolean online, LocalDate releaseDate, Integer pages, Double price, String title) {
        this.author = author;
        this.online = online;
        this.releaseDate = releaseDate;
        this.pages = pages;
        this.price = price;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return  "\n" + getId() + "= [ title= " + title + "\n"
                + "author= " + author + "\n"
                + "pages= " + pages +  "\n"
                + "price= " + price +  "\n"
                + "releaseDate= " + releaseDate + "\n"
                + "online= " + online +" ] \n";
    }
}
