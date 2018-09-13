package com.hyout.dao;

import com.hyout.pojo.LogEvent;

import java.util.List;

public interface LogEventMapper {
    int deleteByPrimaryKey(Long eventId);

    int insert(LogEvent record);

    int insertSelective(LogEvent record);

    LogEvent selectByPrimaryKey(Long eventId);

    int updateByPrimaryKeySelective(LogEvent record);

    int updateByPrimaryKeyWithBLOBs(LogEvent record);

    int updateByPrimaryKey(LogEvent record);

    List<LogEvent> selectByMerchantOrderNo(String merchantOrderNo);
}