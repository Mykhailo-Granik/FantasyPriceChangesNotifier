package http;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpGetRequestImplTest {

    public static final String BODY = "body";
    public static final String URL = "http://google.com";

    @Test
    public void shouldReturnResponseBodyIfRequestIsSuccessful() {
        HttpGetRequest underTest = underTest(HttpURLConnection.HTTP_OK);
        assertEquals(BODY, underTest.send(URL));
    }

    private HttpGetRequestImpl underTest(int httpForbidden) {
        return new HttpGetRequestImpl(
                new TestingHttpClient(
                        new TestingHttpResponse(httpForbidden, BODY)
                )
        );
    }

    @Test
    public void shouldThrowAnExceptionIfResponseIndicatesBadRequest() {
        HttpGetRequest underTest = underTest(HttpURLConnection.HTTP_BAD_REQUEST);
        assertThrows(RuntimeException.class, () -> underTest.send(URL));
    }

    @Test
    public void shouldThrowAnExceptionIfResponseIndicatesUnauthorized() {
        HttpGetRequest underTest = underTest(HttpURLConnection.HTTP_UNAUTHORIZED);
        assertThrows(RuntimeException.class, () -> underTest.send(URL));
    }

    @Test
    public void shouldThrowAnExceptionIfResponseIndicatesForbidden() {
        HttpGetRequest underTest = underTest(HttpURLConnection.HTTP_FORBIDDEN);
        assertThrows(RuntimeException.class, () -> underTest.send(URL));
    }

    @Test
    public void shouldThrowAnExceptionIfResponseIndicatesNotFound() {
        HttpGetRequest underTest = underTest(HttpURLConnection.HTTP_NOT_FOUND);
        assertThrows(RuntimeException.class, () -> underTest.send(URL));
    }

    @Test
    public void shouldThrowAnExceptionIfResponseIndicatesInternalServerError() {
        HttpGetRequest underTest = underTest(HttpURLConnection.HTTP_INTERNAL_ERROR);
        assertThrows(RuntimeException.class, () -> underTest.send(URL));
    }

    @Test
    public void shouldThrowAnExceptionIfResponseIndicatesServiceUnavailable() {
        HttpGetRequest underTest = underTest(HttpURLConnection.HTTP_UNAVAILABLE);
        assertThrows(RuntimeException.class, () -> underTest.send(URL));
    }

    @Test
    public void shouldThrowAnExceptionIfResponseIndicatesGatewayTimeout() {
        HttpGetRequest underTest = underTest(HttpURLConnection.HTTP_GATEWAY_TIMEOUT);
        assertThrows(RuntimeException.class, () -> underTest.send(URL));
    }

    @Test
    public void shouldThrowAnExceptionWhenHttpClientThrowsAnException() {
        HttpGetRequest underTest = new HttpGetRequestImpl(new FailingHttpClient());
        assertThrows(RuntimeException.class, () -> underTest.send(URL));
    }

    @RequiredArgsConstructor
    private static class TestingHttpClient extends HttpClient {

        private final HttpResponse<String> response;

        @Override
        public Optional<CookieHandler> cookieHandler() {
            return Optional.empty();
        }

        @Override
        public Optional<Duration> connectTimeout() {
            return Optional.empty();
        }

        @Override
        public Redirect followRedirects() {
            return null;
        }

        @Override
        public Optional<ProxySelector> proxy() {
            return Optional.empty();
        }

        @Override
        public SSLContext sslContext() {
            return null;
        }

        @Override
        public SSLParameters sslParameters() {
            return null;
        }

        @Override
        public Optional<Authenticator> authenticator() {
            return Optional.empty();
        }

        @Override
        public Version version() {
            return null;
        }

        @Override
        public Optional<Executor> executor() {
            return Optional.empty();
        }

        @Override
        public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
            return (HttpResponse<T>) response;
        }

        @Override
        public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
            return null;
        }

        @Override
        public <T> CompletableFuture<HttpResponse<T>> sendAsync(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler, HttpResponse.PushPromiseHandler<T> pushPromiseHandler) {
            return null;
        }
    }

    @RequiredArgsConstructor
    private static class TestingHttpResponse implements HttpResponse<String> {

        private final int statusCode;
        private final String body;

        @Override
        public int statusCode() {
            return statusCode;
        }

        @Override
        public HttpRequest request() {
            return null;
        }

        @Override
        public Optional<HttpResponse<String>> previousResponse() {
            return Optional.empty();
        }

        @Override
        public HttpHeaders headers() {
            return null;
        }

        @Override
        public String body() {
            return body;
        }

        @Override
        public Optional<SSLSession> sslSession() {
            return Optional.empty();
        }

        @Override
        public URI uri() {
            return null;
        }

        @Override
        public HttpClient.Version version() {
            return null;
        }
    }

    private static class FailingHttpClient extends TestingHttpClient {

        public FailingHttpClient() {
            super(new TestingHttpResponse(HttpURLConnection.HTTP_OK, BODY));
        }

        @Override
        public <T> HttpResponse<T> send(HttpRequest request, HttpResponse.BodyHandler<T> responseBodyHandler) {
            throw new RuntimeException();
        }
    }
}