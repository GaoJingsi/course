package com.slyk.course.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionPageDto extends PageDto<SectionDto> {

    private String chapterId;

}
