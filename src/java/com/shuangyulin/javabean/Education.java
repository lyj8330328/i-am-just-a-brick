package com.shuangyulin.javabean;

/*��ʵ���Ӧ���ݿ���ѧ����Ϣ��
CREATE TABLE [dbo].[educationInfo] (
	[educationId] [int] IDENTITY (1, 1) NOT NULL ,						//ѧ����α��
	[educationName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL		//ѧ���������
) ON [PRIMARY]
*/

public class Education {
  private int educationId;
  private String educationName;
public int getEducationId() {
	return educationId;
}
public void setEducationId(int educationId) {
	this.educationId = educationId;
}
public String getEducationName() {
	return educationName;
}
public void setEducationName(String educationName) {
	this.educationName = educationName;
}
}
