package com.edu.sec03.brd01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	
} // End - public class BoardDAO





