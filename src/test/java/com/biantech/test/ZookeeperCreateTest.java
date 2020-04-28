package biantech.test;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.junit.Test;

import java.util.ArrayList;

public class ZookeeperCreateTest extends ZookeeperBaseTest{

    @Test
    public void createNode1() throws KeeperException, InterruptedException {
        //args1 create path
        //
        zooKeeper.create("/create/node1","node1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    @Test
    public void createNode2() throws KeeperException, InterruptedException {
        //args1 create path
        String result=zooKeeper.create("/create/node2","node2".getBytes(), ZooDefs.Ids.READ_ACL_UNSAFE, CreateMode.PERSISTENT);
        logger.info("createResult="+result);
    }

    @Test
    public void createNode3() throws KeeperException, InterruptedException {
        //args1 create path
        ArrayList<ACL> aclArrayList = new ArrayList<>();
        Id id = new Id("world","anyone");
        aclArrayList.add(new ACL(ZooDefs.Perms.READ,id));
        aclArrayList.add(new ACL(ZooDefs.Perms.WRITE,id));
        String result=zooKeeper.create("/create/node3","node3".getBytes(),aclArrayList, CreateMode.PERSISTENT);
        logger.info("createResult="+result);
    }

    @Test
    public void createNode4() throws KeeperException, InterruptedException {
        //args1 create path
        ArrayList<ACL> aclArrayList = new ArrayList<>();
        Id id = new Id("ip","192.168.0.1");
        aclArrayList.add(new ACL(ZooDefs.Perms.ALL,id));
        //aclArrayList.add(new ACL(ZooDefs.Perms.WRITE,id));
        String result=zooKeeper.create("/create/node4","node4".getBytes(),aclArrayList, CreateMode.PERSISTENT);
        logger.info("createResult="+result);
    }

    @Test
    public void createWithAuth() throws KeeperException, InterruptedException {
        //args1 create path
        //ArrayList<ACL> aclArrayList = new ArrayList<>();
        //Id id = new Id("ip","192.168.0.1");
        //aclArrayList.add(new ACL(ZooDefs.Perms.ALL,id));
        //aclArrayList.add(new ACL(ZooDefs.Perms.WRITE,id));
        zooKeeper.addAuthInfo("digest","itcast:123456".getBytes());
        //String result=zooKeeper.create("/create/node4","node4".getBytes(),aclArrayList, CreateMode.PERSISTENT);
        String result=zooKeeper.create("/create/node5","node5".getBytes(),ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
        logger.info("createResult="+result);
    }

    @Test
    public void createWithAuth2() throws KeeperException, InterruptedException {
        //args1 create path
        ArrayList<ACL> aclArrayList = new ArrayList<>();
        zooKeeper.addAuthInfo("digest","itcast:123456".getBytes());
        Id id = new Id("auth","itcast");
        aclArrayList.add(new ACL(ZooDefs.Perms.READ,id));
        //aclArrayList.add(new ACL(ZooDefs.Perms.WRITE,id));
        //String result=zooKeeper.create("/create/node4","node4".getBytes(),aclArrayList, CreateMode.PERSISTENT);
        String result=zooKeeper.create("/create/node6","node6".getBytes(),aclArrayList, CreateMode.PERSISTENT);
        logger.info("createResult="+result);
    }

    @Test
    public void createWithAuth3() throws KeeperException, InterruptedException {
        //args1 create path
        ArrayList<ACL> aclArrayList = new ArrayList<>();
        //zooKeeper.addAuthInfo("digest","itcast:123456".getBytes());
        Id id = new Id("digest","itheima:123456");
        aclArrayList.add(new ACL(ZooDefs.Perms.ALL,id));
        String result=zooKeeper.create("/create/node71","node71".getBytes(),aclArrayList, CreateMode.PERSISTENT);
        logger.info("createResult="+result);
        //zkCli 中执行 addauth digest itheima:123456
    }

    @Test
    public void createNode8() throws KeeperException, InterruptedException {
        //args1 create path
        String result=zooKeeper.create("/create/node8","node8".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        logger.info("createResult="+result);
    }

    @Test
    public void createNode9() throws KeeperException, InterruptedException {
        //args1 create path
        String result=zooKeeper.create("/create/node9","node9".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        logger.info("createResult="+result);
        //zkCli get /create/node9 临时节点不能拿到数据
    }

    @Test
    public void createNode10() throws KeeperException, InterruptedException {
        //args1 create path
        String result=zooKeeper.create("/create/node10","node9".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        logger.info("createResult="+result);
        //zkCli get /create/node10 临时节点不能拿到数据
    }

    @Test
    public void createNode11() throws KeeperException, InterruptedException {
        //args1 create path
        zooKeeper.create("/create/node10", "node9".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, String name) {
                logger.info(rc+",path="+path+",ctx="+ctx+",name="+name);
            }
        },"I am context");
        Thread.sleep(1000);
        logger.info("finished");
        //logger.info("createResult="+result);
        //zkCli get /create/node10 临时节点不能拿到数据
    }

}
