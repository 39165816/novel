package com.tmall.neo.home.web.module.screen;

import com.alibaba.citrus.turbine.Context;

public class Checkpreload {
    
    public void execute(Context context) {
        context.put("result", "success");
    }
}
