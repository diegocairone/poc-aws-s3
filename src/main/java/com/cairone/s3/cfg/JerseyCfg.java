package com.cairone.s3.cfg;

import com.cairone.s3.ui.ctrl.BucketCtrl;
import com.cairone.s3.ui.ctrl.EchoCtrl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyCfg extends ResourceConfig {

    public JerseyCfg() {
        register(EchoCtrl.class);
        register(BucketCtrl.class);
    }
}
