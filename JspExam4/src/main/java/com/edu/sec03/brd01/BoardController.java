package com.edu.sec03.brd01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//-----------------------------------------------------------------------------------------------------------
//public class BoardController
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static String ARTICLE_IMAGE_REPO = "C:\\data\\article_image";
	BoardService	boardService;
	ArticleVO		articleVO;

	//-----------------------------------------------------------------------------------------------------------
	// init()
	//-----------------------------------------------------------------------------------------------------------
	public void init(ServletConfig config) throws ServletException {
		// 서블릿 초기화 시에 BoardService 객체를 생성한다.
		boardService = new BoardService();
	}

	//-----------------------------------------------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	//-----------------------------------------------------------------------------------------------------------
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	//-----------------------------------------------------------------------------------------------------------
	// doHandle()
	//-----------------------------------------------------------------------------------------------------------
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("콘트롤러의 doHandle에 진입했습니다.");
		
		String	nextPage	= "";
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String	action = request.getPathInfo();	// 요청명을 가져온다.
		System.out.println("콘트롤러의 doHandle action : " + action);
		
		try {
			List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
			
			if(action == null) {
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board01/listArticles.jsp";
				
			} else if(action.equals("/listArticles.do")) {	// 전체 게시글 목록 요청
				articlesList = boardService.listArticles();
				request.setAttribute("articlesList", articlesList);
				nextPage = "/board01/listArticles.jsp";
				
			} else if(action.equals("/articleForm.do")) {	// 게시글 등록 화면 요청
				nextPage = "/board01/articleForm.jsp";
				
			} else {
				nextPage = "/board01/listArticles.jsp";
			}
			
			// nextPage 에 있는 내용대로 포워딩시킨다.
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // End - private void doHandle(HttpServletRequest request, HttpServletResponse response)
	
} // End - public class BoardController
