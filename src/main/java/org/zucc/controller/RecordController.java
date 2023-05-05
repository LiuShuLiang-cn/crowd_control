package org.zucc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zucc.service.RecordService;

@RestController
@RequestMapping("/record")
public class RecordController {
    private RecordService recordService;

    @Autowired
    public void setRecordService(RecordService recordService) {
        this.recordService = recordService;
    }

    @GetMapping("/count")
    public int logCounts(@RequestParam("systemName") String systemName,
                         @RequestParam("userName") String userName) {
        return recordService.logCounts(systemName, userName);
    }
}
