package tim10.project.web.dto;

import tim10.project.model.user.User;

import java.util.ArrayList;

public class SetReviewersDTO {
    private String paperTitle;
    private ArrayList<Integer> reviewers;

    public SetReviewersDTO(String paperTitle, ArrayList<Integer> reviewers) {
        this.paperTitle = paperTitle;
        this.reviewers = reviewers;
    }

    public SetReviewersDTO() {
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public ArrayList<Integer> getReviewers() {
        return reviewers;
    }

    public void setReviewers(ArrayList<Integer> reviewers) {
        this.reviewers = reviewers;
    }
}
