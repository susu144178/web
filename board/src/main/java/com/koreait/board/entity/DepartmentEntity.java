package com.koreait.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//     department_code VARCHAR(5) PRIMARY KEY COMMENT '부서코드',
//     name VARCHAR(100) NOT NULL COMMENT '부서명',
//     cheif INT NOT NULL COMMENT '부서장',
//     tel_number VARCHAR(15) NOT NULL COMMENT '부서 전화번호',

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Department")
@Table(name = "Department")
public class DepartmentEntity {
    
    @Id
    //^ 부서코드
    private String departmentCode;
    //^ 부서명
    private String name;
    //^ 부서장 사번
    private int cheif;
    //^ 부서 전화번호
    private String telNumber;
}
