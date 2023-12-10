package modelBEAN;

public class Video {
    private  int IDVD;
    private  String video_title;
    private  String course_id;
    private String  file_path;

    public  Video(){

    }
    public Video(int IDVD, String video_title, String course_id, String file_path){
        this.IDVD = IDVD;
        this.video_title = video_title;
        this.course_id  = course_id;
        this.file_path = file_path;
    }

    public void setIDVD(int IDVD) {
        this.IDVD = IDVD;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public int getIDVD() {
        return IDVD;
    }

    public String getCourse_id() {
        return course_id;
    }

    public String getFile_path() {
        return file_path;
    }

    public String getVideo_title() {
        return video_title;
    }
}
