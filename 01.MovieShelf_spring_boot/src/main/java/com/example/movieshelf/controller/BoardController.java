package com.example.movieshelf.controller;

import com.example.movieshelf.domain.Comment.Comment;
import com.example.movieshelf.domain.Comment.CommentRequestDTO;
import com.example.movieshelf.domain.Talk.Talk;
import com.example.movieshelf.domain.Talk.TalkRequestDTO;
import com.example.movieshelf.domain.User.User;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    // 게시판 Controller
    private final TalkController tc;

    // 댓글 Controller
    private final CommentController cc;

    // 영화검색 Controller
    private final MovieRestController mc;

    @GetMapping("/boardList/{nowPage}")
    public String boardList(@PathVariable String nowPage, HttpServletRequest request) {
        // 아무것도 없이 들어왔을 때, 1로 돌려줌            nowPage = "1";
        if(nowPage == ""){
        }
        // 5개씩만 보이게끔 처리
        int nowPageInt = Integer.parseInt(nowPage);
        int boardListPageSize = tc.getTalks().size()/5;

        // 5배수로 딱떨어지지 않을 경우 >> 한페이지 더 추가
        if(tc.getTalks().size()%5 != 0){
            boardListPageSize++;
        }

        List<Talk> boardListInNowPage = tc.getTalksInNowPage(nowPageInt);
        request.setAttribute("nowPage", nowPageInt);
        request.setAttribute("boardListPageSize", boardListPageSize);
        request.setAttribute("boardListInNowPage", boardListInNowPage);
        return "board/boardList.jsp";
    }

    @GetMapping("/board/{talk_no}")
    public String post(@PathVariable int talk_no, HttpServletRequest request) {
        Talk post = tc.getTalk(talk_no);
        // sortForSortNo() : 댓글 정렬, 반환
        ArrayList<Comment> comments = this.sortForSortNo(cc.getCommentListByTalkNo(talk_no));
        request.setAttribute("post", post);
        request.setAttribute("comments", comments);
        return "board/boardView_comment.jsp";
    }

    @GetMapping("/boardWriteForm")
    public String writeForm(HttpServletRequest request) {
        User user = this.checkLogin(request);
        // 로그인 되어 있을 경우만 들어감
        // ㄴ jsp 에서 1차 필터링 되나 기능++ 하여 보안 업그레이드
        if (user != null) return "board/boardWrite.jsp";
        else return this.boardList("1", request);
    }

    @PostMapping("/boardWrite")
    public String addPost(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("log");

        // 로그인 되어 있을 경우만 실행
        if (user != null) {
            String pw = request.getParameter("pw");
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String moviePoster = request.getParameter("moviePoster");
            String movieName = request.getParameter("movieName");
            // 수정 필요 moviename 추가
            tc.addTalk(new TalkRequestDTO(user.getUser_id(), pw, title, content,movieName, moviePoster));
            System.out.println("추가완료!\nUser Id:" + user.getUser_id() + "\ntitle: " + title + "content: " + content);
            System.out.println("영화제목:" + movieName + "포스터 url: " + moviePoster);
        }
        return this.boardList("1", request);
    }

    @GetMapping("/boardUpdateForm/{talk_no}")
    public String update(@PathVariable int talk_no, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("log");
        Talk post = tc.getTalk(talk_no);

        // id가 맞을 경우만 업데이트 페이지로 이동
        if (user != null && user.getUser_id().equals(post.getUser_id())) {
            request.setAttribute("post", post);
            return "board/boardUpdate.jsp";
        } else return this.post(post.getTalk_no(), request);
    }

    // 해당 부분은 수정사항이 전부 전달되지 않아도 가능
    @PatchMapping("/boardUpdate/{talk_no}")
    public String updatePost(@PathVariable int talk_no, HttpServletRequest request) {
        // 세션 받아옴
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("log");

        // 게시글, 변경사항 가져옴
        Talk post = tc.getTalk(talk_no);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String pw = request.getParameter("pw");

        // 로그인된 유저와 비교, 빈칸 유효성 검사 및 실행
        if (user != null && user.getUser_id().equals(post.getUser_id()) && post.getTalk_password().equals(pw)) {
            if(!title.equals("") && !content.equals("") && !pw.equals("")){
                TalkRequestDTO talkRequestDTO = new TalkRequestDTO(post.getTalk_no(), post.getUser_id(), post.getTalk_password(), title, content, post.getTalk_likes(), post.getTalk_regdate(), post.getMovie_name(),  post.getMovie_poster());
                post.update(talkRequestDTO);
                tc.updateTalk(talk_no, talkRequestDTO);
                System.out.println("게시글 수정 완료!\nUpdate Post Num: " + post.getTalk_no());
            }
        }
        return this.post(post.getTalk_no(), request);
    }

    @GetMapping("/board/{talk_no}/increaseLike")
    public String increaseLike(@PathVariable int talk_no, HttpServletRequest request) {
        // 세션 받아옴
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("log");

        // 게시글, 변경사항 가져옴
        Talk post = tc.getTalk(talk_no);

        // 로그인된 유저와 같지 않을 경우 실행
        if (user != null && !user.getUser_id().equals(post.getUser_id())) {
            int like = post.getTalk_likes() + 1;
            TalkRequestDTO talkRequestDTO = new TalkRequestDTO(post.getTalk_no(), post.getUser_id(), post.getTalk_password(), post.getTalk_title(), post.getTalk_content(), like, post.getTalk_regdate(), post.getMovie_name(), post.getMovie_poster());

            post.update(talkRequestDTO);
            tc.updateTalk(talk_no, talkRequestDTO);
            System.out.println("좋아요+1!\nLike Post Num: " + post.getTalk_no());
        }
        return this.post(post.getTalk_no(), request);
    }

    @GetMapping("/boardDelete/{talk_no}")
    public void deletePost(@PathVariable int talk_no, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        User user = (User) session.getAttribute("log");
        Talk post = tc.getTalk(talk_no);

        // 유저 객체 null X, 유저 id 동일(탈퇴자도 중복 가입 없도록 처리되어 무결성 확보됨)
        if (user != null && user.getUser_id().equals(post.getUser_id())) {
            // 게시글 삭제
            int delNum = tc.deleteTalk(talk_no);

            // 해당 게시글 댓글 삭제(삭제한 댓글 개수 반환:int DB삭제용)
            int delCommentCnt = cc.deleteCommentByTalkNo(talk_no);

            System.out.println("게시글 삭제 삭제완료!\ndelNum: " + delNum);
            System.out.println("댓글 " + delCommentCnt + "개 삭제");
        }
        // 기존의 데이터는 필요 없으니 sendRedirect
        response.sendRedirect("/boardList");
    }


    // ====== 검색 매핑 ==================================================================
    // JSON 반환
    // ㄴ String 값에 대한 전달밖에 안됨 => 데이터 이동과 관련하여 HTTP 프로토콜 전송방식에 대한 이해가 더 필요함

    // 무비 REST Controller를 통해 받아온 json 객체
    @GetMapping("/boardSearch/{searchTitle}")
    @ResponseBody
    public JSONObject getSearchJsonResult(@PathVariable String searchTitle, HttpServletRequest request) throws ParseException {
        return mc.getSearchJsonResult(searchTitle);
    }


    // 인덱스 받아 다시 검색해서 request.set 해서 보내기
    // 기능 : 영화 선택하면 해당 번호를 받아서 다시 페이지로 이동(제목, 포스터 노출)
    // ㄴ 선택한 인덱스를 활용해 제목 반환 및 객체 데이터 반환
    @GetMapping("/boardWrite/choice")
    public String writePost(HttpServletRequest request) throws ParseException{
        int idx = Integer.parseInt(request.getParameter("idxHidden"));
        String searchWord = request.getParameter("searchWord");

        // json객체 mc REST Controller 통해서 받음
        JSONObject jsonObject = mc.getSearchJsonResult(searchWord);
        // 파싱
        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
        JSONObject obj = (JSONObject) jsonArray.get(idx);

        String movieName = (String) obj.get("title"); // 제목
        String moviePoster = (String) obj.get("image"); // 포스터링크

        movieName = movieName.replace("<b>", "");
        movieName = movieName.replace("</b>", "");

        System.out.println("idx : " + idx);
        System.out.println("movieName: " + movieName);

        request.setAttribute("movieName", movieName);
        request.setAttribute("moviePoster", moviePoster);

        return "/board/boardWrite.jsp";
    }

    private User checkLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("log");

        if (user != null) return user;
        else return null;
    }


    // ====== 코멘트 매핑 ==================================================================
    // 댓글 추가 메서드
    @GetMapping("/board/comment/addComment/{talk_no}")
    public void addComment(@PathVariable int talk_no, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("log");
        String comment = request.getParameter("comment");
        // 게시글 번호, 현재 로그인된 유저 id, 댓글 내용
        // ㄴ 나머지 mysql에서 auto 설정
        cc.addComment(new CommentRequestDTO(talk_no, user.getUser_id(), comment, -1, 0, 0));
        response.sendRedirect("/board/" + talk_no);
    }

    // 대댓글 추가 및 이동 메서드 => 부모 댓글의 번호를 sort_no로 가지고 들어온다
    @GetMapping("/board/comment/addPlusComment/{talk_no}/{sort_no}/{mother_comment_id}")
    public String addPlusComment(@PathVariable int talk_no, @PathVariable int sort_no, @PathVariable int mother_comment_id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("log");
        Talk post = tc.getTalk(talk_no);

        // 게시글 번호, 현재 로그인된 유저 id, 댓글 내용
        // ㄴ 나머지 mysql에서 auto 설정
        // 객체 생성(comment 부분 빈칸 저장) => (내용만 입력받아 변경)this.setPlusComment
        CommentRequestDTO dto = new CommentRequestDTO(talk_no, user.getUser_id(), "", sort_no, 1, mother_comment_id);
        Comment plusComment = cc.addComment(dto);
        ArrayList<Comment> comments = cc.getCommentListByTalkNo(talk_no);
        System.out.println("plusComment의 sortNum : " + plusComment.getSort_no());

        // 방금 추가된 객체 빼고 sort_no update (밀어주기)
        ArrayList<Comment> updateArr = this.pushSortNo(talk_no, plusComment.getSort_no());

        // comments 변경되었기 때문에 다시 get 후 sort
        ArrayList<Comment> sortedComments = this.sortForSortNo(comments);

        request.setAttribute("post", post);
        request.setAttribute("comments", sortedComments);
        request.setAttribute("plusComment", plusComment); // view에 뿌리기 위해 저장
        return "/board/boardView_plusCommentAdd.jsp";
    }

    // 새로 대댓글 추가시 sortno 뒤로뒤로 => 추가 후 실행
    private ArrayList<Comment> pushSortNo(int talk_no, int sort_no) {
        ArrayList<Comment> comments = cc.getCommentListByTalkNo(talk_no);
        ArrayList<Comment> updateArr = new ArrayList<>();

        int i = 0;
        for (Comment c : comments) {
            if (c.getSort_no() >= sort_no) { // 같거나 큰애들
                CommentRequestDTO temp = cc.changeDTO(c.getComment_id());
                temp.setSort_no(temp.getSort_no() + 1);
                c.updateSortNo(temp);
                updateArr.add(c);
            }
            i++;
            // 방금 추가된 애는 넘어가지 않음 (plusComment 이후에 진행되기 때문)
            if (i == comments.size() - 1) break;
        }
        // 바뀐 애들만 ArrayList 받아서 객체 바꿔주는 메서드 CommentService
        cc.updateComments(updateArr);
        // ㄴ 무조건 직접 Repository에서 객체를 꺼내서 바꿔줘야함
        // ㄴ key!!! repo에 직접 접근! @Transection 필수
        return comments;
    }

    // 댓글, 대댓글 삭제시 sortNo 당겨주기 => 삭제 후 실행
    private ArrayList<Comment> pullSortNo(int talk_no, int sort_no, int howMany) {
        ArrayList<Comment> comments = cc.getCommentListByTalkNo(talk_no);
        ArrayList<Comment> updateArr = new ArrayList<>();

        for (Comment c : comments) {
            if (c.getSort_no() > sort_no) { // 같거나 큰애들
                CommentRequestDTO temp = cc.changeDTO(c.getComment_id());
                temp.setSort_no(temp.getSort_no() - howMany);
                c.updateSortNo(temp);
                updateArr.add(c);
            }
        }
        // 바뀐 애들만 ArrayList 받아서 객체 바꿔주는 메서드 CommentService
        cc.updateComments(updateArr);
        // ㄴ 무조건 직접 Repository에서 객체를 꺼내서 바꿔줘야함
        // ㄴ key!!! repo에 직접 접근! @Transection 필수
        return comments;
    }

    // sort_no을 통한 정렬 메서드
    // ㄴ 같지 않으면 끝나버림 => 삽입, 삭제시 인덱스 조절 필수
    private ArrayList<Comment> sortForSortNo(ArrayList<Comment> comments) {
        ArrayList<Comment> tempArr = new ArrayList<>();
        int idx = 1;
        for (int i = 0; i < comments.size(); i++) {
            if (comments.get(i).getSort_no() == idx) {
                tempArr.add(comments.get(i));
                idx++;
                i = 0;
            }
        }
        return tempArr;
    }

    // 대댓글 등록 메서드
    @GetMapping("/board/comment/setPlusComment/{talk_no}/{comment_id}")
    public void setPlusComment(@PathVariable int talk_no, @PathVariable int comment_id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Comment comment = cc.getOneComment(comment_id); // 코멘트 받아오기
        CommentRequestDTO commentRequestDTO = cc.changeDTO(comment.getComment_id()); // 객체 변경

        String plusComment = request.getParameter("plusComment"); // 적은 내용
        commentRequestDTO.setComment_content(plusComment); // 내용 변경

        cc.updateComment(comment_id, commentRequestDTO); // 업데이트
        response.sendRedirect("/board/" + talk_no);
    }

    @GetMapping("/board/{talk_no}/updateComment/{comment_id}")
    public String updateCommentPost(@PathVariable int talk_no, @PathVariable int comment_id, HttpServletRequest request) {
        Talk post = tc.getTalk(talk_no);
        ArrayList<Comment> comments = this.sortForSortNo(cc.getCommentListByTalkNo(talk_no));
        Comment updateComment = cc.getOneComment(comment_id);
        request.setAttribute("post", post);
        request.setAttribute("comments", comments);
        request.setAttribute("updateComment", updateComment);
        return "board/boardView_commentUpdate.jsp";
    }

    @GetMapping("/board/comment/updateComment/{comment_id}")
    public void updateComment(@PathVariable int comment_id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommentRequestDTO commentRequestDTO = cc.changeDTO(comment_id);
        commentRequestDTO.setComment_content(request.getParameter("reComment"));
        cc.updateComment(comment_id, commentRequestDTO);
        response.sendRedirect("/board/" + commentRequestDTO.getTalk_no());
    }

    @GetMapping("/board/comment/deleteComment/{comment_id}")
    public void deleteComment(@PathVariable int comment_id, HttpServletResponse response) throws IOException {
        Comment comment = cc.getOneComment(comment_id);
        System.out.println("댓글 삭제 완료!");
        int howMany = 1; // 삭제된 후에 sort_no 정렬해주기 위한 int 변수
        if (comment.getDepth() == 1) {
            cc.deleteComment(comment_id);
        } else {
            cc.deleteComment(comment_id); // 해당 객체 삭제하고
            howMany += this.deleteAllComments(comment.getTalk_no(), comment_id); // 대댓글들도 삭제
        }
        this.pullSortNo(comment.getTalk_no(), comment.getSort_no(), howMany);
        response.sendRedirect("/board/" + comment.getTalk_no());
    }

    // 대댓글 같이 삭제하는 메서드
    private int deleteAllComments(int talk_no, int mother_comment_id) {
        // 삭제 하고 값을 반환해줘
        ArrayList<Comment> comments = cc.getCommentListByTalkNo(talk_no);
        ArrayList<Comment> deleteArr = new ArrayList<>();

        for (Comment c : comments) {
            // 같거나 크고, 다음 depth 0보다 작아야해
            if (c.getMother_comment_id() == mother_comment_id) {
                deleteArr.add(c);
            }
        }
        return cc.deleteComments(deleteArr); // 삭제 수 반환
    }
}

