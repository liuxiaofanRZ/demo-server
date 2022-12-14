package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Platform;
import com.example.demo.mapper.PlatformMapper;
import com.example.demo.service.PlatformService;
import org.springframework.stereotype.Service;


@Service
public class PlatformServiceImpl extends ServiceImpl<PlatformMapper, Platform> implements PlatformService {
}
