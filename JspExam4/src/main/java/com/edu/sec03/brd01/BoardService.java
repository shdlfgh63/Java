package com.edu.sec03.brd01;

import java.util.List;





//-----------------------------------------------------------------------------------------------------------
//public class BoardService
//-----------------------------------------------------------------------------------------------------------
public class BoardService {
	BoardDAO boardDAO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 생성자
	//-----------------------------------------------------------------------------------------------------------
	public BoardService() {
		// 생성자 호출 시 BoardDAO 객체를 생성한다.
		boardDAO = new BoardDAO();
	} // End - public BoardService()

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 전체 목록 보기
	//-----------------------------------------------------------------------------------------------------------
	public List<ArticleVO> listArticles() {

		System.out.println("서비스의 게시글목록 보기에 진입했습니다.");

		
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		// DAO에서 찾아온 게시글 전체 목록을 콘트롤러에게 돌려준다.
		return articlesList;
	} // End - public List<ArticleVO> listArticles()
	

} // End - public class BoardService