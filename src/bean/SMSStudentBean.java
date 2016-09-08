package bean;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by bill on 16/9/8.
 */
public class SMSStudentBean {

    public static void delete(SMSStudent student) throws Exception {
        String sql = "SELECT * FROM student WHERE id = ?";
        try {
            PreparedStatement preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
            preparedStatement.setString(1, student.getStuNumber());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                sql = "DELETE FROM student WHERE id = ?";
                preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
                preparedStatement.setString(1, student.getStuNumber());
                int row = preparedStatement.executeUpdate();
                System.out.println("删除了" + row + "行");
                return;
            } else {
                throw new Exception("没有这个学生!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void add(SMSStudent student) throws Exception{
        String sql = "SELECT * FROM student WHERE id = ?";
        PreparedStatement preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
        preparedStatement.setString(1, student.getStuNumber());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            throw new Exception("学号已被占用!!");
        } else {
            sql = "INSERT INTO user(id, name, class, dorm, sex, joinccp) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
            preparedStatement.setString(1, student.getStuNumber());
            preparedStatement.setString(2, student.getStuName());
            preparedStatement.setString(3, student.getStuClass());
            preparedStatement.setString(4, student.getStuDorm());
            if (student.getStuSex().equals("男")) preparedStatement.setString(5, "1");
            else preparedStatement.setString(5, "0");
            if (student.getJoinCCP().equals("是")) preparedStatement.setString(1, "1");
            else preparedStatement.setString(1, "0");
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                return;
            } else {
                throw new Exception("SQL Error");
            }
        }
    }

    public static void edit(SMSStudent oldStudent, SMSStudent newStudent) throws Exception {
        String sql = "select * from student where id = ?";
        PreparedStatement preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
        preparedStatement.setString(1, oldStudent.getStuNumber());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            throw new Exception("无此用户");
        } else {
            sql = "UPDATE student SET id = ?, name = ?, class = ?, dorm = ?, sex = ?, joinccp = ? WHERE id = ?";
            preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
            preparedStatement.setString(1, newStudent.getStuNumber());
            preparedStatement.setString(2, newStudent.getStuName());
            preparedStatement.setString(3, newStudent.getStuClass());
            preparedStatement.setString(4, newStudent.getStuDorm());
            preparedStatement.setString(5, newStudent.getStuSex());
            preparedStatement.setString(6, newStudent.getJoinCCP());
            preparedStatement.setString(7, oldStudent.getStuNumber());
            int row = preparedStatement.executeUpdate();
            if (row == 1) {
                return;
            } else {
                throw new Exception("SQL Error");
            }
        }
    }

    public static SMSStudentArray show() throws Exception {
        String sql = "select * from student";
        try {
            PreparedStatement preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            SMSStudentArray smsStudentArray = new SMSStudentArray();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String classString = resultSet.getString("class");
                String dorm = resultSet.getString("dorm");
                String sex = resultSet.getInt("sex") == 1 ? "男" : "女";
                String joinccp = resultSet.getInt("joinccp") == 1 ? "党员" : "非党员";
                SMSStudent student = new SMSStudent(name, sex, classString, id, dorm, joinccp);
                smsStudentArray.getStudentList().add(student);
            }
            return smsStudentArray;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("数据库错误!");
        }
    }
}
