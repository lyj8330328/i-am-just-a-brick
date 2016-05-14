package com.shuangyulin.javabean;

import java.sql.Date;

/*��ʵ��javabean��Ӧ���ݿ��е�Ա����Ϣ��
CREATE TABLE [dbo].[employeeInfo] (						
	[employeeNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//Ա�����
	[employeeName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//Ա������
	[employeePassword] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,	//Ա����½����
	[employeeSex] [nchar] (1) COLLATE Chinese_PRC_CI_AS NULL ,			//Ա���Ա�
	[employeeBirthday] [datetime] NULL ,								//Ա������
	[employeeEducationId] [int] NULL ,									//������α��
	[employeeHomeTel] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//��ͥ�绰
	[employeeMobile] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,	//�ƶ��绰
	[employeeCard] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//���֤��
	[employeeEmail] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,		//�ʼ���ַ
	[employeeAddress] [nvarchar] (80) COLLATE Chinese_PRC_CI_AS NULL 	//��ס��ַ
) ON [PRIMARY]
*/
public class Employee {
	private String employeeNo;
	private String employeeName;
	private String employeePassword;
	private String employeeSex;
	private Date employeeBirthday;
	private int employeeEducationId;
	private String employeeHomeTel;
	private String employeeMobile;
	private String employeeCard;
	private String employeeEmail;
	private String employeeAddress;
	
	public String getEmployeeAddress() {
		return employeeAddress;
	}
	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}
	public Date getEmployeeBirthday() {
		return employeeBirthday;
	}
	public void setEmployeeBirthday(Date employeeBirthday) {
		this.employeeBirthday = employeeBirthday;
	}
	public String getEmployeeCard() {
		return employeeCard;
	}
	public void setEmployeeCard(String employeeCard) {
		this.employeeCard = employeeCard;
	}
	public int getEmployeeEducationId() {
		return employeeEducationId;
	}
	public void setEmployeeEducationId(int employeeEducationId) {
		this.employeeEducationId = employeeEducationId;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeHomeTel() {
		return employeeHomeTel;
	}
	public void setEmployeeHomeTel(String employeeHomeTel) {
		this.employeeHomeTel = employeeHomeTel;
	}
	public String getEmployeeMobile() {
		return employeeMobile;
	}
	public void setEmployeeMobile(String employeeMobile) {
		this.employeeMobile = employeeMobile;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public String getEmployeeSex() {
		return employeeSex;
	}
	public void setEmployeeSex(String employeeSex) {
		this.employeeSex = employeeSex;
	}
	
}
