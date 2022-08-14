package com.dssc.pcube3.entityservice.wrapper;

import com.dssc.pcube3.entityservice.dataVO.EventVO;
import com.dssc.pcube3.entityservice.entity.Event;
import org.springframework.stereotype.Service;

@Service
public class EventVOWrapper {

    public EventVO wrap(Event event) {
        EventVO eventVO = new EventVO();
        eventVO.setEid(event.getEid());
        eventVO.setName(event.getName());
        eventVO.setCrawlingTime(event.getTimestamp());
        eventVO.setAbstractText(event.getAbstractText());
        eventVO.setRelatedImgList(event.getRelatedImgList());
        eventVO.setTimeStamp(event.getEid().substring(0,10));
        return eventVO;
    }
}
