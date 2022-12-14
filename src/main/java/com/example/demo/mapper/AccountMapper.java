package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
