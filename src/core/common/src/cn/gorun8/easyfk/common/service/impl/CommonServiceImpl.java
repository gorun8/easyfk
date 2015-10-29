package cn.gorun8.easyfk.common.service.impl;

import cn.gorun8.easyfk.common.service.CommonService;
import org.springframework.stereotype.Service;

/**
 * Created by hezhiping(110476592@qq.com) on 15-10-6.
 */
@Service("commonServiceImpl")
public class CommonServiceImpl implements CommonService {
    @Override
    public String getName() {
        return "hzp";
    }
}
