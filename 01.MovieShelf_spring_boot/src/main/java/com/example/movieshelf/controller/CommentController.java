package com.example.movieshelf.controller;

import com.example.movieshelf.domain.Comment.Comment;
import com.example.movieshelf.domain.Comment.CommentRequestDTO;
import com.example.movieshelf.service.CommentService.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    // 페이지 이동은 Board에서 다룸 comment는 데이터만 주고 받으면 됨
    // ㄴ Controller 필요X
    private final CommentService service;

    @GetMapping("/v1/comments")
    public List<Comment> getCommentList() {
        return service.getCommentList();
    }

    @GetMapping("/v1/comments/board-no={talk_no}")
    public ArrayList<Comment> getCommentListByTalkNo(@PathVariable int talk_no) {
        return service.getCommentListByTalkNo(talk_no);
    }

    @GetMapping("/v1/comments/user-id={user_id}")
    public ArrayList<Comment> getCommentListByUserId(@PathVariable String user_id) {
        return service.getCommentListByUserId(user_id);
    }

    @GetMapping("/v1/comment/comment-id={comment_id}")
    public Comment getOneComment(@PathVariable int comment_id) {
        return service.getOneComment(comment_id);
    }

    @PostMapping("/v1/comment")
    public Comment addComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        // 게시글 내 댓글순서 결정 후 내보냄 (뎁스는 그냥 들어옴)
        // ㄴ commentRequestDTO를 새로 set 해서 내보내기
        // ㄴ 댓글, 대댓글 둘다 이용

        // 필요 순서 셋팅, 반환 메서드
        CommentRequestDTO dto = this.sortSortNo(commentRequestDTO);
        return service.addComment( dto );
    }

    @PatchMapping("/v1/comments")
    public void updateComments(ArrayList<Comment> comments) {
        service.updateComments(comments);
    }

    // 필요 순서를 셋팅하여 객체 반환하는 메서드
    // ㄴ 댓글 대댓글 식별, sort_no 반환, 들어온 댓글에 순서 부여, (전체 순서 컨트롤)
    private CommentRequestDTO sortSortNo(CommentRequestDTO commentRequestDTO){
        int depth = commentRequestDTO.getDepth();
        int postNo = commentRequestDTO.getTalk_no();

        System.out.println("postNo: " +postNo);
        ArrayList<Comment> comments = this.getCommentListByTalkNo(postNo);
        int sortNo = commentRequestDTO.getSort_no(); // 최초 -1로 들어옴
        System.out.println("sortNo: " +sortNo);

        // 뎁스가 0일경우(댓글) : 그냥 마지막에 다는거
        if(depth == 0){ // 댓글
            if(comments.size() == 0){ // 댓글이 존재하지 않을 때
                sortNo = 1;
            }
            else {
                for (Comment c : comments) {
                    if (c.getSort_no() > sortNo) {
                        sortNo = c.getSort_no(); // 마지막 댓글 순서 뽑기
                    }
                }
                sortNo += 1; // 마지막 보다 +1
            }
            commentRequestDTO.setSort_no(sortNo); // return 객체 변경
            System.out.println("댓글 순서 반환 : " + sortNo);
        }

        // 뎁스가 1일경우(대댓글) : 해당 sort_no 조회 -> 이후 뎁스 1인경우 의 전 번호부여 => 나머지 번호 미루기
        // ㄴ 대댓글은 댓글의 번호를 sort_no로 가지고 들어옴
        else{ // 대댓글
            boolean motherComment = false;
            for (Comment c : comments) {
                if (c.getDepth() == 0 && c.getSort_no() > sortNo) {
                    sortNo = c.getSort_no(); // 부모댓글의 sort_no 보다 큰 0뎁스의 숫자 뽑기 => 해당 숫자로 변경
                    motherComment = true;
                    break;
                }
            }
            if(motherComment == false){ // 댓글이 하나일 경우 지속적으로 대댓글이 중복되는 현상 발생 예외처리
                for (Comment c : comments) {
                    if (c.getSort_no() > sortNo) {
                        sortNo = c.getSort_no(); // 마지막 댓글 순서 뽑기
                    }
                }
                sortNo += 1; // 하나 더해주기
            }
            commentRequestDTO.setSort_no(sortNo); // return 객체 변경
            System.out.println("대댓글 작성! 정렬 완료!");
            System.out.println("motherComment : " + motherComment);
        }

        System.out.println("sortNo_change: " +sortNo);

        return commentRequestDTO;
    }

    @PatchMapping("/v1/comment/comment-id={comment_id}")
    public Comment updateComment(@PathVariable int comment_id, @RequestBody CommentRequestDTO commentRequestDTO) {
        return service.updateComment(comment_id, commentRequestDTO);
    }

    @DeleteMapping("/v1/comments")
    public int deleteComments(ArrayList<Comment> comments) {
        return service.deleteComments(comments);
    }

    @DeleteMapping("/v1/comment/comment-id={comment_id}")
    public int deleteComment(@PathVariable int comment_id) {
        return service.deleteComment(comment_id);
    }

    @DeleteMapping("/v1/comments/board-no={talk_no}")
    public int deleteCommentByTalkNo(@PathVariable int talk_no) {
        return service.deleteCommentAllByTalkNo(talk_no);
    }

    // Comment => CommentRequestDTO 바꿔주는 객체
    public CommentRequestDTO changeDTO(int comment_id) {
        return service.changeDTO(comment_id);
    }


}