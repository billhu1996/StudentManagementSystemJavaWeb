package structs;

import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.*;

/**
 * Created by bill on 16/9/6.
 */
final class Usage {
    public static int login = 0;
    public static int changePassword = 1;
    public static int register = 2;
    public static int delete = 3;
}

public class userAction implements Action {
    private int usage;
    private String id;
    private String password;
    private String newPassword;
    private String verfyPassword;
    private String verfyCode;
    private HttpServletRequest req;
    private HttpServletResponse resp;

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public HttpServletResponse getResp() {
        return resp;
    }

    public int getUsage() {
        return usage;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getVerfyPassword() {
        return verfyPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    public void setResp(HttpServletResponse resp) {
        this.resp = resp;
    }

    public void setUsage(int usage) {
        this.usage = usage;
    }

    public void setVerfyPassword(String verfyPassword) {
        this.verfyPassword = verfyPassword;
    }

    public void setVerfyCode(String verfyCode) {
        this.verfyCode = verfyCode;
    }

    public String getVerfyCode() {
        return verfyCode;
    }

    @Override
    public String execute() throws Exception {
        req = ServletActionContext.getRequest();
        resp = ServletActionContext.getResponse();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        System.out.println(req);
        System.out.println(resp);
        System.out.println(id);

        String serverCheckcode = (String)(req.getSession().getAttribute(UserBean.string2MD5(verfyCode)));
        //(String) req.getSession().getAttribute("verfyCodeSecret");//从服务器端的session中取出验证码
        if (serverCheckcode != null) {
            if (verfyCode.equals(serverCheckcode)) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过
                if (usage == Usage.login) {
                    User user = UserBean.login(id, password);
                    if (user != null) {
                        req.getSession().setAttribute("userName", user.getName());
                        req.getSession().setAttribute("user", user);
                        return "loginSuccess";
                    } else {
                        resp.getWriter().print("<script>alert(\"用户名或密码错误!!\");window.location.href=\"index.jsp\";</script>");
                    }
                } else if (usage == Usage.changePassword) {
                    try {
                        UserBean.changePassword(id, password, newPassword);
                        resp.getWriter().print("<script>alert(\"修改成功!!\");window.location.href=\"index.jsp\";</script>");
                    } catch (Exception e) {
                        resp.getWriter().print("<script>alert(\"" + e.getMessage() + "\");window.location.href=\"index.jsp\";</script>");
                        e.printStackTrace();
                    }
                } else if (usage == Usage.register) {
                    try {
                        UserBean.register(id, id, password);
                        resp.getWriter().print("<script>alert(\"注册成功!!\");window.location.href=\"index.jsp\";</script>");
                    } catch (Exception e) {
                        resp.getWriter().print("<script>alert(\"" + e.getMessage() + "\");window.location.href=\"index.jsp\";</script>");
                        e.printStackTrace();
                    }
//                } else if (usage == Usage.delete) {
//                    try {
//                        UserBean.delete(id, password);
//                        resp.getWriter().print("<script>alert(\"删除成功!!\");window.location.href=\"index.jsp\";</script>");
//                    } catch (Exception e) {
//                        resp.getWriter().print("<script>alert(\"" + e.getMessage() + "\");window.location.href=\"index.jsp\";</script>");
//                        e.printStackTrace();
//                    }
                }
            } else {
                resp.getWriter().print("<script>alert(\"验证码错误!!\");window.location.href=\"index.jsp\";</script>");
            }
        } else {
            resp.getWriter().print("<script>alert(\"验证码错误!!\");window.location.href=\"index.jsp\";</script>");
        }

        return null;
    }

}
