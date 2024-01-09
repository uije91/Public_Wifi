package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkDTO {
    private int id; //북마크 번호
    private String name; //북마크 그룹명
    private String mrgNo; //와이파이 관리번호
    private String distance; //거리
    private String wifiName; //와이파이명
    private String regDate; //등록일자
}
