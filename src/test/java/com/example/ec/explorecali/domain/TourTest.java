package com.example.ec.explorecali.domain;

import com.example.ec.domain.Difficulty;
import com.example.ec.domain.Region;
import com.example.ec.domain.Tour;
import com.example.ec.domain.TourPackage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TourTest {
    @Test
    public void testConstructorAndGetters() throws Exception {
        TourPackage p = new TourPackage("CC","name");
        Tour tour = new Tour("title","description","blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.Difficult, Region.Central_Coast);
        assertNull(tour.getId());
        assertEquals(tour.getTitle(), "title");
        assertEquals(tour.getDescription(), "description");
        assertEquals(tour.getBlurb(), "blurb");
        assertTrue(tour.getPrice() == 50);
        assertEquals(tour.getDuration(), "1 day");
        assertEquals(tour.getBullets(), "bullet");
        assertEquals(tour.getKeywords(), "keywords");
        assertEquals(tour.getTourPackage().getCode(), "CC");
        assertEquals(tour.getDifficulty(), Difficulty.Difficult);
        assertEquals(tour.getRegion(), Region.Central_Coast);

    }

    @Test
    public void equalsHashcodeVerify() {
        TourPackage p = new TourPackage("CC","name");
        Tour tour1 = new Tour("title","description","blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.Difficult, Region.Central_Coast);
        Tour tour2 = new Tour("title","description","blurb", 50, "1 day", "bullet",
                "keywords", p, Difficulty.Difficult, Region.Central_Coast);

        assertEquals(tour1, tour2);
        assertEquals(tour1.hashCode(), tour2.hashCode());
    }

}
