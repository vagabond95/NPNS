package com.example.npns_demo.thrift;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Created by JHKim on 2017-11-22.
 */

public class HelloServer {

    private void start() {
        try {
            HelloService.Processor processor = new HelloService.Processor(new HelloServiceAPI());

            TThreadPoolServer.Args serverArgs = new TThreadPoolServer.Args(new TServerSocket(7911));
            serverArgs.protocolFactory(new TCompactProtocol.Factory());
            serverArgs.transportFactory(new TFastFramedTransport.Factory());
            serverArgs.minWorkerThreads(20);
            serverArgs.maxWorkerThreads(1500);
            serverArgs.processorFactory(new TProcessorFactory(processor));

            TServer server = new TThreadPoolServer(serverArgs);
            System.out.println("Starting server on port 7911 ...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
