package com.example.demo.src.chatting.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class GetChattingRoomRes {
    @NotNull
    private Integer shopIdx;
    @NotNull
    private Integer chatRoomIdx;
    @NotNull
    private String shopName;
    @NotNull
    private String recentMessageTime;
    @NotNull
    private String recentMessage;

}
