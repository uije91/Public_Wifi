package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryDTO {
    private int id; //사용번호
    private String lat; //위도
    private String lnt; //경도
    private String searchDate; //조회일자
}
