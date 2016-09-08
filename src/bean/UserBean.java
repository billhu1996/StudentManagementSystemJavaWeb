package bean;

import java.security.MessageDigest;
import java.sql.*;

/**
 * Created by bill on 16/8/31.
 */
public class UserBean {

    public static User login(String id, String password) {
        String sql = "select * from user where id = ?";
        try {
            PreparedStatement preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet .getString("password"));
                if (user.password.equals(string2MD5(password)) && user.id.equals(id)) {
                    return user;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void register(String id, String userName, String password) throws Exception {
        String sql = "select * from user where id = ?";
        PreparedStatement preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getString("id"));
            throw new Exception("用户名已被注册");
        } else {
            if (password.equals("") || password.equals("123456")) {
                throw new Exception("密码过于简单");
            } else {
                sql = "INSERT INTO user(id, name, password) VALUES (?, ?, ?)";
                preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
                preparedStatement.setString(1, id);
                preparedStatement.setString(2, userName);
                preparedStatement.setString(3, string2MD5(password));
                int row = preparedStatement.executeUpdate();
                if (row == 1) {
                    return;
                } else {
                    throw new Exception("SQL Error");
                }
            }
        }
    }

    public static void changePassword(String id, String oldPassword, String password) throws Exception {
        String sql = "select * from user where id = ?";
        PreparedStatement preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            throw new Exception("无此用户");
        } else {
            String passwordInDB = resultSet.getString("password");
            if (password.equals("") || password.equals("123456")) {
                throw new Exception("密码过于简单");
            } else if (passwordInDB.equals(string2MD5(oldPassword))) {
                sql = "UPDATE user SET password = ? WHERE id = ?";
                preparedStatement = HBConnection.getDefaultConnection().prepareStatement(sql);
                preparedStatement.setString(1, string2MD5(password));
                preparedStatement.setString(2, id);
                int row = preparedStatement.executeUpdate();
                if (row == 1) {
                    return;
                } else {
                    throw new Exception("SQL Error");
                }
            } else {
                throw new Exception("原密码错误");
            }
        }
    }

    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
//        bean.User user = bean.UserBean.login("2014011251", "5f4dcc3b5aa765d61d8327deb882cf99");
//        System.out.println(user.id);
        try {
            UserBean.register("2015011195", "userName", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }

        User user =  UserBean.login("2014011251", "password");
        System.out.println(user);

        try {
            UserBean.changePassword("2015011195", "password", "newPassword");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
