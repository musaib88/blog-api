package Musaib.MybackendProject.Utilities;

import Musaib.MybackendProject.Payloads.PostDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Setter
@Getter

public class PageResponse {
    List<PostDto> content;
    int pageNum;
    int pageSize;
    int totalPages;
    int totalElements;
    boolean lastPage;
}
