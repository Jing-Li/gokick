import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.mockito.Mockito;

public class HelloWorldServletTest {
	@Test
	public void testDoGet() throws IOException, ServletException{
		HelloWorldServlet helloWorldServlet = new HelloWorldServlet();
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req.getParameter("time")).thenReturn(DateFormat.getDateInstance().format(new Date()));
		Mockito.when(req.getParameter("echostr")).thenReturn("wuhusihai");
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(System.out));
		helloWorldServlet.doGet(req, resp);
	}
	@Test
	public void testDoPost() throws IOException, ServletException{
		final String  input = "<?xml version=\"1.0\"?>\n" +
				"<xml>\n" +
				"<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
				"<FromUserName><![CDATA[aqa]]></FromUserName>\n" +
				"<CreateTime>1348831860</CreateTime>\n" +
				"<MsgType><![CDATA[text]]></MsgType>\n" +
				"<Content><![CDATA[ok-a]]></Content>\n" +
				"<MsgId>1234567890123456</MsgId>\n" +
				"</xml>";
		System.err.println(input);
		final InputStream in = IOUtils.toInputStream(input);
		HelloWorldServlet helloWorldServlet = new HelloWorldServlet();
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		Mockito.when(req.getInputStream()).thenReturn(new ServletInputStream() {
	        @Override
	        public int read() throws IOException {
	            return in.read();
	        }
	    });
		HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
		Mockito.when(resp.getWriter()).thenReturn(new PrintWriter(System.out));
		helloWorldServlet.doPost(req, resp);
	}
}
