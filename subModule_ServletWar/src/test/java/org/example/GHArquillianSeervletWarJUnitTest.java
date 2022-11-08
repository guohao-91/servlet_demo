package org.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class GHArquillianSeervletWarJUnitTest {

    @Deployment
    public static WebArchive createDeployment() {

        WebArchive archive = ShrinkWrap.create(WebArchive.class,"vfs-test.war")
                .addPackages(true, "org/example")
                .addAsResource(new File("src/main/resources/"), "")
                .addAsResource(new File("src/test/resources/"), "")
                .setWebXML(new File("web/WEB-INF/web.xml"));
        return archive;
    }


    @Test
    public void testservlet() throws Exception {
        String str = Thread.currentThread().getContextClassLoader().getResource("arquillian.xml").toString();
        assertEquals("---testservlet---", str);
    }

    @Test
    @RunAsClient
    public void test() throws Exception {
        String str = getContent(new URL("http://127.0.0.1:8080/vfs-test/ghservlet"));
        assertEquals("---GHServlet---", str);
    }

    private String getContent(URL url) throws Exception {
        InputStream is = url.openStream();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int read;
            while ((read = is.read()) != -1) {
                out.write(read);
            }
        } finally {
            try {
                is.close();
            } catch (Exception e) {
            }
        }
        return out.toString();
    }
}
