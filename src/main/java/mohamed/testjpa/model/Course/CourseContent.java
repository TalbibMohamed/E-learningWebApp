package mohamed.testjpa.model.Course;

public class CourseContent {
    private int id;
    private String file_path;
    private String chapterName;
    private String contentType;

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFile_path() {
        return this.file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getChapterName() {
        return this.chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

}
