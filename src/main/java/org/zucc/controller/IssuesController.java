package org.zucc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
}
