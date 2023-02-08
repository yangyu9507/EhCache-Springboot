package com.yaron.ehcachespringboot.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Yaron
 * @version 1.0
 * @date 2023/02/08
 * @description
 */
@Data
@Entity
@Table(name = "tb_inf_employee")
public class TbInfEmployee implements Serializable {

    private static final long serialVersionUID = -3782023646688429221L;

    /**
     * 员工编号
     */
    @Id
    @Column(name = "id")
    private Integer id ;

    /**
     * 员工姓名(数据库规范，数据库字段不要命名为name)
     */
    @Column(name = "emp_name")
    private String name ;

    /**
     * 员工年龄
     */
    @Column(name = "age")
    private int age;

    /**
     * 工资
     */
    @Column(name = "salary")
    private double salary = 0.00;

    /**
     * 部门
     */
    @Column(name = "department")
    private String department;

    /**
     * 入职时间
     */
    @Column(name = "hire_date")
    private LocalDate hireDate = LocalDate.of(1970, 1, 1);

    /**
     * 注意：被序列化对象应提供一个无参的构造函数，否则会抛出异常
     */
    public TbInfEmployee() {

    }
}
