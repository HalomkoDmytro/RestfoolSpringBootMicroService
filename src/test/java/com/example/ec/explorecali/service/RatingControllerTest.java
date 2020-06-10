package com.example.ec.explorecali.service;

import com.example.ec.controller.RatingDto;
import com.example.ec.domain.Tour;
import com.example.ec.domain.TourRating;
import com.example.ec.service.TourRatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

/**
 * Invoke the Controller methods via HTTP.
 * Do not invoke the tourRatingService methods, use Mock instead
 */
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class RatingControllerTest {
    private static final String RATINGS_URL = "/ratings";

    //These Tour and rating id's do not already exist in the db
    private static final int TOUR_ID = 999;
    private static final int RATING_ID = 555;
    private static final int CUSTOMER_ID = 1000;
    private static final int SCORE = 3;
    private static final String COMMENT = "comment";

    @MockBean
    private TourRatingService tourRatingServiceMock;

    @Mock
    private TourRating tourRatingMock;

    @Mock
    private Tour tourMock;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeEach
    public void setupReturnValuesOfMockMethods() {
        lenient().when(tourRatingMock.getTour()).thenReturn(tourMock);
        lenient().when(tourMock.getId()).thenReturn(TOUR_ID);
        lenient().when(tourRatingMock.getComment()).thenReturn(COMMENT);
        lenient().when(tourRatingMock.getScore()).thenReturn(SCORE);
        lenient().when(tourRatingMock.getCustomerId()).thenReturn(CUSTOMER_ID);
    }


    /**
     * HTTP GET /ratings
     */
    @Test
    public void getRatings() {
        when(tourRatingServiceMock.lookupAll()).thenReturn(Arrays.asList(tourRatingMock, tourRatingMock, tourRatingMock));

        ResponseEntity<List<RatingDto>> response = restTemplate.exchange(RATINGS_URL, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<RatingDto>>() {
                });

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().size(), is(3));
    }

    /**
     * HTTP GET /ratings/{id}
     */
    @Test
    public void getOne() {

        when(tourRatingServiceMock.lookupRatingById(RATING_ID)).thenReturn(Optional.of(tourRatingMock));

        ResponseEntity<RatingDto> response =
                restTemplate.getForEntity(RATINGS_URL + "/" + RATING_ID, RatingDto.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().getCustomerId(), is(CUSTOMER_ID));
        assertThat(response.getBody().getComment(), is(COMMENT));
        assertThat(response.getBody().getScore(), is(SCORE));
    }
}
