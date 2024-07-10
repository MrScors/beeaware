package com.beeaware.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hive {
    private int id;
    private List<Integer> occupiedBoxes;
    private boolean isActive;

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("H");
        builder.append(id);
        builder.append(".B");
        occupiedBoxes.forEach(id -> {
            builder.append(" ").append(id);
        });
        return builder.toString();
    }
}