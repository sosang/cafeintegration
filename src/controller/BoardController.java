package controller;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import logic.AdminVo;
import logic.BoardFaq;
import logic.BoardFaqService;
import logic.BoardNotice;
import logic.BoardNoticeService;
import logic.BoardQa;
import logic.BoardQaComments;
import logic.BoardQaCommentsService;
import logic.BoardQaService;
import logic.BoardReviews;
import logic.BoardReviewsComments;
import logic.BoardReviewsCommentsService;
import logic.BoardReviewsService;
import logic.MemberVo;
import logic.SaveFilePathTo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

	@Autowired
	BoardNoticeService boardNoticeService;
	@Autowired
	BoardQaService boardQaService;
	@Autowired
	BoardQaCommentsService boardQaCommentsService;
	@Autowired
	BoardReviewsService boardReviewsService;
	@Autowired
	BoardReviewsCommentsService boardReviewsCommentsService;
	@Autowired
	BoardFaqService boardFaqService;

	private FileOutputStream fos;

	// 공지사항 목록 보기
	@RequestMapping
	public ModelAndView boardNotice(HttpServletRequest request, Integer pageNo) throws Throwable{
		// 공지사항 목록 취득
		List<BoardNotice> boardNoticeList = null;
		boardNoticeList = this.boardNoticeService.getBoardNoticeList(request, pageNo);
		// 모델 생성
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("boardNoticeList", boardNoticeList);

		// 반환값인 ModelAndView 인스턴스 생성
		ModelAndView modelAndView = new ModelAndView("board/boardNotice");
		modelAndView.addAllObjects(model);

		return modelAndView;
	}

	// 공지사항 내용 보기
	@RequestMapping
	public ModelAndView boardNoticeDetail(Integer pageNo, Integer bdNoNtc){
		//선택된 공지번호로 부터 공지 내용 취득
		ModelAndView modelAndView = new ModelAndView();
//		this.boardNoticeService.countUp(bdNoNtc); // 공지사항 내용을 볼 때 조회수를 올려줌
		BoardNotice boardNotice = this.boardNoticeService.getBoardNoticeByBdNoNtc(bdNoNtc);
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardNotice", boardNotice);
		model.put("pageNo", pageNo);

		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	// 자유게시판 목록 보기
	@RequestMapping
	public ModelAndView boardQa(HttpServletRequest request, Integer pageNo) throws Throwable{
		// Q&A 목록 취득
		List<BoardQa> boardQaList = null;
		boardQaList = this.boardQaService.getBoardQaList(request, pageNo);
		// 모델 생성
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("boardQaList", boardQaList);
		// 반환값인 ModelAndView 인스턴스 생성
		ModelAndView modelAndView = new ModelAndView("board/boardQa");
		modelAndView.addAllObjects(model);

		return modelAndView;
	}
	// 자유게시판 내용 보기
	@RequestMapping
	public ModelAndView boardQaDetail(HttpServletRequest request, Integer pageNo, Integer bdNoQa) throws Throwable{
		//선택된 공지번호로 부터 공지 내용 취득
		ModelAndView modelAndView = new ModelAndView();
		BoardQa boardQa = this.boardQaService.getBoardQaByBdNoQa(bdNoQa);
		// 테그 합치기
		String ret1 = boardQa.getTitleQa();
		try { 
			ret1 = replace(ret1, "&lt;", "<"); 
		} catch (NullPointerException e) { 
			e.printStackTrace(); 
		}  
		boardQa.setTitleQa(ret1);
		// 테그 합치기
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardQa", boardQa);
		model.put("pageNo", pageNo);
		// 글쓴이와 로그인 한 자가 같을 때 메뉴를 보이게 할려구 해봅 시도를 해봄... 해봄
		model.put("writer", boardQa.getUserEmail());
		this.boardQaService.countUpForBdNoQa(bdNoQa);

		// 해당 게시물의 댓글들을 화면에 뿌려주고싶다.
		// Q&A 리플목록 취득
			List<BoardQaComments> boardQaCommentsList = null;
			boardQaCommentsList = this.boardQaCommentsService.getBoardQaCommentsList(request, pageNo, bdNoQa);
			// 모델 생성
			model.put("boardQaCommentsList", boardQaCommentsList);
			// 반환값인 ModelAndView 인스턴스 생성
			modelAndView.addAllObjects(model);
		// 게시물 목록을 화면에 뿌려주고싶다.
			// Q&A 목록 취득
			List<BoardQa> boardQaList = null;
			boardQaList = this.boardQaService.getBoardQaList(request, pageNo);
			// 모델 생성
			model.put("boardQaList", boardQaList);
			modelAndView.addAllObjects(model);
//		System.out.println(boardQa.getUserEmail());
//		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");
//		System.out.println(userKey.getUserEmail());
		return modelAndView;
	}

	// 댓글 쓰기(게시물 읽기 아래쪽에 달린거)
	@RequestMapping("boardQaWriCom")
	public ModelAndView boardQaWriCom(HttpServletRequest request, @Valid BoardQaComments bdQaCom, BindingResult bindingResult, Integer pageNo, Integer bdNoQa){
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");	// 글쓴사람을 잡아오기 위해서
		if(userKey == null){
			AdminVo admin = (AdminVo)request.getSession().getAttribute("ADMIN_KEY");
			String userIp = "255.0.0.1";
			MemberVo adminKey = new MemberVo();
			adminKey.setUserEmail(admin.getAdminEmail());
			adminKey.setUserAlias("운영자");
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("board/boardQaWrite");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			// 테그 방지
						String ret1 = bdQaCom.getBdQaCommentsContent();
						try { 
							ret1 = replace(ret1, "<", "&lt;"); 
							ret1 = replace(ret1, "&lt;br", "<br");
						} catch (NullPointerException e) { 
							e.printStackTrace(); 
						}  
						bdQaCom.setBdQaCommentsContent(ret1);
			// 테그 방지
			this.boardQaCommentsService.setCommentsAtBdNoQa(bdQaCom, adminKey, bdNoQa, userIp);
			String url="redirect:boardQaDetail.html?pageNo="+pageNo+"&bdNoQa="+bdNoQa;
			return new ModelAndView(url);	// 댓글을 쓰고 새로고침
		}else{
			// 아이피 알기
			String userIp1 = (String)request.getRemoteAddr();
			String userIp = userIp1.substring(0, 7)+"...";
			// 아이피 알기 끝
		
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("board/boardQaWrite");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			this.boardQaCommentsService.setCommentsAtBdNoQa(bdQaCom, userKey, bdNoQa, userIp);
			String url="redirect:boardQaDetail.html?pageNo="+pageNo+"&bdNoQa="+bdNoQa;
			return new ModelAndView(url);	// 댓글을 쓰고 새로고침
		}
	}

	// 댓글삭제(게시물 읽기 아래쪽에 달린거)
	@RequestMapping("boardQaDelCom")
	public ModelAndView boardQaDelCom(HttpServletRequest request, Integer pageNo, Integer bdNoQa, Integer bdNoQaComments){
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("bdNoQa", bdNoQa);
//		String bdNoQaComments1 = (String)request.getAttribute("bdNoQaComments");
//		Integer bdNoQaComments = Integer.parseInt(bdNoQaComments1);
		this.boardQaCommentsService.deleteBoardQaComment(bdNoQaComments);
		String url="redirect:boardQaDetail.html?pageNo="+pageNo+"&bdNoQa="+bdNoQa;
		return new ModelAndView(url);	// 댓글을 쓰고 새로고침
	}

	// 자유게시판 내용 쓰기 객체 생성(제한 : 로그인 한 자)
	@RequestMapping
	public ModelAndView boardQaWriteBefore(){
		ModelAndView modelAndView = new ModelAndView("board/boardQaWrite");
		modelAndView.addObject(new BoardQa());
		return modelAndView;
	}

	// 리플레이스 메소드
	public static String replace(String str, String pattern, String replace) { 
		int s = 0; 
		int e = 0; 
		StringBuffer result = new StringBuffer(); 

		while ((e = str.indexOf(pattern, s)) >= 0) { 
		result.append(str.substring(s, e)); 
		result.append(replace); 
		s = e+pattern.length(); 
		} 
		result.append(str.substring(s)); 
		return result.toString(); 
		}
	// 자유게시판 내용 쓰기 디비에 저장(제한 : 로그인 한 자)
	// boardQaWriteBefore()에서 생성한 객체에 내용을 사빕해 새 글을 작성한다
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView boardQaWrite(@Valid BoardQa boardQa, BindingResult bindingResult, HttpServletRequest request) throws Throwable{
		// 로그인된 USER_KEY를 이용해 userEmail을 추출한다

		// 하지만 boardQaWrite.jsp 에서 
		// <input type="hidden" value="${USER_KEY.userEmail }" name="userEmail">
		// 한줄로 끝냈다... 주석처리...ㅡ.ㅜ
//		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");
//		boardQa.setUserEmail(userKey.getUserEmail());
//		String userEmail = (String) boardQa.getUserEmail();
//		System.out.println(userEmail);
//		public static String encodeContent(String content) { 
//			String ret = content; 
//			try { 
//			ret = replace(ret, "&", "&"); 
//			ret = replace(ret, "<", "<"); 
//			ret = replace(ret, ">", ">"); 
//			} catch (NullPointerException e) { 
//			e.printStackTrace(); 
//			} 
//			return ret; 
//			} 
//
//			public static String decodeContent(String content) { 
//			String ret = content; 
//			try { 
//			ret = replace(ret, "&", "&"); 
//			ret = replace(ret, "<", "<"); 
//			ret = replace(ret, ">", ">"); 
//			} 
//			catch (NullPointerException e){ 
//			e.printStackTrace(); 
//			} 
//			return ret; 
//			}
		
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");
		if(userKey != null){
			// 접속된 IP를 얻어온다.
			String userIp1 = (String)request.getRemoteAddr();
			String userIp = userIp1.substring(0, 7)+"...";
	
			// @valid 때문인데. 글쓰는 곳에서 설정해줬으면 되는거였다.
			// ip의 경우 request를 이용해 얻어온 것을 억지로 넘겨줬다. 뭔가 방법이 있을것 같다.
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("board/boardQaWrite");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			// 내용을 사빕한다.
			// 테그 방지
				String ret1 = boardQa.getTitleQa();
				String ret2 = boardQa.getContentQa();
				try { 
					ret1 = replace(ret1, "<", "&lt;");
					ret2 = replace(ret2, "<", "&lt;"); 
					ret2 = replace(ret2, "&lt;br", "<br");
				} catch (NullPointerException e) { 
					e.printStackTrace(); 
				}  
				boardQa.setTitleQa(ret1);
				boardQa.setContentQa(ret2);
			// 테그 방지
			this.boardQaService.boardQaWrite(boardQa, userKey, userIp);
			return new ModelAndView("redirect:boardQa.html?pageNo=1");	// 글을 쓰고 목록 첫 페이지로 돌아감
		}else{
			// 접속된 IP를 얻어온다.
			String userIp = "255.0.0.1";
			AdminVo admin = (AdminVo)request.getSession().getAttribute("ADMIN_KEY");
			MemberVo adminKey = new MemberVo();
			adminKey.setUserEmail(admin.getAdminEmail());
			adminKey.setUserAlias("운영자");
			
	
			// @valid 때문인데. 글쓰는 곳에서 설정해줬으면 되는거였다.
			// ip의 경우 request를 이용해 얻어온 것을 억지로 넘겨줬다. 뭔가 방법이 있을것 같다.
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("board/boardQaWrite");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			// 내용을 사빕한다.
			this.boardQaService.boardQaWrite(boardQa, adminKey, userIp);
			return new ModelAndView("redirect:boardQa.html?pageNo=1");	// 글을 쓰고 목록 첫 페이지로 돌아감
		}
	}


	// 자유게시판 내용 수정을 위해 boardQadetail 에서 읽어온 게시물을 불러옴 (제한 : 자신이 작성한 글만 jsp 상에서 수정버튼이 보이게 해놓음)
	@RequestMapping
	public ModelAndView boardQaEdit(Integer pageNo, Integer bdNoQa){
		ModelAndView modelAndView = new ModelAndView("board/boardQaUpdate");
		BoardQa boardQa = boardQaService.getBoardQaByBdNoQa(bdNoQa);
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardQa", boardQa);
		model.put("pageNo", pageNo);
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	// boardQaEdit(Integer pageNo, Integer bdNoQa)에서 읽어온 게시물을 수정한다.
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView boardQaUpdateForm(@Valid BoardQa boardQa, BindingResult bindingResult, HttpServletRequest request, Integer pageNo){
		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("boardQaEdit");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		String userIp1 = (String)request.getRemoteAddr();
		String userIp = userIp1.substring(0, 7)+"...";
		
		// 테그 방지
		String ret1 = boardQa.getTitleQa();
		String ret2 = boardQa.getContentQa();
		try { 
			ret1 = replace(ret1, "<", "&lt;");
			ret2 = replace(ret2, "<", "&lt;"); 
			ret2 = replace(ret2, "&lt;br", "<br");
		} catch (NullPointerException e) { 
			e.printStackTrace(); 
		}  
		boardQa.setTitleQa(ret1);
		boardQa.setContentQa(ret2);
		// 테그 방지
		
		this.boardQaService.boardQaWriteUpdate(boardQa, userIp);
		String url="redirect:boardQa.html?pageNo="+pageNo;
		return new ModelAndView(url);	// 글을 쓰고 목록 첫 페이지로 돌아감
	}

	// 답글쓰기 (제한 : 로그인한 사람만)
	// boardQaDetail 에서 읽어온 게시물을 저장해서 화면에 뿌려주기 위한 코드가 있음
	@RequestMapping
	public ModelAndView boardQaReplyBefore(Integer pageNo, Integer bdNoQa, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("board/boardQaReply");
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("bdNoQa", bdNoQa);
		BoardQa boardQa = this.boardQaService.getBoardQaByBdNoQa(bdNoQa);
		request.setAttribute("boardQaTitle", boardQa.getTitleQa());
		request.setAttribute("boardQaContent", boardQa.getContentQa());
		modelAndView.addObject(new BoardQa());
		return modelAndView;
//		ModelAndView modelAndView = new ModelAndView("board/boardQaReply");
//		modelAndView.addObject(new BoardQa());
//		BoardQa boardQa = boardQaService.getBoardQaByBdNoQa(bdNoQa);	// boardQaDetail 에서 읽어온 게시물을 저장해서 화면에 뿌려주기 위한 코드
//		// 모델 생성
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("pageNo", pageNo);
//		model.put("bdNoQa", bdNoQa);
//		model.put("bdQaTitle", boardQa.getTitleQa());
//		model.put("bdQaContent", boardQa.getContentQa());
//		modelAndView.addObject(boardQa);
//		System.out.println(boardQa.getUserAlias());
//		System.out.println(boardQa.getUserAlias());
//		System.out.println(boardQa.getReStep());
//		modelAndView.addObject(model);
//		return modelAndView;
	}

	// boardQaReply(Integer pageNo, Integer bdNoQa)를 실행 -> // bdNoQa로 ref 설정한 후 새 게시물 작성
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView boardQaReplyForm(@Valid BoardQa boardQa, BindingResult bindingResult, HttpServletRequest request, Integer pageNo, Integer bdNoQa){
//		System.out.println(boardQa.getUserAlias());
//		System.out.println(boardQa.getReStepQa());
		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("boardQaReply");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		String userIp1 = (String)request.getRemoteAddr();
		String userIp = userIp1.substring(0, 7)+"...";
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");
//		System.out.println(boardQa.getReStepQa());
		// 테그 방지
		String ret1 = boardQa.getTitleQa();
		String ret2 = boardQa.getContentQa();
		try { 
			ret1 = replace(ret1, "<", "&lt;");
			ret2 = replace(ret2, "<", "&lt;"); 
			ret2 = replace(ret2, "&lt;br", "<br");
		} catch (NullPointerException e) { 
			e.printStackTrace(); 
		}  
		boardQa.setTitleQa(ret1);
		boardQa.setContentQa(ret2);
		// 테그 방지
		this.boardQaService.setBoardQaReplyByBdNoQa(boardQa, bdNoQa, userKey, userIp);
		String url="redirect:boardQa.html?pageNo="+pageNo;
		return new ModelAndView(url);	// 글을 쓰고 목록 첫 페이지로 돌아감
	}

	// 삭제하기 (제한 : 로긴한자 + 게시자)
	@RequestMapping(value="boardQaDeleteBefore",method = RequestMethod.POST)
	public ModelAndView boardQaDeleteBefore(HttpServletRequest request, Integer pageNo, Integer bdNoQa){
//		ModelAndView modelAndView = new ModelAndView("board/boardQaDelete");
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("bdNoQa", bdNoQa);
		this.boardQaService.deleteBoardQaArticle(bdNoQa);
		String url="redirect:boardQa.html?pageNo="+pageNo;
		return new ModelAndView(url);	// 글을 쓰고 목록 해당 페이지로 돌아감
	}

	// 후기게시판 목록 불러오기
	@RequestMapping
	public ModelAndView boardReviews(HttpServletRequest request, Integer pageNo) throws Throwable{
		// 공지사항 목록 취득
		List<BoardReviews> boardReviewsList = null;
		boardReviewsList = this.boardReviewsService.getBoardReviewsList(request, pageNo);
		// 모델 생성
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("boardReviewsList", boardReviewsList);

		// 반환값인 ModelAndView 인스턴스 생성
		ModelAndView modelAndView = new ModelAndView("board/boardReviews");
		modelAndView.addAllObjects(model);

		return modelAndView;
	}
	// 후기게시판 내용 보기
	@RequestMapping
	public ModelAndView boardReviewsDetail(HttpServletRequest request, Integer pageNo, Integer bdNoRev) throws Throwable{
		//선택된 공지번호로 부터 공지 내용 취득
		ModelAndView modelAndView = new ModelAndView();
		BoardReviews boardReviews = this.boardReviewsService.getBoardReviewsByBdNoRev(bdNoRev);
		// 테그 합치기
		String ret1 = boardReviews.getTitleRev();
		String ret2 = boardReviews.getContentRev();
		try { 
			ret1 = replace(ret1, "&lt;", "<");
			ret2 = replace(ret2, "&lt;", "<"); 
		} catch (NullPointerException e) { 
			e.printStackTrace(); 
		}  
		boardReviews.setTitleRev(ret1);
		boardReviews.setContentRev(ret2);
		// 테그 합치기
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardReviews", boardReviews);
		model.put("pageNo", pageNo);
		// 글쓴이와 로그인 한 자가 같을 때 메뉴를 보이게 할려구 해봅 시도를 해봄... 해봄
		model.put("writer", boardReviews.getUserEmail());
		this.boardReviewsService.countUpForBdNoRev(bdNoRev);
		modelAndView.addAllObjects(model);

		// 해당 게시물의 댓글들을 화면에 뿌려주고싶다.
		// 후기 댓글 목록 취득
			List<BoardReviewsComments> boardReviewsCommentsList = null;
			boardReviewsCommentsList = this.boardReviewsCommentsService.getBoardRevCommentsList(request, pageNo, bdNoRev);
			// 모델 생성
			model.put("boardReviewsCommentsList", boardReviewsCommentsList);
			// 반환값인 ModelAndView 인스턴스 생성

		// 파일경로 부르기
//		SaveFilePathTo sfpt = this.boardReviewsService.getFilePath(bdNoRev);
//		model.put("SFPT",sfpt);
			// 아래쪽에 리스트 보여줄려구
					List<BoardReviews> boardReviewsList = null;
					boardReviewsList = this.boardReviewsService.getBoardReviewsList(request, pageNo);
					// 모델 생성
					model.put("boardReviewsList", boardReviewsList);
			//아래쪽에 리스트 보여줄려구
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	// 후기게시판 게시물 추천수 올리기, 같은사람은 추천 못한다! 경고한다! 경고했다!
	// TODO Auto-generated method stub
	@RequestMapping(value="boardReviewsRecommend", method = RequestMethod.GET)
	public ModelAndView boardReviewsRecommend(HttpServletRequest request, Integer pageNo, Integer bdNoRev){
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");	// 글쓴사람을 잡아오기 위해서
		// 아이피 훔치기
		String userIp1 = (String)request.getRemoteAddr();
		String userIp = userIp1.substring(0, 7)+"...";
		this.boardReviewsService.RecommendUpByBdNoRev(bdNoRev, userKey, userIp);
		String url="redirect:boardReviewsDetail.html?pageNo="+pageNo+"&bdNoRev="+bdNoRev;
		return new ModelAndView(url);
	}

	// 댓글 쓰기(게시물 읽기 아래쪽에 달린거)
	@RequestMapping("boardRevWriCom")
	public ModelAndView boardRevWriCom(HttpServletRequest request, @Valid BoardReviewsComments bdRevCom, BindingResult bindingResult, Integer pageNo, Integer bdNoRev){
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");	// 글쓴사람을 잡아오기 위해서
		
		if(userKey != null){
			// 아이피 알기
			String userIp1 = (String)request.getRemoteAddr();
			String userIp = userIp1.substring(0, 7)+"...";
			// 아이피 알기 끝
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("board/boardRevWrite");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			// 테그 방지
			String ret1 = bdRevCom.getBdRevCommentsContent();
			try { 
				ret1 = replace(ret1, "<", "&lt;"); 
				ret1 = replace(ret1, "&lt;br", "<br");
			} catch (NullPointerException e) { 
				e.printStackTrace(); 
			}  
			bdRevCom.setBdRevCommentsContent(ret1);
			// 테그 방지
			this.boardReviewsCommentsService.setCommentsAtBdNoRev(bdRevCom, userKey, bdNoRev, userIp);
			String url="redirect:boardReviewsDetail.html?pageNo="+pageNo+"&bdNoRev="+bdNoRev;
			return new ModelAndView(url);	// 댓글을 쓰고 새로고침
		}else{
			String userIp = "255.0.0.1";
			AdminVo admin = (AdminVo)request.getSession().getAttribute("ADMIN_KEY");
			MemberVo adminKey = new MemberVo();
			adminKey.setUserEmail(admin.getAdminEmail());
			adminKey.setUserAlias("운영자");
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("board/boardRevWrite");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			this.boardReviewsCommentsService.setCommentsAtBdNoRev(bdRevCom, adminKey, bdNoRev, userIp);
			String url="redirect:boardReviewsDetail.html?pageNo="+pageNo+"&bdNoRev="+bdNoRev;
			return new ModelAndView(url);	// 댓글을 쓰고 새로고침
		}
	}

	// 댓글삭제(게시물 읽기 아래쪽에 달린거)
	@RequestMapping("boardRevDelCom")
	public ModelAndView boardRevDelCom(HttpServletRequest request, Integer pageNo, Integer bdNoRev, Integer bdNoRevComments){
//			request.setAttribute("pageNo", pageNo);
//			request.setAttribute("bdNoQa", bdNoRev);
		this.boardReviewsCommentsService.deleteBoardQaComment(bdNoRevComments);
		String url="redirect:boardReviewsDetail.html?pageNo="+pageNo+"&bdNoRev="+bdNoRev;
		return new ModelAndView(url);	// 댓글을 쓰고 새로고침
	}

	// 후기게시판 내용 쓰기 객체 생성(제한 : 로그인 한 자)
	@RequestMapping
	public ModelAndView boardReviewsWriteBefore(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("board/boardReviewsWrite");
		modelAndView.addObject(new BoardReviews());
		modelAndView.addObject(new SaveFilePathTo());
		return modelAndView;
	}

	// 후기게시판 파일 저장을 위한 페이지 생성
	@RequestMapping("boardReviewsWrite")
	public ModelAndView boardReviewsWrite(@Valid BoardReviews boardRev, BindingResult bindingResult, HttpServletRequest request) throws Exception{
	 	// 그럼 게시물 작성
		// 로그인된 USER_KEY를 이용해 userEmail을 추출한다
		// 접속된 IP를 얻어온다.
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");
		if(userKey != null){
			String userIp1 = (String)request.getRemoteAddr();
			String userIp = userIp1.substring(0, 7)+"...";
			// @valid 때문인데. 글쓰는 곳에서 설정해줬으면 되는거였다.
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("board/boardReviewsWrite");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			// 내용을 사빕한다.
			// 테그 방지
			String ret1 = boardRev.getTitleRev();
			String ret2 = boardRev.getContentRev();
			try { 
				ret1 = replace(ret1, "<", "&lt;"); 
				ret2 = replace(ret2, "<", "&lt;");
				ret2 = replace(ret2, "&lt;br", "<br");
			} catch (NullPointerException e) { 
				e.printStackTrace(); 
			}  
			boardRev.setTitleRev(ret1);
			boardRev.setContentRev(ret2);
			// 테그 방지
			this.boardReviewsService.boardRevWrite(boardRev, userKey, userIp);
			return new ModelAndView("redirect:boardReviews.html?pageNo=1");	// 글을 쓰고 목록 첫 페이지로 돌아감
		}else{
			String userIp = "255.0.0.1";
			AdminVo admin = (AdminVo)request.getSession().getAttribute("ADMIN_KEY");
			MemberVo adminKey = new MemberVo();
			adminKey.setUserEmail(admin.getAdminEmail());
			adminKey.setUserAlias("운영자");
			// @valid 때문인데. 글쓰는 곳에서 설정해줬으면 되는거였다.
			if(bindingResult.hasErrors()){
				ModelAndView modelAndView = new ModelAndView("board/boardReviewsWrite");
				modelAndView.getModel().putAll(bindingResult.getModel());
				// 여기부터 바인딩 에러 내용보기!
				Map<String, Object> map = bindingResult.getModel();
				Set<String> keys = map.keySet();
				Iterator<String> it = keys.iterator();
				while(it.hasNext()) {
				Object key = it.next();
				Object val = map.get(key);
				System.out.println("에러내용 :: "+val);
				}
				// 여기까지 바인딩 에러 내용보기!
				return modelAndView;
			}
			// 내용을 사빕한다.
			this.boardReviewsService.boardRevWrite(boardRev, adminKey, userIp);
			return new ModelAndView("redirect:boardReviews.html?pageNo=1");	// 글을 쓰고 목록 첫 페이지로 돌아감
		}
	}
	// 파일을 쓰기위한 메소드
	 public void writeFile(MultipartFile filePath, String path, String fileName){
         
	        try{
	            byte fileData[] = filePath.getBytes();
	            fos = new FileOutputStream(path + "\\" + fileName);
	            fos.write(fileData);
	        }catch(Exception e){
	            e.printStackTrace();
	        }finally{
	            if(fos != null){
	                try{
	                    fos.close();
	                }catch(Exception e){}
	                }
	        }// try end;
	    }// wirteFile() end;


	// 후기게시판 내용 수정을 위해 boardReviewsdetail 에서 읽어온 게시물을 불러옴 (제한 : 자신이 작성한 글만 jsp 상에서 수정버튼이 보이게 해놓음)
	@RequestMapping
	public ModelAndView boardReviewsEdit(Integer pageNo, Integer bdNoRev){
		ModelAndView modelAndView = new ModelAndView("board/boardReviewsUpdate");
		BoardReviews boardRev = this.boardReviewsService.getBoardReviewsByBdNoRev(bdNoRev);
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardRev", boardRev);
		model.put("pageNo", pageNo);
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	// boardReviewsEdit(Integer pageNo, Integer bdNoRev)에서 읽어온 게시물을 수정한다.
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView boardReviewsUpdateForm(@Valid BoardReviews boardRev, BindingResult bindingResult, HttpServletRequest request, Integer pageNo){
		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("boardReviewsEdit");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		String userIp1 = (String)request.getRemoteAddr();
		String userIp = userIp1.substring(0, 7)+"...";
		// 테그 방지
		String ret1 = boardRev.getTitleRev();
		String ret2 = boardRev.getContentRev();
		try { 
			ret1 = replace(ret1, "<", "&lt;"); 
			ret2 = replace(ret2, "<", "&lt;");
			ret2 = replace(ret2, "&lt;br", "<br");
		} catch (NullPointerException e) { 
			e.printStackTrace(); 
		}  
		boardRev.setTitleRev(ret1);
		boardRev.setContentRev(ret2);
		// 테그 방지
		this.boardReviewsService.boardReviewsWriteUpdate(boardRev, userIp);
		String url="redirect:boardReviews.html?pageNo="+pageNo;
		return new ModelAndView(url);	// 글을 쓰고 목록 첫 페이지로 돌아감
	}

	// 후기 답글쓰기 (제한 : 로그인한 사람만)
		// boardReviewsDetail 에서 읽어온 게시물을 저장해서 화면에 뿌려주기 위한 코드가 있음
	@RequestMapping
	public ModelAndView boardReviewsReplyBefore(Integer pageNo, Integer bdNoRev, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("board/boardReviewsReply");
		// 여기는 실험정신
		BoardReviews boardRev = this.boardReviewsService.getBoardReviewsByBdNoRev(bdNoRev);
		request.setAttribute("boardRevTitle", boardRev.getTitleRev());
		request.setAttribute("boardRevContent", boardRev.getContentRev());
		// 여기까지 실험정신
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("bdNoRev", bdNoRev);
		modelAndView.addObject(new BoardReviews());
		return modelAndView;
	}

	// boardQaReply(Integer pageNo, Integer bdNoRev)를 실행 -> // bdNoRev로 ref 설정한 후 새 게시물 작성
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView boardReviewsReplyForm(@Valid BoardReviews boardRev, BindingResult bindingResult, HttpServletRequest request, Integer pageNo, Integer bdNoRev){
		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("boardReviewsReply");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		String userIp1 = (String)request.getRemoteAddr();
		String userIp = userIp1.substring(0, 7)+"...";
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");
		// 테그 방지
		String ret1 = boardRev.getTitleRev();
		String ret2 = boardRev.getContentRev();
		try { 
			ret1 = replace(ret1, "<", "&lt;"); 
			ret2 = replace(ret2, "<", "&lt;");
			ret2 = replace(ret2, "&lt;br", "<br");
		} catch (NullPointerException e) { 
			e.printStackTrace(); 
		}  
		boardRev.setTitleRev(ret1);
		boardRev.setContentRev(ret2);
		// 테그 방지
		this.boardReviewsService.setBoardReviewsReplyByBdNoRev(boardRev, bdNoRev, userKey, userIp);
		String url="redirect:boardReviews.html?pageNo="+pageNo;
		return new ModelAndView(url);	// 글을 쓰고 목록 첫 페이지로 돌아감
	}

	// 삭제하기 (제한 : 로긴한자 + 게시자)
	@RequestMapping(value="boardReviewsDeleteBefore",method = RequestMethod.POST)
	public ModelAndView boardRevDeleteBefore(HttpServletRequest request, Integer pageNo, Integer bdNoRev){
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("bdNoRev", bdNoRev);
		this.boardReviewsService.deleteBoardReviewsArticle(bdNoRev);
		String url="redirect:boardReviews.html?pageNo="+pageNo;
		return new ModelAndView(url);	// 글을 쓰고 목록 해당 페이지로 돌아감
	}

	// FAQ 목록 싸그리 불러오기
	@RequestMapping
	public ModelAndView boardFaq(HttpServletRequest request, Integer pageNo) throws Throwable{
		// 공지사항 목록 취득
		List<BoardFaq> boardFaqList = null;
		boardFaqList = this.boardFaqService.getBoardFaqList(request, pageNo);
		// 모델 생성
		Map<String, Object> model = new HashMap<String,Object>();
		model.put("boardFaqList", boardFaqList);

		// 반환값인 ModelAndView 인스턴스 생성
		ModelAndView modelAndView = new ModelAndView("board/boardFaq");
		modelAndView.addAllObjects(model);

		return modelAndView;
	}

	// 공지사항 목록 보기(어드민)
 	@RequestMapping("admin/boardNoticeList")
 	public ModelAndView boardNoticeAdmin(HttpServletRequest request, Integer pageNo) throws Throwable{
 		// 공지사항 목록 취득
 		List<BoardNotice> boardNoticeList = null;
 		boardNoticeList = this.boardNoticeService.getBoardNoticeList(request, pageNo);
 		// 모델 생성
 		Map<String, Object> model = new HashMap<String,Object>();
 		model.put("boardNoticeList", boardNoticeList);
 
 		// 반환값인 ModelAndView 인스턴스 생성
 		ModelAndView modelAndView = new ModelAndView("admin/boardNoticeList");
 		modelAndView.addAllObjects(model);
 
 		return modelAndView;
 	}
	 	
 	// 공지사항 내용 보기(어드민)
	@RequestMapping("/admin/boardNoticeDetailAdmin")
	public ModelAndView boardNoticeDetailAdmin(Integer pageNo, Integer bdNoNtc){
		//선택된 공지번호로 부터 공지 내용 취득
		ModelAndView modelAndView = new ModelAndView("admin/boardNoticeDetailAdmin");
 //			this.boardNoticeService.countUp(bdNoNtc); // 공지사항 내용을 볼 때 조회수를 올려줌
		BoardNotice boardNotice = this.boardNoticeService.getBoardNoticeByBdNoNtc(bdNoNtc);
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardNotice", boardNotice);
		model.put("pageNo", pageNo);
		modelAndView.addAllObjects(model);
		return modelAndView;
	}
 	
 	// 공지사항 내용 쓰기 (어드민)
 	@RequestMapping("admin/boardNoticeWriteBefore")
 	public ModelAndView boardNoticeWriteBefore(){
 		ModelAndView modelAndView = new ModelAndView("admin/boardNoticeWrite");
 		modelAndView.addObject(new BoardNotice());
 		return modelAndView;
 	}
 	
 	// 공지사항 게시 (어드민)
 	@RequestMapping(value="admin/boardNoticeWrite",method = RequestMethod.POST)
 	public ModelAndView boardNoticeWrite(@Valid BoardNotice boardNotice, BindingResult bindingResult, HttpServletRequest request) throws Throwable{
 		if(bindingResult.hasErrors()){
 			ModelAndView modelAndView = new ModelAndView("admin/boardNoticeList");
 			modelAndView.getModel().putAll(bindingResult.getModel());
 			// 여기부터 바인딩 에러 내용보기!
 			Map<String, Object> map = bindingResult.getModel();
 			Set<String> keys = map.keySet();
 			Iterator<String> it = keys.iterator();
 			while(it.hasNext()) {
 			Object key = it.next();
 			Object val = map.get(key);
 			System.out.println("에러내용 :: "+val);
 			}
 			// 여기까지 바인딩 에러 내용보기!
 			return modelAndView;
 		}
 		// 내용을 사빕한다.
 		this.boardNoticeService.boardNoticeWrite(boardNotice);
 		return new ModelAndView("redirect:boardNoticeList.html?pageNo=1");	// 글을 쓰고 목록 첫 페이지로 돌아감
 	}
 	
 	// 공지사항 수정(어드민)
 	@RequestMapping("/admin/boardNoticeUpdateBefore")
	public ModelAndView boardNoticeUpdateBefore(Integer pageNo, Integer bdNoNtc){
		ModelAndView modelAndView = new ModelAndView("admin/boardNoticeUpdate");
		BoardNotice boardNtc = this.boardNoticeService.getBoardNoticeByBdNoNtc(bdNoNtc);
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardNtc", boardNtc);
		model.put("pageNo", pageNo);
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

	// 공지사항 수정(어드민) boardNoticeUpdateBefore(Integer pageNo, Integer bdNoNtc)에서 읽어온 공지사항을 수정한다.
	@RequestMapping(value="admin/boardNoticeUpdate",method = RequestMethod.POST)
	public ModelAndView boardNoticeUpdate(@Valid BoardNotice boardNtc, BindingResult bindingResult, HttpServletRequest request, Integer pageNo){
		if(bindingResult.hasErrors()){
			ModelAndView modelAndView = new ModelAndView("boardNoticeUpdateBefore");
			modelAndView.getModel().putAll(bindingResult.getModel());
			return modelAndView;
		}
		this.boardNoticeService.boardNoticeUpdate(boardNtc);
		String url="redirect:boardNoticeList.html?pageNo="+pageNo;
		return new ModelAndView(url);	// 글을 쓰고 목록 첫 페이지로 돌아감
	}
 	
 	
 	// 공지사항 삭제 (어드민)
 	@RequestMapping(value={"admin/boardNoticeDeleteBefore","boardNoticeDeleteBefore"}, method = RequestMethod.POST)
	public ModelAndView boardNoticeDeleteBefore(HttpServletRequest request, Integer pageNo, Integer bdNoNtc){
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("bdNoNtc", bdNoNtc);
		this.boardNoticeService.deleteBoardNoticeArticle(bdNoNtc);
		String url="redirect:boardNoticeList.html?pageNo="+pageNo;
		return new ModelAndView(url);	// 글을 쓰고 목록 해당 페이지로 돌아감
	}
 	
 	 // FAQ 목록 싸그리 불러오기 (어드민)
 	@RequestMapping("admin/boardFaqList")
 	public ModelAndView boardFaqList(HttpServletRequest request, Integer pageNo) throws Throwable{
 		// 공지사항 목록 취득
 		List<BoardFaq> boardFaqList = null;
 		boardFaqList = this.boardFaqService.getBoardFaqList(request, pageNo);
 		// 모델 생성
 		Map<String, Object> model = new HashMap<String,Object>();
 		model.put("boardFaqList", boardFaqList);
 
 		// 반환값인 ModelAndView 인스턴스 생성
 		ModelAndView modelAndView = new ModelAndView("admin/boardFaqList");
 		modelAndView.addAllObjects(model);
 
 		return modelAndView;
 	}
 	
 	// FAQ 내용 쓰기 (어드민)
 	@RequestMapping("admin/boardFaqWriteBefore")
 	public ModelAndView boardFaqWriteBefore(){
 		ModelAndView modelAndView = new ModelAndView("admin/boardFaqWrite");
 		modelAndView.addObject(new BoardFaq());
 		return modelAndView;
 	}
 	
 	// FAQ 게시 (어드민)
 	@RequestMapping(value="admin/boardFaqWrite",method = RequestMethod.POST)
 	public ModelAndView boardFaqWrite(@Valid BoardFaq boardFaq, BindingResult bindingResult, HttpServletRequest request) throws Throwable{
 		if(bindingResult.hasErrors()){
 			ModelAndView modelAndView = new ModelAndView("admin/boardFaqList");
 			modelAndView.getModel().putAll(bindingResult.getModel());
 			// 여기부터 바인딩 에러 내용보기!
 			Map<String, Object> map = bindingResult.getModel();
 			Set<String> keys = map.keySet();
 			Iterator<String> it = keys.iterator();
 			while(it.hasNext()) {
 			Object key = it.next();
 			Object val = map.get(key);
 			System.out.println("에러내용 :: "+val);
 			}
 			// 여기까지 바인딩 에러 내용보기!
 			return modelAndView;
 		}
 		// 내용을 사빕한다.
 		this.boardFaqService.boardFaqWrite(boardFaq);
 		return new ModelAndView("redirect:boardFaqList.html?pageNo=1");	// 글을 쓰고 목록 첫 페이지로 돌아감
 	}
 	
 	// FAQ 수정(어드민)
  	@RequestMapping("/admin/boardFaqUpdateBefore")
 	public ModelAndView boardFaqUpdateBefore(Integer pageNo, Integer bdNoFaq){
 		ModelAndView modelAndView = new ModelAndView("admin/boardFaqUpdate");
 		BoardFaq boardFaq = this.boardFaqService.getBoardFaqByBdNoFaq(bdNoFaq);
 		// 모델 생성
 		Map<String, Object> model = new HashMap<String, Object>();
 		model.put("boardFaq", boardFaq);
 		model.put("pageNo", pageNo);
 		modelAndView.addAllObjects(model);
 		return modelAndView;
 	}

 	// FAQ 수정(어드민) boardNoticeUpdateBefore(Integer pageNo, Integer bdNoNtc)에서 읽어온 공지사항을 수정한다.
 	@RequestMapping(value="admin/boardFaqUpdate",method = RequestMethod.POST)
 	public ModelAndView boardFaqUpdate(@Valid BoardFaq boardFaq, BindingResult bindingResult, HttpServletRequest request, Integer pageNo){
 		if(bindingResult.hasErrors()){
 			ModelAndView modelAndView = new ModelAndView("boardNoticeUpdateBefore");
 			modelAndView.getModel().putAll(bindingResult.getModel());
 			return modelAndView;
 		}
 		this.boardFaqService.boardFaqUpdate(boardFaq);
 		String url="redirect:boardFaqList.html?pageNo="+pageNo;
 		return new ModelAndView(url);	// 글을 쓰고 목록 첫 페이지로 돌아감
 	}
 	
 	// FAQ 삭제 (어드민)
  	@RequestMapping(value="admin/boardFaqDeleteBefore", method = RequestMethod.GET)
 	public ModelAndView boardFaqDeleteBefore(HttpServletRequest request, Integer pageNo, Integer bdNoFaq){
 		request.setAttribute("pageNo", pageNo);
 		request.setAttribute("bdNoFaq", bdNoFaq);
 		this.boardFaqService.deleteBoardFaqArticle(bdNoFaq);
 		String url="redirect:boardFaqList.html?pageNo="+pageNo;
 		return new ModelAndView(url);	// 글을 쓰고 목록 해당 페이지로 돌아감
 	}
 	
}