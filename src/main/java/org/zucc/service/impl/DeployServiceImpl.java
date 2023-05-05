package org.zucc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.zucc.dao.DeployDao;
import org.zucc.entity.Deploy;
import org.zucc.entity.vo.DeployVo;
import org.zucc.service.DeployService;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class DeployServiceImpl extends ServiceImpl<DeployDao, Deploy> implements DeployService {
    @Resource
    private DeployDao deployDao;
    @Resource
    private RedisTemplate<String, List<Deploy>> redisTemplate;

    @Override
    public void initDeploy(String systemName) {
        /*
        初始化部署人数
         */
        double[][] a =
                {
                        {120.164273, 30.248522}, {120.166338, 30.249134},
                        {120.166327, 30.250394}, {120.162964, 30.250237},
                        {120.164208, 30.250316}, {120.158726, 30.258179},
                        {120.161741, 30.258193}, {120.163854, 30.258351},
                        {120.163951, 30.257021}, {120.162615, 30.256932},
                        {120.162894, 30.255561}, {120.162878, 30.253996},
                        {120.162968, 30.25275}
                };
        QueryWrapper<Deploy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sysName", systemName);
        List<Deploy> deploys = baseMapper.selectList(queryWrapper);
        if (deploys.isEmpty())
            for (int i = 0; i < 13; i++) {
                Deploy deploy = new Deploy();
                deploy.setZyz(10);
                deploy.setCg(0);
                deploy.setJj(0);
                deploy.setGa(0);
                deploy.setSysName(systemName);
                deploy.setCgLat(a[i][1]);//纬度
                deploy.setCgLng(a[i][0]);//经度
                deploy.setJjLat(a[i][1]);//纬度
                deploy.setJjLng(a[i][0]);//经度
                deploy.setGaLat(a[i][1]);//纬度
                deploy.setGaLng(a[i][0]);//经度
                deploy.setZyzLat(a[i][1]);//纬度
                deploy.setZyzLng(a[i][0]);//经度
                baseMapper.insert(deploy);
            }
    }

    @Override
    public void directivesDeploy(DeployVo deployVo) {
        /*
        部署人员
         */
        switch (deployVo.getRole()) {
            case "志愿者" -> deployDao.updateZyz(deployVo);
            case "城管" -> deployDao.updateCg(deployVo);
            case "交警" -> deployDao.updateJj(deployVo);
            case "公安" -> deployDao.updateGa(deployVo);
        }
        List<Deploy> deploys = deployDao.getNumBySys(deployVo.getSystemName());
        redisTemplate.opsForValue().set(deployVo.getSystemName() + "_Deploys", deploys);
        log.info("部署了" + deployVo.getNum() + "人");
    }

}
