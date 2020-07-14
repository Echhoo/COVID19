package com.test.thrift;

import group.corona.thrift.spider.SpiderService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用来调用爬虫服务的Server Client
 * @author balance
 */
@Component
public class ServerProvider {

    @Value("${thrift.spider.ip}")
    private String spiderServiceIp;
    @Value("${thrift.spider.port}")
    private int spiderServicePort;

    private enum ServerType{
        // 爬虫thrift服务
        SPIDER
    }

    private TServiceClient getService(String address, int port, ServerType serverType){
        TSocket socket = new TSocket(address, port);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        // 判断server的类型
        TServiceClient result = null;
        switch (serverType){
            case SPIDER:
                result = new SpiderService.Client(protocol);
                break;
        }
        return result;
    }

    public SpiderService.Client getSpiderService(){
        return (SpiderService.Client)getService(spiderServiceIp, spiderServicePort, ServerType.SPIDER);
    }

}
