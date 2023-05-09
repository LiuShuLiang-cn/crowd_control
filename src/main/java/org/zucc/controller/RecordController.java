package org.zucc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    @ResponseBody
    public int logCounts(@RequestParam("systemName") String systemName,
                         @RequestParam("userName") String userName) {
        return recordService.logCounts(systemName, userName);
    }
}
