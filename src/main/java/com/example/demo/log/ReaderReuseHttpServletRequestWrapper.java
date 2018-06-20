package com.example.demo.log;

import org.apache.commons.io.IOUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ReaderReuseHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private byte[] body;

    public ReaderReuseHttpServletRequestWrapper(HttpServletRequest request)
            throws IOException {
        super(request);
        body = IOUtils.toString(request.getReader()).getBytes(Charset.forName("UTF-8"));
    }

    public void setBody(String body) {
        this.body = body.getBytes();
    }
    public String getBody(){
        return new String(body);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);
        return new ServletInputStream() {

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }

            @Override
            public int read() throws IOException {
                return bais.read();
            }

        };
    }
}
