package org.zucc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author Administrator
 */
public class Constant {
    public static final String SUBWAY_IN = "subwayIn";
    public static final String SUBWAY_OUT = "subwayOut";
    public static final String SUBWAY = "地铁";
    public static final String BUS = "公交";
    public static final String BUS_IN = "busIn";
    public static final String BUS_OUT = "busOut";
    public static final String LONGXIANGLI = "龙翔里";
    public static final String REAL_TIME = "realTime";
    public static final String DEPLOY = "deploy";
    public static final String SYSTEM_NORM = "示例系统";
    public static final int STATUS_ONLINE = 1;
    public static final int STATUS_OFFLINE = 0;
    public static final int TIME_INCR = 1;
    public static final int TRANSFER_RATE = 50;
    public static final int NUMBER_MIN = 600;
    public static final int TIME_LIMIT = 1640973600;
    public static final int TIME_INIT = 1640944800;
    public static final List<Integer> INIT_REAL_TIME = new ArrayList<>(Arrays.asList(8000,8120,8020,7950,8020,8350,8680,9860,10060,12600,13500,15960,18300,21300,21200,21020,20300,21300));
    public static final List<Integer> INIT_SUBWAY_IN = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,100,450,580,860,900,1020,1520,2030,1460,1620,1560,2430));
    public static final List<Integer> INIT_SUBWAY_OUT = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,120,1000,2560,3560,3020,2620,2660,2330,1860,2060,2360,3450));
}
