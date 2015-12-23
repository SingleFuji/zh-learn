package com.zh.utils.xstream.dif;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * 
 * @copyrights 新国都技术股份有限公司
 * @author hang_zhou
 * @Date 2015年6月30日
 * @desc 转清商户新增和修改响应对象
 * @version
 * @tosee
 *
 */
@XStreamAlias("TransSettleXmlAddResp")
public class TransSettleXmlAddResp extends TransSettleXmlBaseResp
{
	private static final long serialVersionUID = -1533885611434310535L;
	// 商户号
	@XStreamAlias("mch_id")
	private String mch_id;
	// 商户识别码
	@XStreamAlias("sub_mch_id")
	private String sub_mch_id;
	// 处理结果
	@XStreamAlias("result_code")
	private String result_code;
	// 处理信息
	@XStreamAlias("result_msg")
	private String result_msg;

	public String getMch_id()
	{
		return mch_id;
	}

	public void setMch_id(String mch_id)
	{
		this.mch_id = mch_id;
	}

	public String getSub_mch_id()
	{
		return sub_mch_id;
	}

	public void setSub_mch_id(String sub_mch_id)
	{
		this.sub_mch_id = sub_mch_id;
	}

	public String getResult_code()
	{
		return result_code;
	}

	public void setResult_code(String result_code)
	{
		this.result_code = result_code;
	}

	public String getResult_msg()
	{
		return result_msg;
	}

	public void setResult_msg(String result_msg)
	{
		this.result_msg = result_msg;
	}
	
}
