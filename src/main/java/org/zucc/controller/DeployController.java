package org.zucc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zucc.entity.vo.DeployVo;
import org.zucc.service.DeployService;

@Controller
@RequiredArgsConstructor
public class DeployController {
    private final DeployService deployService;
    @GetMapping ("/directives/deploy")
    @ResponseBody
    public void directivesDeploy(DeployVo deployVo) {
        deployService.directivesDeploy(deployVo);
    }
}
