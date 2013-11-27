package org.skyrails.test;

import org.skyrails.client.NotConnectedException;
import org.skyrails.client.SkyrailsClient;
import org.skyrails.client.handle.IServerHandle;
import org.skyrails.client.operator.BulkOperator;

import java.io.IOException;

/**
 * @author activey
 * @date: 16.09.13 16:07
 */
public class TestApp {

    private SkyrailsClient client;

    public static void main(String[] args) throws Exception {
        TestApp app = new TestApp();
        app.start();

        for (int i = 0; i < 10; i++) {
            app.test();
        }
        app.stop();

    }

    private void test() throws Exception {
        client.doOnServer(new BulkOperator(15) {
            @Override
            public void doOnServer(IServerHandle serverHandle) {
                String root = serverHandle.createNode("ROOT", "textures/num1.gif");

                for (int i = 0; i < 150; i++) {
                    String node = serverHandle.createNode("node" + i, "textures/computer.gif");
                    serverHandle.createEdge(root, node, "HAS");
                }
            }
        });
    }


    private void start() throws Exception {
        //this.client = new SkyrailsClient("10.57.16.232", 9999);
        this.client = new SkyrailsClient("localhost", 9999);
        client.connect();

        //client.disconnect();
    }

    private void stop() throws NotConnectedException, IOException {
        client.disconnect();
    }
}
