package kr.deity.restdocwithoutsecurity.common;

import org.springframework.http.HttpMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class UrlWrapperService {

    private static UrlResponse getHttpURLConnectionWrapper(org.springframework.http.HttpMethod httpMethod, String apiUrl, String methodUri, String data) {

        String httpMethodName = httpMethod.name();
        HttpURLConnection conn;

        BufferedReader br;

        StringBuffer sb;

        URL url;
        try{
            if(httpMethod == HttpMethod.GET){
                url = new URL(apiUrl + methodUri + data);
            }else{
                url = new URL(apiUrl+methodUri);
            }

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethodName);

            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("x-api-version", "v1");

            if(httpMethod != HttpMethod.GET){
                try (OutputStream os = conn.getOutputStream()){
                    byte request_data[] = data.getBytes("utf-8");
                    os.write(request_data);
                }
                catch(Exception e) {
                    String errorMsg = "Data Send Error";
                    throw new RuntimeException(errorMsg);
                }
            }

            conn.connect();

            System.out.println("######################################################");
            System.out.println("http 요청 타입 : "+"application/json");
            System.out.println("http 요청 주소 : "+ apiUrl + methodUri);
            System.out.println("http 요청 데이터 : "+ data);
            System.out.println("http method : " + httpMethodName);
            System.out.println("######################################################");


            //접속오류 발생
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        int responseCode = 0;
        try {

            responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                InputStream errorStream = conn.getErrorStream();
                br = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
            }

            sb = new StringBuffer();

            String response;

            while ((response = br.readLine()) != null) {
                sb.append(response);
            }

            //데이터 가져오다가 발생
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UrlResponse urlDto = new UrlResponse();
        urlDto.setResponseHttpStatus(responseCode);
        urlDto.setReturnData(sb.toString());

        return urlDto;
    }

    private static UrlResponse getHttpURLConnectionWrapperForThrow(org.springframework.http.HttpMethod httpMethod, String apiUrl, String methodUri, String data) throws IOException {

        String httpMethodName = httpMethod.name();
        HttpURLConnection conn;

        BufferedReader br;

        StringBuffer sb;

        URL url;
        try{
            if(httpMethod == HttpMethod.GET){
                url = new URL(apiUrl + methodUri + data);
            }else{
                url = new URL(apiUrl+methodUri);
            }

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(httpMethodName);

            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("x-api-version", "v1");

            if(httpMethod != HttpMethod.GET){
                try (OutputStream os = conn.getOutputStream()){
                    byte request_data[] = data.getBytes("utf-8");
                    os.write(request_data);
                }
                catch(Exception e) {
                    String errorMsg = "Data Send Error";
                    throw new RuntimeException(errorMsg);
                }
            }

            conn.connect();

            System.out.println("######################################################");
            System.out.println("http 요청 타입 : "+"application/json");
            System.out.println("http 요청 주소 : "+ apiUrl + methodUri);
            System.out.println("http 요청 데이터 : "+ data);
            System.out.println("http method : " + httpMethodName);
            System.out.println("######################################################");


            //접속오류 발생
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        int responseCode = 0;


        responseCode = conn.getResponseCode();

        if (responseCode == 200) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } else {
            InputStream errorStream = conn.getErrorStream();
            br = new BufferedReader(new InputStreamReader(errorStream, "UTF-8"));
        }

        sb = new StringBuffer();

        String response;

        while ((response = br.readLine()) != null) {
            sb.append(response);
        }

        //데이터 가져오다가 발생


        UrlResponse urlDto = new UrlResponse();
        urlDto.setResponseHttpStatus(responseCode);
        urlDto.setReturnData(sb.toString());

        return urlDto;
    }

    public UrlResponse apiRequestGet(String apiUrl, String methodUri, String data) {
        UrlResponse urlDto = getHttpURLConnectionWrapper(HttpMethod.GET, apiUrl, methodUri, data);

        return urlDto;
    }

    public UrlResponse apiCreatePost(String apiUrl, String methodUri, String data){

        UrlResponse urlDto = getHttpURLConnectionWrapper(HttpMethod.POST,apiUrl,methodUri, data);

        return urlDto;
    }

    public UrlResponse apiCreatePostForThrow(String apiUrl, String methodUri, String data){

        //TODO 리턴값에 따라서 오류일경우에만 Exception이 나도록 할수도 있다.
        UrlResponse urlDto;
        try{
            urlDto = getHttpURLConnectionWrapperForThrow(HttpMethod.POST,apiUrl,methodUri, data);
        }catch (IOException e){
            throw new RuntimeException("중복건이 발생하였습니다.");
        }
        return urlDto;
    }

    public UrlResponse apiUpdatePut(String apiUrl, String methodUri, String data) {
        UrlResponse urlDto = getHttpURLConnectionWrapper(HttpMethod.POST,apiUrl,methodUri, data);

        return urlDto;
    }

    public UrlResponse apiDelete(String apiUrl,String methodUri, String data){
        UrlResponse urlDto = getHttpURLConnectionWrapper(HttpMethod.POST,apiUrl,methodUri, data);

        return urlDto;
    }
}