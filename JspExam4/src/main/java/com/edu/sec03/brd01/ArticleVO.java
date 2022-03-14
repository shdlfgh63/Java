package com.edu.sec03.brd01;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;

//-----------------------------------------------------------------------------------------------------------
// 게시글
//-----------------------------------------------------------------------------------------------------------
public class ArticleVO {

	private	int		level;
	private	int		articleNO;
	private	int		parentNO;
	private	String	title;
	private	String	content;
	private	String	imageFileName;
	private	String	id;
	private	Date	writeDate;
	
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO() {}
	
	//-----------------------------------------------------------------------------------------------------------
	public ArticleVO(int level, int articleNO, int parentNO, String title, String content,
					 String imageFileName, String id) {
		super();
		this.level			= level;
		this.articleNO		= articleNO;
		this.parentNO		= parentNO;
		this.title			= title;
		this.content		= content;
		this.imageFileName	= imageFileName;
		this.id				= id;
	}
	//-----------------------------------------------------------------------------------------------------------

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getArticleNO() {
		return articleNO;
	}

	public void setArticleNO(int articleNO) {
		this.articleNO = articleNO;
	}

	public int getParentNO() {
		return parentNO;
	}

	public void setParentNO(int parentNO) {
		this.parentNO = parentNO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageFileName() {
		try {
			// 파일이름에 특수문자가 포함되어 있을 경우 인코딩한다.
			if(imageFileName != null && imageFileName.length() != 0) {
				this.imageFileName = URLDecoder.decode(imageFileName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		try {
			// 파일이름에 특수문자가 포함되어 있을 경우 인코딩한다.
			// 길이 체크는 URLEncoder.encode 사용하기 위한 필수 조건이다.
			if(imageFileName != null && imageFileName.length() != 0) {
				this.imageFileName = URLEncoder.encode(imageFileName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return "ArticleVO [level=" + level + ", articleNO=" + articleNO + ", parentNO=" + parentNO + ", title=" + title
				+ ", content=" + content + ", imageFileName=" + imageFileName + ", id=" + id + ", writeDate="
				+ writeDate + "]";
	}
	
} // End - public class ArticleVO

