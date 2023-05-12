package org.zucc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zucc.entity.Issue;

public interface IssueService extends IService<Issue> {
    Issue getIssue(String role);

    int getCount(String systemName, String roleTopic);
}
