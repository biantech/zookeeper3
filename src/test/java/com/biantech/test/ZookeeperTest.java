package biantech.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperTest {

    private Logger logger = LoggerFactory.getLogger(ZookeeperTest.class);

    @Test
    public void testAndExample() throws IOException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //arg1 服务器的IP集端口
        //会话超时时间
        //监视器对象
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 1000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(event.getState()==Event.KeeperState.SyncConnected){
                    System.out.println("连接创建成功");
                    countDownLatch.countDown();
                }
            }
        });
        //等待连接对象
        countDownLatch.await();
        logger.info(zooKeeper.getSessionId()+"");
    }
}
