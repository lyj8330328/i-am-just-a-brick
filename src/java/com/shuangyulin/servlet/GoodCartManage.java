package com.shuangyulin.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shuangyulin.dao.GoodCartDAO;
import com.shuangyulin.javabean.GoodCart;

/*����Ա��������Ʒʱ���ﳵ����Ŀ��Ʋ�*/
public class GoodCartManage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action.equals("add")) { /*���ﳵ�м�����Ʒ*/
			/*
		     �Բ���,�˹��ܱ�����,����Ҫ�����ѿ�����ϵ����
		     ����: ������ ����:1985��1��7��(ũ��)
		     ����: ˫����(����˫����) Ѫ��: 0
		     ��ҵѧУ: 2007���ڳɶ�����ѧ
		     �����ַ: �Ĵ�������Ϫ���ɽ��5��
		     ��ϵQQ: 287307421 ��ϵ�绰: 13558690869
		     ��ϵEmail: wangjianlin1985@126.com
		     ˫���ֵ��Թ������Ա���: http://shop34864101.taobao.com
		     ˫���ֵ��Թ��������ĵ�: http://287307421.paipai.com
	          ��������Ǿ����ҵ���ƻ�����,������ϵ�����,����ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver�ṩ����ָ��!
	       */ 
		} else if(action.equals("del")) { /*ɾ�����ﳵ��ĳ��������Ϣ*/
			/*
		     �Բ���,�˹��ܱ�����,����Ҫ�����ѿ�����ϵ����
		     ����: ������ ����:1985��1��7��(ũ��)
		     ����: ˫����(����˫����) Ѫ��: 0
		     ��ҵѧУ: 2007���ڳɶ�����ѧ
		     �����ַ: �Ĵ�������Ϫ���ɽ��5��
		     ��ϵQQ: 287307421 ��ϵ�绰: 13558690869
		     ��ϵEmail: wangjianlin1985@126.com
		     ˫���ֵ��Թ������Ա���: http://shop34864101.taobao.com
		     ˫���ֵ��Թ��������ĵ�: http://287307421.paipai.com
	          ��������Ǿ����ҵ���ƻ�����,������ϵ�����,����ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver�ṩ����ָ��!
	       */ 
		} else if(action.equals("finishSell")) { /*�������*/
			/*
		     �Բ���,�˹��ܱ�����,����Ҫ�����ѿ�����ϵ����
		     ����: ������ ����:1985��1��7��(ũ��)
		     ����: ˫����(����˫����) Ѫ��: 0
		     ��ҵѧУ: 2007���ڳɶ�����ѧ
		     �����ַ: �Ĵ�������Ϫ���ɽ��5��
		     ��ϵQQ: 287307421 ��ϵ�绰: 13558690869
		     ��ϵEmail: wangjianlin1985@126.com
		     ˫���ֵ��Թ������Ա���: http://shop34864101.taobao.com
		     ˫���ֵ��Թ��������ĵ�: http://287307421.paipai.com
	          ��������Ǿ����ҵ���ƻ�����,������ϵ�����,����ASP,VB,DELPHI,JSP,C,asp,asp.net,access,sqlserver�ṩ����ָ��!
	       */ 
		}
	}

}
