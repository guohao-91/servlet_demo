package org.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.descriptor.api.Descriptor;
import org.jboss.shrinkwrap.descriptor.api.Descriptors;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GHArquillianManageJUnitTest {


    @Deployment
    public static WebArchive createDeployment2() {

        WebArchive archive = ShrinkWrap.create(WebArchive.class,"vfs2-test.war")
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
    @RunAsClient
    public void testRunAsClient() {
        System.out.println("arquillian junit testRunAsClient ");
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("aaa.xml").toString());
        assertEquals(true, true);
    }
}
