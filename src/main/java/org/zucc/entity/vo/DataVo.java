package org.zucc.entity.vo;

import lombok.Data;
import org.zucc.dao.NumberOfPeopleDao;
import org.zucc.entity.Deploy;
import org.zucc.entity.NumberOfPeople;

import java.util.List;

@Data
public class DataVo {
    private List<NumberOfPeople> numberOfPeopleList;
    private List<Deploy> deployList;

    private String systemTime;
}
