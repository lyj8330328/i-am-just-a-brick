package com.shuangyulin.javabean;
/*��Ӧ����Ϣ��
CREATE TABLE [dbo].[supplierInfo] (
	[supplierName] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//��Ӧ�̹�˾����
	[supplierLawyer] [nvarchar] (4) COLLATE Chinese_PRC_CI_AS NULL ,	//��Ӧ�̷��˴���
	[supplierTelephone] [varchar] (11) COLLATE Chinese_PRC_CI_AS NULL ,	//��Ӧ�̵绰
	[supplierAddress] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL 	//��Ӧ�̵�ַ
) ON [PRIMARY]
*/
public class Supplier {
	private String supplierName;
	private String supplierLawyer;
	private String supplierTelephone;
	private String supplierAddress;
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getSupplierLawyer() {
		return supplierLawyer;
	}
	public void setSupplierLawyer(String supplierLawyer) {
		this.supplierLawyer = supplierLawyer;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierTelephone() {
		return supplierTelephone;
	}
	public void setSupplierTelephone(String supplierTelephone) {
		this.supplierTelephone = supplierTelephone;
	}
	
}
