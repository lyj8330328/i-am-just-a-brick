package com.shuangyulin.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.shuangyulin.DBUtils.DB;
import com.shuangyulin.javabean.GoodCart;
import com.shuangyulin.javabean.SellInfo;

/*���ڹ��ﳵ��ҵ����*/
public class GoodCartDAO {
	private String errMessage;

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	
	/*����Ա����ŷ��ظ�Ա����Ӧ�Ĺ��ﳵ��Ϣ��һ�����ﳵ������һ���˿Ͷ���*/
	public static ArrayList<GoodCart> GetGoodCartByEmployeeNo(String employeeNo) {
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
		return null;
	}
	

    /*������Ʒ���ﳵ��¼��ź�Ҫ�޸ĵ�������Ŀִ�и��²���*/
    public boolean UpdateGoodCartInfo(int goodCartId, int goodCount)
    {
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
        return true;
    }
    /*������Ʒ���۹��ﳵ���ʵ�ָü�¼��ɾ������*/
    public boolean DeleteGoodCartInfo(int goodCartId)
    {
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
        return true;
    }
    /*������Ʒ���۹��ﳵ��Ϣģ�Ͷ��󣬽���Ʒ������Ϣ���뵽ϵͳ��*/
    public boolean AddGoodCartInfo(GoodCart goodCart)
    {
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
        return true;
    }
    /*����Ա����ŵõ����ﳵ���ܵ���Ʒ����*/
    public static int GetTotalGoodCountInCart(String employeeNo)
    {
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
        return 0;
    }
    /*����Ա����ŵõ����ﳵ����Ʒ���ܼ۸�*/
    public static float GetTotalPriceInCart(String employeeNo)
    {
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
       return 0.0f;
    }
    /*���ݴ��ݹ���������СƱ�ź�Ա�����ʵ�ֶ�Ӧ���ﳵ����Ʒ������Ϣ�ĵǼ�,Ȼ����չ��ﳵ*/
    public static boolean AddGoodSellInfoInCart(String sellNo, String employeeNo)
    {
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
        return true;
    }
}
