package com.bravo.stream.test;

import lombok.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangxinyu
 * @since 2022/5/30 19:14
 */
public class ReduceComplexApplyTest {
    @Test
    public void test() {

        User john = User.builder().username("John").age(30).build();
        john.getRating().add(new Review(5, ""));
        john.getRating().add(new Review(3, "not bad"));

        User julie = User.builder().username("John").age(35).build();
        john.getRating().add(new Review(4, "great!"));
        john.getRating().add(new Review(2, "terrible experience"));
        john.getRating().add(new Review(4, ""));

        List<User> users = Arrays.asList(john, julie);
        // 处理评分
        Rating averageRating = users.stream()
                .reduce(new Rating(),
                        (rating, user) -> Rating.average(rating, user.getRating()),
                        Rating::average);
    }

}

@Builder
@Setter@Getter
class User {
    private String username;
    private int age;
    private Rating rating;
}

@Data
@AllArgsConstructor
class Review {

    private int points;
    private String review;

    // constructor, getters and setters
}

class Rating {

    double points;
    List<Review> reviews = new ArrayList<>();

    public void add(Review review) {
        reviews.add(review);
        computeRating();
    }

    private double computeRating() {
        double totalPoints =
                reviews.stream().map(Review::getPoints).reduce(0, Integer::sum);
        this.points = totalPoints / reviews.size();
        return this.points;
    }

    public static Rating average(Rating r1, Rating r2) {
        Rating combined = new Rating();
        combined.reviews = new ArrayList<>(r1.reviews);
        combined.reviews.addAll(r2.reviews);
        combined.computeRating();
        return combined;
    }

}

