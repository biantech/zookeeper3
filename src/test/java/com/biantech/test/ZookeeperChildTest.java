package biantech.test;

import org.apache.zookeeper.KeeperException;
import org.junit.Test;

import java.util.List;

public class ZookeeperChildTest extends ZookeeperBaseTest {

    @Test
    public void getChildren1() throws KeeperException, InterruptedException {
        List<String> list= zooKeeper.getChildren("/get",false);
        logger.info(list.toString());
    }
}
