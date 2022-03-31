package com.example.demo.src.event;


import com.example.demo.src.event.model.GetEventRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventProvider {

    private final EventDao eventDao;


    public List<GetEventRes> getEvents()  {

        return eventDao.getEvents();

    }

    public GetEventRes getEvent(int eventIdx)  {

        return eventDao.getEvent(eventIdx);
        }


}
