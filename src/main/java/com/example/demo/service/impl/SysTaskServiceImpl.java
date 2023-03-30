package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.SysTask;
import com.example.demo.mapper.SysTaskMapper;
import com.example.demo.service.SysTaskService;
import org.springframework.stereotype.Service;

@Service
public class SysTaskServiceImpl extends ServiceImpl<SysTaskMapper, SysTask> implements SysTaskService {
}
