package com.koreait.board.dto.response.humanResource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostHumanResourceResponseDto {
    
    //^ 사번
    private int employeeNumber;
    //^ 직급
    private String position;
    //^ 사원이름
    private String name;
    //^ 나이
    private int age;
    //^ 성별
    private String gender;
    //^ 학력
    private String academicAbility;
    //^ 생년월일
    private String birth;
    //^ 전화번호
    private String telNumber;
    //^ 주소
    private String address;
    //^ 상세주소
    private String addressDetail;
    //^ 입사일
    private String joinDate;
    //^ 퇴사일
    private String resignationDate;
    //^ 부서
    private String department;
    //^ 연봉
    private int annualIncome;
    //^ 비고
    private String note;
}
