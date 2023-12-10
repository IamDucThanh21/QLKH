package modelDAO;

import modelBEAN.Course;
import modelBEAN.Video;
import util.DBUtil;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VideoDAO {
    Connection connection = DBUtil.getConnection();

    public VideoDAO() throws  SQLException{

    }
    public List<Video> getAllVideo() throws SQLException{
        List<Video> videos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM video");
                 ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
//                Course course = new Course();
                Video video = new Video();
                video.setIDVD(resultSet.getInt("video_id"));
                video.setVideo_title(resultSet.getString("video_title"));
                video.setFile_path(resultSet.getString("file_path"));

                // Các trường khác tương tự

                videos.add(video);
            }
        }
        return videos;

    }
    public List<Video> getAllVideoByCourseID(int courseID) throws  SQLException{
        List<Video> videos = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM video WHERE course_id = ?")
        ) {
            statement.setInt(1, courseID);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Video video = new Video();
                    video.setIDVD(resultSet.getInt("video_id"));
                    video.setVideo_title(resultSet.getString("video_title"));
                    video.setFile_path(resultSet.getString("file_path"));
                    // Các trường khác tương tự

                    videos.add(video);
                }
            }
        }

        return videos;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public void closeConnection() {

        DBUtil.closeConnection(connection);
    }
}
