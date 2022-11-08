package org.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GHArquillianEmbeddedWildflyJunitTest {

    @Deployment
    public static WebArchive createDeployment() {

        WebArchive archive = ShrinkWrap.create(WebArchive.class,"vfs-test.war")
                .addPackages(true, "org/example")
                .addAsResource(new File("src/main/resources/"), "")
                .addAsResource(new File("src/test/resources/"), "");
        return archive;
    }

    @Test
    public void test() throws IOException {
        System.out.println("arquillian junit test ");
        String str = Thread.currentThread().getContextClassLoader().getResource("aaa.xml").toString();
        System.out.println(str);

        File file = new File("C:\\temp\\test.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(str);
        writer.flush();
        writer.close();

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
