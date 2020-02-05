package tim10.project.web.dto;

import javax.validation.constraints.NotNull;

public class PaperDTO {
    @NotNull
    private String paperTitle;
    @NotNull
    private String paperStatus;

    public PaperDTO(@NotNull String paperTitle, @NotNull String paperStatus) {
        this.paperTitle = paperTitle;
        this.paperStatus = paperStatus;
    }

    public PaperDTO() {
    }

    public String getPaperTitle() {
        return paperTitle;
    }

    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
    }

    public String getPaperStatus() {
        return paperStatus;
    }

    public void setPaperStatus(String paperStatus) {
        this.paperStatus = paperStatus;
    }
}
