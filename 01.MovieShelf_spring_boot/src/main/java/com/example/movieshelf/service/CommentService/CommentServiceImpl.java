package com.example.movieshelf.service.CommentService;

import com.example.movieshelf.domain.Comment.Comment;
import com.example.movieshelf.domain.Comment.CommentRepository;
import com.example.movieshelf.domain.Comment.CommentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository repo;

    public List<Comment> getCommentList(){
        return repo.findAll();
    }

    // 게시글에 이용
    public ArrayList<Comment> getCommentListByTalkNo(int talk_no){
        List<Comment> allComment = getCommentList();

        ArrayList<Comment> postComment = new ArrayList<Comment>();
        for(Comment com : allComment){
            if(com.getTalk_no() == talk_no){
                postComment.add(com);
            }
        }
        return postComment;
    }

    // 마이페이지 이용
    public ArrayList<Comment> getCommentListByUserId(String user_id){
        List<Comment> allComment = getCommentList();
        ArrayList<Comment> userComment = new ArrayList<Comment>();
        for(Comment com : allComment){
            if(com.getUser_id().equals(user_id) ){
                userComment.add(com);
            }
        }
        return userComment;    }

    public Comment getOneComment(int comment_id){
        return repo.findById(comment_id).orElseThrow(
                () -> new IllegalArgumentException("없는 댓글 입니다")
        );
    }

    public Comment addComment(CommentRequestDTO commentRequestDTO){
        return repo.save(new Comment(commentRequestDTO));
    }

    @Transactional
    public Comment updateComment(int comment_id, CommentRequestDTO commentRequestDTO){
        Comment comment = getOneComment(comment_id);
        comment.update(commentRequestDTO);
        return comment;
    }

    // ArrayList에 담긴 애들을 업데이트 시켜준다
    @Transactional
    public ArrayList<Comment> updateComments(ArrayList<Comment> comments){
        for(Comment c : comments){
            Comment existComment = repo.findById(c.getComment_id()).orElseThrow(
                    () -> new IllegalArgumentException("없는 댓글 입니다")
            );
            int sort_no = existComment.updateSortNo( this.changeDTO(c.getComment_id()) );
            // repo 객체에 직접 접근해야 업데이트 됨...
            // 어줍잖게 새로운 변수에 넣어서 update 금지
            System.out.println(">> 정렬중 c_no" + c.getSort_no() + " // sort_no: " + sort_no);
        }
        return comments;
    }

    @Transactional
    public int deleteComment(int comment_id){
        repo.deleteById(comment_id);
        return comment_id;
    }

    // 삭제 목록 받아서 삭제하는 메서드
    @Transactional
    public int deleteComments(ArrayList<Comment> comments) {
        int howMany = comments.size();
        for (Comment c : comments) {
            repo.deleteById(c.getComment_id());
        }
        return howMany;
    }

    @Transactional
    public int deleteCommentAllByTalkNo(int talk_no){
        ArrayList<Comment> comments = this.getCommentListByTalkNo(talk_no);
        int delCommentCnt = comments.size();
        for(Comment c : comments){
            repo.deleteById(c.getComment_id());
        }
        return delCommentCnt;
    }

    // Comment => CommentRequestDTO 바꿔주는 객체
    public CommentRequestDTO changeDTO(int comment_id){
        Comment tmp = getOneComment(comment_id);
        return new CommentRequestDTO(tmp.getComment_id(), tmp.getTalk_no(), tmp.getUser_id(),tmp.getComment_content(), tmp.getComment_date(), tmp.getSort_no(), tmp.getDepth(), tmp.getMother_comment_id());
    }

}
