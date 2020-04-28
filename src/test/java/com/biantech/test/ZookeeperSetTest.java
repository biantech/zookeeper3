package biantech.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZookeeperSetTest extends ZookeeperBaseTest{


    @Test
    public void createDate1() throws KeeperException, InterruptedException {
        List<ACL> list = new ArrayList<>();
        ACL acl = new ACL();
        list.add(acl);
        zooKeeper.create("/set/node1","node1".getBytes(),ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void setData1() throws KeeperException, InterruptedException {
        //args1 节点路径
        //args2 修改数据
        //args3 数据版本号
        zooKeeper.setData("/set/node1","node1".getBytes(),1);
    }


}
