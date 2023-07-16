package org.zucc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zucc.entity.Issue;
import org.zucc.service.IssueService;

import javax.annotation.Resource;

@Controller
public class IssuesController {
    @Resource
    private IssueService issueService;

    @RequestMapping("/issue/{role}")
    @ResponseBody
    public Issue getIssue(@PathVariable("role") String role) {
        return issueService.getIssue(role);
    }

    @GetMapping("/issue/count")
    @ResponseBody
    public int getIssueCount(@RequestParam("systemName") String systemName,
                             @RequestParam("roleTopic") String roleTopic) {
        return issueService.getCount(systemName, roleTopic);
    }
}
