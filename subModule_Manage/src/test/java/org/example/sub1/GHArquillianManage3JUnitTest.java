package org.example.sub1;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GHArquillianManage3JUnitTest {

    @Deployment
    public static WebArchive createDeployment() {

        WebArchive archive = ShrinkWrap.create(WebArchive.class,"vfs-test.war")
                .addPackages(true, "org/example")
                .addAsResource(new File("src/main/resources/"), "")
                .addAsResource(new File("src/test/resources/"), "");
        return archive;
    }


    @Test
    public void test() {
        System.out.println("arquillian junit test ");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("aaa.xml").toString());
        assertEquals(true, true);
    }


    @Test
    public void test2() {
        System.out.println("arquillian junit test ");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("aaa.xml").toString());
        assertEquals(true, true);
    }


    @Test
    public void test3() {
        System.out.println("arquillian junit test ");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("aaa.xml").toString());
        assertEquals(true, true);
    }

    @Test
    @RunAsClient
    public void testRunAsClient() {
        System.out.println("arquillian junit testRunAsClient ");

        System.out.println(Thread.currentThread().getContextClassLoader().getResource("aaa.xml").toString());
        assertEquals(true, true);
    }
}
