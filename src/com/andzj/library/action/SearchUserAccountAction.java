package com.andzj.library.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.andzj.library.service.AdministratorService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SearchUserAccountAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	private AdministratorService administratorService;

	public void setAdministratorService(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}
	
	public String execute() {
		try 
		{
			ActionContext context = ActionContext.getContext();
			HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
			String searchMode = request.getParameter("mode");
			//all,account,nothing
			if ("all".equals(searchMode))
			{
				if (administratorService.findAllUserAccount())
				{
					return null;
				}
			}
			else if ("account".equals(searchMode))
			{
				String accountName = request.getParameter("search_words");
				if (administratorService.searchUserAccount(accountName))
				{
					return null;
				}
			}
			else if ("nothing".equals(searchMode))
			{
				return null;
			}
			else
			{
				return null;
			}
			return ERROR;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return ERROR;
	}
}

