package com.edu.sec03.brd01;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

//-----------------------------------------------------------------------------------------------------------
// public class BoardController
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
		articleVO	 = new ArticleVO();
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
				
			} else if(action.equals("/addArticle.do")) {	// 게시글 데이터 등록
				// 전송된 회원를 가져와서 테이블에 추가하는 작업을 한다.
				// 파일 업로드 기능을 사용하기 위해서 upload()로 요청을 전달해서 처리한다.
				int articleNO = 0;
				
				Map<String, String> articleMap = upload(request, response);
				
				String	title			= articleMap.get("title");
				String	content			= articleMap.get("content");
				String	imageFileName	= articleMap.get("imageFileName");
				
				articleVO.setParentNO(0);	// 새글의 부모 글 번호를 0으로 설정한다.
				articleVO.setId("hong");	// 새글 작성자의 ID를 hong으로 설정한다.
				articleVO.setTitle(title);	
				articleVO.setContent(content);
				articleVO.setImageFileName(imageFileName);

				System.out.println("imageFileName ==> " + imageFileName);
				System.out.println("imageFileName.length() ==> " + imageFileName.length());
				
				// 테이블에 새로운 글을 추가한 후에 새글에 대한 글 번호를 가져온다.
				articleNO =  boardService.addArticle(articleVO);
				
				// 파일을 첨부한 경우에만 실행한다.
				if(imageFileName != null && imageFileName.length() != 0) {
					// temp 폴더에 임시로 업로드된 파일 객체를 생성하낟.
					File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + imageFileName);
					File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + articleNO);
System.out.println("========================================");
					System.out.println(ARTICLE_IMAGE_REPO + "\\" + articleNO);
					destDir.mkdirs();
					// 임시폴더(temp)에 있는 파일을 articleNO에 해당하는 방으로 옮긴다.
					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				// 새 글이 등록되었다는 메시지를 보여준 후에 자바스크립트의 location 객체의
				// href 속성을 이용하여 글 목록 보기를 요청한다.
				PrintWriter pw = response.getWriter();
				pw.print("<script>"
						+" alert('새글을 추가하였습니다.');"
						+" location.href='" + request.getContextPath() + "/board/listArticles.do';"
						+"</script>");
				return;
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
	
	//-----------------------------------------------------------------------------------------------------------
	// 파일 올리기 메서드
	// 1. commons-fileupload를 사용하기 위해서는 라이브러리가 필요하다.
	// 	commons-fieupload : http://commons.apache.org/fileupload/
	//	commons-io : http://commons.apache.org/io/
	//	commons-fileupload-1.2.2.jar와 commons-io-2.1.jar
	//
	// 2. Web App Libraries (WebContent/WEB-INF/lib 또는 webapp/WEB-INF/lib) 폴더에 복사한다.
	//
	// 3. 입력폼의 method를 post로 지정하고, enctype 속성의 값을 multipart/form-data로 설정한다.
	//
	//-----------------------------------------------------------------------------------------------------------

	//-----------------------------------------------------------------------------------------------------------
	// 파일 올리기 메서드
	//-----------------------------------------------------------------------------------------------------------
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<String, String> articleMap = new HashMap<String, String>();
		String encoding = "utf-8";
		// 게시글 이미지 저장 폴더에 대해서 파일 객체를 생성한다.
		
		// 업로드할 파일의 경로를 지정한다.
		File currentDirPath 		= new File(ARTICLE_IMAGE_REPO);
		DiskFileItemFactory factory	= new DiskFileItemFactory();
		
		// 파일 경로를 설정한다.
		factory.setRepository(currentDirPath);
		// 업로드될 최대 파일의 크기를 설정한다.
		factory.setSizeThreshold(1024*1024); // 기본값은 10240 byte(10kb)
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			// request객체에서 매개변수를 List로 가져온다.
			List items = upload.parseRequest(request);
			for(int i = 0; i < items.size(); i++) {
				// 파일 업로드창에서 업로드된 항목들을 하나씩 가져다 작업한다.
				FileItem fileItem = (FileItem) items.get(i);
				
				// isFormField() : 일 파라미터 인지 여부를 반환한다. 일반파라미터이면 true를 반환한다.
				// 폼 필드이면 전송된 매개변수의 값을 출력한다.
				if(fileItem.isFormField()) {
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					articleMap.put(fileItem.getFieldName(), fileItem.getString(encoding));
				} else {
					// 폼 필드가 아니면 파일 업로드 기능을 수행한다.
					System.out.println("매개변수이름 : " + fileItem.getFieldName());
					System.out.println("파일이름 : " + fileItem.getName());
					System.out.println("파일크기 : " + fileItem.getSize() + "bytes");
					
					// 업로드한 파일 이름을 가져온다.
					if(fileItem.getSize() > 0) {
						int idx = fileItem.getName().lastIndexOf("\\");
						if(idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						String fileName = fileItem.getName().substring(idx+1);
						System.out.println("파일명 : " + fileName);
						
						// 업로드한 파일 이름으로 저장소(currentDirPath)에 파일을 업로드한다.
						// File uploadFile = new File(currentDirPath + "\\" + fileName);
						// 파일이름이 중복되면 마지막에 올린 파일만 존재하게 되므로 임시 파일에 저장시키고,
						// 게시글 번호를 받게되면 게시글 번호의 폴더를 만들어 저장시키도록 한다.
						
						//---------------------------------------------------
						// upload()를 호출한 곳으로 articleMap에 파일정보를 넣어준다.
						//---------------------------------------------------
						articleMap.put(fileItem.getFieldName(), fileName);
						
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					}
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return articleMap;
		
	} // End - private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
	
	
	
	
	
} // End - public class BoardController
