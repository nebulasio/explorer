package io.nebulas.explorer.service;

import com.google.common.collect.Lists;
import io.nebulas.explorer.domain.NebDynasty;
import io.nebulas.explorer.mapper.NebDynastyMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Nebulas dynasty related operation service
 *
 * @author nathan wang
 * @version 1.0
 * @since 2018-02-03
 */
@AllArgsConstructor
@Service
public class NebDynastyService {
    private final NebDynastyMapper nebDynastyMapper;

    /**
     * batch save dynasty information
     *
     * @param dynasties NebDynasty list
     * @return effect rows number
     */
    public Integer batchAddNebDynasty(List<NebDynasty> dynasties) {
        if (CollectionUtils.isEmpty(dynasties)) {
            return 0;
        }
        return nebDynastyMapper.batchAdd(dynasties);
    }

    /**
     * according to block height query dynasty
     *
     * @param blockHeight block height
     * @return dynasty list
     */
    public List<NebDynasty> findDynastyByBlockHeight(long blockHeight) {
        return nebDynastyMapper.findByBlockHeight(blockHeight);
    }

    /**
     * according to block height query dynasty delegate
     *
     * @param blockHeight block height
     * @return dynasty delegate list
     */
    public List<String> findDynastyDelegateByBlockHeight(long blockHeight) {
        List<NebDynasty> dynastyList = findDynastyByBlockHeight(blockHeight);
        List<String> delegateList = Lists.newLinkedList();
        dynastyList.forEach(d -> delegateList.add(d.getDelegate()));
        return delegateList;
    }


}
