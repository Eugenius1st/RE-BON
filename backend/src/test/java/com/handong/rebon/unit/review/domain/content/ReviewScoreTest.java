package com.handong.rebon.unit.review.domain.content;

import com.handong.rebon.exception.review.ReviewLikeCountException;
import com.handong.rebon.exception.review.ReviewStarException;
import com.handong.rebon.review.domain.content.ReviewScore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReviewScoreTest {

    @Test
    @DisplayName("review star에는 음수가 들어갈 수 없다.")
    public void reviewStarNegativeException() {
        //given
        //when, then
        assertThatThrownBy(() -> new ReviewScore(-1, 5))
                .isInstanceOf(ReviewStarException.class);
    }

    @ParameterizedTest
    @ValueSource(doubles = {6,5.5})
    @DisplayName("review star는 5점을 넘을 수 없다.")
    public void reviewStarExcessFiveException(double star) {
        //given
        //when, then
        assertThatThrownBy(() -> new ReviewScore(star, 0))
                .isInstanceOf(ReviewStarException.class);
    }

    @Test
    @DisplayName("review likeCount에는 음수가 들어갈 수 없다.")
    public void reviewLikeCountNegativeException() {
        //given
        //when, then
        assertThatThrownBy(() -> new ReviewScore(0, -1))
                .isInstanceOf(ReviewLikeCountException.class);
    }
}