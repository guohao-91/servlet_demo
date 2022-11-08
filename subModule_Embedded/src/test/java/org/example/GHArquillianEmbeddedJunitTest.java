package org.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GHArquillianEmbeddedJunitTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
