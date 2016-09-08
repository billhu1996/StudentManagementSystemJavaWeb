package structs;

import bean.SMSStudentArray;
import bean.SMSStudentBean;
import com.opensymphony.xwork2.Action;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by bill on 16/9/8.
 */
public class showStudentAction implements Action {

    private String userName;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HttpServletResponse getResp() {
        return resp;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public void setResp(HttpServletResponse resp) {
        this.resp = resp;
    }

    public void setReq(HttpServletRequest req) {
        this.req = req;
    }

    @Override
    public String execute() throws Exception {
        req = ServletActionContext.getRequest();
        resp = ServletActionContext.getResponse();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        SMSStudentArray studentArray = new SMSStudentArray();
        try {
            studentArray = SMSStudentBean.show();
        } catch (Exception e) {
            resp.getWriter().print("<script>alert(\"" + e.getMessage() + "\");window.location.href=\"index.jsp\";</script>");
        }
        req.getSession().setAttribute("students", studentArray);
        return SUCCESS;
    }
}
