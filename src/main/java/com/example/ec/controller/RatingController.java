package com.example.ec.controller;

import com.example.ec.domain.TourRating;
import com.example.ec.service.TourRatingService;
import com.example.ec.web.RatingAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/ratings")
public class RatingController {
    private TourRatingService tourRatingService;
    private RatingAssembler assembler;

    @Autowired
    public RatingController(TourRatingService tourRatingService, RatingAssembler ratingAssembler) {
        this.tourRatingService = tourRatingService;
        this.assembler = ratingAssembler;
    }

    @GetMapping
    public List<RatingDto> getAll() {
        return assembler.toResources(tourRatingService.lookupAll());
    }

    @GetMapping("/{id}")
    public RatingDto getRating(@PathVariable("id") Integer id) {
        return assembler.toResource(tourRatingService.lookupRatingsById(id).orElseThrow(
                () -> new NoSuchElementException("Rating " + id + " not found."))
        );
    }

}
