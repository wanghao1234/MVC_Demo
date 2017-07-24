package com.company.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.company.dao.pojo.Product;
import com.company.service.factory.ServiceFactory;


@WebServlet("/ProductFindByIdServlet")
public class ProductFindByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1-取值 
		int id = Integer.parseInt(request.getParameter("pid"));
		//2-处理 
		Product product = ServiceFactory.getProductInstance().findById(id);
		//3-反馈  
		if(product!= null){
			List<Product> productList = new ArrayList<Product>();
			productList.add(product);
			request.setAttribute("productListFromServer", productList);
			request.getRequestDispatcher("/product_findAll.jsp").forward(request, response);
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("errMsg", "Didn't found this product!");
			response.sendRedirect(request.getContextPath()+"/error.jsp");
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
