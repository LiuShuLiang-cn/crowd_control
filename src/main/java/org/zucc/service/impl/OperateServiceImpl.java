package org.zucc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zucc.dao.OperateDao;
import org.zucc.entity.Operate;
import org.zucc.service.OperateService;
@Service
public class OperateServiceImpl extends ServiceImpl<OperateDao, Operate> implements OperateService {
    @Override
    public void initOperate(String systemName) {
        Operate operate = new Operate();
        operate.setSystemName(systemName);
        baseMapper.insert(operate);
    }

    @Override
    public void update(String systemName, String activate,String status) {
//        baseMapper.updateStatus(systemName, activate);
        QueryWrapper<Operate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("systemName", systemName);
        UpdateWrapper<Operate> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("systemName",systemName).set(activate, status);
        baseMapper.update(null, updateWrapper);
    }

    @Override
    public Operate queryStatus(String systemName) {
        QueryWrapper<Operate> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("systemName", systemName);
        return baseMapper.selectOne(queryWrapper);
    }
}
