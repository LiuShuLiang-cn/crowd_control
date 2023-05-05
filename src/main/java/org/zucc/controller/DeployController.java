package org.zucc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.zucc.entity.vo.DeployVo;
import org.zucc.service.DeployService;

import javax.annotation.Resource;

@Controller
public class DeployController {
    @Resource
    private DeployService deployService;
    @GetMapping ("/directives/deploy")
    public void directivesDeploy(DeployVo deployVo) {
        deployService.directivesDeploy(deployVo);
    }
}
