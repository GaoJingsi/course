package com.slyk.course.server.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterPageDto extends PageDto<ChapterDto> {

    private String courseId;

}
