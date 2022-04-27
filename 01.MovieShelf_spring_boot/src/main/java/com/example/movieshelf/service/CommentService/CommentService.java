package com.example.movieshelf.service.CommentService;

import com.example.movieshelf.domain.Comment.Comment;
import com.example.movieshelf.domain.Comment.CommentRequestDTO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public interface CommentService {

    public List<Comment> getCommentList();
    public ArrayList<Comment> getCommentListByTalkNo(int talk_no);
    public ArrayList<Comment> getCommentListByUserId(String user_id);
    public Comment getOneComment(int comment_id);
    public Comment addComment(CommentRequestDTO commentRequestDTO);
    public Comment updateComment(int comment_id, CommentRequestDTO commentRequestDTO);
    public ArrayList<Comment> updateComments(ArrayList<Comment> comments); // ArrayList에 담긴 애들을 업데이트 시켜준다
    public int deleteComment(int comment_id);
    public int deleteComments(ArrayList<Comment> comments); // 삭제 목록 받아서 삭제하는 메서드
    public int deleteCommentAllByTalkNo(int talk_no);
    public CommentRequestDTO changeDTO(int comment_id); // Comment => CommentRequestDTO 바꿔주는 객체
}
