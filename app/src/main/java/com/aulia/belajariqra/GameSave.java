package com.aulia.belajariqra;

import java.util.HashSet;

public class GameSave {
    public HashSet<Integer> learningProgress1;
    public HashSet<Integer> learningProgress2;
    public HashSet<Integer> learningProgress3;

    public int age;

    public GameSave() {
        learningProgress1 = new HashSet<>();
        learningProgress2 = new HashSet<>();
        learningProgress3 = new HashSet<>();
    }
}
