package cn.ichensw.jayapiadmin.service.impl.inner;

import cn.ichensw.jayapiadmin.service.InterfaceInfoService;
import cn.ichensw.jayapicommon.model.entity.InterfaceInfo;
import cn.ichensw.jayapicommon.service.InnerInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
* @author jay
*/
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Override
    public InterfaceInfo getInterfaceInfo(String path, String method) {
        return interfaceInfoService.query()
                .eq("url", path)
                .eq("method", method)
                .one();
    }
}




