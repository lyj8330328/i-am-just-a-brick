package com.shuangyulin.javabean;

/*��ʵ��ӳ�����ݿ��е�Ա������ҵ����Ϣ��
CREATE TABLE [dbo].[employeeSellResult] (
	[employeeNo] [varchar] (20) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//Ա�����
	[employeeName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,		//Ա������
	[employeeSellMoney] [float] NULL 									//���۽��
) ON [PRIMARY]
*/

public class EmployeeSellResult {
	private String employeeNo;
	private String employeeName;
	private float employeeSellMoney;
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
	public float getEmployeeSellMoney() {
		return employeeSellMoney;
	}
	public void setEmployeeSellMoney(float employeeSellMoney) {
		this.employeeSellMoney = employeeSellMoney;
	}
}
