package team.akina.qmoj.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpRequestHelper {

    private CloseableHttpClient httpClient;

    private RequestConfig config;

    @Autowired
    public void setUserDao(CloseableHttpClient httpClient, RequestConfig config) {
        this.httpClient = httpClient;
        this.config = config;
    }

    /**
     * 无参get请求，如果状态码为200，则返回body，否则返回null
     *
     * @param url 请求的url
     * @return UTF-8格式的请求结果字符串，如果状态码不为200，返回null
     * @throws Exception
     */
    public String doGet(String url) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);
        HttpResult httpResult = new HttpResult();

        // 装载配置信息
        httpGet.setConfig(config);

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);

        // 判断状态码是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            // 返回响应体的内容
            return EntityUtils.toString(response.getEntity(), "UTF-8");
        }

        return null;
    }


    /**
     * 带参get请求
     *
     * @param url 请求的url
     * @param map 携带的参数
     * @return UTF-8格式的请求结果字符串，如果状态码不为200，返回null
     * @throws Exception
     */
    public String doGet(String url, Map<String, Object> map) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (map != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString());
    }

    /**
     * 带参数的post请求
     *
     * @param url         请求的url
     * @param formContent form表单内容
     * @param headers     请求要带的header
     * @return HttpResult实体类，包含状态码和返回内容
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> formContent, Map<String, String> headers) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        // 设置header
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // 判断map是否为空，不为空则进行遍历，封装form表单对象
        if (formContent != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : formContent.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }

            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }

    /**
     * 不带参数post请求
     *
     * @param url 请求的url
     * @return HttpResult实体类，包含状态码和返回内容
     * @throws Exception
     */
    public HttpResult doPost(String url) throws Exception {
        return this.doPost(url, null, null);
    }

    /**
     * application/json类型的Post请求
     *
     * @param url         请求的url
     * @param jsonContent body内容，json字符串
     * @param headers     请求要带的header
     * @return HttpResult实体类，包含状态码和返回内容
     * @throws Exception
     */
    public HttpResult doJsonPost(String url, String jsonContent, Map<String, String> headers) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);

        HttpEntity httpEntity = new StringEntity(jsonContent, "application/json; charset=utf-8", "UTF-8");
        httpPost.setEntity(httpEntity);

        // 设置header
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse response = httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(
                response.getEntity(), "UTF-8"));
    }
}
