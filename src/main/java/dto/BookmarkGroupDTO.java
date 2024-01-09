package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookmarkGroupDTO {
    private int id; //북마크 그룹번호
    private String name; //북마크 그룹명
    private int order; //북마크그룹순서
    private String regDate; //등록일자
    private String editDate; //수정일자
}
