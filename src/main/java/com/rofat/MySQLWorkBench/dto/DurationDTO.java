package com.rofat.MySQLWorkBench.dto;

import com.rofat.MySQLWorkBench.model.DurationEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DurationDTO {
    private String start;
    private String end;
    DurationDTO(DurationEntity durationEntity){
        this.start = durationEntity.getStart();
        this.end = durationEntity.getEnd();
    }
}
