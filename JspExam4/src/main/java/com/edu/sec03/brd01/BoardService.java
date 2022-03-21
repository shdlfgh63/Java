package com.edu.sec03.brd01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//-----------------------------------------------------------------------------------------------------------
// public class BoardService
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
	// 게시글 전체 목록 보기(section, pageNum) : listArticles()를 오버로딩해서 만들었다.
	//-----------------------------------------------------------------------------------------------------------
	public Map listArticles(Map<String, Integer> pagingMap) {
		
		Map	articlesMap	= new HashMap();
		
		List<ArticleVO> articlesList 	= boardDAO.selectAllArticles(pagingMap);
		int				totArticles		= boardDAO.selectTotArticles();
			
		articlesMap.put("articlesList", articlesList);
		articlesMap.put("totArticles",  totArticles);
		
		return articlesMap;
		
	} // End - public Map listArticles(Map<String, Integer> pagingMap)
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 전체 목록 보기
	//-----------------------------------------------------------------------------------------------------------
		public List<ArticleVO> listArticles() {
		System.out.println("서비스의 게시글목록 보기에 진입했습니다.");
		
		List<ArticleVO> articlesList = boardDAO.selectAllArticles();
		// DAO에서 찾아온 게시글 전체 목록을 콘트롤러에게 돌려준다.
		return articlesList;
	} // End - public List<ArticleVO> listArticles()

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	public int addArticle(ArticleVO article) {
		System.out.println("서비스의 게시글 등록하기에 진입했습니다.");
		
		// 게시글을 등록한 후에 등록한 게시글의 번호를 Controller에 돌려준다.
		return boardDAO.insertNewArticle(article);
		
	} // End - public int addArticle(ArticleVO article)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세보기
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO viewArticle(int articleNO) {
		System.out.println("서비스의 게시글 상세보기에 진입했습니다.");
		
		ArticleVO articleVO = null;
		articleVO = boardDAO.selectArticle(articleNO);
		return articleVO;
		
	} // End - public ArticleVO viewAritcle(int articleNO)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정하기
	//-----------------------------------------------------------------------------------------------------------
	public void modArticle(ArticleVO articleVO) {
		System.out.println("서비스의 게시글 수정하기에 진입했습니다.");
		
		boardDAO.updateArticle(articleVO);
		
	} // End - public void modArticle(ArticleVO articleVO)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public List<Integer> removeArticle(int articleNO) {
		System.out.println("서비스의 게시글 삭제하기에 진입했습니다.");
		
		// 글을 삭제하기 전 글 번호들을 객체에 저장한다.
		List<Integer> articleNOList = boardDAO.selectRemovedArticles(articleNO);
		
		// 게시글 번호에 해당하는 게시글 자료를 삭제한다.
		boardDAO.deleteArticle(articleNO);
		
		// 삭제한 글 번호 목록을 컨트롤러로 반환한다.
		return articleNOList;
		
	} // End - public List<Integer> removeArticle(int articleNO)

	//-----------------------------------------------------------------------------------------------------------
	// 답글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	public int addReply(ArticleVO articleVO) {
		System.out.println("서비스의 답글 등록하기에 진입했습니다.");
		
		return boardDAO.insertNewArticle(articleVO);
		
	} // End - public int addReply(ArticleVO articleVO)
	
	
	
	

} // End - public class BoardService







