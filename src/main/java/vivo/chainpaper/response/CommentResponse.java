package vivo.chainpaper.response;

import vivo.chainpaper.entity.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentResponse extends Response {

    private List<CommentItem> comments;

    public CommentResponse(List<Comment> comments) {
        this.comments = new ArrayList<>();
        for(Comment comment: comments){
            this.comments.add(new CommentItem(comment.getUserId(), comment.getTime_stamp(), comment.getComment()));
        }
    }

    public List<CommentItem> getComments() {
        return comments;
    }

    public void setComments(List<CommentItem> comments) {
        this.comments = comments;
    }
}
