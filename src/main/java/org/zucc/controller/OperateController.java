package org.zucc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zucc.entity.Operate;
import org.zucc.service.OperateService;

@Controller
@RequestMapping("/operate")
@RequiredArgsConstructor
public class OperateController {
    private final OperateService operateService;

    @GetMapping("/{systemName}")
    @ResponseBody
    public Operate get(@PathVariable String systemName) {
        return operateService.queryStatus(systemName);
    }

    @GetMapping("/{systemName}/{activate}/{status}")
    @ResponseBody
    public String set(@PathVariable("systemName") String systemName,
                      @PathVariable("activate") String activate,
                      @PathVariable("status") String status) {
        operateService.update(systemName,activate,status);
        return "switch";
    }

}
