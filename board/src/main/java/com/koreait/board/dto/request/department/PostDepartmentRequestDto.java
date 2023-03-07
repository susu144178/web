package com.koreait.board.dto.request.department;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostDepartmentRequestDto {
    
    //^ 부서코드
    @NotBlank
    @Length(min = 0, max = 5)
    private String departmentCode;
    //^ 부서명
    @NotBlank
    @Length(min = 0, max = 50)
    private String name;
    //^ 부서장 사번
    @Min(1)
    private int cheif;
    //^ 부서 전화번호
    @NotBlank
    @Length(min = 0, max = 15)
    private String telNumber;
}
