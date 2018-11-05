package io.pivio.view.app.exporter.util;

import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


public class MySimpleClientHttpRequestFactory extends SimpleClientHttpRequestFactory{
    private final HostnameVerifier verifier;
    private final String cookie;

    public MySimpleClientHttpRequestFactory(HostnameVerifier verifier,
                                            String cookie) {
        this.verifier = verifier;
        this.cookie = cookie;
    }

    @Override
    protected void prepareConnection(HttpURLConnection connection,
                                     String httpMethod) throws IOException {
        if (connection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) connection).setHostnameVerifier(verifier);
            ((HttpsURLConnection) connection)
                    .setSSLSocketFactory(trustSelfSignedSSL()
                            .getSocketFactory());
            connection.setAllowUserInteraction(true);
            String rememberMeCookie = cookie == null ? "" : cookie;
            connection.setRequestProperty("Cookie",
                    rememberMeCookie);
        }
        super.prepareConnection(connection, httpMethod);
    }

    public SSLContext trustSelfSignedSSL() {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,
                                               String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs,
                                               String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLContext.setDefault(ctx);
            return ctx;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
