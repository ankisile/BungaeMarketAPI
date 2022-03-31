package com.example.demo.src.event.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GetEventRes {
    @NotNull
    private Integer eventIdx;
    @NotNull
    private String eventName;
    @NotNull
    private String eventImageUrl;
    @NotNull
    private String eventText;
    @NotNull
    private Date startDate;
    @NotNull
    private Date endDate;

}
