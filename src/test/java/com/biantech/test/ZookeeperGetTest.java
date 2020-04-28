package biantech.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperGetTest extends ZookeeperBaseTest{


    @Test
    public void get1() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        zooKeeper.getData("/set",false,stat);
        logger.info(stat.getVersion()+"");
        logger.info(stat.toString());
    }

    @Test
    public void get2() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        zooKeeper.getData("/set", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                //0 代表读取成功
                logger.info(rc+"");
                //path 路径
                logger.info(path);
                //ctx
                logger.info(ctx.toString());

            }
        },stat);
        //logger.info(stat.getVersion()+"");
        //logger.info(stat.toString());
        Thread.sleep(1000);
    }
}
