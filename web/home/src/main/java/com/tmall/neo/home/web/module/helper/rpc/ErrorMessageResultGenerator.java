package com.tmall.neo.home.web.module.helper.rpc;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.citrus.extension.rpc.RPCRequestContext;
import com.alibaba.citrus.extension.rpc.RPCResultGenerator;
import com.alibaba.citrus.extension.rpc.validation.ErrorContext;
import com.alibaba.citrus.extension.rpc.validation.ErrorItem;

/**
 * rpc�����쳣����
 */
public class ErrorMessageResultGenerator implements RPCResultGenerator {

    private static final Logger              logger    = LoggerFactory.getLogger(ErrorMessageResultGenerator.class);
    /**
     * code<br/>
     * 500: RPC�����쳣<br/>
     * 404: û��ƥ���RPC����<br/>
     * rpc_system_exception: RPC����쳣<br/>
     * rpc_invalid_arg: ���ݰ��쳣<br/>
     * null: ������֤�쳣
     */
    private static final Map<String, String> ERROR_MAP = new HashMap<String, String>(8);

    static {
        ERROR_MAP.put("rpc_invalid_arg", "�Ƿ�����");
        ERROR_MAP.put("rpc_system_exception", "RPCϵͳ�쳣");
        ERROR_MAP.put("500", "ϵͳ�쳣");
        ERROR_MAP.put("404", "û�ж�Ӧ�ķ���");
    }

    @Override
    public Object generate(Object result, RPCRequestContext requestContext, ErrorContext errorContext) {
        if (result != null || !errorContext.hasError()) {
            return result;
        }

        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("result", false);
        Iterator<ErrorItem> it = errorContext.getErrrors();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            ErrorItem item = it.next();
            if (item.getCode() == null) {
                sb.append(item.getMsg());
            } else if (ERROR_MAP.containsKey(item.getCode())) {
                sb.append(ERROR_MAP.get(item.getCode()));
                if (logger.isWarnEnabled()) {
                    logger.warn(new StringBuilder("RPC Errors, code:").append(item.getCode()).append(", field:").append(item.getField()).append(", msg:").append(item.getMsg()).toString());
                }
            }
            sb.append(";");
        }
        sb.deleteCharAt(sb.lastIndexOf("��"));
        obj.put("message", sb.toString());
        return obj;
    }
}
