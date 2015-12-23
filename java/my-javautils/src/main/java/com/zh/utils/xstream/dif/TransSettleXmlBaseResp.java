package com.zh.utils.xstream.dif;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @copyrights 新国都技术股份有限公司
 * @author hang_zhou
 * @Date 2015年6月30日
 * @desc 转清商户响应基础数据
 * @version
 * @tosee
 *
 */
@XStreamAlias("TransSettleXmlBaseResp")
public class TransSettleXmlBaseResp implements Serializable
{

    private static final long serialVersionUID = -6400605085352755892L;
	// 返回状态码
    @XStreamAlias("return_code")
	private String return_code;
	// 返回信息
    @XStreamAlias("return_msg")
	private String return_msg;

	public String getReturn_code()
	{
		return return_code;
	}

	public void setReturn_code(String return_code)
	{
		this.return_code = return_code;
	}

	public String getReturn_msg()
	{
		return return_msg;
	}

	public void setReturn_msg(String return_msg)
	{
		this.return_msg = return_msg;
	}
	
}

