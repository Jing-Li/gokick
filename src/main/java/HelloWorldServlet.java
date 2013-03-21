/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * <p>
 * A simple servlet taking advantage of features added in 3.0.
 * </p>
 * 
 * <p>
 * The servlet is registered and mapped to /HelloServlet using the {@linkplain WebServlet
 * @HttpServlet}. The {@link HelloService} is injected by CDI.
 * </p>
 * 
 * @author Pete Muir
 * 
 */
@SuppressWarnings("serial")
@WebServlet("/qutiqiu")
public class HelloWorldServlet extends HttpServlet {

   static String PAGE_HEADER = "<html><head /><body>";

   static String PAGE_FOOTER = "</body></html>";

   private static final String RESPONSE_TXT = "<xml>" +
			"<ToUserName><![CDATA[%s]]></ToUserName>" +
			"<FromUserName><![CDATA[%s]]></FromUserName>" +
			"<CreateTime>%s</CreateTime>" +
			"<MsgType><![CDATA[%s]]></MsgType>" +
			"<Content><![CDATA[%s]]></Content>" +
			"<FuncFlag>0</FuncFlag>" +
			"</xml>";
   @Inject
   HelloService helloService;

   private Logger log = Logger.getLogger(HelloWorldServlet.class.getName());
   
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   
	   String del = (String) req.getParameter("del");
	   if(del != null && del.equals("yes")){
		   log.info("remove file");
		   MatchUtil.removeFile();
	   }
	   
	   
	   String time = (String) req.getParameter("time");
	   if(time == null){
		   time = DateFormat.getDateInstance().format(new Date());
	   }
	   log.info("time is " + time);
	   if(MatchUtil.isFileExist()){
		   Match match = MatchUtil.readMatch();
		   try {
			   match.setTime(DateFormat.getDateInstance().parse(time));
		   } catch (ParseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   match.setMatchCount((match.getMatchCount())+1);
		   MatchUtil.writeMatch(match);
	   }else{
		   Match match = new Match();
		   try {
			   match.setTime(DateFormat.getDateInstance().parse(time));
		   } catch (ParseException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		   }
		   match.setMatchCount((match.getMatchCount())+1);
		   MatchUtil.writeMatch(match);
	   }
	  log.info((MatchUtil.readMatch()).toString());
	  String s = (String) req.getParameter("echostr");
      PrintWriter writer = resp.getWriter();
      writer.print(s);
      writer.close();
      
   }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");  
		resp.setCharacterEncoding("UTF-8");  
        PrintWriter out = resp.getWriter();  
  
        Document doc = null;  
        SAXReader reader = new SAXReader();  
        InputStream in = req.getInputStream();  
        try {
        	
            doc = reader.read(in);  
            Metadata question = new Metadata(doc);
            String answer = MessageProcessor.process(question);
            log.info("=============="+ answer);
            out.printf(RESPONSE_TXT, question.getFromUserName(),question.getToUserName(),System.currentTimeMillis(),"text",answer);  
        } catch (DocumentException e) {  
            e.printStackTrace();  
        }  
        in.close();  
        in = null;  
        out.close();  
        out = null;  
	}

}
