package team.akina.qmoj.utils.http;

import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HttpClientUtil {

    @Value("${http.maxTotal}")
    private Integer maxTotal;

    @Value("${http.defaultMaxPerRoute}")
    private Integer defaultMaxPerRoute;

    @Value("${http.connectTimeout}")
    private Integer connectTimeout;

    @Value("${http.connectionRequestTimeout}")
    private Integer connectionRequestTimeout;

    @Value("${http.socketTimeout}")
    private Integer socketTimeout;

    @Value("${http.staleConnectionCheckEnabled}")
    private boolean staleConnectionCheckEnabled;


    /**
     * 获取HttpClient
     *
     * @param httpClientBuilder 实例化的HttpClientBuilder
     * @return httpClient实例
     */
    @Bean
    public CloseableHttpClient getCloseableHttpClient(@Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
        return httpClientBuilder.build();
    }

    /**
     * 设置连接池管理器，并返回HttpClientBuilder实例
     *
     * @param httpClientConnectionManager 实例化完成的连接池管理器
     * @return HttpClientBuilder实例
     */
    @Bean(name = "httpClientBuilder")
    public HttpClientBuilder getHttpClientBuilder(
            @Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager,
            @Qualifier("httpCookieStore") CookieStore httpCookieStore) {

        // 使用HttpClientBuilder提供的静态方法create()来获取HttpClientBuilder对象
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);

        httpClientBuilder.setConnectionManager(httpClientConnectionManager).setConnectionManagerShared(true);

        return httpClientBuilder;
    }

    @Bean(name = "httpCookieStore")
    public CookieStore getHttpCookieStore() {
        return new BasicCookieStore();
    }

    /**
     * 实例化连接池管理器，并设置最大连接数和并发数
     *
     * @return 实例化完成的连接池管理器
     */
    @Bean(name = "httpClientConnectionManager")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        httpClientConnectionManager.setMaxTotal(maxTotal);
        // 并发数
        httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
        return httpClientConnectionManager;
    }

    /**
     * 构建设置了默认参数的RequestConfig对象
     *
     * @param builder RequestConfig的Builder实例
     * @return RequestConfig实例
     */
    @Bean
    public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
        return builder.build();
    }

    /**
     * 设置builder的连接信息
     * 这里还可以设置proxy，cookieSpec等属性。有需要的话可以在此设置
     *
     * @return RequestConfig的Builder实例
     */
    @Bean(name = "builder")
    public RequestConfig.Builder getBuilder() {
        RequestConfig.Builder builder = RequestConfig.custom();
        return builder.setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setSocketTimeout(socketTimeout)
                .setCookieSpec(CookieSpecs.STANDARD)
                .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
    }
}
