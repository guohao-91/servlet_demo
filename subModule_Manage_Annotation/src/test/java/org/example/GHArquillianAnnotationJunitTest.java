package org.example;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.core.api.annotation.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GHArquillianAnnotationJunitTest {

    @ArquillianResource
    ContainerController controller;

    @ArquillianResource
    Deployer deployer;


    @Deployment(name="deploy-0")
    @TargetsContainer("node-0")
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class,"vfs-test.war")
                .addPackages(true, "org/example")
                .addAsResource(new File("src/main/resources/"), "")
                .addAsResource(new File("src/test/resources/"), "");
        return archive;
    }

    @Deployment(name="deploy-1", managed = false)
//    @TargetsContainer("node-1")
    public static WebArchive createDeployment1() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class,"vfs-test.war")
                .addPackages(true, "org/example")
                .addAsResource(new File("src/main/resources/"), "")
                .addAsResource(new File("src/test/resources/"), "");
        return archive;
    }

//    @Deployment(name="deploy-2", managed = false)
//    @TargetsContainer("node-2")
//    public static WebArchive createDeployment2() {
//        WebArchive archive = ShrinkWrap.create(WebArchive.class,"vfs-test.war")
//                .addPackages(true, "org/example")
//                .addAsResource(new File("src/main/resources/"), "")
//                .addAsResource(new File("src/test/resources/"), "");
//        return archive;
//    }

    @Test
    @RunAsClient
    @InSequence(-1)
    public void startServer() {
        controller.start("node-0");
        deployer.deploy("deploy-0");
        deployer.deploy("deploy-1");
    }

    @Test
    @RunAsClient
    @InSequence(1)
    public void stopServer() {
        deployer.undeploy("deploy-0");
        deployer.undeploy("deploy-1");
        controller.stop("node-0");
//        controller.kill("node-0");
    }

    @Test
    public void test(){
        System.out.println("arquillian junit test ");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("arquillian.xml"));
        assertEquals(true, true);
    }
}
