package io.nebulas.explorer.service.blockchain;

import io.nebulas.explorer.domain.NebEvent;
import io.nebulas.explorer.domain.NebEventCondition;
import io.nebulas.explorer.mapper.NebEventMapper;
import io.nebulas.explorer.service.thirdpart.nebulas.NebApiServiceWrapper;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.Event;
import io.nebulas.explorer.service.thirdpart.nebulas.bean.GetEventsByHashResponse;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Service
public class NebEventService {

    private final NebEventMapper nebEventMapper;

    private final NebApiServiceWrapper nebApiServiceWrapper;

    /**
     * find events data by the transaction hash
     * @param hash: transaction hash
     * @return EventList
     */
    public List<Event> findEventListByHash(String hash){

        if(StringUtils.isEmpty(hash)){
            return Collections.emptyList();
        }

        List<Event> eventList = nebEventMapper.selectByHash(hash);
        if (eventList.size() == 0){
            //再去rpc上查询一次，依旧没有的话返回空数组
            GetEventsByHashResponse response = nebApiServiceWrapper.getEventsByHash(hash);
            eventList = response.getEvents();
            if(eventList.size() == 0){
                return Collections.emptyList();
            }
        }
        return eventList;
    }

}
