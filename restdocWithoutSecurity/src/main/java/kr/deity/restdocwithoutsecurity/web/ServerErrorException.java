package kr.deity.restdocwithoutsecurity.web;

public class ServerErrorException extends RuntimeException{

    public ServerErrorException(String message) {
        super(message);
    }
}
