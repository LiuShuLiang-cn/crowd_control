package org.zucc.server;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zucc.entity.NumberOfPeople;
import org.zucc.entity.Operate;
import org.zucc.entity.Weight;
import org.zucc.service.OperateService;
import org.zucc.utils.CastClass;
import org.zucc.utils.Constant;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 区域间人流移动
 */
@Component
@Slf4j
public class PedestrianTask {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private OperateService operateService;

    @Scheduled(fixedDelay = 8 * 1000)
    public void interareaTask() {
        Set<String> keys = redisTemplate.keys("*" + "_NumberOfPeople");
        assert keys != null;
        if (keys.isEmpty()) {
            return;
        }
        for (String key : keys) {
            String[] parts = key.split("_");
            transferPedestrian(parts[0]);
        }
    }

    public void transferPedestrian(String systemName) {
        int count = 0;
        Object object = redisTemplate.opsForValue().get(systemName + "_NumberOfPeople");
        List<NumberOfPeople> numberOfPeopleList = CastClass.castList(object, NumberOfPeople.class);
        List<NumberOfPeople> res = new ArrayList<>();
        for (NumberOfPeople numberOfPeople : numberOfPeopleList) {
            int number = numberOfPeople.getNumber() / Constant.TRANSFER_RATE;
            /* 流入的人数 */
            numberOfPeople.setNumber(numberOfPeople.getNumber() + number);
            res.add(numberOfPeople);
            count += number;
        }
        redisTemplate.delete(systemName + "_NumberOfPeople");
        redisTemplate.opsForValue().set(systemName + "_NumberOfPeople", res);
        updateNumber(count, systemName, res);
    }

    /**
     * 根据比例转移人数
     *
     * @param num        转移人数
     * @param systemName 系统名称
     */
    private void updateNumber(int num, String systemName, List<NumberOfPeople> numberOfPeopleList) {
        List<Weight> categoryList = getWeightCategoryList();
        Operate operate = (Operate) redisTemplate.opsForValue().get(systemName + "_operate");
        if (operate == null) {
            QueryWrapper<Operate> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("systemName", systemName);
            operate = operateService.queryStatus(systemName);
            redisTemplate.opsForValue().set(systemName + "_operate",operate);
        }
        for (Weight weight : categoryList) {
            //检查音乐喷泉活动是否开启
            if (operate.getStatusActivity().equals("1") && ("学士里".equals(weight.getCategory()) || "仁和里".equals(weight.getCategory()))) {
                weight.setWeight(10);
                //log.info("{}:检测到音乐喷泉处于开启状态，修改{}的权重为{}", systemName, weight.getCategory(), weight.getWeight());
            }
            //检查志愿者，交警，公安，城管有多少人,修改权重
            //交通
            weight.setWeight(weight.getWeight() - getBusAndSub(systemName) / 4);

        }
        for (int i = 0; i < num; i++) {
            /**
             * 不知道这是干嘛的
             */
            getWeight(categoryList);
        }
        /*根据权重新分配人数*/
        for (Weight weight : categoryList) {
            numberOfPeopleList.forEach(numberOfPeople -> {
                if (weight.getCategory().equals(numberOfPeople.getRegion())) {
                    if (numberOfPeople.getNumber() - weight.getWeight() * 10 >= Constant.NUMBER_MIN) {
                        /* 出去的人数 */
                        numberOfPeople.setNumber(numberOfPeople.getNumber() - weight.getWeight() * 10);
                    }
                }
            });
        }
    }

    /**
     * @return 返回九个里的权重数据
     */
    private List<Weight> getWeightCategoryList() {
        List<Weight> categoryList = new ArrayList<>();
        Weight weightCategory1 = new Weight("钱塘里", 4, 0);
        Weight weightCategory2 = new Weight("长生里", 6, 0);
        Weight weightCategory3 = new Weight("劝业里", 2, 0);
        Weight weightCategory4 = new Weight("学士里", 2, 0);
        Weight weightCategory5 = new Weight("龙翔里", 4, 0);
        Weight weightCategory6 = new Weight("仁和里", 2, 0);
        Weight weightCategory7 = new Weight("东坡里", 6, 0);
        Weight weightCategory8 = new Weight("将军里", 8, 0);
        Weight weightCategory9 = new Weight("泗水里", 8, 0);
        categoryList.add(weightCategory1);
        categoryList.add(weightCategory2);
        categoryList.add(weightCategory3);
        categoryList.add(weightCategory4);
        categoryList.add(weightCategory5);
        categoryList.add(weightCategory6);
        categoryList.add(weightCategory7);
        categoryList.add(weightCategory8);
        categoryList.add(weightCategory9);
        return categoryList;
    }

    public static void getWeight(List<Weight> categorys) {
        int weightSum = 0;
        for (Weight wc : categorys) {
            weightSum += wc.getWeight();
        }

        if (weightSum <= 0) {
            log.error("Error: weightSum={}", weightSum);
        }
        Random random = new Random();
        int n = random.nextInt(weightSum); // n in [0, weightSum)
        int m = 0;
        for (Weight wc : categorys) {
            if (m <= n && n < m + wc.getWeight()) {
                int num = wc.getNum();
                num++;
                wc.setNum(num);
                break;
            }
            m += wc.getWeight();
        }
    }

    private int getBusAndSub(String systemName) {

        Operate operate = (Operate) redisTemplate.opsForValue().get(systemName + "_operate");
        if (operate == null) {
            QueryWrapper<Operate> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("systemName", systemName);
            operate = operateService.queryStatus(systemName);
            redisTemplate.opsForValue().set(systemName + "_operate",operate);
        }
        int s = 0;
        if (operate.getBusA().equals("1")) {
            s++;
        }
        if (operate.getBusB().equals("1")) {
            s++;
        }
        if (operate.getBusC().equals("1")) {
            s++;
        }
        if (operate.getBusD().equals("1")) {
            s++;
        }
        if (operate.getSubwayA().equals("1")) {
            s++;
        }
        if (operate.getSubwayB().equals("1")) {
            s++;
        }
        if (operate.getSubwayC().equals("1")) {
            s++;
        }
        if (operate.getSubwayD().equals("1")) {
            s++;
        }
        return s;
    }
}
