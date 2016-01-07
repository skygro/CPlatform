package com.common.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class TreeNodes implements Serializable
{

    public String getOrdered()
    {
        return ordered;
    }

    public void setOrdered(String ordered)
    {
        this.ordered = ordered;
    }

    public String getCode()
    {
        return code;
    }

    public Map getAttribute()
    {
        return attribute;
    }

    public void setAttribute(Map attribute)
    {
        this.attribute = attribute;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public TreeNodes()
    {
    }

    public TreeNodes(String newId, String newText, boolean newLeaf, List newChildren)
    {
        id = newId;
        text = newText;
        leaf = newLeaf;
        children = newChildren;
    }

    public List getChildren()
    {
        return children;
    }

    public String getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }

    public boolean isLeaf()
    {
        return leaf;
    }

    public void setChildren(List newChildren)
    {
        children = newChildren;
    }

    public void setId(String newId)
    {
        id = newId;
    }

    public void setLeaf(boolean newLeaf)
    {
        leaf = newLeaf;
    }

    public void setText(String newText)
    {
        text = newText;
    }
    public void setCode(String s)
    {
    	code=s;
    }

    public boolean isExpanded()
    {
        return expanded;
    }

    public void setExpanded(boolean expanded)
    {
        this.expanded = expanded;
    }

    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isCascadeCheck() {
		return cascadeCheck;
	}

	public void setCascadeCheck(boolean cascadeCheck) {
		this.cascadeCheck = cascadeCheck;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	private static final long serialVersionUID = 1L;
    private String id;
    private String text;
    private boolean leaf;
    private List children;
    private String url;
    private Map attribute;
    private String code;
    private String ordered;
    private boolean expanded;
    private String state;
    private boolean cascadeCheck;
    private boolean checked;

}