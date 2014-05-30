package controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import logic.BoardNotice;
import logic.BoardNoticeService;
import logic.BoardQa;
import logic.BoardQaComments;
import logic.BoardQaCommentsService;
import logic.BoardQaService;
import logic.MemberVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	@Autowired
	BoardNoticeService boardNoticeService;
	@Autowired
	BoardQaService boardQaService;
	@Autowired
	BoardQaCommentsService boardQaCommentsService;

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
	
	// 질답 게시판 목록 보기
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
	// 질답 게시판 내용 보기
	@RequestMapping
	public ModelAndView boardQaDetail(HttpServletRequest request, Integer pageNo, Integer bdNoQa) throws Throwable{
		//선택된 공지번호로 부터 공지 내용 취득
		ModelAndView modelAndView = new ModelAndView();
		BoardQa boardQa = this.boardQaService.getBoardQaByBdNoQa(bdNoQa);
		// 모델 생성
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("boardQa", boardQa);
		model.put("pageNo", pageNo);
		// 글쓴이와 로그인 한 자가 같을 때 메뉴를 보이게 할려구 해봅 시도를 해봄... 해봄
		model.put("writer", boardQa.getUserEmail());
		this.boardQaService.countUpForBdNoQa(bdNoQa);
		modelAndView.addAllObjects(model);
		
		// 해당 게시물의 댓글들을 화면에 뿌려주고싶다.
		// Q&A 목록 취득
			List<BoardQaComments> boardQaCommentsList = null;
			boardQaCommentsList = this.boardQaCommentsService.getBoardQaCommentsList(request, pageNo, bdNoQa);
			// 모델 생성
			model.put("boardQaCommentsList", boardQaCommentsList);
			// 반환값인 ModelAndView 인스턴스 생성
			modelAndView.addAllObjects(model);
		

//		System.out.println(boardQa.getUserEmail());
//		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");
//		System.out.println(userKey.getUserEmail());
		return modelAndView;
	}
	
	// 댓글 쓰기(게시물 읽기 아래쪽에 달린거) MemberVo userKey
	@RequestMapping("boardQaWriCom")
	public ModelAndView boardQaWriCom(HttpServletRequest request, @Valid BoardQaComments bdQaCom, BindingResult bindingResult, Integer pageNo, Integer bdNoQa){
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");	// 글쓴사람을 잡아오기 위해서
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
	
	// 질답 게시판 내용 쓰기 객체 생성(제한 : 로그인 한 자)
	@RequestMapping
	public ModelAndView boardQaWriteBefore(){
		ModelAndView modelAndView = new ModelAndView("board/boardQaWrite");
		modelAndView.addObject(new BoardQa());
		return modelAndView;
	}
	
	// 질답 게시판 내용 쓰기 디비에 저장(제한 : 로그인 한 자)
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
		// 접속된 IP를 얻어온다.
		MemberVo userKey = (MemberVo)request.getSession().getAttribute("USER_KEY");
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
		this.boardQaService.boardQaWrite(boardQa, userKey, userIp);
		return new ModelAndView("redirect:boardQa.html?pageNo=1");	// 글을 쓰고 목록 첫 페이지로 돌아감
	}
	
	
	// 질답 게시판 내용 수정을 위해 boardQadetail 에서 읽어온 게시물을 불러옴 (제한 : 자신이 작성한 글만 jsp 상에서 수정버튼이 보이게 해놓음)
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

}
