package dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WifiDTO {
    private String distance; //조회한 위치와 근처WIFI 위치의 거리
    private String mrgNo; //관리번호
    private String city; //자치구
    private String wifiName; //와이파이명
    private String addr1; //도로명주소
    private String addr2; //상세주소
    private String insFloor; //설치위치(층)
    private String insType; //설치유형
    private String insAgency; //설치기관
    private String svcType; //서비스구분
    private String netType; // 망종류
    private String insDate; //설치년도
    private String inOutDoor; //실내외구분
    private String wifiConn; //wifi접속환경
    private String lat; //위도
    private String lnt; //경도
    private String workDate; //작업일자
}
