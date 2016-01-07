package com.common.base.pojo;

import com.common.util.bean.BeansUtil;
import com.common.util.data.map.BaseDmp;
import com.common.util.data.map.Dmp;

public abstract class BaseVo {
	 public Dmp toDmp()
	  {
	    Dmp map = new BaseDmp();
	    BeansUtil.copyPropFromBean2LMap(this, map);
	    return map;
	  }

	  public String toXml(String pStyle)
	  {
	    Dmp map = new BaseDmp();
	    return map.toXml(pStyle);
	  }

	  public String toJson()
	  {
	    Dmp map = new BaseDmp();
	    return map.toJson();
	  }

}
