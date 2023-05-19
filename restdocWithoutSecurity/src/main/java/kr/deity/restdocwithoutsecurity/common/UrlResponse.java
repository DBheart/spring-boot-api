package kr.deity.restdocwithoutsecurity.common;

import lombok.Data;

@Data
public class UrlResponse {
    int responseHttpStatus;
    String returnData;

    //TODO 값의 정의에 따라서 returnData 변경하거나, jsonObject 변경하여서 사용한다. get 만들어도 괜찮을것 같다.

}
