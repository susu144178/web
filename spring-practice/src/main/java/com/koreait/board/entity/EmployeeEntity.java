package com.koreait.board.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.koreait.board.dto.request.humanResource.PatchHumanResourceRequestDto;
import com.koreait.board.dto.request.humanResource.PostHumanResourceRequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// position VARCHAR(10) NOT NULL COMMENT '직책',
    // name VARCHAR(10) NOT NULL COMMENT '이름',
    // age INT NOT NULL COMMENT '나이',
    // gender VARCHAR(10) NOT NULL COMMENT '성별',
    // academic_ability VARCHAR(20) NOT NULL COMMENT '학력',
    // birth DATE NOT NULL COMMENT '생년월일',
    // tel_number VARCHAR(15) NOT NULL UNIQUE COMMENT '전화번호',
    // address TEXT NOT NULL COMMENT '주소',
    // address_detail TEXT NOT NULL COMMENT '상세주소',
    // join_date DATE NOT NULL COMMENT '입사일',
    // resignation_date DATE COMMENT '퇴사일',
    // department VARCHAR(45) DEFAULT '미정' COMMENT '부서',
    // annual_income INT NOT NULL COMMENT '연봉',
    // note TEXT COMMENT '비고'

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Employee")
@Table(name = "Employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public EmployeeEntity(PostHumanResourceRequestDto dto) {
        this.position = dto.getPosition();
        this.name = dto.getName();
        this.age = dto.getAge();
        this.gender = dto.getGender();
        this.academicAbility = dto.getAcademicAbility();
        this.birth = dto.getBirth();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.joinDate = dto.getJoinDate();
        this.resignationDate = dto.getResignationDate();
        this.department = dto.getDepartment();
        this.annualIncome = dto.getAnnualIncome();
        this.note = dto.getNote();
    }

    public EmployeeEntity(PatchHumanResourceRequestDto dto) {
        this.employeeNumber = dto.getEmployeeNumber();
        this.position = dto.getPosition();
        this.name = dto.getName();
        this.age = dto.getAge();
        this.gender = dto.getGender();
        this.academicAbility = dto.getAcademicAbility();
        this.birth = dto.getBirth();
        this.telNumber = dto.getTelNumber();
        this.address = dto.getAddress();
        this.addressDetail = dto.getAddressDetail();
        this.joinDate = dto.getJoinDate();
        this.resignationDate = dto.getResignationDate();
        this.department = dto.getDepartment();
        this.annualIncome = dto.getAnnualIncome();
        this.note = dto.getNote();
    }

}
