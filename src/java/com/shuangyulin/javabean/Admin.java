package com.shuangyulin.javabean;

/*��ʵ��javabean��Ӧ���ݿ��еĹ���Ա��Ϣ��
 
CREATE TABLE [dbo].[admin] (
	[adminUsername] [varchar] (20) COLLATE Chinese_PRC_CI_AS NOT NULL ,	//����Ա�ʺ�
	[adminPassword] [varchar] (32) COLLATE Chinese_PRC_CI_AS NULL 		//����Ա����
) ON [PRIMARY]
*/
public class Admin {
	private String adminUsername;
	private String adminPassword;
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
}
