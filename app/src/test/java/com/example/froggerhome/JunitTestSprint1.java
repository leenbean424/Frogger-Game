package com.example.froggerhome;

import org.junit.Test;
import static org.junit.Assert.*;

public class JunitTestSprint1 {

    @Test
    public void whitespaceOnlyTest() {
        assertTrue(InitialConfig.getPlayerName(), true);
    }

}
