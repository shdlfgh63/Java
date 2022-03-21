package com.edu.sec03.brd01;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//-----------------------------------------------------------------------------------------------------------
// 게시글 관련 DAO
//-----------------------------------------------------------------------------------------------------------
public class BoardDAO {

	private	DataSource	dataFactory;
	Connection			conn;
	PreparedStatement	pstmt;
	
	//-----------------------------------------------------------------------------------------------------------
	// 생성자
	//-----------------------------------------------------------------------------------------------------------
	public BoardDAO() {
		try {
			Context	ctx	= new InitialContext();
			Context	env	= (Context) ctx.lookup("java:/comp/env");
			dataFactory	= (DataSource) env.lookup("jdbc/oracle");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // End - public BoardDAO()

	//-----------------------------------------------------------------------------------------------------------
	// 게시글(t_board) 전체 건수를 가져오기 
	//-----------------------------------------------------------------------------------------------------------
	public int selectTotArticles() {
		
		ResultSet	rs	= null;
		String		sql	= "";
		int			returnCount	= 0;
		
		try {
			conn	= dataFactory.getConnection();
			sql		= "SELECT COUNT(articleNO) FROM t_board ";
			pstmt	= conn.prepareStatement(sql);
			rs		= pstmt.executeQuery();
			
			if(rs.next() ) {
				returnCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnCount;
		
	} // End - public int selectTotArticles()

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 전체 목록 가져오기 (Paging)
	//-----------------------------------------------------------------------------------------------------------
	public List selectAllArticles(Map pagingMap) {
		
		System.out.println("DAO의 게시글 전체 목록 가져오기 (Paging)에 진입했습니다.");

		List articlesList = new ArrayList();
		// section과 pageNum를 추출한다.
		int	section	= (Integer) pagingMap.get("section");
		int	pageNum	= (Integer) pagingMap.get("pageNum");
		
		ResultSet	rs	= null;
		String		sql	= "";
				
		try {
			conn	= dataFactory.getConnection();
			sql	 = "SELECT * FROM (";
			sql	+= 		"SELECT	rownum AS recNum, ";
			sql	+= 		"		LVL, articleNO, parentNO, title, ";
			sql	+= 		"		content, id, writeDate ";
			sql	+= 		"FROM ";	
			sql	+= 			"(SELECT 	LEVEL AS LVL, ";
			sql	+= 			"articleNO,	parentNO, title, content, id, writeDate ";
			sql	+= 			"FROM	t_board ";
			sql	+= 			"START WITH parentNO = 0 ";
			sql	+= 			"CONNECT BY PRIOR ARTICLENO = PARENTNO ";
			sql	+= 			"ORDER siblings BY ARTICLENO DESC) ";
			sql	+=		") ";
			sql	+= 	"WHERE ";
			sql	+= 		"recNum between (?-1)*100+(?-1)*10+1 ";
			sql	+= 				"AND 	(?-1)*100+(?)*10" ;
			
			System.out.println("selectAllArticles ==> " + sql);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, section);
			pstmt.setInt(2, pageNum);
			pstmt.setInt(3, section);
			pstmt.setInt(4, pageNum);
			
			rs	= pstmt.executeQuery();
			
			while(rs.next()) {
				int		level		= rs.getInt("LVL");
				int		articleNO	= rs.getInt("articleNO");
				int		parentNO	= rs.getInt("parentNO");
				String	title		= rs.getString("title");
				String	content		= rs.getString("content");
				String	id			= rs.getString("id");
				Date	writeDate	= rs.getDate("writeDate");
				
				ArticleVO	articleVO	= new ArticleVO();
				
				articleVO.setLevel(level);
				articleVO.setArticleNO(articleNO);
				articleVO.setParentNO(parentNO);
				articleVO.setTitle(title);
				articleVO.setContent(content);
				articleVO.setId(id);
				articleVO.setWriteDate(writeDate);
				
				articlesList.add(articleVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articlesList;
		
	} // End - public List selectAllArticles(Map pagingMap)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 전체 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	public List<ArticleVO> selectAllArticles() {
		
		System.out.println("DAO의 게시글목록 보기에 진입했습니다.");

		List<ArticleVO> articlesList = new ArrayList<ArticleVO>();
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection(); // 커넥션풀의 연결정보를 가져온다.
			
			String sql = "";
			sql	= "SELECT LEVEL, articleNO, parentNO, title, content, id, writeDate "
				+ "FROM t_board "
				+ "START WITH parentNO=0 "
				+ "CONNECT BY PRIOR articleNO=parentNO "
				+ "ORDER SIBLINGS BY articleNO DESC ";
			pstmt	= conn.prepareStatement(sql);
			rs 		= pstmt.executeQuery();
			
			while(rs.next()) {
				int		level		= rs.getInt("level");
				int		articleNO	= rs.getInt("articleNO");
				int		parentNO	= rs.getInt("parentNO");
				String	title		= rs.getString("title");
				String	content		= rs.getString("content");
				String	id			= rs.getString("id");
				Date	writeDate	= rs.getDate("writeDate");
				
				// 글 정보를 ArticleVO 객체의 속성에 설정한다.
				ArticleVO article	= new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				
				articlesList.add(article);
			}
			 rs.close();
			 pstmt.close();
			 conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articlesList;
	} // End - public List selectAllArticles()

	//-----------------------------------------------------------------------------------------------------------
	// 새글을 등록하기 위해서 글번호를 추출한다.
	//-----------------------------------------------------------------------------------------------------------
	private int getNewArticleNO() {
		System.out.println("DAO의 새글 번호 추출하기에 진입하였습니다.");
		try {
			conn	= dataFactory.getConnection();
			// 게시글 번호 중 가장 큰 번호보다 1 더 큰수를 추출한다.
			String	sql = "SELECT MAX(articleNO) FROM t_board ";
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				return (rs.getInt(1) + 1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	} // End - private int getNewArticleNO()
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 등록하기
	//-----------------------------------------------------------------------------------------------------------
	public int insertNewArticle(ArticleVO article) {
		
		System.out.println("DAO의 게시글목록 보기에 진입했습니다.");
		int		articleNO		= getNewArticleNO();
		
		try {
			conn	= dataFactory.getConnection();
			// int		articleNO		= getNewArticleNO();
			int		parentNO		= article.getParentNO();
			String	title			= article.getTitle();
			String	content			= article.getContent();
			String	id				= article.getId();
			String	imageFileName	= article.getImageFileName();
			
			String	sql = ""; 
			sql  = "INSERT INTO t_board ";
			sql	+= "(articleNO, parentNO, title, content, imageFileName, id) ";
			sql += "VALUES(?, ?, ?, ?, ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt	(1, articleNO);
			pstmt.setInt	(2, parentNO);
			pstmt.setString	(3, title);
			pstmt.setString	(4, content);
			pstmt.setString	(5, imageFileName);
			pstmt.setString	(6, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNO;
	} // End - public void insertNewArticle(ArticleVO article)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 상세보기
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO selectArticle(int articleNO) {
		
		System.out.println("DAO의 게시글 상세 보기에 진입했습니다.");
		
		ArticleVO 	articleVO = new ArticleVO();
		ResultSet 	rs	= null;
		String		sql	= "";
		
		try {
			conn = dataFactory.getConnection();
			
			// 게시글 번호에 해당하는 
			// articleNO, parentNO, title, content, imageFileName, id, writeDate를 찾아오자!
			// 단, 이미지파일명이 없는 경우는 이미지파일명을 'null'로 지정한다.
			
			sql	 = "SELECT articleNO, parentNO, title, content, ";
			sql	+= "NVL(imageFileName, 'null') as imageFileName, id, writeDate ";
			sql	+= "FROM t_board ";
			sql += "WHERE articleNO = ? ";
			
			pstmt	= conn.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			
			rs		= pstmt.executeQuery();
			// 실 데이터를 가리키기 위해 커서를 이동시킨다.
			rs.next();
			int		_articleNO		= rs.getInt("articleNO");
			int		parentNO		= rs.getInt("parentNO");
			String	title			= rs.getString("title");
			String	content			= rs.getString("content");
			// 파일이름에 특수문자가 있으면 인코딩한다.
			String	imageFileName	= URLEncoder.encode(rs.getString("imageFileName"), "UTF-8");
			String	id				= rs.getString("id");
			Date	writeDate		= rs.getDate("writeDate");
			
			articleVO.setArticleNO(_articleNO);
			articleVO.setParentNO(parentNO);
			articleVO.setTitle(title);
			articleVO.setContent(content);
			articleVO.setImageFileName(imageFileName);
			articleVO.setId(id);
			articleVO.setWriteDate(writeDate);
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articleVO;
		
	} // End - public ArticleVO selectArticle(int articleNO)
	

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 수정하기
	//-----------------------------------------------------------------------------------------------------------
	public void updateArticle(ArticleVO articleVO) {
		
		// 작업할 데이터를 분류해 놓는다.
		int		articleNO		= articleVO.getArticleNO();
		String	title			= articleVO.getTitle();
		String	content			= articleVO.getContent();
		String	imageFileName	= articleVO.getImageFileName();
		String	sql				= "";
		
		try {
			conn = dataFactory.getConnection();
			sql	 = "UPDATE t_board ";
			sql	+= "SET title=?, content=? ";
			// 이미지 파일이 있는 경우만 이미지를 업데이트 한다. 
			if(imageFileName != null && imageFileName.length() != 0) {
				sql += ", imageFIleName=? ";
			}
			sql	+= "WHERE articleNO = ? ";
			System.out.println("sql ==> " + sql);
			
			pstmt	= conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			// 이미지 파일이 있는 경우만 이미지를 업데이트 한다. 
			if(imageFileName != null && imageFileName.length() != 0) {
				pstmt.setString	(3, imageFileName);
				pstmt.setInt	(4, articleNO);
			} else {
				pstmt.setInt(3, articleNO);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // End - public void updateArticle(ArticleVO articleVO)

	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제 대상 목록 추출하기
	//-----------------------------------------------------------------------------------------------------------
	public List<Integer> selectRemovedArticles(int articleNO) {
		
		System.out.println("DAO의 게시글 삭제 대상 목록 추출하기에 진입했습니다.");
		
		List<Integer> articleNOList = new ArrayList<Integer>();
		ResultSet	rs	= null;
		String		sql	= "";
		
		try {
			conn	= dataFactory.getConnection();
			
			// articleNO와 articleNO에 딸린 자식 글 번호를 추출하자.
			sql	 = "SELECT  articleNO ";
			sql	+= "FROM    t_board ";
			sql	+= "START WITH articleNO=? ";
			sql	+= "CONNECT BY PRIOR articleNO = parentNO ";

			pstmt	= conn.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			rs	= pstmt.executeQuery();
			
			while(rs.next() ) {
				articleNO	= rs.getInt("articleNO");
				
				articleNOList.add(articleNO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleNOList;
		
	} // End - public List<Integer> selectRemovedArticles(int articleNO)
	
	//-----------------------------------------------------------------------------------------------------------
	// 게시글 삭제하기
	//-----------------------------------------------------------------------------------------------------------
	public void deleteArticle(int articleNO) {
		
		System.out.println("DAO의 게시글 삭제하기에 진입했습니다.");
		String	sql	= "";
		
		try {
			conn	= dataFactory.getConnection();
			
			sql	 = "DELETE FROM t_board ";
			sql	+= "WHERE articleNO IN ";
			sql	+= "(SELECT  articleNO ";
			sql	+= "FROM    t_board ";
			sql	+= "START WITH articleNO = ? ";
			sql	+= "CONNECT BY PRIOR articleNO = parentNO)";

			pstmt	= conn.prepareStatement(sql);
			pstmt.setInt(1, articleNO);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // End - public void deleteArticle(int articleNO)
	
	
	
} // End - public class BoardDAO








