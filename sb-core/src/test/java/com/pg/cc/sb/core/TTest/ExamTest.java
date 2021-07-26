package com.pg.cc.sb.core.TTest;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExamTest {

    private Exam examUnderTest;

    @Before
    public void setUp() {
        examUnderTest = new Exam();
    }

    @Test
    public void testCanViewAnswer() {
        // Setup

        // Run the test
        final boolean result = examUnderTest.canViewAnswer("viewAnswer");

        // Verify the results
        assertThat(result).isTrue();
    }
}
