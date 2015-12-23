package com.zh.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;

import com.zh.xgd.utils.StringByteConvertUtil;

public class HttpClientTest {

	private static final String FORMATE_DATE = "yyyy-MM-dd";
	private static final String FORMATE_SECONDS = "HH:mm:ss";
	private static final String FORMATE_FULL = FORMATE_DATE.concat(" ").concat(FORMATE_SECONDS);
	/**
	 * HttpClient连接SSL
	 */
	public static void ssl() {
		CloseableHttpClient httpclient = null;
		try {
			String KEY_TYPE = "PKCS12";
			KeyStore trustStore = KeyStore.getInstance(KEY_TYPE);
			String caPath = getCAPath();
			FileInputStream instream = new FileInputStream(new File(caPath));
			try {
				// 加载keyStore d:\\tomcat.keystore  
				trustStore.load(instream, "1242722402".toCharArray());
			} catch (CertificateException e) {
				e.printStackTrace();
			} finally {
				try {
					instream.close();
				} catch (Exception ignore) {
				}
			}
			// 相信自己的CA和所有自签名的证书
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
			// 只允许使用TLSv1协议
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			// 创建http请求(get方式)
			HttpGet httpget = new HttpGet("https://localhost:8443/myDemo/Ajax/serivceJ.action");
			System.out.println("executing request" + httpget.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				if (entity != null) {
					System.out.println("Response content length: " + entity.getContentLength());
					System.out.println(EntityUtils.toString(entity));
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} finally {
			if (httpclient != null) {
				try {
					httpclient.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String getCAPath()
	{
		String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		String fileName = "apiclient_cert.p12";
		String caPath = classPath + File.separator + fileName;
		return caPath;
	}
	
	/**
	 * post方式提交表单（模拟用户登录请求）
	 */
	public void postForm() {
		// 创建默认的httpClient实例.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost  
		HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");
		// 创建参数队列  
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("username", "admin"));
		formparams.add(new BasicNameValuePair("password", "123456"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public void post() {
		// 创建默认的httpClient实例.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost  
		HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceJ.action");
		// 创建参数队列  
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("type", "house"));
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println("--------------------------------------");
					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送 get请求
	 * @throws Exception 
	 * @throws UnsupportedOperationException 
	 */
	public static void get(String url) throws UnsupportedOperationException, Exception {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.  
			HttpGet httpget = new HttpGet(url);
//			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.  
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体  
				HttpEntity entity = response.getEntity();
//				System.out.println("--------------------------------------");
				// 打印响应状态  
//				System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度  
//					System.out.println("Response content length: " + entity.getContentLength());
					byte[] tempImg = readStream(entity.getContent());
//					for(byte tmp : tempImg)
//					{
//						System.out.print(tmp+" ");
//					}
//					System.out.println();
//					String hexImg = StringByteConvertUtil.toHexString(tempImg);
//					System.out.println(hexImg);
//					// 打印响应内容  
//					System.out.println("Response content: " + EntityUtils.toString(entity));
				}
//				System.out.println("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 上传文件
	 */
	public void upload() {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost("http://localhost:8080/myDemo/Ajax/serivceFile.action");

			FileBody bin = new FileBody(new File("F:\\image\\sendpix0.jpg"));
			StringBody comment = new StringBody("A binary file of some kind", ContentType.TEXT_PLAIN);

			HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("bin", bin).addPart("comment", comment).build();

			httppost.setEntity(reqEntity);

			System.out.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("Response content length: " + resEntity.getContentLength());
				}
				EntityUtils.consume(resEntity);
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static byte[] readStream(InputStream is) throws Exception{  
        byte[] bytes = new byte[1024];  
        int leng;  
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        while((leng=is.read(bytes)) != -1){  
            baos.write(bytes, 0, leng);  
        }  
        return baos.toByteArray();  
    } 
	
	public static void main(String[] args) throws UnsupportedOperationException, Exception
	{
//		for(int i=0;i<10;i++)
//		{
//			new Thread(new Runnable() {
//				
//				public void run() {
//					try {
//						repeatReq(1000);
//					} catch (UnsupportedOperationException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//				}
//			}).start();
//		}
//		repeatReq(1);
		mpayGet("http://172.20.4.20:51888/accountService", "849441959770084", "47596142", "xgd.crm.export.getMerchantInfo");
		
	}
	
	public static void repeatReq(int times) throws UnsupportedOperationException, Exception
	{
		String url = "http://172.20.4.20:51888/accountService?jsonparam={\"lmerchantcode\":\"849441959770084\",\"method\":\"xgd.crm.export.getMerchantInfo\",\"termno\":\"47596142\"}";
		System.out.println(url.charAt(50));
		System.out.println(new DateTime().toString(FORMATE_FULL));
		for(int i = 0;i < times;i++)
		{
			get(url);
		}
		System.out.println(new DateTime().toString(FORMATE_FULL));
	}
	
	public static String getQRCode() throws UnsupportedOperationException, Exception
	{
		String url = "http://qr.liantu.com/api.php?text=weixin://wxpay/bizpayurl?appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.  
			HttpGet httpget = new HttpGet(url);
			System.out.println("executing request " + httpget.getURI());
			// 执行get请求.  
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体  
				HttpEntity entity = response.getEntity();
				System.out.println("--------------------------------------");
				// 打印响应状态  
				System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度  
					System.out.println("Response content length: " + entity.getContentLength());
					byte[] tempImg = readStream(entity.getContent());
					for(byte tmp : tempImg)
					{
						System.out.print(tmp+" ");
					}
					System.out.println();
					String hexImg = StringByteConvertUtil.toHexString(tempImg);
					
					System.out.println(hexImg);
					return hexImg;
				}
				System.out.println("------------------------------------");
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
				return "";
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	
	
	public static String mpayGet(String baseUrl, String merchantNo, String terminalNo, String method) throws ClientProtocolException, IOException {
		String responseFromMpay = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httpget.
		String url = baseUrl+"?jsonparam=";
		String toEncoder = "{\"lmerchantcode\":\""+merchantNo+"\",\"method\":\""+method+"\",\"termno\":\""+terminalNo+"\"}";
//		toEncoder="http://172.20.4.20:51888/accountService?jsonparam={\"lmerchantcode\":\"849441959770084\",\"method\":\"xgd.crm.export.getMerchantInfo\",\"termno\":\"47596142\"}";
		String encodeUrl = URLEncoder.encode(toEncoder, "utf-8");
		StringBuffer sb = new StringBuffer();
		sb.append(url).append(encodeUrl);
		HttpGet httpget = new HttpGet(sb.toString());
//		HttpGet httpget = new HttpGet(encodeUrl);
		System.out.println("EXECUTING REQUEST MPAY" + httpget.getURI());
//		logger.info("EXECUTING REQUEST MPAY" + httpget.getURI());
		// 执行get请求.
		CloseableHttpResponse response = httpclient.execute(httpget);
		// 获取响应实体
		HttpEntity entity = response.getEntity();
		// 打印响应状态
		System.out.println(response.getStatusLine());
		if (entity != null) {
			// 打印响应内容长度
			System.out.println("Response content length: " + entity.getContentLength());
			// 打印响应内容
			responseFromMpay = EntityUtils.toString(entity);
//			logger.info("RESPONSE FROM MPAY: " + responseFromMpay);
			System.out.println("RESPONSE FROM MPAY: " + responseFromMpay);
			response.close();
			// 关闭连接,释放资源
			httpclient.close();
		}
		return responseFromMpay;
	}
}