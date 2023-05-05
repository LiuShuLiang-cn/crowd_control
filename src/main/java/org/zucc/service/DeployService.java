package org.zucc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zucc.entity.Deploy;
import org.zucc.entity.vo.DeployVo;

public interface DeployService extends IService<Deploy> {
    void initDeploy(String systemName);

    void directivesDeploy(DeployVo deployVo);
}
